package net.mickylus.bflife;

import net.mickylus.bflife.component.HungerComponent;
import net.mickylus.bflife.component.HungerComponentImpl;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.pig.Pig;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;

public class ModComponents {

    public static final ComponentKey<HungerComponent> HUNGER =
            ComponentRegistry.getOrCreate(
                    Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID, "hunger"),
                    HungerComponent.class
            );

    public static void registerEntityComponents(EntityComponentFactoryRegistry registry) {
        registry.registerFor(Pig.class,HUNGER,HungerComponentImpl::new);
        registry.registerFor(Cow.class,HUNGER,HungerComponentImpl::new);
    }

    public static void registerModComponents(){
        BFLBetterFarmLife.LOGGER.info("Registering Mod Components for " + BFLBetterFarmLife.MOD_ID);
    }
}