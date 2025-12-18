package net.therealvoin.hyperrealism.mixin.eanimod;

import mokiyoki.enhancedanimals.gui.EnhancedAnimalScreen;
import net.therealvoin.hyperrealism.util.LiteralToTranslatable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EnhancedAnimalScreen.class)
public class EnhancedAnimalScreenMixin {
    @ModifyArg(method = "renderLabels", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;FFIZ)I", ordinal = 1), index = 1)
    private String modifyRenderLabels(String originalText) {
        return LiteralToTranslatable.makeTranslatable("text.hyperrealism.eanimod.inventory");
    }
}