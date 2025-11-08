package net.therealvoin.hyperrealism.improvement.creativemodetab;

import com.chaosthedude.realistictorches.registry.RealisticTorchesRegistry;
import com.legacy.dungeons_plus.registry.DPItems;
import net.capitaojob.bettercampfires.init.BetterCampfiresModItems;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pixelbank.burnt.init.BurntModItems;
import net.therealvoin.hyperrealism.util.ModDetectionHandler;
import net.therealvoin.hyperrealism.main.HyperRealism;
import tallestegg.guardvillagers.GuardItems;
import weather2.WeatherItems;

@Mod.EventBusSubscriber(modid = HyperRealism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeModeTabsItemsManager {
    @SubscribeEvent
    public static void removeModdedItemsFromVanillaTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> currentTab = event.getTabKey();

        if(currentTab == CreativeModeTabs.BUILDING_BLOCKS) {
            if(ModDetectionHandler.isWeather2Loaded()) {
                removeItem(event, WeatherItems.WEATHER_ITEM.get());
            }
        } else if(currentTab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            if(ModDetectionHandler.isRealisticTorchesLoaded()) {
                removeItem(event, RealisticTorchesRegistry.LIT_TORCH_ITEM.get());
            }
        }

        if(ModDetectionHandler.isBurntBasicLoaded()) {
            if(currentTab == CreativeModeTabs.BUILDING_BLOCKS) {
                removeItem(event, BurntModItems.BURNT_LOG.get());
                removeItem(event, BurntModItems.BROKEN_BURNT_LOG.get());
                removeItem(event, BurntModItems.STRIPPED_BURNT_LOG.get());
                removeItem(event, BurntModItems.STRIPPED_BROKEN_BURNT_LOG.get());
                removeItem(event, BurntModItems.BURNT_PLANKS.get());
                removeItem(event, BurntModItems.BURNT_DOOR.get());
                removeItem(event, BurntModItems.BURNT_TRAPDOOR.get());
                removeItem(event, BurntModItems.BURNT_STAIRS.get());
                removeItem(event, BurntModItems.BURNT_SLAB.get());
                removeItem(event, BurntModItems.BURNT_FENCE.get());
                removeItem(event, BurntModItems.BURNT_FENCEGATE.get());
                removeItem(event, BurntModItems.BURNT_OAK_TRAPDOOR.get());
                removeItem(event, BurntModItems.BURNT_SPRUCE_TRAPDOOR.get());
                removeItem(event, BurntModItems.BURNT_BIRCH_TRAPDOOR.get());
                removeItem(event, BurntModItems.BURNT_JUNGLE_TRAPDOOR.get());
                removeItem(event, BurntModItems.BURNT_DARK_OAK_TRAPDOOR.get());
                removeItem(event, BurntModItems.BURNT_MANGROVE_TRAPDOOR.get());
                removeItem(event, BurntModItems.BURNT_CHERRY_TRAPDOOR.get());
                removeItem(event, BurntModItems.BURNT_ACACIA_TRAPDOOR.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_TRAPDOOR.get());
                removeItem(event, BurntModItems.BURNT_BUTTON.get());
                removeItem(event, BurntModItems.BURNT_PRESSURE_PLATE.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_STAIRS.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_MOSAIC_STAIRS.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_FENCE.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_FENCEGATE.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_PLANKS.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_MOSAIC_PLANKS.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_BLOCK.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_SLAB.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_MOSAIC_SLAB.get());
                removeItem(event, BurntModItems.BURNT_WOOD.get());
                removeItem(event, BurntModItems.STRIPPED_BURNT_WOOD.get());
                removeItem(event, BurntModItems.BURNT_BOOKSHELF.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_BUTTON.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_PRESSURE_PLATE.get());
                removeItem(event, BurntModItems.BURNT_ACACIA_DOOR.get());
                removeItem(event, BurntModItems.BURNT_BAMBOO_DOOR.get());
                removeItem(event, BurntModItems.BURNT_BIRCH_DOOR.get());
                removeItem(event, BurntModItems.BURNT_CHERRY_DOOR.get());
                removeItem(event, BurntModItems.BURNT_DARK_OAK_DOOR.get());
                removeItem(event, BurntModItems.BURNT_JUNGLE_DOOR.get());
                removeItem(event, BurntModItems.BURNT_MANGROVE_DOOR.get());
                removeItem(event, BurntModItems.BURNT_OAK_DOOR.get());
                removeItem(event, BurntModItems.BURNT_SPRUCE_DOOR.get());
            } else if(currentTab == CreativeModeTabs.COLORED_BLOCKS) {
                removeItem(event, BurntModItems.BURNT_WOOL.get());
            } else if(currentTab == CreativeModeTabs.NATURAL_BLOCKS) {
                removeItem(event, BurntModItems.BURNT_LEAVES.get());
                removeItem(event, BurntModItems.BURNT_LOG.get());
                removeItem(event, BurntModItems.BURNT_GRASS.get());
                removeItem(event, BurntModItems.BURNT_DIRT.get());
                removeItem(event, BurntModItems.BURNT_MANGROVE_ROOTS.get());
                removeItem(event, BurntModItems.BURNT_HAY.get());
                removeItem(event, BurntModItems.BURNT_MOSS.get());
                removeItem(event, BurntModItems.BURNT_CACTUS.get());
                removeItem(event, BurntModItems.BURNT_MOSS_CARPET.get());
                removeItem(event, BurntModItems.BURNT_PUMPKIN.get());
                removeItem(event, BurntModItems.BURNT_CARVED_PUMPKIN.get());
                removeItem(event, BurntModItems.BURNT_JACK_O_LANTERN.get());
                removeItem(event, BurntModItems.BURNT_MELON.get());
                removeItem(event, BurntModItems.COOKED_RED_MUSHROOM_BLOCK.get());
                removeItem(event, BurntModItems.COOKED_BROWN_MUSHROOM_BLOCK.get());
                removeItem(event, BurntModItems.COOKED_MUSHROOM_STEM.get());
            } else if(currentTab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
                removeItem(event, BurntModItems.BURNT_LADDER.get());
                removeItem(event, BurntModItems.BURNT_BARREL.get());
            } else if(currentTab == CreativeModeTabs.REDSTONE_BLOCKS) {
                removeItem(event, BurntModItems.FIRE_BARREL.get());
            } else if(currentTab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
                removeItem(event, BurntModItems.EXTINGUISHER.get());
            } else if(currentTab == CreativeModeTabs.FOOD_AND_DRINKS) {
                removeItem(event, BurntModItems.BAKED_APPLE.get());
                removeItem(event, BurntModItems.EMBER_BERRIES.get());
            } else if(currentTab == CreativeModeTabs.INGREDIENTS) {
                removeItem(event, BurntModItems.SOOT.get());
            }
        }

        if(currentTab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            if(ModDetectionHandler.isRealisticTorchesLoaded()) {
                removeItem(event, RealisticTorchesRegistry.MATCHBOX_ITEM.get());
            }
        }

        if(currentTab == CreativeModeTabs.COMBAT) {
            if(ModDetectionHandler.isDungeonsPlusLoaded()) {
                removeItem(event, DPItems.FROSTED_COWL.get());
                removeItem(event, DPItems.LEVIATHAN_BLADE.get());
                removeItem(event, DPItems.WARPED_AXE.get());
                removeItem(event, DPItems.SOUL_CANNON.get());
            }
        }

        if(currentTab == CreativeModeTabs.INGREDIENTS) {
            if(ModDetectionHandler.isBuildYourCampfireLoaded()) {
                removeItem(event, BetterCampfiresModItems.FIREWOOD.get());
            }

            if(ModDetectionHandler.isRealisticTorchesLoaded()) {
                removeItem(event, RealisticTorchesRegistry.GLOWSTONE_CRYSTAL_ITEM.get());
                removeItem(event, RealisticTorchesRegistry.GLOWSTONE_PASTE_ITEM.get());
            }
        }

        if(currentTab == CreativeModeTabs.SPAWN_EGGS) {
            if(ModDetectionHandler.isGuardVillagersLoaded()) {
                removeItem(event, GuardItems.GUARD_SPAWN_EGG.get());
                removeItem(event, GuardItems.ILLUSIONER_SPAWN_EGG.get());
            }
        }

        if(currentTab == net.therealvoin.hyperrealism.improvement.creativemodetab.CreativeModeTabs.BURNT_BASIC_TAB.getKey()) {
            removeItem(event, BurntModItems.GRASS_FLAMES.get());
            removeItem(event, BurntModItems.FIRE_SET_BLOCK.get());
            removeItem(event, BurntModItems.SMOLDERING_HIGH_GRASS.get());
            removeItem(event, BurntModItems.BURNT_HIGH_GRASS.get());
        }
    }

    private static void removeItem(BuildCreativeModeTabContentsEvent event, Item item) {
            event.getEntries().remove(new ItemStack(item));
    }
}
