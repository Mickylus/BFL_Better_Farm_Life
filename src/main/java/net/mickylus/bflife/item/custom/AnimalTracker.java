package net.mickylus.bflife.item.custom;

import net.mickylus.bflife.ModComponents;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class AnimalTracker extends Item {
    public AnimalTracker(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity target, InteractionHand type) {
        if(!player.level().isClientSide()){
            if(target instanceof Animal animal){
                AnimalDataComponent data = ModComponents.ANIMAL_DATA.getNullable(animal);

                if(!data.isWild() && !data.isTracked()){
                    data.setTrackStatus(true);
                    Level level = player.level();

                    level.addParticle(ParticleTypes.FLAME,animal.getX()+0.5f,animal.getY()+1f,animal.getZ()+0.5f,0,1,0);
                    player.sendOverlayMessage(Component.literal(
                            "The animal is being tracked!"
                    ));
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }
}
