package net.mc_orion.essentialized.mixin.cosmetics;

import gg.essential.cosmetics.CosmeticsRenderState;
import gg.essential.cosmetics.WearablesManager;
import net.mc_orion.essentialized.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = CosmeticsRenderState.Live.class, remap = false)
public class CosmeticsRenderStateLiveMixin {

    @Inject(method = "wearablesManager", at = @At("HEAD"), cancellable = true)
    private void disableCosmeticRendering(CallbackInfoReturnable<WearablesManager> cir) {
        if (!Config.get().blockCosmetics) return;
        cir.setReturnValue(null);
    }
}
