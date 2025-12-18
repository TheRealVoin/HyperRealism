package net.therealvoin.hyperrealism.mixin.eanimod;

import mokiyoki.enhancedanimals.blocks.SparseGrassBlock;
import mokiyoki.enhancedanimals.init.ModBlocks;
import mokiyoki.enhancedanimals.util.handlers.EventSubscriber;
import net.minecraft.core.Direction;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EventSubscriber.class)
public class EventSubscriberMixin {
    @Inject(method = "onBlockInteractEvent", at = @At("HEAD"), cancellable = true, remap = false)
    public void modifyOnBlockInteractEvent(PlayerInteractEvent.RightClickBlock event, CallbackInfo callbackInfo) {
        Item item = event.getItemStack().getItem();
        Block block = event.getLevel().getBlockState(event.getPos()).getBlock();
        if (!(block == Blocks.HAY_BLOCK) || !(item instanceof AxeItem) && !(item instanceof SwordItem) && !(item instanceof ShearsItem)) {
            if (block instanceof SparseGrassBlock && item instanceof HoeItem) {
                event.getLevel().setBlock(event.getPos(), Blocks.FARMLAND.defaultBlockState(), 11);
            } else if (block instanceof SparseGrassBlock && item instanceof ShovelItem) {
                event.getLevel().setBlock(event.getPos(), Blocks.DIRT_PATH.defaultBlockState(), 11);
            }
        } else {
            Direction.Axis axis = event.getLevel().getBlockState(event.getPos()).getValue(BlockStateProperties.AXIS);
            event.getLevel().setBlock(event.getPos(), (ModBlocks.UNBOUNDHAY_BLOCK.get()).defaultBlockState().setValue(BlockStateProperties.AXIS, axis), 11);
        }

        if (!(block == dev.enemeez.simplefarming.common.registries.ModBlocks.BARLEY_HAY_BLOCK.get()) || !(item instanceof AxeItem) && !(item instanceof SwordItem) && !(item instanceof ShearsItem)) {
            if (block instanceof SparseGrassBlock && item instanceof HoeItem) {
                event.getLevel().setBlock(event.getPos(), Blocks.FARMLAND.defaultBlockState(), 11);
            } else if (block instanceof SparseGrassBlock && item instanceof ShovelItem) {
                event.getLevel().setBlock(event.getPos(), Blocks.DIRT_PATH.defaultBlockState(), 11);
            }
        } else {
            Direction.Axis axis = event.getLevel().getBlockState(event.getPos()).getValue(BlockStateProperties.AXIS);
            event.getLevel().setBlock(event.getPos(), (net.therealvoin.hyperrealism.init.ModBlocks.UNBOUND_BARLEY_HAY_BLOCK.get()).defaultBlockState().setValue(BlockStateProperties.AXIS, axis), 11);
        }

        if (!(block == dev.enemeez.simplefarming.common.registries.ModBlocks.OAT_HAY_BLOCK.get()) || !(item instanceof AxeItem) && !(item instanceof SwordItem) && !(item instanceof ShearsItem)) {
            if (block instanceof SparseGrassBlock && item instanceof HoeItem) {
                event.getLevel().setBlock(event.getPos(), Blocks.FARMLAND.defaultBlockState(), 11);
            } else if (block instanceof SparseGrassBlock && item instanceof ShovelItem) {
                event.getLevel().setBlock(event.getPos(), Blocks.DIRT_PATH.defaultBlockState(), 11);
            }
        } else {
            Direction.Axis axis = event.getLevel().getBlockState(event.getPos()).getValue(BlockStateProperties.AXIS);
            event.getLevel().setBlock(event.getPos(), (net.therealvoin.hyperrealism.init.ModBlocks.UNBOUND_OAT_HAY_BLOCK.get()).defaultBlockState().setValue(BlockStateProperties.AXIS, axis), 11);
        }

        if (!(block == dev.enemeez.simplefarming.common.registries.ModBlocks.RICE_HAY_BLOCK.get()) || !(item instanceof AxeItem) && !(item instanceof SwordItem) && !(item instanceof ShearsItem)) {
            if (block instanceof SparseGrassBlock && item instanceof HoeItem) {
                event.getLevel().setBlock(event.getPos(), Blocks.FARMLAND.defaultBlockState(), 11);
            } else if (block instanceof SparseGrassBlock && item instanceof ShovelItem) {
                event.getLevel().setBlock(event.getPos(), Blocks.DIRT_PATH.defaultBlockState(), 11);
            }
        } else {
            Direction.Axis axis = event.getLevel().getBlockState(event.getPos()).getValue(BlockStateProperties.AXIS);
            event.getLevel().setBlock(event.getPos(), (net.therealvoin.hyperrealism.init.ModBlocks.UNBOUND_RICE_HAY_BLOCK.get()).defaultBlockState().setValue(BlockStateProperties.AXIS, axis), 11);
        }

        if (!(block == dev.enemeez.simplefarming.common.registries.ModBlocks.RYE_HAY_BLOCK.get()) || !(item instanceof AxeItem) && !(item instanceof SwordItem) && !(item instanceof ShearsItem)) {
            if (block instanceof SparseGrassBlock && item instanceof HoeItem) {
                event.getLevel().setBlock(event.getPos(), Blocks.FARMLAND.defaultBlockState(), 11);
            } else if (block instanceof SparseGrassBlock && item instanceof ShovelItem) {
                event.getLevel().setBlock(event.getPos(), Blocks.DIRT_PATH.defaultBlockState(), 11);
            }
        } else {
            Direction.Axis axis = event.getLevel().getBlockState(event.getPos()).getValue(BlockStateProperties.AXIS);
            event.getLevel().setBlock(event.getPos(), (net.therealvoin.hyperrealism.init.ModBlocks.UNBOUND_RYE_HAY_BLOCK.get()).defaultBlockState().setValue(BlockStateProperties.AXIS, axis), 11);
        }

        callbackInfo.cancel();
    }
}