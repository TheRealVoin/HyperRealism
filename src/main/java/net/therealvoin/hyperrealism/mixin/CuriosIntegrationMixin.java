package net.therealvoin.hyperrealism.mixin;

import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.theillusivec4.curios.client.gui.CuriosScreen;
import top.theillusivec4.curios.client.gui.CuriosScreenV2;

@Mixin(value = com.illusivesoulworks.diet.common.integration.CuriosIntegration.class, remap = false)
public class CuriosIntegrationMixin {
    @Inject(method = "isCuriosScreen", at = @At("HEAD"), cancellable = true)
    private static void modifyIsCuriosScreen(Screen screen, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(screen instanceof CuriosScreen || screen instanceof CuriosScreenV2);
    }
}
