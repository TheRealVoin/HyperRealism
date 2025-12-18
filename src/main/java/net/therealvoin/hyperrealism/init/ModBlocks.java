package net.therealvoin.hyperrealism.init;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.therealvoin.hyperrealism.block.UnboundBarleyHayBlock;
import net.therealvoin.hyperrealism.block.UnboundOatHayBlock;
import net.therealvoin.hyperrealism.block.UnboundRiceHayBlock;
import net.therealvoin.hyperrealism.block.UnboundRyeHayBlock;
import net.therealvoin.hyperrealism.main.HyperRealism;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCK_DEFERRED_REGISTER;
    public static final RegistryObject<Block> UNBOUND_BARLEY_HAY_BLOCK;
    public static final RegistryObject<Block> UNBOUND_OAT_HAY_BLOCK;
    public static final RegistryObject<Block> UNBOUND_RICE_HAY_BLOCK;
    public static final RegistryObject<Block> UNBOUND_RYE_HAY_BLOCK;

    static {
        BLOCK_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, HyperRealism.MOD_ID);

        UNBOUND_BARLEY_HAY_BLOCK = BLOCK_DEFERRED_REGISTER.register("unbound_barley_hay_block",
                () -> new UnboundBarleyHayBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.GRASS)
                        .strength(0.5F)
                        .sound(SoundType.GRASS)
                        .noOcclusion()
                        .isSuffocating((a, b, c) -> false)));

        UNBOUND_OAT_HAY_BLOCK = BLOCK_DEFERRED_REGISTER.register("unbound_oat_hay_block",
                () -> new UnboundOatHayBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.GRASS)
                        .strength(0.5F)
                        .sound(SoundType.GRASS)
                        .noOcclusion()
                        .isSuffocating((a, b, c) -> false)));

        UNBOUND_RICE_HAY_BLOCK = BLOCK_DEFERRED_REGISTER.register("unbound_rice_hay_block",
                () -> new UnboundRiceHayBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.GRASS)
                        .strength(0.5F)
                        .sound(SoundType.GRASS)
                        .noOcclusion()
                        .isSuffocating((a, b, c) -> false)));

        UNBOUND_RYE_HAY_BLOCK = BLOCK_DEFERRED_REGISTER.register("unbound_rye_hay_block",
                () -> new UnboundRyeHayBlock(BlockBehaviour.Properties.of()
                        .mapColor(MapColor.GRASS)
                        .strength(0.5F)
                        .sound(SoundType.GRASS)
                        .noOcclusion()
                        .isSuffocating((a, b, c) -> false)));
    }

    public static void register(IEventBus eventBus) {
        BLOCK_DEFERRED_REGISTER.register(eventBus);
    }
}