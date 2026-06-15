package net.mickylus.bflife.item;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.mickylus.bflife.item.custom.AnimalTamer;
import net.mickylus.bflife.item.custom.ControlStaff;
import net.mickylus.bflife.item.custom.DebugGetProduct;
import net.mickylus.bflife.item.custom.RegisterItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class ModItems {

    public static final Item ANIMAL_TAMER = registerItem("animal_tamer", AnimalTamer::new);
    public static final Item REGISTER = registerItem("register", RegisterItem::new);
    public static final Item MOD_BOOK = registerItem("mod_book", Item::new);
    public static final Item DEBUG_PRODUCT = registerItem("test_get_product", DebugGetProduct::new);
    public static final Item CONTROL_STAFF = registerItem("control_staff", ControlStaff::new);

    private static Item registerItem(String name, Function<Item.Properties, Item> function){
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID,name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM,Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID,name)))));
    }

    public static void registerModItems(){
        BFLBetterFarmLife.LOGGER.info("Registering Mod Items for " + BFLBetterFarmLife.MOD_ID);
    }
}
