package net.mickylus.bflife.item.custom;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.mickylus.bflife.ModComponents;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.mickylus.bflife.networking.AnimalDataPayload;
import net.mickylus.bflife.screen.AnimalScannerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.effects.PlaySoundEffect;

public class AnimalScanner extends Item {
    public AnimalScanner(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(final ItemStack itemStack, final Player player, final LivingEntity target, final InteractionHand type){
        if(target instanceof Animal animal){
            if(!player.level().isClientSide()){
                AnimalDataComponent data = ModComponents.ANIMAL_DATA.get(animal);
                if(data.isWild()){
                    player.sendOverlayMessage(Component.literal("This Animal is wild"));
                    player.level().playSound(
                            null,
                            player.blockPosition(),
                            SoundEvents.PIGLIN_HURT,
                            SoundSource.PLAYERS,
                            1.0f,
                            0.2f
                    );
                } else {
                    // Manda i dati al client
                    ServerPlayNetworking.send(
                            (ServerPlayer) player,
                            new AnimalDataPayload(
                                    data.getHunger(),
                                    data.getMood(),
                                    data.getProduction(),
                                    data.isWild(),
                                    animal.getId()
                            )
                    );
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

}
