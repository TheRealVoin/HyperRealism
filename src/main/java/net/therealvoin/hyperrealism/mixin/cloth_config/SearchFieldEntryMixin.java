package net.therealvoin.hyperrealism.mixin.cloth_config;

import me.shedaniel.clothconfig2.gui.widget.SearchFieldEntry;
import net.minecraft.client.gui.components.EditBox;
import net.therealvoin.hyperrealism.util.LiteralToTranslatable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SearchFieldEntry.class)
public class SearchFieldEntryMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/EditBox;setSuggestion(Ljava/lang/String;)V"))
    private void modifyRender(EditBox editBox, String suggestion) {
        LiteralToTranslatable.makeTranslatable(editBox, suggestion, "Search...", "text.hyperrealism.cloth_config.search");
    }
}