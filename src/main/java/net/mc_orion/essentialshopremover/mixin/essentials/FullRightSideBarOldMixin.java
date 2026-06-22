package net.mc_orion.essentialshopremover.mixin.essentials;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.components.UIContainer;
import gg.essential.gui.menu.full.FullRightSideBarOld;
import net.mc_orion.essentialshopremover.Config;
import net.mc_orion.essentialshopremover.EssentialShopRemover;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Mixin(value = FullRightSideBarOld.class, remap = false)
public class FullRightSideBarOldMixin {

    @Unique
    private static boolean logged = false;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void removeMonetizationUI(CallbackInfo ci) {
        if (!Config.REMOVE_MAIN_MENU_BUTTONS.get() && !Config.REMOVE_SOCIAL_FEATURES.get()) return;

        FullRightSideBarOld self = (FullRightSideBarOld) (Object) this;

        if (Config.REMOVE_SOCIAL_FEATURES.get()) {
            int count = self.getChildren().size();
            self.clearChildren();
            if (!logged) {
                logged = true;
                EssentialShopRemover.LOGGER.info("[FullRightSideBarOldMixin] Cleared all {} children (social features disabled)", count);
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
                EssentialShopRemover.LOGGER.info("[FullRightSideBarOldMixin] Removed {} monetization elements, kept social features", toRemove.size());
            }
        }
    }

    @Unique
    private static void resolveAndCollect(FullRightSideBarOld self, ArrayList<UIComponent> toRemove, String methodName) {
        try {
            Method m = FullRightSideBarOld.class.getDeclaredMethod(methodName);
            m.setAccessible(true);
            Object value = m.invoke(self);
            if (value instanceof UIComponent comp) {
                toRemove.add(comp);
            }
        } catch (Throwable ignored) {
        }
    }
}
