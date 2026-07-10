package net.mc_orion.essentialized;

import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

public class EssentializedMod {
    public static final String MOD_ID = "essentialized";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        Config.get();
        LOGGER.info("Essentials Essentialized loaded");
    }
}
