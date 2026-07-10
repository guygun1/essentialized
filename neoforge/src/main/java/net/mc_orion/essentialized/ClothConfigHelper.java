package net.mc_orion.essentialized;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClothConfigHelper {

    public static Screen createScreen(Screen parent) {
        Config config = Config.get();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("title.essentialized.config"))
                .setSavingRunnable(Config::save);

        ConfigCategory category = builder.getOrCreateCategory(
                Component.translatable("category.essentialized.general"));
        ConfigEntryBuilder entry = builder.entryBuilder();

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialized.blockCosmetics"), config.blockCosmetics)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.blockCosmetics = v)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialized.blockEmotes"), config.blockEmotes)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.blockEmotes = v)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialized.blockPurchases"), config.blockPurchases)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.blockPurchases = v)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialized.blockCoins"), config.blockCoins)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.blockCoins = v)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialized.blockCheckoutPackets"), config.blockCheckoutPackets)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.blockCheckoutPackets = v)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialized.blockNotices"), config.blockNotices)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.blockNotices = v)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialized.blockWardrobeNag"), config.blockWardrobeNag)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.blockWardrobeNag = v)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialized.removeMainMenuButtons"), config.removeMainMenuButtons)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.removeMainMenuButtons = v)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialized.removePauseMenuButtons"), config.removePauseMenuButtons)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.removePauseMenuButtons = v)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialized.removeSocialFeatures"), config.removeSocialFeatures)
                .setDefaultValue(false)
                .setSaveConsumer(v -> config.removeSocialFeatures = v)
                .build());

        return builder.build();
    }
}
