package net.mc_orion.essentialized.mixin.notices;

import gg.essential.network.connectionmanager.notices.NoticeBannerManager;
import net.mc_orion.essentialized.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = NoticeBannerManager.class, remap = false)
public class NoticeBannerManagerMixin {

    @Inject(method = "noticeAdded", at = @At("HEAD"), cancellable = true)
    private void onNoticeAdded(CallbackInfo ci) {
        if (!Config.get().blockNotices) return;
        ci.cancel();
    }
}
