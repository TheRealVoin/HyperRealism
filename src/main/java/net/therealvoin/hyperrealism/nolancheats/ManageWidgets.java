package net.therealvoin.hyperrealism.nolancheats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.screens.ShareToLanScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.therealvoin.hyperrealism.main.HyperRealism;

@Mod.EventBusSubscriber(modid = HyperRealism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ManageWidgets {
    private static final byte REQUIRED_PERMISSION_LEVEL = 2;
    private static final byte GAMEMODE_BUTTON = 0;
    private static final byte CHEATS_BUTTON = 1;

    @SubscribeEvent
    public static void onLanScreenInit(ScreenEvent.Init.Post event) {
        if (event.getScreen() instanceof ShareToLanScreen lanScreen) {
            if (wasWorldCreatedWithCheatsOn()) {
                return;
            }

            AbstractButton gamemodeButton = (AbstractButton) lanScreen.renderables.get(GAMEMODE_BUTTON);
            AbstractButton cheatsButton = (AbstractButton) lanScreen.renderables.get(CHEATS_BUTTON);

            gamemodeButton.active = false;
            cheatsButton.active = false;
        }
    }

    private static boolean wasWorldCreatedWithCheatsOn() {
        LocalPlayer player = Minecraft.getInstance().player;
        return player != null && player.hasPermissions(REQUIRED_PERMISSION_LEVEL);
    }
}