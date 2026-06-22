package net.mc_orion.essentialshopremover;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ClothConfigHelper {

    public static Screen createScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("title.essentialshopremover.config"))
                .setSavingRunnable(() -> Config.SPEC.save());

        ConfigCategory category = builder.getOrCreateCategory(
                Component.translatable("category.essentialshopremover.general"));
        ConfigEntryBuilder entry = builder.entryBuilder();

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialshopremover.blockCosmetics"), Config.BLOCK_COSMETICS.get())
                .setDefaultValue(true)
                .setSaveConsumer(Config.BLOCK_COSMETICS::set)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialshopremover.blockEmotes"), Config.BLOCK_EMOTES.get())
                .setDefaultValue(true)
                .setSaveConsumer(Config.BLOCK_EMOTES::set)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialshopremover.blockPurchases"), Config.BLOCK_PURCHASES.get())
                .setDefaultValue(true)
                .setSaveConsumer(Config.BLOCK_PURCHASES::set)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialshopremover.blockCoins"), Config.BLOCK_COINS.get())
                .setDefaultValue(true)
                .setSaveConsumer(Config.BLOCK_COINS::set)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialshopremover.blockCheckoutPackets"), Config.BLOCK_CHECKOUT_PACKETS.get())
                .setDefaultValue(true)
                .setSaveConsumer(Config.BLOCK_CHECKOUT_PACKETS::set)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialshopremover.blockNotices"), Config.BLOCK_NOTICES.get())
                .setDefaultValue(true)
                .setSaveConsumer(Config.BLOCK_NOTICES::set)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialshopremover.blockWardrobeNag"), Config.BLOCK_WARDROBE_NAG.get())
                .setDefaultValue(true)
                .setSaveConsumer(Config.BLOCK_WARDROBE_NAG::set)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialshopremover.removeMainMenuButtons"), Config.REMOVE_MAIN_MENU_BUTTONS.get())
                .setDefaultValue(true)
                .setSaveConsumer(Config.REMOVE_MAIN_MENU_BUTTONS::set)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialshopremover.removePauseMenuButtons"), Config.REMOVE_PAUSE_MENU_BUTTONS.get())
                .setDefaultValue(true)
                .setSaveConsumer(Config.REMOVE_PAUSE_MENU_BUTTONS::set)
                .build());

        category.addEntry(entry.startBooleanToggle(
                        Component.translatable("option.essentialshopremover.removeSocialFeatures"), Config.REMOVE_SOCIAL_FEATURES.get())
                .setDefaultValue(false)
                .setSaveConsumer(Config.REMOVE_SOCIAL_FEATURES::set)
                .build());

        return builder.build();
    }
}
