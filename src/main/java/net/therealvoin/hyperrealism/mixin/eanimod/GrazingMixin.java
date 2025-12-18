package net.therealvoin.hyperrealism.mixin.eanimod;

import mokiyoki.enhancedanimals.ai.brain.Grazing;
import mokiyoki.enhancedanimals.blocks.GrowableDoubleHigh;
import mokiyoki.enhancedanimals.blocks.GrowablePlant;
import mokiyoki.enhancedanimals.blocks.UnboundHayBlock;
import mokiyoki.enhancedanimals.capability.hay.HayCapabilityProvider;
import mokiyoki.enhancedanimals.entity.EnhancedAnimalAbstract;
import mokiyoki.enhancedanimals.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Mixin(Grazing.class)
public class GrazingMixin {
    @Shadow private boolean searchingForFood;
    @Shadow private List<BlockPos> eatingDestinations = new ArrayList();
    @Shadow private boolean eating;
    @Shadow private boolean seekingHay;
    @Shadow private int otherHungerRestore;
    @Shadow private int hayHungerRestore;
    @Shadow private int eatingGrassTimer;
    @Shadow protected static Predicate<BlockState> IS_GRASS;
    @Shadow protected static Predicate<BlockState> IS_TALL_GRASS_BLOCK;

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true, remap = false)
    protected void modifyTick(ServerLevel serverLevel, EnhancedAnimalAbstract geneticAnimal, long gameTime, CallbackInfo callbackInfo) {
        BlockPos currentDestination = this.eatingDestinations.get(0);
        if (this.searchingForFood) {
            if (!currentDestination.below().closerToCenterThan(geneticAnimal.position(), 1.0F)) {
                BehaviorUtils.setWalkAndLookTargetMemories(geneticAnimal, currentDestination.below(), 1.0F, this.getAcceptableDistance());
            } else {
                this.eating = true;
                this.searchingForFood = false;
                if (this.seekingHay) {
                    geneticAnimal.decreaseHunger((float)this.hayHungerRestore);
                } else {
                    geneticAnimal.decreaseHunger((float)this.otherHungerRestore);
                }

                this.eatingGrassTimer = 140;
                geneticAnimal.level().broadcastEntityEvent(geneticAnimal, (byte)10);
                geneticAnimal.getNavigation().stop();
            }
        } else if (this.eating) {
            this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
            if (this.eatingGrassTimer == 4) {
                if (this.seekingHay) {
                    BlockState blockState = geneticAnimal.level().getBlockState(currentDestination);
                    if (blockState.getBlock() instanceof UnboundHayBlock) {
                        ((UnboundHayBlock)blockState.getBlock()).eatFromBlock(geneticAnimal.level(), blockState, currentDestination);
                    } else if (blockState.getBlock() instanceof UnboundBarleyHayBlock) {
                        ((UnboundBarleyHayBlock)blockState.getBlock()).eatFromBlock(geneticAnimal.level(), blockState, currentDestination);
                    } else if (blockState.getBlock() instanceof UnboundOatHayBlock) {
                        ((UnboundOatHayBlock)blockState.getBlock()).eatFromBlock(geneticAnimal.level(), blockState, currentDestination);
                    } else if (blockState.getBlock() instanceof UnboundRiceHayBlock) {
                        ((UnboundRiceHayBlock)blockState.getBlock()).eatFromBlock(geneticAnimal.level(), blockState, currentDestination);
                    } else if (blockState.getBlock() instanceof UnboundRyeHayBlock) {
                        ((UnboundRyeHayBlock)blockState.getBlock()).eatFromBlock(geneticAnimal.level(), blockState, currentDestination);
                    } else {
                        geneticAnimal.level().getCapability(HayCapabilityProvider.HAY_CAP, null).orElse(new HayCapabilityProvider()).removeHayPos(currentDestination);
                    }
                } else {
                    this.eatBlocks(geneticAnimal, currentDestination);
                }
            } else if (this.eatingGrassTimer == 0) {
                this.eating = false;
                this.eatingDestinations.remove(0);
                if (!this.eatingDestinations.isEmpty()) {
                    this.searchingForFood = true;
                }
            }
        }
        callbackInfo.cancel();
    }

    public int getAcceptableDistance() {
        return this.seekingHay ? 2 : 1;
    }

    protected void eatBlocks(EnhancedAnimalAbstract geneticAnimal, BlockPos currentDestination) {
        BlockPos blockpos = new BlockPos(geneticAnimal.blockPosition());
        if (this.isInEdibleBlock(geneticAnimal, blockpos)) {
            if (ForgeEventFactory.getMobGriefingEvent(geneticAnimal.level(), geneticAnimal)) {
                geneticAnimal.level().destroyBlock(blockpos, false);
            }
        } else if (ForgeEventFactory.getMobGriefingEvent(geneticAnimal.level(), geneticAnimal)) {
            if (geneticAnimal.level().getBlockState(currentDestination.below()).getBlock() == Blocks.GRASS_BLOCK) {
                this.eatBlock(geneticAnimal, currentDestination.below(), Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()), ((Block) ModBlocks.SPARSEGRASS_BLOCK.get()).defaultBlockState());
            } else if (geneticAnimal.level().getBlockState(currentDestination.below()).getBlock() == ModBlocks.SPARSEGRASS_BLOCK.get()) {
            }
        }

        geneticAnimal.ate();
    }

    private boolean isInEdibleBlock(EnhancedAnimalAbstract geneticAnimal, BlockPos blockpos) {
        BlockState blockState = geneticAnimal.level().getBlockState(blockpos);
        return IS_GRASS.test(blockState) || IS_TALL_GRASS_BLOCK.test(blockState) || blockState.getBlock() instanceof GrowablePlant || blockState.getBlock() instanceof GrowableDoubleHigh;
    }

    protected void eatBlock(EnhancedAnimalAbstract geneticAnimal, BlockPos currentDestination, int eatenBlock, BlockState newBlock) {
        geneticAnimal.level().levelEvent(2001, currentDestination, eatenBlock);
        geneticAnimal.level().setBlock(currentDestination, newBlock, 2);
    }
}