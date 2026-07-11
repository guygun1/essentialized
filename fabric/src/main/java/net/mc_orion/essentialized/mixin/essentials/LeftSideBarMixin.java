package net.mc_orion.essentialized.mixin.essentials;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.constraints.ConstantColorConstraint;
import gg.essential.elementa.constraints.PixelConstraint;
import gg.essential.elementa.state.BasicState;
import gg.essential.gui.menu.LeftSideBar;
import net.mc_orion.essentialized.Config;
import net.mc_orion.essentialized.EssentializedMod;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Mixin(value = LeftSideBar.class, remap = false)
public class LeftSideBarMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void removeLeftSidebarButtons(CallbackInfo ci) {
        if (!Config.get().removeMainMenuButtons) return;
        
        LeftSideBar self = (LeftSideBar) (Object) this;
        
        Minecraft.getInstance().tell(() -> {
            try {
                List<UIComponent> wardrobeButtons = new ArrayList<>();
                
                // Method 1: getWardrobeButton() method
                try {
                    Method m = LeftSideBar.class.getDeclaredMethod("getWardrobeButton");
                    m.setAccessible(true);
                    Object value = m.invoke(self);
                    if (value instanceof UIComponent component) {
                        wardrobeButtons.add(component);
                    }
                } catch (Exception ignored) {}
                
                // Method 2: Direct field access for the delegate pattern
                try {
                    Field wardrobeField = LeftSideBar.class.getDeclaredField("wardrobeButton$delegate");
                    wardrobeField.setAccessible(true);
                    Object delegate = wardrobeField.get(self);
                    if (delegate != null) {
                        Field menuButtonField = delegate.getClass().getDeclaredField("menuButton");
                        menuButtonField.setAccessible(true);
                        Object menuButton = menuButtonField.get(delegate);
                        if (menuButton instanceof UIComponent component) {
                            wardrobeButtons.add(component);
                        }
                    }
                } catch (Exception ignored) {}
                
                // Method 3: Search through all fields for wardrobe-related components
                Field[] fields = LeftSideBar.class.getDeclaredFields();
                for (Field field : fields) {
                    String fieldName = field.getName();
                    if (fieldName.contains("wardrobe")) {
                        field.setAccessible(true);
                        Object value = field.get(self);
                        if (value instanceof UIComponent component) {
                            wardrobeButtons.add(component);
                        }
                    }
                }
                
                // Collapse all found wardrobe buttons
                for (UIComponent component : wardrobeButtons) {
                    collapse(component);
                }
                
                if (!wardrobeButtons.isEmpty()) {
                    EssentializedMod.LOGGER.info("[LeftSideBarMixin] Collapsed {} wardrobe button(s) from top-left corner", wardrobeButtons.size());
                }
                
            } catch (Throwable e) {
                EssentializedMod.LOGGER.error("[LeftSideBarMixin] Failed", e);
            }
        });
    }

    @Unique
    private static void collapse(UIComponent component) {
        try {
            BasicState<Float> zeroSize = new BasicState<>(0f);
            BasicState<Boolean> falseState = new BasicState<>(false);
            PixelConstraint zc = new PixelConstraint(zeroSize, falseState, falseState);
            component.setWidth(zc);
            component.setHeight(zc);
            
            ConstantColorConstraint transparent = new ConstantColorConstraint(new Color(0, 0, 0, 0));
            component.setColor(transparent);
            
            for (UIComponent child : new ArrayList<>(component.getChildren())) {
                collapse(child);
            }
        } catch (Throwable ignored) {}
    }
}