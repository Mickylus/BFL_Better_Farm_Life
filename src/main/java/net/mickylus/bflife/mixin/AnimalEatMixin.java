package net.mickylus.bflife.mixin;

import net.mickylus.bflife.ModComponents;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Animal.class)
public class AnimalEatMixin {
    @Inject(at = @At("HEAD"), method = "mobInteract")
    private void onMobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        Animal animal = (Animal)(Object)this;
        ItemStack itemInHand = player.getItemInHand(hand);
        if(animal.is(EntityType.PIG) || animal.is(EntityType.COW) || animal.is(EntityType.SHEEP) || animal.is(EntityType.CHICKEN))
            if (!animal.level().isClientSide() && animal.isFood(itemInHand)) {
                AnimalDataComponent data = ModComponents.ANIMAL_DATA.getNullable(animal);
                if (data != null && !data.isWild()) {
                    data.setHunger(Math.min(data.getHunger() + 20, 100));
                }
            }
    }
}
