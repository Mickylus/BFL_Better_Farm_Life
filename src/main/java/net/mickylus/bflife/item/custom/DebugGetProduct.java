package net.mickylus.bflife.item.custom;

import net.mickylus.bflife.ModComponents;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.chicken.Chicken;
import net.minecraft.world.entity.animal.cow.Cow;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class DebugGetProduct extends Item {
    public DebugGetProduct(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(final ItemStack itemStack, final Player player, final LivingEntity target, final InteractionHand type) {
        if(!player.level().isClientSide()){
            if(target instanceof Animal animal) {
                AnimalDataComponent data = ModComponents.ANIMAL_DATA.get(animal);

                if(!data.isWild()){
                    if(player.isCrouching()){
                        if(data.getProduction()>3){
                            data.setProduction(1);
                            player.sendOverlayMessage(
                                    Component.literal("§5DEBUG: §7 Production §8 -> §b 1 ")
                            );
                        }else {
                            data.setProduction(data.getProduction()+1);
                            player.sendOverlayMessage(
                                    Component.literal("§5DEBUG: §7 Production §8 -> §b " + data.getProduction())
                            );
                        }
                        return  InteractionResult.SUCCESS;
                    }else{
                        dropProducts(animal,data,player);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    private void dropProducts(Animal targetEntity, AnimalDataComponent data, Player player){
        if(targetEntity instanceof Chicken chicken) {
            int count = data.getBaseMultiplier() * data.getProduction();

            ItemStack eggs_product = new ItemStack(Items.EGG, count + 1);
            chicken.drop(eggs_product, true, false);

            ItemStack chicken_product = new ItemStack(Items.CHICKEN, count);
            chicken.drop(chicken_product, true, false);

            player.sendOverlayMessage(
                    Component.literal("§5DEBUG: §7 Dropped §9 " + (count + 1) + "§6 §fEgg §7 , §9 " + count + "§6 §fRaw Chicken")
            );
        }

        if(targetEntity instanceof Pig pig){
            int count = data.getBaseMultiplier() * data.getProduction();

            ItemStack porkchop_product = new ItemStack(Items.PORKCHOP, count);
            pig.drop(porkchop_product, true, false);

            player.sendOverlayMessage(
                    Component.literal("§5DEBUG: §7 Dropped §9 " + count + "§6 §fRaw Porkchop")
            );
        }
        if(targetEntity instanceof Cow cow){
            int count = data.getBaseMultiplier() * data.getProduction();

            ItemStack milk_product = new ItemStack(Items.MILK_BUCKET, count - 1);
            cow.drop(milk_product, true, false);

            ItemStack leather_product = new ItemStack(Items.LEATHER, count + 1);
            cow.drop(leather_product, true, false);

            ItemStack beef_product = new ItemStack(Items.BEEF, count);
            cow.drop(beef_product, true, false);

            player.sendOverlayMessage(
                    Component.literal("§5DEBUG: §7 Dropped §9 " + (count - 1)  + "§6 §fMilk Bucket §7 , §9 " + (count + 1) + "§6 §fLeather §7 , §9 " + count + "§6 §fRaw Beef")
            );
        }
        if(targetEntity instanceof Sheep sheep) {
            int count = data.getBaseMultiplier() * data.getProduction();

            ItemStack wool_product = new ItemStack(Items.EGG,count);
            sheep.drop(wool_product, true, false);

            ItemStack mutton_product = new ItemStack(Items.CHICKEN, count + 1);
            sheep.drop(mutton_product, true, false);

            player.sendOverlayMessage(
                    Component.literal("§5DEBUG: §7 Dropped §9 " + (count + 1) + "§6 §fWool Block §7 , §9 " + count + "§6 §fRaw Mutton")
            );
        }
    }

}

