package net.mc_orion.essentialshopremover;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(EssentialShopRemover.MODID)
public class EssentialShopRemover {
    public static final String MODID = "essentialshopremover";
    public static final Logger LOGGER = LogUtils.getLogger();

    public EssentialShopRemover(net.neoforged.fml.ModContainer container) {
        container.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);

        container.registerExtensionPoint(IConfigScreenFactory.class, (modContainer, parent) -> {
            try {
                Class<?> helper = Class.forName("net.mc_orion.essentialshopremover.ClothConfigHelper");
                return (net.minecraft.client.gui.screens.Screen) helper
                        .getMethod("createScreen", net.minecraft.client.gui.screens.Screen.class)
                        .invoke(null, parent);
            } catch (Throwable t) {
                LOGGER.info("Cloth Config not available, using built-in config screen");
                return new ConfigurationScreen(modContainer, parent);
            }
        });

        LOGGER.info("Essential Shop Remover loaded");
    }
}
