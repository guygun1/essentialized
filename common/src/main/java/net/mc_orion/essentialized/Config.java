package net.mc_orion.essentialized;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Paths.get("config", "essentialized.json");
    private static Config INSTANCE;

    public boolean blockCosmetics = true;
    public boolean blockEmotes = true;
    public boolean blockPurchases = true;
    public boolean blockCoins = true;
    public boolean blockCheckoutPackets = true;
    public boolean blockNotices = true;
    public boolean blockWardrobeNag = true;
    public boolean removeMainMenuButtons = true;
    public boolean removePauseMenuButtons = true;
    public boolean removeSocialFeatures = false;

    public static Config get() {
        if (INSTANCE == null) {
            load();
        }
        return INSTANCE;
    }

    public static void load() {
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                INSTANCE = GSON.fromJson(reader, Config.class);
            } catch (Exception e) {
                EssentializedMod.LOGGER.error("Failed to load config, using defaults", e);
                INSTANCE = new Config();
            }
        } else {
            INSTANCE = new Config();
            save();
        }
    }

    public static void save() {
        try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
            GSON.toJson(INSTANCE, writer);
        } catch (Exception e) {
            EssentializedMod.LOGGER.error("Failed to save config", e);
        }
    }
}
