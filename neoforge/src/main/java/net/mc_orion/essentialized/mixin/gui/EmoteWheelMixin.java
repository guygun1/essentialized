package net.mc_orion.essentialized.mixin.gui;

import gg.essential.model.BedrockModel;
import net.mc_orion.essentialized.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "gg.essential.gui.emotes.EmoteWheel$Companion", remap = false)
public class EmoteWheelMixin {

    @Inject(method = "equipEmote", at = @At("HEAD"), cancellable = true)
    private void onEquipEmote(BedrockModel emote, CallbackInfo ci) {
        if (!Config.get().blockEmotes) return;
        ci.cancel();
    }
}
