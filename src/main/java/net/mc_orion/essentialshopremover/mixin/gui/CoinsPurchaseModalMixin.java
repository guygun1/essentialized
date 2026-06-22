package net.mc_orion.essentialshopremover.mixin.gui;

import gg.essential.gui.wardrobe.WardrobeState;
import net.mc_orion.essentialshopremover.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "gg.essential.gui.wardrobe.modals.CoinsPurchaseModal$Companion", remap = false)
public class CoinsPurchaseModalMixin {

    @Inject(method = "open", at = @At("HEAD"), cancellable = true)
    private void onOpen(WardrobeState state, Integer coinsNeeded, CallbackInfo ci) {
        if (!Config.BLOCK_COINS.get()) return;
        ci.cancel();
    }
}
