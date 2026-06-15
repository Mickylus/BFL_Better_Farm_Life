package net.mickylus.bflife;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.mickylus.bflife.item.ModItems;
import net.mickylus.bflife.networking.SwitchModePayload;
import net.mickylus.bflife.screen.ModScreenHandlers;
import net.mickylus.bflife.screen.custom.registeritem.RegisterScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class BFLBetterFarmLifeClient implements ClientModInitializer {
    private static final KeyMapping.Category BFL_CATEGORY =
            KeyMapping.Category.register(
                    Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID, "general")
            );

    public static KeyMapping SWITCH_MODE_KEY;

    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModScreenHandlers.REGISTER_SCREEN, RegisterScreen::new);

        SWITCH_MODE_KEY = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.bflife.switch_mode",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_ALT,
                BFL_CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            if (!client.player.isHolding(ModItems.CONTROL_STAFF)) return;

            while (SWITCH_MODE_KEY.consumeClick()) {
                ClientPlayNetworking.send(new SwitchModePayload());
            }
        });
    }
}