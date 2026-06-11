package net.mickylus.bflife;

import net.fabricmc.api.ModInitializer;

import net.mickylus.bflife.block.ModBlocks;
import net.mickylus.bflife.creativemodetab.ModCreativeModeTabs;
import net.mickylus.bflife.item.ModItems;
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
	}
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		ModComponents.registerEntityComponents(registry);
	}
}