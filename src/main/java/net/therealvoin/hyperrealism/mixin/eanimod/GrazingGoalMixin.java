package net.therealvoin.hyperrealism.mixin.eanimod;

import mokiyoki.enhancedanimals.ai.general.AIStatus;
import mokiyoki.enhancedanimals.ai.general.GrazingGoal;
import mokiyoki.enhancedanimals.blocks.GrowableDoubleHigh;
import mokiyoki.enhancedanimals.blocks.GrowablePlant;
import mokiyoki.enhancedanimals.blocks.UnboundHayBlock;
import mokiyoki.enhancedanimals.capability.hay.HayCapabilityProvider;
import mokiyoki.enhancedanimals.entity.EnhancedAnimalAbstract;
import mokiyoki.enhancedanimals.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.therealvoin.hyperrealism.block.UnboundBarleyHayBlock;
import net.therealvoin.hyperrealism.block.UnboundOatHayBlock;
import net.therealvoin.hyperrealism.block.UnboundRiceHayBlock;
import net.therealvoin.hyperrealism.block.UnboundRyeHayBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

@Mixin(GrazingGoal.class)
public class GrazingGoalMixin {
    @Shadow private boolean eatingSearch;
    @Shadow protected int timeoutCounter;
    @Shadow private boolean searchHay;
    @Shadow protected BlockPos destinationBlock;
    @Shadow protected EnhancedAnimalAbstract eanimal;
    @Shadow private boolean eatingHay;
    @Shadow private int eatingGrassTimer;
    @Shadow private int hayHungerRestore;
    @Shadow private boolean eating;
    @Shadow private int amountToEat;
    @Shadow private int otherHungerRestore;
    @Shadow public double movementSpeed;
    @Shadow protected static Predicate<BlockState> IS_GRASS;
    @Shadow protected static Predicate<BlockState> IS_TALL_GRASS_BLOCK;
    @Shadow boolean randomSelection;
    @Shadow List<BlockPos> allFoundPos;
    @Shadow protected static Predicate<BlockState> IS_GRASS_BLOCK;
    @Shadow private int waitingTimer;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void modifyTick(CallbackInfo callbackInfo) {
        if (this.eatingSearch) {
            ++this.timeoutCounter;
            if (this.searchHay) {
                if (!this.destinationBlock.closerToCenterThan(this.eanimal.position(), this.getHayBlockTargetDistanceSq())) {
                    if (this.shouldStillMove()) {
                        this.eanimal.getNavigation().moveTo((float)this.destinationBlock.getX(), this.destinationBlock.getY(), (float)this.destinationBlock.getZ(), this.movementSpeed);
                    }
                } else {
                    this.eatingHay = true;
                    this.searchHay = false;
                    this.eatingSearch = false;
                    this.eanimal.decreaseHunger((float)this.hayHungerRestore);
                    this.eatingGrassTimer = 40;
                    this.eanimal.level().broadcastEntityEvent(this.eanimal, (byte)10);
                    this.eanimal.getNavigation().stop();
                }
            } else if (!this.destinationBlock.above().closerToCenterThan(this.eanimal.position(), this.getGoundBlockTargetDistanceSq())) {
                if (this.shouldStillMove()) {
                    this.eanimal.getNavigation().moveTo(((float)this.destinationBlock.getX()), (this.destinationBlock.getY() + 1), (float)this.destinationBlock.getZ(), this.movementSpeed);
                }
            } else {
                this.eating = true;
                this.eatingSearch = false;
                this.eanimal.decreaseHunger((float)this.otherHungerRestore);
                this.eatingGrassTimer = 40;
                this.eanimal.level().broadcastEntityEvent(this.eanimal, (byte)10);
                this.eanimal.getNavigation().stop();
            }
        } else if (this.eating) {
            this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
            if (this.eatingGrassTimer == 4) {
                this.eatBlocks();
            } else if (this.eatingGrassTimer == 0) {
                this.eating = false;
                --this.amountToEat;
                this.shouldContinueEating();
            }
        } else if (this.eatingHay) {
            this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
            if (this.eatingGrassTimer == 4) {
                BlockState blockState = this.eanimal.level().getBlockState(this.destinationBlock);
                if (blockState.getBlock() instanceof UnboundHayBlock) {
                    ((UnboundHayBlock)blockState.getBlock()).eatFromBlock(this.eanimal.level(), blockState, this.destinationBlock);
                } else if (blockState.getBlock() instanceof UnboundBarleyHayBlock) {
                    ((UnboundBarleyHayBlock)blockState.getBlock()).eatFromBlock(this.eanimal.level(), blockState, this.destinationBlock);
                } else if (blockState.getBlock() instanceof UnboundOatHayBlock) {
                    ((UnboundOatHayBlock)blockState.getBlock()).eatFromBlock(this.eanimal.level(), blockState, this.destinationBlock);
                } else if (blockState.getBlock() instanceof UnboundRiceHayBlock) {
                    ((UnboundRiceHayBlock)blockState.getBlock()).eatFromBlock(this.eanimal.level(), blockState, this.destinationBlock);
                } else if (blockState.getBlock() instanceof UnboundRyeHayBlock) {
                    ((UnboundRyeHayBlock)blockState.getBlock()).eatFromBlock(this.eanimal.level(), blockState, this.destinationBlock);
                } else {
                    (this.eanimal.level().getCapability(HayCapabilityProvider.HAY_CAP, null).orElse(new HayCapabilityProvider())).removeHayPos(this.destinationBlock);
                }
            } else if (this.eatingGrassTimer == 0) {
                this.eatingHay = false;
            }
        }

        callbackInfo.cancel();
    }

    public double getGoundBlockTargetDistanceSq() {
        return this.eanimal.isLeashed() ? (double)1.5F : (double)1.0F;
    }

    protected void eatBlocks() {
        BlockPos blockpos = new BlockPos(this.eanimal.blockPosition());
        if (this.isInEdibleBlock(blockpos)) {
            if (ForgeEventFactory.getMobGriefingEvent(this.eanimal.level(), this.eanimal)) {
                this.eanimal.level().destroyBlock(blockpos, false);
            }

            this.eanimal.ate();
        } else {
            BlockPos blockposDown = blockpos.below();
            if (this.eanimal.level().getBlockState(blockposDown).getBlock() == Blocks.GRASS_BLOCK) {
                if (ForgeEventFactory.getMobGriefingEvent(this.eanimal.level(), this.eanimal)) {
                    this.eanimal.level().levelEvent(2001, blockposDown, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
                    this.eanimal.level().setBlock(blockposDown, ModBlocks.SPARSEGRASS_BLOCK.get().defaultBlockState(), 2);
                }

                this.eanimal.ate();
            } else if (this.eanimal.level().getBlockState(blockposDown).getBlock() == ModBlocks.SPARSEGRASS_BLOCK.get()) {
                if (ForgeEventFactory.getMobGriefingEvent(this.eanimal.level(), this.eanimal)) {
                    this.eanimal.level().levelEvent(2001, blockposDown, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
                    this.eanimal.level().setBlock(blockposDown, Blocks.DIRT.defaultBlockState(), 2);
                }

                this.eanimal.ate();
            }
        }

    }

    private boolean isInEdibleBlock(BlockPos blockpos) {
        BlockState blockState = this.eanimal.level().getBlockState(blockpos);
        return IS_GRASS.test(blockState) || IS_TALL_GRASS_BLOCK.test(blockState) || blockState.getBlock() instanceof GrowablePlant || blockState.getBlock() instanceof GrowableDoubleHigh;
    }

    public boolean shouldStillMove() {
        return this.timeoutCounter % 40 == 0;
    }

    public double getHayBlockTargetDistanceSq() {
        return 2.0F;
    }

    private void shouldContinueEating() {
        if (this.amountToEat > 0) {
            this.eatingRoute();
        }

    }

    private boolean eatingRoute() {
        boolean foundFood = this.searchForDestination();
        if (foundFood) {
            this.eanimal.setAIStatus(AIStatus.EATING);
            this.eatingSearch = true;
            return true;
        } else {
            return false;
        }
    }

    protected boolean searchForDestination() {
        if (this.findIfNearbyHay()) {
            this.searchHay = true;
            return true;
        } else {
            BlockPos baseBlockPos = new BlockPos(this.eanimal.blockPosition());
            BlockPos.MutableBlockPos mutableblockpos = new BlockPos.MutableBlockPos();
            Vec3 directionVec = this.getDirectionVec();
            int horizontalRange = 10;
            int verticalRange = 3;
            Entity holder = this.eanimal.getLeashHolder();
            if (holder != null) {
                if (holder instanceof Player) {
                    this.destinationBlock = new BlockPos(this.eanimal.blockPosition().getX(), this.eanimal.blockPosition().getY() - 1, this.eanimal.blockPosition().getZ());
                    return true;
                }

                horizontalRange = 4;
                verticalRange = 2;
                baseBlockPos = new BlockPos(this.eanimal.getLeashHolder().blockPosition());
                this.randomSelection = true;
            } else {
                this.allFoundPos.clear();
                this.randomSelection = false;
                if (this.checkSquaresInFront(baseBlockPos, mutableblockpos, directionVec)) {
                    this.destinationBlock = mutableblockpos;
                    return true;
                }
            }

            mutableblockpos.set(baseBlockPos);
            if (this.randomSelection && !this.allFoundPos.isEmpty()) {
                this.destinationBlock = this.allFoundPos.get(ThreadLocalRandom.current().nextInt(0, this.allFoundPos.size()));
                return true;
            } else {
                for(int k = 0; k <= verticalRange; k = k > 0 ? -k : 1 - k) {
                    for(int l = 0; l < horizontalRange; ++l) {
                        for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                            for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                                mutableblockpos.set(baseBlockPos).move(i1, k - 1, j1);
                                if (this.isEdibleBlock(this.eanimal.level(), mutableblockpos)) {
                                    if (!this.randomSelection) {
                                        this.destinationBlock = mutableblockpos;
                                        return true;
                                    }

                                    this.allFoundPos.add(new BlockPos(mutableblockpos));
                                }
                            }
                        }
                    }
                }

                if (this.randomSelection) {
                    int range = this.allFoundPos.size();
                    if (range > 0) {
                        this.destinationBlock = this.allFoundPos.get(ThreadLocalRandom.current().nextInt(0, range));
                        return true;
                    }
                }

                this.waitingTimer = 500;
                return false;
            }
        }
    }

    private boolean findIfNearbyHay() {
        Set<BlockPos> hayList = (this.eanimal.level().getCapability(HayCapabilityProvider.HAY_CAP, null).orElse(new HayCapabilityProvider())).getAllHayPos();
        double closestDistance = 128.0F;
        boolean found = false;

        for(BlockPos pos : hayList) {
            if (this.eanimal.getLeashHolder() != null) {
                double distance = pos.distSqr(this.eanimal.getLeashHolder().blockPosition());
                if (distance < (double)12.0F) {
                    closestDistance = distance;
                    this.destinationBlock = pos;
                    found = true;
                }
            } else {
                double distance = pos.distSqr(this.eanimal.blockPosition());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    this.destinationBlock = pos;
                    found = true;
                }
            }
        }

        return found;
    }

    private Vec3 getDirectionVec() {
        Vec3 lockingVec = this.eanimal.getLookAngle().align(EnumSet.of(Direction.Axis.X, Direction.Axis.Z));
        double x = Math.abs(lockingVec.x);
        double z = Math.abs(lockingVec.z);
        return x > z ? new Vec3(lockingVec.x, 0.0F, 0.0F) : new Vec3(0.0F, 0.0F, lockingVec.z);
    }

    private boolean checkSquaresInFront(BlockPos eanimalBlockPos, BlockPos.MutableBlockPos mutableblockpos, Vec3 directionVec) {
        if (this.isEdibleBlock(this.eanimal.level(), mutableblockpos.set(eanimalBlockPos).move((int)(directionVec.x * (double)2.0F) + 1, -1, (int)(directionVec.z * (double)2.0F) + 1))) {
            this.destinationBlock = mutableblockpos;
            return true;
        } else if (this.isEdibleBlock(this.eanimal.level(), mutableblockpos.set(eanimalBlockPos).move((int)(directionVec.x * (double)3.0F) + 1, -1, (int)(directionVec.z * (double)3.0F) + 1))) {
            this.destinationBlock = mutableblockpos;
            return true;
        } else {
            if (directionVec.x != (double)0.0F) {
                if (this.isEdibleBlock(this.eanimal.level(), mutableblockpos.set(eanimalBlockPos).move((int)directionVec.x + 1, -1, -1))) {
                    this.destinationBlock = mutableblockpos;
                    return true;
                }

                if (this.isEdibleBlock(this.eanimal.level(), mutableblockpos.set(eanimalBlockPos).move((int)directionVec.x + 1, -1, 1))) {
                    this.destinationBlock = mutableblockpos;
                    return true;
                }
            } else {
                if (this.isEdibleBlock(this.eanimal.level(), mutableblockpos.set(eanimalBlockPos).move(-1, -1, (int)directionVec.z + 1))) {
                    this.destinationBlock = mutableblockpos;
                    return true;
                }

                if (this.isEdibleBlock(this.eanimal.level(), mutableblockpos.set(eanimalBlockPos).move(1, -1, (int)directionVec.z + 1))) {
                    this.destinationBlock = mutableblockpos;
                    return true;
                }
            }

            return false;
        }
    }

    protected boolean isEdibleBlock(LevelReader worldIn, BlockPos pos) {
        BlockState blockstate = worldIn.getBlockState(pos);
        return IS_GRASS_BLOCK.test(blockstate);
    }
}