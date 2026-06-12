package net.mickylus.bflife;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.mickylus.bflife.networking.AnimalDataPayload;
import net.mickylus.bflife.screen.AnimalScannerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class BFLBetterFarmLifeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(AnimalDataPayload.TYPE, (payload, context) -> {
            context.client().execute(() -> {
                Minecraft.getInstance().setScreen(
                        new AnimalScannerScreen(
                                Component.translatable("screen.bflife.animal_scanner_screen"),
                                payload
                        )
                );
            });
        });
    }
}