package net.therealvoin.hyperrealism.mixin.sound_physics_remastered;

import com.sonicether.soundphysics.integration.voicechat.SimpleVoiceChatPlugin;
import de.maxhenkel.voicechat.api.VolumeCategory;
import de.maxhenkel.voicechat.api.events.VoicechatServerStartedEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SimpleVoiceChatPlugin.class)
public class SimpleVoiceChatPluginMixin {
    @Inject(method = "onServerStarted", at = @At("HEAD"), cancellable = true, remap = false)
    private void modifyOnServerStarted(VoicechatServerStartedEvent event, CallbackInfo callbackInfo) {
        VolumeCategory ownVoice = event.getVoicechat().volumeCategoryBuilder()
                .setId(SimpleVoiceChatPlugin.OWN_VOICE_CATEGORY)
                .setName("Own Voice")
                .setNameTranslationKey("text.hyperrealism.sound_physics_remastered.plugin_name")
                .setDescription("The volume of your own voice")
                .setDescriptionTranslationKey("text.hyperrealism.sound_physics_remastered.plugin_description")
                .build();
        event.getVoicechat().registerVolumeCategory(ownVoice);

        callbackInfo.cancel();
    }
}