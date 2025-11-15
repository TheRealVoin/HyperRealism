package net.therealvoin.hyperrealism.util;

import net.minecraftforge.fml.ModList;

public class ModDetectionHandler {
    public static boolean isAlexsMobsLoaded() {
        return ModList.get().isLoaded("alexsmobs");
    }

    public static boolean isBuildYourCampfireLoaded() {
        return ModList.get().isLoaded("better_campfires");
    }

    public static boolean isBurntBasicLoaded() {
        return ModList.get().isLoaded("burnt");
    }

    public static boolean isDeeperAndDarkerLoaded() {
        return ModList.get().isLoaded("deeperdarker");
    }

    public static boolean isDungeonsPlusLoaded() {
        return ModList.get().isLoaded("dungeons_plus");
    }

    public static boolean isGuardVillagersLoaded() {
        return ModList.get().isLoaded("guardvillagers");
    }

    public static boolean isPassableFoliageLoaded() {
        return ModList.get().isLoaded("passablefoliage");
    }

    public static boolean isRealisticTorchesLoaded() {
        return ModList.get().isLoaded("realistictorches");
    }

    public static boolean isSanityDimLoaded() {
        return ModList.get().isLoaded("sanitydim");
    }

    public static boolean isWeather2Loaded() {
        return ModList.get().isLoaded("weather2");
    }
}