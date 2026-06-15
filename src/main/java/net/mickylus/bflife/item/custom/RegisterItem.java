package net.mickylus.bflife.item.custom;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuProvider;
import net.mickylus.bflife.ModComponents;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.mickylus.bflife.screen.custom.registeritem.RegisterScreenHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class RegisterItem extends Item {
    public RegisterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack itemStack, Player player, LivingEntity target, InteractionHand hand) {
        if (!(target instanceof Animal animal)) return InteractionResult.PASS;

        if (!player.level().isClientSide()) {
            AnimalDataComponent data = ModComponents.ANIMAL_DATA.get(animal);

            if (data.isWild()) {
                player.sendOverlayMessage(Component.literal("This animal is wild"));
                player.level().playSound(
                        null,
                        player.blockPosition(),
                        SoundEvents.PIGLIN_HURT,
                        SoundSource.PLAYERS,
                        1.0f, 0.2f
                );
            } else {
                RegisterScreenHandler.Data screenData = new RegisterScreenHandler.Data(
                        animal.getId(),
                        data.getHunger(),
                        data.getMood()   != null ? data.getMood()   : "neutral",
                        data.getProduction(),
                        data.isWild(),
                        data.getMother() != null ? data.getMother() : "",
                        data.getFather() != null ? data.getFather() : ""
                );

                ((ServerPlayer) player).openMenu(
                        new ExtendedMenuProvider() {
                            @Override
                            public RegisterScreenHandler.Data getScreenOpeningData(ServerPlayer player) {
                                return screenData;
                            }

                            @Override
                            public Component getDisplayName() {
                                return Component.translatable("screen.bflife.animal_scanner_screen");
                            }

                            @Override
                            public AbstractContainerMenu createMenu(int syncId, Inventory inv, Player p) {
                                return new RegisterScreenHandler(syncId, inv, screenData);
                            }
                        }
                );
            }
        }

        return InteractionResult.SUCCESS;
    }
}
