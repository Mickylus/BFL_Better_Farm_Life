package net.mickylus.bflife.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.mickylus.bflife.BFLBetterFarmLife;
import net.mickylus.bflife.block.ModBlocks;
import net.mickylus.bflife.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab BFL_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID,"bfl_tab"),
            FabricCreativeModeTab.builder()
                    .icon( () -> new ItemStack(ModItems.ANIMAL_SCANNER))
                    .title(Component.translatable("creativemodetab.bflife.bfl_tab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.ANIMAL_SCANNER);
                        output.accept(ModItems.ANIMAL_TAMER);
                        output.accept(ModBlocks.SMALL_MANGER);
                        output.accept(ModBlocks.SMALL_FEEDER);
                    }).build());



    public static void registerModCreativeModeTabs(){
        BFLBetterFarmLife.LOGGER.info("Registering Mod Creative Mode Tabs for " + BFLBetterFarmLife.MOD_ID);
    }
}
