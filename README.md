# Essential Shop Remover

A client-side NeoForge mod that disables the shop, cosmetics, and emote features from the [Essential mod](https://essential.gg). All features are toggleable via config.

## Features

- **Blocks all purchase flows** -- Cosmetic, emote, bundle, and gift purchases are intercepted and cancelled.
- **Blocks coin purchases** -- Coin bundle purchases and welcome coin claims are prevented.
- **Blocks checkout packets** -- Monetization-related network packets are dropped before they leave the client.
- **Disables cosmetic rendering** -- Shop cosmetics are not rendered on player models.
- **Blocks emote equipping** -- The emote wheel and emote playback are disabled.
- **Removes sale/promo notices** -- Sale banners, cosmetic "new" dots, and promotional toasts are suppressed.
- **Blocks wardrobe nag modals** -- The "unowned items equipped" warning is removed.
- **Coins purchase modal** -- The coin purchase dialog is blocked from opening.
- **Removes menu buttons** -- Essential's buttons are removed from the main menu and pause menu.

## Configuration

All features are controlled via `config/essentialshopremover.json`. Set any option to `false` to re-enable that feature.

```json
{
  "blockCosmetics": true,
  "blockEmotes": true,
  "blockPurchases": true,
  "blockCoins": true,
  "blockCheckoutPackets": true,
  "blockNotices": true,
  "blockWardrobeNag": true,
  "removeMainMenuButtons": true,
  "removePauseMenuButtons": true
}
```

| Option | Default | Description |
|--------|---------|-------------|
| `blockCosmetics` | `true` | Disables cosmetic rendering on player models |
| `blockEmotes` | `true` | Blocks the emote wheel and emote playback |
| `blockPurchases` | `true` | Blocks all cosmetic/emote/bundle purchase flows |
| `blockCoins` | `true` | Blocks coin purchases and welcome coin claims |
| `blockCheckoutPackets` | `true` | Drops monetization network packets |
| `blockNotices` | `true` | Removes sale banners, promo toasts, cosmetic dots |
| `blockWardrobeNag` | `true` | Removes the "unowned items" wardrobe warning |
| `removeMainMenuButtons` | `true` | Removes Essential buttons from the title screen |
| `removePauseMenuButtons` | `true` | Removes Essential buttons from the pause menu |

## How It Works

This mod uses [SpongePowered Mixin](https://github.com/SpongePowered/Mixin) to intercept Essential's methods at runtime. It does **not** modify Essential's JAR file or redistribute any Essential code. All changes are local and client-side only.

## Keybinds

Essential's keybinds (Wardrobe, Emote Wheel, Toggle Cosmetics, etc.) still appear in the Controls menu but have no effect when the corresponding feature is blocked. You can safely unbind them in Minecraft's Controls settings if desired.

## Dependencies

- Minecraft 1.21.1
- NeoForge 21.1.x
- [Essential mod](https://essential.gg) (required at runtime)

## Legal Disclaimer

### Essential Mod

Essential is a closed-source mod by Spark Universe / ModCore Inc. This mod does **not**:
- Include, bundle, or redistribute Essential's code, assets, or JAR
- Modify Essential's JAR file
- Access or alter Essential's servers or account data
- Forge purchases or bypass account entitlements

This mod interacts with Essential solely through Mixin, a standard Minecraft modding mechanism that intercepts method calls at runtime. All modifications are local and client-side. This approach is consistent with standard Minecraft modding practices and fair use principles. No Essential intellectual property is reproduced or distributed.

