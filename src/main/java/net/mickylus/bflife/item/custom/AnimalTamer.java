package net.mickylus.bflife.item.custom;

import net.mickylus.bflife.ModComponents;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
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
                    player.sendOverlayMessage(
                            Component.literal("Sucessfully tamed animal")
                    );
                    data.setHunger(100);
                    data.setWildStatus(false);
                    return InteractionResult.CONSUME;
                }else{
                    player.sendOverlayMessage(
                            Component.literal("This animal is already tamed")
                    );
                }
            }
        }

        return InteractionResult.PASS;
    }
}
