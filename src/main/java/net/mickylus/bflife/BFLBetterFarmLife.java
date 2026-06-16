package net.mickylus.bflife;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.mickylus.bflife.block.ModBlockEntities;
import net.mickylus.bflife.block.ModBlocks;
import net.mickylus.bflife.creativemodetab.ModCreativeModeTabs;
import net.mickylus.bflife.item.ModItems;
import net.mickylus.bflife.networking.AnimalDataPayload;
import net.mickylus.bflife.screen.ModScreenHandlers;
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
		ModBlockEntities.registerModBlockEntities();

		PayloadTypeRegistry.clientboundPlay().register(AnimalDataPayload.TYPE, AnimalDataPayload.CODEC);

	}
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry){
		ModComponents.registerEntityComponents(registry);
	}
}