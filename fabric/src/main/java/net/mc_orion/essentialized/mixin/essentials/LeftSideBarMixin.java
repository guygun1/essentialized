package net.mc_orion.essentialized.mixin.essentials;

import gg.essential.gui.menu.LeftSideBar;
import net.mc_orion.essentialized.Config;
import net.mc_orion.essentialized.EssentializedMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = LeftSideBar.class, remap = false)
public class LeftSideBarMixin {

    @Unique
    private static boolean logged = false;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void removeMonetizationUI(CallbackInfo ci) {
        if (!Config.get().removeMainMenuButtons) return;

        LeftSideBar self = (LeftSideBar) (Object) this;
        int count = self.getChildren().size();
        self.clearChildren();

        if (!logged) {
            logged = true;
            EssentializedMod.LOGGER.info("[LeftSideBarMixin] Cleared {} children from LeftSideBar", count);
        }
    }
}
