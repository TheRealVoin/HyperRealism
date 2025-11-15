package net.therealvoin.hyperrealism.improvement.creativemodetab;

import com.chaosthedude.realistictorches.registry.RealisticTorchesRegistry;
import com.legacy.dungeons_plus.registry.DPItems;
import croissantnova.sanitydim.item.ItemRegistry;
import net.capitaojob.bettercampfires.init.BetterCampfiresModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.pixelbank.burnt.init.BurntModItems;
import net.therealvoin.hyperrealism.util.ModDetectionHandler;
import net.therealvoin.hyperrealism.main.HyperRealism;
import tallestegg.guardvillagers.GuardItems;

public class CreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB_DEFERRED_REGISTER;
    public static final RegistryObject<CreativeModeTab> BUILD_YOUR_CAMPFIRE_TAB;
    public static final RegistryObject<CreativeModeTab> BURNT_BASIC_TAB;
    public static final RegistryObject<CreativeModeTab> DUNGEONS_PLUS_TAB;
    public static final RegistryObject<CreativeModeTab> GUARD_VILLAGERS_TAB;
    public static final RegistryObject<CreativeModeTab> PASSABLE_FOLIAGE_TAB;
    public static final RegistryObject<CreativeModeTab> REALISTIC_TORCHES_TAB;
    public static final RegistryObject<CreativeModeTab> SANITYDIM_TAB;

    static {
        CREATIVE_MODE_TAB_DEFERRED_REGISTER =
                DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HyperRealism.MOD_ID);

        if(ModDetectionHandler.isBuildYourCampfireLoaded()) {
            BUILD_YOUR_CAMPFIRE_TAB = CREATIVE_MODE_TAB_DEFERRED_REGISTER.register("better_campfires_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(BetterCampfiresModItems.FIREWOOD.get()))
                            .title(Component.translatable("creativemodetab.hyperrealism.better_campfires_tab"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(BetterCampfiresModItems.FIREWOOD.get());
                            })
                            .build());
        } else {
            BUILD_YOUR_CAMPFIRE_TAB = null;
        }

        if(ModDetectionHandler.isBurntBasicLoaded()) {
            BURNT_BASIC_TAB = CREATIVE_MODE_TAB_DEFERRED_REGISTER.register("burnt_basic_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(BurntModItems.BURNT_PLANKS.get()))
                            .title(Component.translatable("creativemodetab.hyperrealism.burnt_basic_tab"))
                            .displayItems((itemDisplayParameters, output) -> {
                                for(RegistryObject<Item> items : BurntModItems.REGISTRY.getEntries()) {
                                    output.accept(items.get());
                                }
                            })
                            .build());
        } else {
            BURNT_BASIC_TAB = null;
        }

        if(ModDetectionHandler.isDungeonsPlusLoaded()) {
            DUNGEONS_PLUS_TAB = CREATIVE_MODE_TAB_DEFERRED_REGISTER.register("dungeons_plus_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(DPItems.LEVIATHAN_BLADE.get()))
                            .title(Component.translatable("creativemodetab.hyperrealism.dungeons_plus_tab"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(DPItems.FROSTED_COWL.get());
                                output.accept(DPItems.LEVIATHAN_BLADE.get());
                                output.accept(DPItems.SOUL_CANNON.get());
                                output.accept(DPItems.WARPED_AXE.get());
                            })
                            .build());
        } else {
            DUNGEONS_PLUS_TAB = null;
        }

        if(ModDetectionHandler.isGuardVillagersLoaded()) {
            GUARD_VILLAGERS_TAB = CREATIVE_MODE_TAB_DEFERRED_REGISTER.register("guard_villagers_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(GuardItems.GUARD_SPAWN_EGG.get()))
                            .title(Component.translatable("creativemodetab.hyperrealism.guard_villagers_tab"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(GuardItems.GUARD_SPAWN_EGG.get());
                                output.accept(GuardItems.ILLUSIONER_SPAWN_EGG.get());
                            })
                            .build());
        } else {
            GUARD_VILLAGERS_TAB = null;
        }

        if (ModDetectionHandler.isPassableFoliageLoaded()) {
            PASSABLE_FOLIAGE_TAB = CREATIVE_MODE_TAB_DEFERRED_REGISTER.register("passable_foliage_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(Items.OAK_LEAVES))
                            .title(Component.translatable("creativemodetab.hyperrealism.passable_foliage_tab"))
                            .build());
        } else {
            PASSABLE_FOLIAGE_TAB = null;
        }

        if(ModDetectionHandler.isRealisticTorchesLoaded()) {
            REALISTIC_TORCHES_TAB = CREATIVE_MODE_TAB_DEFERRED_REGISTER.register("realistictorches_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(RealisticTorchesRegistry.UNLIT_TORCH_ITEM.get()))
                            .title(Component.translatable("creativemodetab.hyperrealism.realistic_torches_tab"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(RealisticTorchesRegistry.LIT_TORCH_ITEM.get());
                                output.accept(RealisticTorchesRegistry.UNLIT_TORCH_ITEM.get());
                                output.accept(RealisticTorchesRegistry.MATCHBOX_ITEM.get());
                                output.accept(RealisticTorchesRegistry.GLOWSTONE_CRYSTAL_ITEM.get());
                                output.accept(RealisticTorchesRegistry.GLOWSTONE_PASTE_ITEM.get());
                            })
                            .build());
        } else {
            REALISTIC_TORCHES_TAB = null;
        }

        if(ModDetectionHandler.isSanityDimLoaded()) {
            SANITYDIM_TAB = CREATIVE_MODE_TAB_DEFERRED_REGISTER.register("sanitydim_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ItemRegistry.GARLAND.get()))
                            .title(Component.translatable("creativemodetab.hyperrealism.sanitydim_tab"))
                            .displayItems((itemDisplayParameters, output) -> {
                                output.accept(ItemRegistry.GARLAND.get());
                            })
                            .build());
        } else {
            SANITYDIM_TAB = null;
        }
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB_DEFERRED_REGISTER.register(eventBus);
    }
}