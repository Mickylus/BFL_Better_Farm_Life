package net.mickylus.bflife.item;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.mickylus.bflife.item.custom.AnimalScanner;
import net.mickylus.bflife.item.custom.AnimalTamer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {

    public static final Item ANIMAL_SCANNER = registerItem("animal_scanner",AnimalScanner::new);
    public static final Item ANIMAL_TAMER = registerItem("animal_tamer", AnimalTamer::new);

    private static Item registerItem(String name, Function<Item.Properties, Item> function){
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID,name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID,name)))));
    }

    public static void registerModItems(){
        BFLBetterFarmLife.LOGGER.info("Registering Mod Items for " + BFLBetterFarmLife.MOD_ID);
    }
}
