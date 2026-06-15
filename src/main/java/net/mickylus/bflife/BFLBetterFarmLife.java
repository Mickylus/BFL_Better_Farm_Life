package net.mickylus.bflife;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.mickylus.bflife.block.ModBlocks;
import net.mickylus.bflife.creativemodetab.ModCreativeModeTabs;
import net.mickylus.bflife.item.ModItems;
import net.mickylus.bflife.item.custom.ControlStaff;
import net.mickylus.bflife.networking.AnimalDataPayload;
import net.mickylus.bflife.networking.SwitchModePayload;
import net.mickylus.bflife.screen.ModScreenHandlers;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.item.ItemStack;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BFLBetterFarmLife implements ModInitializer, EntityComponentInitializer {
	public static final String MOD_ID = "bflife";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModComponents.registerModComponents();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModScreenHandlers.registerModScreenHandlers();

		PayloadTypeRegistry.clientboundPlay().register(AnimalDataPayload.TYPE, AnimalDataPayload.CODEC);

		PayloadTypeRegistry.serverboundPlay().register(SwitchModePayload.TYPE, SwitchModePayload.CODEC);


		ServerPlayNetworking.registerGlobalReceiver(SwitchModePayload.TYPE, (payload, context) -> {
			ServerPlayer player = context.player();
			context.server().execute(() -> {
				ItemStack stack = player.getMainHandItem();
				if (stack.is(ModItems.CONTROL_STAFF)) {
					ControlStaff.cycleMode(stack);

					int newMode = ControlStaff.getMode(stack);
					String modeName = newMode == 0 ? "§aMove" : "§bFollow";
					player.sendSystemMessage(Component.literal("§6[BFL] Mode: " + modeName));
				}
			});
		});
	}
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry){
		ModComponents.registerEntityComponents(registry);
	}
}