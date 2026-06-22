package net.mc_orion.essentialshopremover.mixin.gui;

import net.mc_orion.essentialshopremover.Config;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin extends Screen {

    protected PauseScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void removeEssentialButtons(CallbackInfo ci) {
        boolean removeMonetization = Config.REMOVE_PAUSE_MENU_BUTTONS.get();
        boolean removeSocial = Config.REMOVE_SOCIAL_FEATURES.get();
        if (!removeMonetization && !removeSocial) return;

        new ArrayList<>(this.children()).stream()
                .filter(child -> child instanceof AbstractButton)
                .map(child -> (AbstractButton) child)
                .filter(button -> isMonetizationButton(button, removeSocial))
                .forEach(this::removeWidget);
    }

    @Unique
    private static boolean isMonetizationButton(AbstractButton button, boolean includeSocial) {
        try {
            String text = button.getMessage().getString().toLowerCase();
            boolean isSocial = text.contains("social") || text.contains("invite");
            if (isSocial) return includeSocial;

            String className = button.getClass().getName();
            if (className.contains("essential") || className.contains("gg.essential")) {
                if (text.contains("wardrobe") || text.contains("pictures")) return true;
                return includeSocial && (text.contains("chat") || text.contains("friends"));
            }
        } catch (Exception e) {
        }
        return false;
    }
}
