package net.therealvoin.hyperrealism.init;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.therealvoin.hyperrealism.main.HyperRealism;

public class ModItems {
    public static final DeferredRegister<Item> ITEM_DEFERRED_REGISTER;
    public static final RegistryObject<Item> UNBOUND_BARLEY_HAY_BLOCK_ITEM;
    public static final RegistryObject<Item> UNBOUND_OAT_HAY_BLOCK_ITEM;
    public static final RegistryObject<Item> UNBOUND_RICE_HAY_BLOCK_ITEM;
    public static final RegistryObject<Item> UNBOUND_RYE_HAY_BLOCK_ITEM;

    static {
        ITEM_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, HyperRealism.MOD_ID);
        UNBOUND_BARLEY_HAY_BLOCK_ITEM = ITEM_DEFERRED_REGISTER.register("unbound_barley_hay_block", () -> new BlockItem(ModBlocks.UNBOUND_BARLEY_HAY_BLOCK.get(), new Item.Properties()));
        UNBOUND_OAT_HAY_BLOCK_ITEM = ITEM_DEFERRED_REGISTER.register("unbound_oat_hay_block", () -> new BlockItem(ModBlocks.UNBOUND_OAT_HAY_BLOCK.get(), new Item.Properties()));
        UNBOUND_RICE_HAY_BLOCK_ITEM = ITEM_DEFERRED_REGISTER.register("unbound_rice_hay_block", () -> new BlockItem(ModBlocks.UNBOUND_RICE_HAY_BLOCK.get(), new Item.Properties()));
        UNBOUND_RYE_HAY_BLOCK_ITEM = ITEM_DEFERRED_REGISTER.register("unbound_rye_hay_block", () -> new BlockItem(ModBlocks.UNBOUND_RYE_HAY_BLOCK.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ITEM_DEFERRED_REGISTER.register(eventBus);
    }
}