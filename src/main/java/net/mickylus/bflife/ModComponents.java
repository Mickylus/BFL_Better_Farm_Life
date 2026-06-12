package net.mickylus.bflife;

import net.mickylus.bflife.component.AnimalComponentImpl;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.Animal;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;

public class ModComponents {

    public static final ComponentKey<AnimalDataComponent> ANIMAL_DATA =
            ComponentRegistry.getOrCreate(
                    Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID, "animal_data"),
                    AnimalDataComponent.class
            );

    public static void registerEntityComponents(EntityComponentFactoryRegistry registry) {
        registry.registerFor(Animal.class,ANIMAL_DATA, AnimalComponentImpl::new);
    }

    public static void registerModComponents(){
        BFLBetterFarmLife.LOGGER.info("Registering Mod Components for " + BFLBetterFarmLife.MOD_ID);
    }
}