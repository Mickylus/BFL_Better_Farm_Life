package net.mickylus.bflife;

import net.fabricmc.api.ClientModInitializer;
import net.mickylus.bflife.screen.ModScreenHandlers;
import net.mickylus.bflife.screen.custom.registeritem.RegisterScreen;
import net.minecraft.client.gui.screens.MenuScreens;

public class BFLBetterFarmLifeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModScreenHandlers.REGISTER_SCREEN, RegisterScreen::new);
    }
}