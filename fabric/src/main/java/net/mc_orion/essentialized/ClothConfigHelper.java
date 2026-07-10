package net.mc_orion.essentialized;

import java.lang.reflect.Method;

public class ClothConfigHelper {

    public static Object createScreen(Object parent) {
        try {
            Config config = Config.get();

            Class<?> configBuilderClass = Class.forName("me.shedaniel.clothconfig2.api.ConfigBuilder");
            Class<?> componentClass = Class.forName("net.minecraft.network.chat.Component");

            Method translatable = componentClass.getMethod("translatable", String.class);
            Object title = translatable.invoke(null, "title.essentialized.config");
            Object categoryTitle = translatable.invoke(null, "category.essentialized.general");

            Method create = configBuilderClass.getMethod("create");
            Object builder = create.invoke(null);

            Method setParentScreen = configBuilderClass.getMethod("setParentScreen", Class.forName("net.minecraft.client.gui.screens.Screen"));
            builder = setParentScreen.invoke(builder, parent);

            Method setTitle = configBuilderClass.getMethod("setTitle", componentClass);
            builder = setTitle.invoke(builder, title);

            Class<?> runnableClass = Class.forName("java.lang.Runnable");
            Method setSavingRunnable = configBuilderClass.getMethod("setSavingRunnable", runnableClass);
            builder = setSavingRunnable.invoke(builder, (Runnable) Config::save);

            Method getOrCreateCategory = configBuilderClass.getMethod("getOrCreateCategory", componentClass);
            Object category = getOrCreateCategory.invoke(builder, categoryTitle);

            Class<?> entryBuilderClass = Class.forName("me.shedaniel.clothconfig2.api.ConfigEntryBuilder");
            Method getEntryBuilder = configBuilderClass.getMethod("entryBuilder");
            Object entry = getEntryBuilder.invoke(builder);

            Method addEntry = Class.forName("me.shedaniel.clothconfig2.api.ConfigCategory").getMethod("addEntry", Class.forName("me.shedaniel.clothconfig2.api.ConfigEntry"));

            Class<?> booleanToggleBuilderClass = Class.forName("me.shedaniel.clothconfig2.impl.builders.BooleanToggleBuilder");

            addBooleanToggle(addEntry, category, entry, translatable, booleanToggleBuilderClass,
                    "option.essentialized.blockCosmetics", config.blockCosmetics, true,
                    v -> config.blockCosmetics = v);
            addBooleanToggle(addEntry, category, entry, translatable, booleanToggleBuilderClass,
                    "option.essentialized.blockEmotes", config.blockEmotes, true,
                    v -> config.blockEmotes = v);
            addBooleanToggle(addEntry, category, entry, translatable, booleanToggleBuilderClass,
                    "option.essentialized.blockPurchases", config.blockPurchases, true,
                    v -> config.blockPurchases = v);
            addBooleanToggle(addEntry, category, entry, translatable, booleanToggleBuilderClass,
                    "option.essentialized.blockCoins", config.blockCoins, true,
                    v -> config.blockCoins = v);
            addBooleanToggle(addEntry, category, entry, translatable, booleanToggleBuilderClass,
                    "option.essentialized.blockCheckoutPackets", config.blockCheckoutPackets, true,
                    v -> config.blockCheckoutPackets = v);
            addBooleanToggle(addEntry, category, entry, translatable, booleanToggleBuilderClass,
                    "option.essentialized.blockNotices", config.blockNotices, true,
                    v -> config.blockNotices = v);
            addBooleanToggle(addEntry, category, entry, translatable, booleanToggleBuilderClass,
                    "option.essentialized.blockWardrobeNag", config.blockWardrobeNag, true,
                    v -> config.blockWardrobeNag = v);
            addBooleanToggle(addEntry, category, entry, translatable, booleanToggleBuilderClass,
                    "option.essentialized.removeMainMenuButtons", config.removeMainMenuButtons, true,
                    v -> config.removeMainMenuButtons = v);
            addBooleanToggle(addEntry, category, entry, translatable, booleanToggleBuilderClass,
                    "option.essentialized.removePauseMenuButtons", config.removePauseMenuButtons, true,
                    v -> config.removePauseMenuButtons = v);
            addBooleanToggle(addEntry, category, entry, translatable, booleanToggleBuilderClass,
                    "option.essentialized.removeSocialFeatures", config.removeSocialFeatures, false,
                    v -> config.removeSocialFeatures = v);

            Method build = configBuilderClass.getMethod("build");
            return build.invoke(builder);
        } catch (Throwable t) {
            EssentializedMod.LOGGER.error("Failed to create Cloth Config screen", t);
            return null;
        }
    }

    private static void addBooleanToggle(Method addEntry, Object category, Object entry,
            Method translatable, Class<?> booleanToggleBuilderClass,
            String key, boolean currentValue, boolean defaultValue,
            java.util.function.Consumer<Boolean> saveConsumer) throws Throwable {
        Object label = translatable.invoke(null, key);
        Method startBooleanToggle = entry.getClass().getMethod("startBooleanToggle",
                Class.forName("net.minecraft.network.chat.Component"), boolean.class);
        Object builder = startBooleanToggle.invoke(entry, label, currentValue);

        Method setDefaultValue = booleanToggleBuilderClass.getMethod("setDefaultValue", boolean.class);
        builder = setDefaultValue.invoke(builder, defaultValue);

        Class<?> consumerClass = Class.forName("java.util.function.Consumer");
        Method setSaveConsumer = booleanToggleBuilderClass.getMethod("setSaveConsumer", consumerClass);
        builder = setSaveConsumer.invoke(builder, saveConsumer);

        Method buildMethod = booleanToggleBuilderClass.getMethod("build");
        Object configEntry = buildMethod.invoke(builder);

        addEntry.invoke(category, configEntry);
    }
}
