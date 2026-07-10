package net.mc_orion.essentialized;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.bus.api.IEventBus;

@Mod(EssentializedMod.MOD_ID)
public class EssentializedNeoForge {

    public EssentializedNeoForge(IEventBus modEventBus) {
        EssentializedMod.init();

        // Register config screen via Cloth Config (optional dependency)
        try {
            net.neoforged.fml.ModContainer container = net.neoforged.fml.ModLoadingContext.get().getActiveContainer();
            container.registerExtensionPoint(IConfigScreenFactory.class, (modContainer, parent) -> {
                try {
                    Class<?> helper = Class.forName("net.mc_orion.essentialized.ClothConfigHelper");
                    return (net.minecraft.client.gui.screens.Screen) helper
                            .getMethod("createScreen", net.minecraft.client.gui.screens.Screen.class)
                            .invoke(null, parent);
                } catch (Throwable t) {
                    EssentializedMod.LOGGER.info("Cloth Config not available, using built-in config screen");
                    return new net.neoforged.neoforge.client.gui.ConfigurationScreen(modContainer, parent);
                }
            });
        } catch (Throwable t) {
            EssentializedMod.LOGGER.warn("Failed to register config screen", t);
        }
    }
}
