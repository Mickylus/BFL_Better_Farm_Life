package net.mickylus.bflife.item.custom;

import net.mickylus.bflife.ModComponents;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class AnimalTamer extends Item {
    public AnimalTamer(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(final ItemStack itemStack, final Player player, final LivingEntity target, final InteractionHand type) {
        if(!player.level().isClientSide()){
            if(target instanceof Animal animal){
                AnimalDataComponent data = ModComponents.ANIMAL_DATA.get(animal);

                if(data.isWild()){
                    setBaseValues(animal,data);
                    player.sendOverlayMessage(
                            Component.literal("Sucessfully tamed animal")
                    );
                    itemStack.consume(1,player);
                    return InteractionResult.SUCCESS;
                }else{
                    player.sendOverlayMessage(
                            Component.literal("This animal is already tamed")
                    );
                }
            }
        }

        return InteractionResult.PASS;
    }
    private void setBaseValues(Animal animal, AnimalDataComponent data){
        data.setWildStatus(false);
        data.setHunger(100);
        data.setMood("Neutral");
        if(animal.is(EntityType.PIG)){
            data.setBaseMultiplier(3);
        }
        if(animal.is(EntityType.CHICKEN)){
            data.setBaseMultiplier(2);
        }
        if(animal.is(EntityType.COW)){
            data.setBaseMultiplier(1);
        }
        if(animal.is(EntityType.SHEEP)){
            data.setBaseMultiplier(3);
        }
    }
}
