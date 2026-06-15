package net.mickylus.bflife.item.custom;

import net.mickylus.bflife.ModComponents;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ControlStaff extends Item {

    private static final Map<UUID, Animal> selectedAnimals = new HashMap<>();

    private int mode = 0;
    // Modes:
    // 0 - Move
    // 1 - Follow

    public ControlStaff(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(final ItemStack itemStack, final Player player, final LivingEntity target, final InteractionHand type){
        if(!player.level().isClientSide()){
            if(target instanceof Animal animal){
                AnimalDataComponent data = ModComponents.ANIMAL_DATA.get(animal);

                if(data.isWild()){
                    player.sendOverlayMessage(
                            Component.literal("This animal is wild!")
                    );
                }else{
                    mode = getMode(itemStack);
                    selectedAnimals.put(player.getUUID(),animal);
                    player.sendOverlayMessage(Component.literal(
                           animal.getPlainTextName() + " selected"
                    ));

                    if(mode == 0){
                        player.sendOverlayMessage(Component.literal(
                                "Select Destination"
                        ));
                    }

                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult useOn(final UseOnContext context) {
        Player player = context.getPlayer();
        Level level  = context.getLevel();

        if (player == null || level.isClientSide()) return InteractionResult.PASS;

        Animal animal = selectedAnimals.get(player.getUUID());

        if (animal == null || !animal.isAlive()) {
            player.sendOverlayMessage(Component.literal("No entity selected!"));
            return InteractionResult.FAIL;
        }

        BlockPos target = context.getClickedPos().above();
        Vec3 destination = Vec3.atCenterOf(target);

        animal.getNavigation().moveTo(
                destination.x,
                destination.y,
                destination.z,
                1.2D
        );

        player.sendOverlayMessage(Component.literal(
                "Destination: §c" + destination.x + " " + destination.y + " " + destination.z
        ));

        selectedAnimals.remove(player.getUUID());

        return InteractionResult.SUCCESS;

    }

    public static int getMode(ItemStack stack) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        return data.copyTag().getIntOr("mode",0); // default 0
    }

    public static void cycleMode(ItemStack stack) {
        CustomData data = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = data.copyTag();
        int current = tag.getIntOr("mode",0);
        tag.putInt("mode", (current + 1) % 2); // 0 → 1 → 0
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
    }
}
