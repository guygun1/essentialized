package net.mc_orion.essentialized.mixin.essentials;

import gg.essential.elementa.UIComponent;
import gg.essential.gui.menu.RightSideBarNew;
import net.mc_orion.essentialized.Config;
import net.mc_orion.essentialized.EssentializedMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Mixin(value = RightSideBarNew.class, remap = false)
public class FullRightSideBarOldMixin {

    @Unique
    private static boolean logged = false;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void removeMonetizationUI(CallbackInfo ci) {
        Config config = Config.get();
        if (!config.removeMainMenuButtons && !config.removeSocialFeatures) return;

        RightSideBarNew self = (RightSideBarNew) (Object) this;

        if (config.removeSocialFeatures) {
            int count = self.getChildren().size();
            self.clearChildren();
            if (!logged) {
                logged = true;
                EssentializedMod.LOGGER.info("[FullRightSideBarOldMixin] Cleared all {} children (social features disabled)", count);
            }
        } else {
            ArrayList<UIComponent> toRemove = new ArrayList<>();

            resolveAndCollect(self, toRemove, "getToolbarContainer");
            resolveAndCollect(self, toRemove, "getToolbar");
            resolveAndCollect(self, toRemove, "getFullscreenToggleButton");
            resolveAndCollect(self, toRemove, "getSilentModeToggleButton");
            resolveAndCollect(self, toRemove, "getCosmeticVisibilityToggleButton");

            for (UIComponent child : toRemove) {
                self.removeChild(child);
            }
            if (!logged && !toRemove.isEmpty()) {
                logged = true;
                EssentializedMod.LOGGER.info("[FullRightSideBarOldMixin] Removed {} monetization elements, kept social features", toRemove.size());
            }
        }
    }

    @Unique
    private static void resolveAndCollect(RightSideBarNew self, ArrayList<UIComponent> toRemove, String methodName) {
        try {
            Method m = RightSideBarNew.class.getDeclaredMethod(methodName);
            m.setAccessible(true);
            Object value = m.invoke(self);
            if (value instanceof UIComponent comp) {
                toRemove.add(comp);
            }
        } catch (Throwable ignored) {
        }
    }
}
