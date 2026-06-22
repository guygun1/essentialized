package net.mc_orion.essentialshopremover.mixin.gui;

import gg.essential.gui.wardrobe.Item;
import gg.essential.gui.wardrobe.WardrobeState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import net.mc_orion.essentialshopremover.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(value = gg.essential.gui.wardrobe.PurchaseKt.class, remap = false)
public class PurchaseFlowMixin {

    @Inject(method = "purchaseCosmeticOrEmote(Lgg/essential/gui/wardrobe/WardrobeState;Lgg/essential/gui/wardrobe/Item$CosmeticOrEmote;Lkotlin/jvm/functions/Function1;)V",
            at = @At("HEAD"), cancellable = true)
    private static void onPurchaseCosmetic(WardrobeState state, Item.CosmeticOrEmote item,
            Function1<? super Boolean, Unit> callback, CallbackInfo ci) {
        if (!Config.BLOCK_PURCHASES.get()) return;
        callback.invoke(true);
        ci.cancel();
    }

    @Inject(method = "purchaseAndCreateOutfitForBundle", at = @At("HEAD"), cancellable = true)
    private static void onPurchaseBundle(WardrobeState state, Item.Bundle item,
            boolean changeSelectedOutfit, Function1<? super Boolean, Unit> callback, CallbackInfo ci) {
        if (!Config.BLOCK_PURCHASES.get()) return;
        callback.invoke(true);
        ci.cancel();
    }

    @Inject(method = "purchaseSelectedBundle", at = @At("HEAD"), cancellable = true)
    private static void onPurchaseSelectedBundle(WardrobeState state,
            Function1<? super Boolean, Unit> callback, CallbackInfo ci) {
        if (!Config.BLOCK_PURCHASES.get()) return;
        callback.invoke(true);
        ci.cancel();
    }

    @Inject(method = "purchaseEquippedCosmetics", at = @At("HEAD"), cancellable = true)
    private static void onPurchaseEquipped(WardrobeState state,
            Function1<? super Boolean, Unit> callback, CallbackInfo ci) {
        if (!Config.BLOCK_PURCHASES.get()) return;
        callback.invoke(true);
        ci.cancel();
    }

    @Inject(method = "purchaseSelectedEmote", at = @At("HEAD"), cancellable = true)
    private static void onPurchaseSelectedEmote(WardrobeState state,
            Function1<? super Boolean, Unit> callback, CallbackInfo ci) {
        if (!Config.BLOCK_PURCHASES.get()) return;
        callback.invoke(true);
        ci.cancel();
    }

    @Inject(method = "giftCosmeticOrEmote", at = @At("HEAD"), cancellable = true)
    private static void onGiftCosmetic(WardrobeState state, Item.CosmeticOrEmote item,
            UUID giftTo, Function2<? super Boolean, ? super String, Unit> callback, CallbackInfo ci) {
        if (!Config.BLOCK_PURCHASES.get()) return;
        callback.invoke(false, null);
        ci.cancel();
    }

    @Inject(method = "openPurchaseItemModal", at = @At("HEAD"), cancellable = true)
    private static void onOpenPurchaseModal(WardrobeState state, Item item,
            Function0<Unit> primaryAction, CallbackInfo ci) {
        if (!Config.BLOCK_PURCHASES.get()) return;
        primaryAction.invoke();
        ci.cancel();
    }
}
