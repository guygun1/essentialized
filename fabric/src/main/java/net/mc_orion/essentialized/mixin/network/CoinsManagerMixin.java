package net.mc_orion.essentialized.mixin.network;

import gg.essential.network.connectionmanager.coins.CoinBundle;
import gg.essential.network.connectionmanager.coins.CoinsManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import net.mc_orion.essentialized.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.URI;

@Mixin(value = CoinsManager.class, remap = false)
public class CoinsManagerMixin {

    @Inject(method = "purchaseBundle", at = @At("HEAD"), cancellable = true)
    private void onPurchaseBundle(CoinBundle bundle,
            Function1<? super URI, Unit> callback, CallbackInfo ci) {
        if (!Config.get().blockCoins) return;
        ci.cancel();
    }

    @Inject(method = "tryClaimingWelcomeCoins", at = @At("HEAD"), cancellable = true)
    private void onClaimWelcomeCoins(CallbackInfo ci) {
        if (!Config.get().blockCoins) return;
        ci.cancel();
    }
}
