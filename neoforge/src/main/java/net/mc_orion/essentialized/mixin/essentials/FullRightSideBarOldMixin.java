package net.mc_orion.essentialized.mixin.essentials;

import gg.essential.gui.menu.RightSideBarNew;
import net.mc_orion.essentialized.Config;
import net.mc_orion.essentialized.EssentializedMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RightSideBarNew.class, remap = false)
public class FullRightSideBarOldMixin {

    @Unique
    private static boolean essentialized$logSocial = false;
    @Unique
    private static boolean essentialized$logMonetization = false;

    @Inject(method = "wardrobeButton", at = @At("HEAD"), cancellable = true)
    private void onWardrobeButton(gg.essential.gui.layoutdsl.LayoutScope scope, CallbackInfo ci) {
        if (Config.get().removeMainMenuButtons) {
            ci.cancel();
            if (!essentialized$logMonetization) {
                essentialized$logMonetization = true;
                EssentializedMod.LOGGER.info("[Essentialized] Cancelled wardrobeButton layout");
            }
        }
    }

    @Inject(method = "picturesButton", at = @At("HEAD"), cancellable = true)
    private void onPicturesButton(gg.essential.gui.layoutdsl.LayoutScope scope, CallbackInfo ci) {
        if (Config.get().removeSocialFeatures) {
            ci.cancel();
        }
    }

    @Inject(method = "settingsButton", at = @At("HEAD"), cancellable = true)
    private void onSettingsButton(gg.essential.gui.layoutdsl.LayoutScope scope, CallbackInfo ci) {
        if (Config.get().removeMainMenuButtons) {
            ci.cancel();
        }
    }

    @Inject(method = "accountButton", at = @At("HEAD"), cancellable = true)
    private void onAccountButton(gg.essential.gui.layoutdsl.LayoutScope scope, CallbackInfo ci) {
        if (Config.get().removeMainMenuButtons) {
            ci.cancel();
        }
    }

    @Inject(method = "worldSettingsButton", at = @At("HEAD"), cancellable = true)
    private void onWorldSettingsButton(gg.essential.gui.layoutdsl.LayoutScope scope, CallbackInfo ci) {
        if (Config.get().removeMainMenuButtons) {
            ci.cancel();
        }
    }

    @Inject(method = "socialButton", at = @At("HEAD"), cancellable = true)
    private void onSocialButton(gg.essential.gui.layoutdsl.LayoutScope scope, CallbackInfo ci) {
        if (Config.get().removeSocialFeatures) {
            ci.cancel();
            if (!essentialized$logSocial) {
                essentialized$logSocial = true;
                EssentializedMod.LOGGER.info("[Essentialized] Cancelled socialButton layout");
            }
        }
    }

    @Inject(method = "messageFlag", at = @At("HEAD"), cancellable = true)
    private void onMessageFlag(gg.essential.gui.layoutdsl.LayoutScope scope, CallbackInfo ci) {
        if (Config.get().removeSocialFeatures) {
            ci.cancel();
        }
    }

    @Inject(method = "hostButton", at = @At("HEAD"), cancellable = true)
    private void onHostButton(gg.essential.gui.layoutdsl.LayoutScope scope, CallbackInfo ci) {
        if (Config.get().removeSocialFeatures) {
            ci.cancel();
        }
    }

    @Inject(method = "inviteOrHostButton", at = @At("HEAD"), cancellable = true)
    private void onInviteOrHostButton(gg.essential.gui.layoutdsl.LayoutScope scope, CallbackInfo ci) {
        if (Config.get().removeSocialFeatures) {
            ci.cancel();
        }
    }

    @Inject(method = "inviteButton", at = @At("HEAD"), cancellable = true)
    private void onInviteButton(gg.essential.gui.layoutdsl.LayoutScope scope, CallbackInfo ci) {
        if (Config.get().removeSocialFeatures) {
            ci.cancel();
        }
    }
}
