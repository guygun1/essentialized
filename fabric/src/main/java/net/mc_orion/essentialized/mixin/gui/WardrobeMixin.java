package net.mc_orion.essentialized.mixin.gui;

import gg.essential.gui.wardrobe.Wardrobe;
import net.mc_orion.essentialized.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Wardrobe.class, remap = false)
public class WardrobeMixin {

    @Inject(method = "displayCartWarningModal", at = @At("HEAD"), cancellable = true)
    private void onDisplayCartWarning(CallbackInfo ci) {
        if (!Config.get().blockWardrobeNag) return;
        ci.cancel();
    }

    @Inject(method = "hasUnownedItems", at = @At("HEAD"), cancellable = true)
    private void onHasUnownedItems(CallbackInfoReturnable<Boolean> cir) {
        if (!Config.get().blockWardrobeNag) return;
        cir.setReturnValue(false);
    }
}
