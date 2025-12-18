package net.therealvoin.hyperrealism.block;

import com.google.common.collect.ImmutableMap;
import dev.enemeez.simplefarming.common.registries.ModItems;
import mokiyoki.enhancedanimals.capability.hay.HayCapabilityProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UnboundRiceHayBlock extends FallingBlock implements SimpleWaterloggedBlock {
    public static final EnumProperty<Axis> AXIS;
    public static final BooleanProperty WATERLOGGED;
    public static final IntegerProperty BITES;
    private static final VoxelShape HAY_BLACK;
    private static final VoxelShape HAY_GREY;
    private static final VoxelShape HAY_WHITE;
    private static final VoxelShape HAY_YELLOW;
    private static final VoxelShape HAY_FUSHIA;
    private static final VoxelShape HAY_CYAN;
    private static final VoxelShape HAY_GREEN;
    private static final VoxelShape HAY_RED;
    private static final VoxelShape HAY_BLUE;
    private static final VoxelShape X_HAY_BLACK;
    private static final VoxelShape X_HAY_GREY;
    private static final VoxelShape X_HAY_WHITE;
    private static final VoxelShape X_HAY_YELLOW;
    private static final VoxelShape X_HAY_FUSHIA;
    private static final VoxelShape X_HAY_CYAN;
    private static final VoxelShape X_HAY_GREEN;
    private static final VoxelShape X_HAY_RED;
    private static final VoxelShape X_HAY_BLUE;
    private static final VoxelShape Z_HAY_BLACK;
    private static final VoxelShape Z_HAY_GREY;
    private static final VoxelShape Z_HAY_WHITE;
    private static final VoxelShape Z_HAY_YELLOW;
    private static final VoxelShape Z_HAY_FUSHIA;
    private static final VoxelShape Z_HAY_CYAN;
    private static final VoxelShape Z_HAY_GREEN;
    private static final VoxelShape Z_HAY_RED;
    private static final VoxelShape Z_HAY_BLUE;
    private final Map<BlockState, VoxelShape> stateToShapeMap;

    public UnboundRiceHayBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BITES, 0).setValue(WATERLOGGED, false).setValue(AXIS, Axis.Y));
        this.stateToShapeMap = ImmutableMap.copyOf((Map)this.stateDefinition.getPossibleStates().stream().collect(Collectors.toMap(Function.identity(), UnboundRiceHayBlock::getShapeForState)));
    }

    private static VoxelShape getShapeForState(BlockState state) {
        VoxelShape voxelshape = Shapes.empty();
        Axis axis = state.getValue(AXIS);
        int bites = 9 - state.getValue(BITES);
        switch (state.getValue(AXIS)) {
            case X:
            default:
                if (bites > 0) {
                    voxelshape = X_HAY_BLACK;
                }

                if (bites > 1) {
                    voxelshape = Shapes.or(voxelshape, X_HAY_YELLOW);
                }

                if (bites > 2) {
                    voxelshape = Shapes.or(voxelshape, X_HAY_GREEN);
                }

                if (bites > 3) {
                    voxelshape = Shapes.or(voxelshape, X_HAY_FUSHIA);
                }

                if (bites > 4) {
                    voxelshape = Shapes.or(voxelshape, X_HAY_RED);
                }

                if (bites > 5) {
                    voxelshape = Shapes.or(voxelshape, X_HAY_GREY);
                }

                if (bites > 6) {
                    voxelshape = Shapes.or(voxelshape, X_HAY_CYAN);
                }

                if (bites > 7) {
                    voxelshape = Shapes.or(voxelshape, X_HAY_WHITE);
                }

                if (bites > 8) {
                    voxelshape = Shapes.or(voxelshape, X_HAY_BLUE);
                }
                break;
            case Z:
                if (bites > 0) {
                    voxelshape = Z_HAY_BLACK;
                }

                if (bites > 1) {
                    voxelshape = Shapes.or(voxelshape, Z_HAY_GREY);
                }

                if (bites > 2) {
                    voxelshape = Shapes.or(voxelshape, Z_HAY_WHITE);
                }

                if (bites > 3) {
                    voxelshape = Shapes.or(voxelshape, Z_HAY_FUSHIA);
                }

                if (bites > 4) {
                    voxelshape = Shapes.or(voxelshape, Z_HAY_CYAN);
                }

                if (bites > 5) {
                    voxelshape = Shapes.or(voxelshape, Z_HAY_YELLOW);
                }

                if (bites > 6) {
                    voxelshape = Shapes.or(voxelshape, Z_HAY_RED);
                }

                if (bites > 7) {
                    voxelshape = Shapes.or(voxelshape, Z_HAY_GREEN);
                }

                if (bites > 8) {
                    voxelshape = Shapes.or(voxelshape, Z_HAY_BLUE);
                }
                break;
            case Y:
                if (bites > 0) {
                    voxelshape = HAY_GREEN;
                }

                if (bites > 1) {
                    voxelshape = Shapes.or(voxelshape, HAY_RED);
                }

                if (bites > 2) {
                    voxelshape = Shapes.or(voxelshape, HAY_BLUE);
                }

                if (bites > 3) {
                    voxelshape = Shapes.or(voxelshape, HAY_FUSHIA);
                }

                if (bites > 4) {
                    voxelshape = Shapes.or(voxelshape, HAY_CYAN);
                }

                if (bites > 5) {
                    voxelshape = Shapes.or(voxelshape, HAY_YELLOW);
                }

                if (bites > 6) {
                    voxelshape = Shapes.or(voxelshape, HAY_GREY);
                }

                if (bites > 7) {
                    voxelshape = Shapes.or(voxelshape, HAY_BLACK);
                }

                if (bites > 8) {
                    voxelshape = Shapes.or(voxelshape, HAY_WHITE);
                }
        }

        return voxelshape;
    }

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return this.stateToShapeMap.get(state);
    }

    public VoxelShape getShape(BlockState state) {
        return this.stateToShapeMap.get(state);
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        switch (rot) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch (state.getValue(AXIS)) {
                    case X -> {
                        return state.setValue(AXIS, Axis.Z);
                    }
                    case Z -> {
                        return state.setValue(AXIS, Axis.X);
                    }
                    default -> {
                        return state;
                    }
                }
            default:
                return state;
        }
    }

    public void eatFromBlock(Level world, BlockState state, BlockPos pos) {
        this.passBiteUp(world, state, pos);
    }

    public void passBiteUp(Level world, BlockState state, BlockPos pos) {
        if (world.getBlockState(pos.above()).getBlock() instanceof UnboundRiceHayBlock) {
            ((UnboundRiceHayBlock)world.getBlockState(pos.above()).getBlock()).passBiteUp(world, world.getBlockState(pos.above()), pos.above());
        } else {
            int bites = state.getValue(BITES);
            if (bites < 8) {
                world.setBlockAndUpdate(pos, state.setValue(BITES, bites + 1).setValue(WATERLOGGED, state.getValue(WATERLOGGED)));
            } else {
                world.getCapability(HayCapabilityProvider.HAY_CAP, null).orElse(new HayCapabilityProvider()).removeHayPos(pos);
                world.removeBlock(pos, false);
            }
        }

    }

    public void handlePrecipitation(BlockState blockState, Level worldIn, BlockPos pos, Precipitation precipitation) {
        if (shouldHandlePrecipitation(worldIn, precipitation)) {
            BlockState state = worldIn.getBlockState(pos);
            int i = state.getValue(BITES);
            if (i < 8) {
                worldIn.setBlock(pos, state.setValue(BITES, i + 1), 3);
            } else {
                worldIn.getCapability(HayCapabilityProvider.HAY_CAP, null).orElse(new HayCapabilityProvider()).removeHayPos(pos);
                worldIn.removeBlock(pos, false);
            }
        }

    }

    protected static boolean shouldHandlePrecipitation(Level world, Precipitation precipitation) {
        if (precipitation == Precipitation.RAIN) {
            return world.getRandom().nextFloat() < 0.05F;
        } else if (precipitation == Precipitation.SNOW) {
            return world.getRandom().nextFloat() < 0.1F;
        } else {
            return false;
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidstate.getType() == Fluids.WATER).setValue(AXIS, context.getClickedFace().getAxis());
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos pos, BlockPos facingPos) {
        if (state.getValue(WATERLOGGED)) {
            if (worldIn instanceof ServerLevel) {
                ((ServerLevel)worldIn).getCapability(HayCapabilityProvider.HAY_CAP, null).orElse(new HayCapabilityProvider()).removeHayPos(pos);
            }

            worldIn.removeBlock(pos, false);
        }

        return super.updateShape(state, facing, facingState, worldIn, pos, facingPos);
    }

    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return !(Boolean)state.getValue(WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BITES, WATERLOGGED, AXIS);
    }

    public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        entityIn.setOnGround(true);
        double entityx = entityIn.getX() - (double)pos.getX();
        double entityy = entityIn.getY() - (double)pos.getY();
        double entityz = entityIn.getZ() - (double)pos.getZ();
        AABB box = this.getShape(state).bounds();
        if (box.contains(entityx, entityy, entityz)) {
            entityIn.makeStuckInBlock(state, new Vec3(0.9, 0.05F, 0.9));
            super.entityInside(state, worldIn, pos, entityIn);
        }

    }

    public void fallOn(Level worldIn, BlockState blockState, BlockPos pos, Entity entityIn, float fallDistance) {
        BlockState state = worldIn.getBlockState(pos);
        float bites = (float) state.getValue(BITES);
        entityIn.causeFallDamage(fallDistance, 0.2F + bites * 0.1F, entityIn.damageSources().fall());
    }

    public void wasExploded(Level worldIn, BlockPos pos, Explosion explosionIn) {
        worldIn.getCapability(HayCapabilityProvider.HAY_CAP, null).orElse(new HayCapabilityProvider()).removeHayPos(pos);
        super.wasExploded(worldIn, pos, explosionIn);
    }

    public void playerWillDestroy(Level worldIn, BlockPos pos, BlockState state, Player player) {
        worldIn.getCapability(HayCapabilityProvider.HAY_CAP, null).orElse(new HayCapabilityProvider()).removeHayPos(pos);
        if (!worldIn.isClientSide && !player.isCreative()) {
            int bites = state.getValue(BITES);
            ItemStack itemstack = new ItemStack(ModItems.RICE.get(), 9 - bites);
            ItemEntity itementity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), itemstack);
            itementity.setDefaultPickUpDelay();
            worldIn.addFreshEntity(itementity);
        }

        super.playerWillDestroy(worldIn, pos, state, player);
    }

    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getCapability(HayCapabilityProvider.HAY_CAP, null).orElse(new HayCapabilityProvider()).addHayPos(pos);
        super.onPlace(state, worldIn, pos, oldState, isMoving);
    }

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        worldIn.getCapability(HayCapabilityProvider.HAY_CAP, null).orElse(new HayCapabilityProvider()).removeHayPos(pos);
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }

    static {
        AXIS = BlockStateProperties.AXIS;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        BITES = BlockStateProperties.LEVEL_COMPOSTER;
        HAY_BLACK = Block.box(0.0F, 0.0F, 0.0F, 5.0F, 16.0F, 5.0F);
        HAY_GREY = Block.box(5.0F, 0.0F, 0.0F, 11.0F, 16.0F, 5.0F);
        HAY_WHITE = Block.box(11.0F, 0.0F, 0.0F, 16.0F, 16.0F, 5.0F);
        HAY_YELLOW = Block.box(0.0F, 0.0F, 5.0F, 5.0F, 16.0F, 11.0F);
        HAY_FUSHIA = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 16.0F, 11.0F);
        HAY_CYAN = Block.box(11.0F, 0.0F, 5.0F, 16.0F, 16.0F, 11.0F);
        HAY_GREEN = Block.box(0.0F, 0.0F, 11.0F, 5.0F, 16.0F, 16.0F);
        HAY_RED = Block.box(5.0F, 0.0F, 11.0F, 11.0F, 16.0F, 16.0F);
        HAY_BLUE = Block.box(11.0F, 0.0F, 11.0F, 16.0F, 16.0F, 16.0F);
        X_HAY_BLACK = Block.box(0.0F, 0.0F, 0.0F, 16.0F, 5.0F, 5.0F);
        X_HAY_GREY = Block.box(0.0F, 5.0F, 0.0F, 16.0F, 11.0F, 5.0F);
        X_HAY_WHITE = Block.box(0.0F, 11.0F, 0.0F, 16.0F, 16.0F, 5.0F);
        X_HAY_YELLOW = Block.box(0.0F, 0.0F, 5.0F, 16.0F, 5.0F, 11.0F);
        X_HAY_FUSHIA = Block.box(0.0F, 5.0F, 5.0F, 16.0F, 11.0F, 11.0F);
        X_HAY_CYAN = Block.box(0.0F, 11.0F, 5.0F, 16.0F, 16.0F, 11.0F);
        X_HAY_GREEN = Block.box(0.0F, 0.0F, 11.0F, 16.0F, 5.0F, 16.0F);
        X_HAY_RED = Block.box(0.0F, 5.0F, 11.0F, 16.0F, 11.0F, 16.0F);
        X_HAY_BLUE = Block.box(0.0F, 11.0F, 11.0F, 16.0F, 16.0F, 16.0F);
        Z_HAY_BLACK = Block.box(0.0F, 0.0F, 0.0F, 5.0F, 5.0F, 16.0F);
        Z_HAY_GREY = Block.box(5.0F, 0.0F, 0.0F, 11.0F, 5.0F, 16.0F);
        Z_HAY_WHITE = Block.box(11.0F, 0.0F, 0.0F, 16.0F, 5.0F, 16.0F);
        Z_HAY_YELLOW = Block.box(0.0F, 5.0F, 0.0F, 5.0F, 11.0F, 16.0F);
        Z_HAY_FUSHIA = Block.box(5.0F, 5.0F, 0.0F, 11.0F, 11.0F, 16.0F);
        Z_HAY_CYAN = Block.box(11.0F, 5.0F, 0.0F, 16.0F, 11.0F, 16.0F);
        Z_HAY_GREEN = Block.box(0.0F, 11.0F, 0.0F, 5.0F, 16.0F, 16.0F);
        Z_HAY_RED = Block.box(5.0F, 11.0F, 0.0F, 11.0F, 16.0F, 16.0F);
        Z_HAY_BLUE = Block.box(11.0F, 11.0F, 0.0F, 16.0F, 16.0F, 16.0F);
    }
}