package net.mickylus.bflife.component;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

import java.util.Objects;

public class HungerComponentImpl implements HungerComponent, ServerTickingComponent {

    private int hunger = 100;

    private final Animal animal;

    public HungerComponentImpl(Animal animal) {
        this.animal = animal;
    }

    @Override
    public int getHunger() { return hunger; }

    @Override
    public void setHunger(int value) {
        this.hunger = Math.clamp(value, 0, 100);
    }

    @Override
    public void serverTick() {

        if (animal.tickCount % 100 == 0) {
            setHunger(hunger - 10);
        }
        if (hunger <= 0) {
            BFLBetterFarmLife.LOGGER.info("Maiale " + animal.getId() + "è morto di fame");
            animal.die(animal.damageSources().starve());
            animal.remove(Entity.RemovalReason.KILLED);
        }
    }

    @Override
    public void readFromNbt(CompoundTag tag, HolderLookup.Provider lookup) {
        hunger = tag.getInt("hunger").orElse(100);
    }

    @Override
    public void writeToNbt(CompoundTag tag, HolderLookup.Provider lookup) {
        tag.putInt("hunger", hunger);
    }

    @Override
    public void readData(ValueInput readView) {

    }

    @Override
    public void writeData(ValueOutput writeView) {

    }
}