package net.mickylus.bflife.screen;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.mickylus.bflife.BFLBetterFarmLife;
import net.mickylus.bflife.screen.custom.registeritem.RegisterScreenHandler;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlers {


    public static final MenuType<RegisterScreenHandler> REGISTER_SCREEN =
            Registry.register(
                    BuiltInRegistries.MENU,
                    Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID, "register"),
                    new ExtendedMenuType<>(RegisterScreenHandler::new, RegisterScreenHandler.Data.STREAM_CODEC)
            );


    public static void registerModScreenHandlers() {
        BFLBetterFarmLife.LOGGER.info("Registering Mod Screen Handlers for " + BFLBetterFarmLife.MOD_ID);
    }
}