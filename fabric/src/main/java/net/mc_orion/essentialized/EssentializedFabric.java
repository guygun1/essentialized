package net.mc_orion.essentialized;

import net.fabricmc.api.ClientModInitializer;

public class EssentializedFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EssentializedMod.init();
    }
}
