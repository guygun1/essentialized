package net.mc_orion.essentialshopremover.mixin.notices;

import gg.essential.network.connectionmanager.notices.CosmeticNotices;
import net.mc_orion.essentialshopremover.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CosmeticNotices.class, remap = false)
public class CosmeticNoticesMixin {

    @Inject(method = "cosmeticAdded", at = @At("HEAD"), cancellable = true)
    private void onCosmeticAdded(CallbackInfo ci) {
        if (!Config.BLOCK_NOTICES.get()) return;
        ci.cancel();
    }
}
