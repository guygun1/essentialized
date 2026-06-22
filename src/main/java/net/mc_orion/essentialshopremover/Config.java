package net.mc_orion.essentialshopremover;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue BLOCK_COSMETICS = BUILDER
            .comment("Block cosmetic rendering from Essential")
            .define("blockCosmetics", true);

    public static final ModConfigSpec.BooleanValue BLOCK_EMOTES = BUILDER
            .comment("Block emote equipping from Essential")
            .define("blockEmotes", true);

    public static final ModConfigSpec.BooleanValue BLOCK_PURCHASES = BUILDER
            .comment("Block all purchase and gift flows in the wardrobe")
            .define("blockPurchases", true);

    public static final ModConfigSpec.BooleanValue BLOCK_COINS = BUILDER
            .comment("Block coin purchases and welcome coins")
            .define("blockCoins", true);

    public static final ModConfigSpec.BooleanValue BLOCK_CHECKOUT_PACKETS = BUILDER
            .comment("Drop monetization packets sent to Essential servers")
            .define("blockCheckoutPackets", true);

    public static final ModConfigSpec.BooleanValue BLOCK_NOTICES = BUILDER
            .comment("Block cosmetic and sale notices from Essential")
            .define("blockNotices", true);

    public static final ModConfigSpec.BooleanValue BLOCK_WARDROBE_NAG = BUILDER
            .comment("Block wardrobe upsell prompts")
            .define("blockWardrobeNag", true);

    public static final ModConfigSpec.BooleanValue REMOVE_MAIN_MENU_BUTTONS = BUILDER
            .comment("Remove Essential monetization buttons from the main menu")
            .define("removeMainMenuButtons", true);

    public static final ModConfigSpec.BooleanValue REMOVE_PAUSE_MENU_BUTTONS = BUILDER
            .comment("Remove Essential monetization buttons from the pause menu")
            .define("removePauseMenuButtons", true);

    public static final ModConfigSpec.BooleanValue REMOVE_SOCIAL_FEATURES = BUILDER
            .comment("Remove Essential social features (chat, friends, world hosting, invite)")
            .define("removeSocialFeatures", false);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
