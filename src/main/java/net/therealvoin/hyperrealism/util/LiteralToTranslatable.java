package net.therealvoin.hyperrealism.util;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class LiteralToTranslatable {
    public static void makeTranslatable(EditBox editBox, String suggestion, String textToTranslate, String translationKey) {
        if (suggestion.equals(textToTranslate)) {
            editBox.setSuggestion(Component.translatable(translationKey).getString());
        } else {
            editBox.setSuggestion(suggestion);
        }
    }

    public static String makeTranslatable(String translationKey) {
        return Component.translatable(translationKey).getString();
    }
}