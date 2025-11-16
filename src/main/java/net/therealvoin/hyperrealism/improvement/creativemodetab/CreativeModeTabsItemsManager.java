package net.therealvoin.hyperrealism.improvement.creativemodetab;

import com.chaosthedude.realistictorches.registry.RealisticTorchesRegistry;
import com.github.alexthe666.alexsmobs.AlexsMobs;
import com.github.alexthe666.alexsmobs.effect.AMEffectRegistry;
import com.github.alexthe666.alexsmobs.enchantment.AMEnchantmentRegistry;
import com.github.alexthe666.alexsmobs.misc.AMCreativeTabRegistry;
import com.kyanite.deeperdarker.DeeperDarker;
import com.kyanite.deeperdarker.content.DDEnchantments;
import com.kyanite.deeperdarker.content.DDPotions;
import com.kyanite.deeperdarker.util.DDCreativeTab;
import com.legacy.dungeons_plus.registry.DPItems;
import net.capitaojob.bettercampfires.init.BetterCampfiresModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pixelbank.burnt.init.BurntModItems;
import net.therealvoin.hyperrealism.util.ModDetectionHandler;
import net.therealvoin.hyperrealism.main.HyperRealism;
import snownee.passablefoliage.PassableFoliage;
import tallestegg.guardvillagers.GuardItems;
import toughasnails.api.enchantment.TANEnchantments;
import toughasnails.api.potion.TANPotions;
import toughasnails.core.ToughAsNails;
import weather2.WeatherItems;

import java.util.Iterator;
import java.util.Map;

@Mod.EventBusSubscriber(modid = HyperRealism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeModeTabsItemsManager {
    @SubscribeEvent
    public static void manageModdedItemsInTabs(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> currentTab = event.getTabKey();

        if (ModDetectionHandler.isAlexsMobsLoaded()) {
            if (currentTab == CreativeModeTabs.COMBAT) {
                removeModdedArrowsFromVanillaTab(event, AlexsMobs.MODID);
            } else if (currentTab == CreativeModeTabs.FOOD_AND_DRINKS) {
                removeModdedPotionsFromVanillaTab(event, AlexsMobs.MODID);
                removeModdedSplashPotionsFromVanillaTab(event, AlexsMobs.MODID);
                removeModdedLingeringPotionsFromVanillaTab(event, AlexsMobs.MODID);
            } else if (currentTab == CreativeModeTabs.INGREDIENTS) {
                removeModdedEnchantmentBooks(event, AlexsMobs.MODID);
            } else if (currentTab == AMCreativeTabRegistry.TAB.getKey()) {
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.KNOCKBACK_RESISTANCE_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_KNOCKBACK_RESISTANCE_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.STRONG_KNOCKBACK_RESISTANCE_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LAVA_VISION_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_LAVA_VISION_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.SPEED_III_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.POISON_RESISTANCE_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_POISON_RESISTANCE_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.BUG_PHEROMONES_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_BUG_PHEROMONES_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.SOULSTEAL_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_SOULSTEAL_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.STRONG_SOULSTEAL_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.CLINGING_POTION);
                addModdedArrowToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_CLINGING_POTION);

                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.KNOCKBACK_RESISTANCE_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_KNOCKBACK_RESISTANCE_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.STRONG_KNOCKBACK_RESISTANCE_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LAVA_VISION_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_LAVA_VISION_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.SPEED_III_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.POISON_RESISTANCE_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_POISON_RESISTANCE_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.BUG_PHEROMONES_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_BUG_PHEROMONES_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.SOULSTEAL_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_SOULSTEAL_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.STRONG_SOULSTEAL_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.CLINGING_POTION);
                addModdedPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_CLINGING_POTION);

                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.KNOCKBACK_RESISTANCE_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_KNOCKBACK_RESISTANCE_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.STRONG_KNOCKBACK_RESISTANCE_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LAVA_VISION_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_LAVA_VISION_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.SPEED_III_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.POISON_RESISTANCE_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_POISON_RESISTANCE_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.BUG_PHEROMONES_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_BUG_PHEROMONES_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.SOULSTEAL_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_SOULSTEAL_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.STRONG_SOULSTEAL_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.CLINGING_POTION);
                addModdedSplashPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_CLINGING_POTION);

                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.KNOCKBACK_RESISTANCE_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_KNOCKBACK_RESISTANCE_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.STRONG_KNOCKBACK_RESISTANCE_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LAVA_VISION_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_LAVA_VISION_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.SPEED_III_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.POISON_RESISTANCE_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_POISON_RESISTANCE_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.BUG_PHEROMONES_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_BUG_PHEROMONES_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.SOULSTEAL_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_SOULSTEAL_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.STRONG_SOULSTEAL_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.CLINGING_POTION);
                addModdedLingeringPotionToTab(event, AMCreativeTabRegistry.TAB, AMEffectRegistry.LONG_CLINGING_POTION);

                addModdedEnchantmentBook(event, AMCreativeTabRegistry.TAB, AMEnchantmentRegistry.STRADDLE_JUMP,  1);
                addModdedEnchantmentBook(event, AMCreativeTabRegistry.TAB, AMEnchantmentRegistry.STRADDLE_JUMP,  2);
                addModdedEnchantmentBook(event, AMCreativeTabRegistry.TAB, AMEnchantmentRegistry.STRADDLE_JUMP,  3);
                addModdedEnchantmentBook(event, AMCreativeTabRegistry.TAB, AMEnchantmentRegistry.STRADDLE_LAVAWAX,  1);
                addModdedEnchantmentBook(event, AMCreativeTabRegistry.TAB, AMEnchantmentRegistry.STRADDLE_SERPENTFRIEND,  1);
                addModdedEnchantmentBook(event, AMCreativeTabRegistry.TAB, AMEnchantmentRegistry.STRADDLE_BOARDRETURN,  1);
            }
        }

        if (ModDetectionHandler.isBuildYourCampfireLoaded()) {
            if (currentTab == CreativeModeTabs.INGREDIENTS) {
                removeItem(event, BetterCampfiresModItems.FIREWOOD.get());
            }
        }

        if (ModDetectionHandler.isBurntBasicLoaded()) {
            if (currentTab == CreativeModeTabs.BUILDING_BLOCKS) {
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
            } else if (currentTab == CreativeModeTabs.COLORED_BLOCKS) {
                removeItem(event, BurntModItems.BURNT_WOOL.get());
            } else if (currentTab == CreativeModeTabs.NATURAL_BLOCKS) {
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
            } else if (currentTab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
                removeItem(event, BurntModItems.BURNT_LADDER.get());
                removeItem(event, BurntModItems.BURNT_BARREL.get());
            } else if (currentTab == CreativeModeTabs.REDSTONE_BLOCKS) {
                removeItem(event, BurntModItems.FIRE_BARREL.get());
            } else if (currentTab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
                removeItem(event, BurntModItems.EXTINGUISHER.get());
            } else if (currentTab == CreativeModeTabs.FOOD_AND_DRINKS) {
                removeItem(event, BurntModItems.BAKED_APPLE.get());
                removeItem(event, BurntModItems.EMBER_BERRIES.get());
            } else if (currentTab == CreativeModeTabs.INGREDIENTS) {
                removeItem(event, BurntModItems.SOOT.get());
            }
        }

        if (ModDetectionHandler.isDeeperAndDarkerLoaded()) {
            if (currentTab == CreativeModeTabs.COMBAT) {
                removeModdedArrowsFromVanillaTab(event, DeeperDarker.MOD_ID);
            } else if (currentTab == CreativeModeTabs.FOOD_AND_DRINKS) {
                removeModdedPotionsFromVanillaTab(event, DeeperDarker.MOD_ID);
                removeModdedSplashPotionsFromVanillaTab(event, DeeperDarker.MOD_ID);
                removeModdedLingeringPotionsFromVanillaTab(event, DeeperDarker.MOD_ID);
            } else if (currentTab == CreativeModeTabs.INGREDIENTS) {
                removeModdedEnchantmentBooks(event, DeeperDarker.MOD_ID);
            } else if (currentTab == DDCreativeTab.DEEPER_DARKER.getKey()) {
                addModdedArrowToTab(event, DDCreativeTab.DEEPER_DARKER, DDPotions.SCULK_AFFINITY);
                addModdedArrowToTab(event, DDCreativeTab.DEEPER_DARKER, DDPotions.LONG_SCULK_AFFINITY);

                addModdedPotionToTab(event, DDCreativeTab.DEEPER_DARKER, DDPotions.SCULK_AFFINITY);
                addModdedPotionToTab(event, DDCreativeTab.DEEPER_DARKER, DDPotions.LONG_SCULK_AFFINITY);
                addModdedSplashPotionToTab(event, DDCreativeTab.DEEPER_DARKER, DDPotions.SCULK_AFFINITY);
                addModdedSplashPotionToTab(event, DDCreativeTab.DEEPER_DARKER, DDPotions.LONG_SCULK_AFFINITY);
                addModdedLingeringPotionToTab(event, DDCreativeTab.DEEPER_DARKER, DDPotions.SCULK_AFFINITY);
                addModdedLingeringPotionToTab(event, DDCreativeTab.DEEPER_DARKER, DDPotions.LONG_SCULK_AFFINITY);

                addModdedEnchantmentBook(event, DDCreativeTab.DEEPER_DARKER, DDEnchantments.CATALYSIS, 1);
                addModdedEnchantmentBook(event, DDCreativeTab.DEEPER_DARKER, DDEnchantments.CATALYSIS, 2);
                addModdedEnchantmentBook(event, DDCreativeTab.DEEPER_DARKER, DDEnchantments.CATALYSIS, 3);
                addModdedEnchantmentBook(event, DDCreativeTab.DEEPER_DARKER, DDEnchantments.SCULK_SMITE, 1);
                addModdedEnchantmentBook(event, DDCreativeTab.DEEPER_DARKER, DDEnchantments.SCULK_SMITE, 2);
                addModdedEnchantmentBook(event, DDCreativeTab.DEEPER_DARKER, DDEnchantments.SCULK_SMITE, 3);
                addModdedEnchantmentBook(event, DDCreativeTab.DEEPER_DARKER, DDEnchantments.SCULK_SMITE, 4);
                addModdedEnchantmentBook(event, DDCreativeTab.DEEPER_DARKER, DDEnchantments.SCULK_SMITE, 5);
            }
        }

        if (ModDetectionHandler.isDungeonsPlusLoaded()) {
            if (currentTab == CreativeModeTabs.COMBAT) {
                removeItem(event, DPItems.FROSTED_COWL.get());
                removeItem(event, DPItems.LEVIATHAN_BLADE.get());
                removeItem(event, DPItems.WARPED_AXE.get());
                removeItem(event, DPItems.SOUL_CANNON.get());
            }
        }

        if (ModDetectionHandler.isGuardVillagersLoaded()) {
            if (currentTab == CreativeModeTabs.SPAWN_EGGS) {
                removeItem(event, GuardItems.GUARD_SPAWN_EGG.get());
                removeItem(event, GuardItems.ILLUSIONER_SPAWN_EGG.get());
            }
        }

        if (ModDetectionHandler.isPassableFoliageLoaded()) {
            if (currentTab == CreativeModeTabs.INGREDIENTS) {
                removeModdedEnchantmentBooks(event, PassableFoliage.ID);
            } else if (currentTab == net.therealvoin.hyperrealism.improvement.creativemodetab.CreativeModeTabs.PASSABLE_FOLIAGE_TAB.getKey()) {
                addLeafWalkerEnchantmentBook(event);
            }
        }

        if (ModDetectionHandler.isRealisticTorchesLoaded()) {
            if (currentTab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
                removeItem(event, RealisticTorchesRegistry.LIT_TORCH_ITEM.get());
            } else if (currentTab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
                removeItem(event, RealisticTorchesRegistry.MATCHBOX_ITEM.get());
            } else if (currentTab == CreativeModeTabs.INGREDIENTS) {
                removeItem(event, RealisticTorchesRegistry.GLOWSTONE_CRYSTAL_ITEM.get());
                removeItem(event, RealisticTorchesRegistry.GLOWSTONE_PASTE_ITEM.get());
            }
        }

        if (ModDetectionHandler.isToughAsNailsLoaded()) {
            ResourceKey<CreativeModeTab> tab = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath("toughasnails", "main"));
            if (currentTab == CreativeModeTabs.COMBAT) {
                removeModdedArrowsFromVanillaTab(event, ToughAsNails.MOD_ID);
            } else if (currentTab == CreativeModeTabs.FOOD_AND_DRINKS) {
                removeModdedPotionsFromVanillaTab(event, ToughAsNails.MOD_ID);
                removeModdedSplashPotionsFromVanillaTab(event, ToughAsNails.MOD_ID);
                removeModdedLingeringPotionsFromVanillaTab(event, ToughAsNails.MOD_ID);
            } else if (currentTab == CreativeModeTabs.INGREDIENTS) {
                removeModdedEnchantmentBooks(event, ToughAsNails.MOD_ID);
            } else if (currentTab == tab) {
                addToughAsNailsArrow(event, tab, TANPotions.ICE_RESISTANCE);
                addToughAsNailsArrow(event, tab, TANPotions.LONG_ICE_RESISTANCE);
                addToughAsNailsPotion(event, tab, TANPotions.ICE_RESISTANCE);
                addToughAsNailsPotion(event, tab, TANPotions.LONG_ICE_RESISTANCE);
                addToughAsNailsSplashPotion(event, tab, TANPotions.ICE_RESISTANCE);
                addToughAsNailsSplashPotion(event, tab, TANPotions.LONG_ICE_RESISTANCE);
                addToughAsNailsLingeringPotion(event, tab, TANPotions.ICE_RESISTANCE);
                addToughAsNailsLingeringPotion(event, tab, TANPotions.LONG_ICE_RESISTANCE);

                addToughAsNailsEnchantmentBook(event, tab, TANEnchantments.THERMAL_TUNING, 1);
                addToughAsNailsEnchantmentBook(event, tab, TANEnchantments.WATER_CLEANSING, 1);
            }
        }

        if (ModDetectionHandler.isWeather2Loaded()) {
            if (currentTab == CreativeModeTabs.BUILDING_BLOCKS) {
                removeItem(event, WeatherItems.WEATHER_ITEM.get());
            }
        }

        if (currentTab == net.therealvoin.hyperrealism.improvement.creativemodetab.CreativeModeTabs.BURNT_BASIC_TAB.getKey()) {
            removeItem(event, BurntModItems.GRASS_FLAMES.get());
            removeItem(event, BurntModItems.FIRE_SET_BLOCK.get());
            removeItem(event, BurntModItems.SMOLDERING_HIGH_GRASS.get());
            removeItem(event, BurntModItems.BURNT_HIGH_GRASS.get());
        }
    }

    private static void addModdedArrowToTab(BuildCreativeModeTabContentsEvent event, RegistryObject<CreativeModeTab> tab, RegistryObject<Potion> potionEffect) {
        addItemToTab(event, tab, potionEffect, Items.TIPPED_ARROW);
    }

    private static void addModdedPotionToTab(BuildCreativeModeTabContentsEvent event, RegistryObject<CreativeModeTab> tab, RegistryObject<Potion> potionEffect) {
        addItemToTab(event, tab, potionEffect, Items.POTION);
    }

    private static void addModdedSplashPotionToTab(BuildCreativeModeTabContentsEvent event, RegistryObject<CreativeModeTab> tab, RegistryObject<Potion> potionEffect) {
        addItemToTab(event, tab, potionEffect, Items.SPLASH_POTION);
    }

    private static void addModdedLingeringPotionToTab(BuildCreativeModeTabContentsEvent event, RegistryObject<CreativeModeTab> tab, RegistryObject<Potion> potionEffect) {
        addItemToTab(event, tab, potionEffect, Items.LINGERING_POTION);
    }

    private static void addToughAsNailsArrow(BuildCreativeModeTabContentsEvent event, ResourceKey<CreativeModeTab> tabKey, Potion potionEffect) {
        addToughAsNailsXPotion(event, tabKey, Items.TIPPED_ARROW, potionEffect);
    }

    private static void addToughAsNailsPotion(BuildCreativeModeTabContentsEvent event, ResourceKey<CreativeModeTab> tabKey, Potion potionEffect) {
        addToughAsNailsXPotion(event, tabKey, Items.POTION, potionEffect);
    }

    private static void addToughAsNailsSplashPotion(BuildCreativeModeTabContentsEvent event, ResourceKey<CreativeModeTab> tabKey, Potion potionEffect) {
        addToughAsNailsXPotion(event, tabKey, Items.SPLASH_POTION, potionEffect);
    }

    private static void addToughAsNailsLingeringPotion(BuildCreativeModeTabContentsEvent event, ResourceKey<CreativeModeTab> tabKey, Potion potionEffect) {
        addToughAsNailsXPotion(event, tabKey, Items.LINGERING_POTION, potionEffect);
    }

    private static void addToughAsNailsXPotion(BuildCreativeModeTabContentsEvent event, ResourceKey<CreativeModeTab> tabKey, Item item, Potion potionEffect) {
        if (event.getTabKey() == tabKey) {
            ItemStack itemStack = new ItemStack(item);
            CompoundTag tag = new CompoundTag();

            ResourceLocation potionId = ForgeRegistries.POTIONS.getKey(potionEffect);
            tag.putString("Potion", potionId.toString());
            itemStack.setTag(tag);
            event.accept(itemStack);
        }
    }

    private static void addToughAsNailsEnchantmentBook(BuildCreativeModeTabContentsEvent event, ResourceKey<CreativeModeTab> tabKey, Enchantment enchantment, int level) {
        if (event.getTabKey() == tabKey) {
            ItemStack itemStack = new ItemStack(Items.ENCHANTED_BOOK);
            CompoundTag tag = new CompoundTag();
            ListTag enchantments = new ListTag();
            CompoundTag enchantmentTag = new CompoundTag();

            ResourceLocation enchantmentId = ForgeRegistries.ENCHANTMENTS.getKey(enchantment);
            enchantmentTag.putString("id", enchantmentId.toString());
            enchantmentTag.putInt("lvl", level);

            enchantments.add(enchantmentTag);
            tag.put("StoredEnchantments", enchantments);

            itemStack.setTag(tag);
            event.accept(itemStack);
        }
    }

    private static void addModdedEnchantmentBook(BuildCreativeModeTabContentsEvent event, RegistryObject<CreativeModeTab> tab, RegistryObject<Enchantment> enchantment, int level) {
        if (event.getTabKey() == tab.getKey()) {
            ItemStack itemStack = new ItemStack(Items.ENCHANTED_BOOK);
            CompoundTag tag = new CompoundTag();
            ListTag enchantments = new ListTag();
            CompoundTag enchantmentTag = new CompoundTag();

            enchantmentTag.putString("id", enchantment.getId().toString());
            enchantmentTag.putInt("lvl", level);

            enchantments.add(enchantmentTag);
            tag.put("StoredEnchantments", enchantments);

            itemStack.setTag(tag);
            event.accept(itemStack);
        }
    }

    private static void addLeafWalkerEnchantmentBook(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == net.therealvoin.hyperrealism.improvement.creativemodetab.CreativeModeTabs.PASSABLE_FOLIAGE_TAB.getKey()) {
            ItemStack itemStack = new ItemStack(Items.ENCHANTED_BOOK);
            CompoundTag tag = new CompoundTag();
            ListTag enchantments = new ListTag();
            CompoundTag enchantmentTag = new CompoundTag();

            enchantmentTag.putString("id", "passablefoliage:leaf_walker");
            enchantmentTag.putInt("lvl", 1);

            enchantments.add(enchantmentTag);
            tag.put("StoredEnchantments", enchantments);

            itemStack.setTag(tag);
            event.accept(itemStack);
        }
    }

    private static void addItemToTab(BuildCreativeModeTabContentsEvent event, RegistryObject<CreativeModeTab> tab, RegistryObject<Potion> potionEffect, Item item) {
        if (event.getTabKey() == tab.getKey()) {
            ItemStack itemStack = new ItemStack(item);
            CompoundTag tag = new CompoundTag();
            tag.putString("Potion", potionEffect.getId().toString());
            itemStack.setTag(tag);
            event.accept(itemStack);
        }
    }

    private static void removeItem(BuildCreativeModeTabContentsEvent event, Item item) {
        event.getEntries().remove(new ItemStack(item));
    }

    private static void removeModdedArrowsFromVanillaTab(BuildCreativeModeTabContentsEvent event, String modId) {
        removeItemsFromVanillaTab(event, Items.TIPPED_ARROW, modId);
    }

    private static void removeModdedPotionsFromVanillaTab(BuildCreativeModeTabContentsEvent event, String modId) {
        removeItemsFromVanillaTab(event, Items.POTION, modId);
    }

    private static void removeModdedSplashPotionsFromVanillaTab(BuildCreativeModeTabContentsEvent event, String modId) {
        removeItemsFromVanillaTab(event, Items.SPLASH_POTION, modId);
    }

    private static void removeModdedLingeringPotionsFromVanillaTab(BuildCreativeModeTabContentsEvent event, String modId) {
        removeItemsFromVanillaTab(event, Items.LINGERING_POTION, modId);
    }

    private static void removeItemsFromVanillaTab(BuildCreativeModeTabContentsEvent event, Item item, String modId) {
        Iterator<Map.Entry<ItemStack, CreativeModeTab.TabVisibility>> iterator = event.getEntries().iterator();

        while (iterator.hasNext()) {
            Map.Entry<ItemStack, CreativeModeTab.TabVisibility> entry = iterator.next();
            ItemStack itemStack = entry.getKey();

            if (itemStack.getItem() == item && itemStack.hasTag()) {
                CompoundTag tag = itemStack.getTag();
                if (tag != null && tag.contains("Potion")) {
                    String potionId = tag.getString("Potion");
                    if (potionId.startsWith(modId + ":")) {
                        iterator.remove();
                    }
                }
            }
        }
    }

    private static void removeModdedEnchantmentBooks(BuildCreativeModeTabContentsEvent event, String modId) {
        Iterator<Map.Entry<ItemStack, CreativeModeTab.TabVisibility>> iterator = event.getEntries().iterator();

        while (iterator.hasNext()) {
            Map.Entry<ItemStack, CreativeModeTab.TabVisibility> entry = iterator.next();
            ItemStack itemStack = entry.getKey();

            if (itemStack.getItem() == Items.ENCHANTED_BOOK && itemStack.hasTag()) {
                CompoundTag tag = itemStack.getTag();
                if (tag != null && tag.contains("StoredEnchantments")) {
                    ListTag enchantments = tag.getList("StoredEnchantments", ListTag.TAG_COMPOUND);
                    CompoundTag enchantment = enchantments.getCompound(0);
                    String enchantmentId = enchantment.getString("id");
                    if (enchantmentId.startsWith(modId + ":")) {
                        iterator.remove();
                    }
                }
            }
        }
    }
}