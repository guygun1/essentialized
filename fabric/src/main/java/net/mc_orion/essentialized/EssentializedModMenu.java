package net.mc_orion.essentialized;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.mc_orion.essentialized.EssentializedMod;

public class EssentializedModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // Use reflection to load Cloth Config screen - only works when Cloth Config is installed
            try {
                Class<?> helper = Class.forName("net.mc_orion.essentialized.ClothConfigHelper");
                return (net.minecraft.client.gui.screens.Screen) helper
                        .getMethod("createScreen", net.minecraft.client.gui.screens.Screen.class)
                        .invoke(null, parent);
            } catch (Throwable t) {
                EssentializedMod.LOGGER.info("Cloth Config not available, config screen unavailable");
                return null;
            }
        };
    }
}
