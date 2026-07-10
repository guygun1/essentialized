package net.mc_orion.essentialized.platform;

import java.util.ServiceLoader;

public class PlatformServices {
    private static final IPlatformHelper INSTANCE = ServiceLoader.load(IPlatformHelper.class)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No platform helper found"));

    public static IPlatformHelper get() {
        return INSTANCE;
    }
}
