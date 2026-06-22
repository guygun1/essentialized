package net.mc_orion.essentialshopremover.mixin.gui;

import gg.essential.gui.wardrobe.Wardrobe;
import net.mc_orion.essentialshopremover.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Wardrobe.class, remap = false)
public class WardrobeMixin {

    @Inject(method = "displayCartWarningModal", at = @At("HEAD"), cancellable = true)
    private void onDisplayCartWarning(CallbackInfo ci) {
        if (!Config.BLOCK_WARDROBE_NAG.get()) return;
        ci.cancel();
    }

    @Inject(method = "hasUnownedItems", at = @At("HEAD"), cancellable = true)
    private void onHasUnownedItems(CallbackInfoReturnable<Boolean> cir) {
        if (!Config.BLOCK_WARDROBE_NAG.get()) return;
        cir.setReturnValue(false);
    }
}
