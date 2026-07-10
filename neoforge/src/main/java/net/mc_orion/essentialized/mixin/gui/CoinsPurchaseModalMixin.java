package net.mc_orion.essentialized.mixin.gui;

import gg.essential.gui.wardrobe.WardrobeState;
import net.mc_orion.essentialized.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "gg.essential.gui.wardrobe.modals.CoinsPurchaseModal$Companion", remap = false)
public class CoinsPurchaseModalMixin {

    @Inject(method = "open", at = @At("HEAD"), cancellable = true)
    private void onOpen(WardrobeState state, Integer coinsNeeded, CallbackInfo ci) {
        if (!Config.get().blockCoins) return;
        ci.cancel();
    }
}
