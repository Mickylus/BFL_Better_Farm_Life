package net.mickylus.bflife.component;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import org.ladysnake.cca.api.v3.component.ComponentV3;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public interface HungerComponent extends ComponentV3, ServerTickingComponent {
    int getHunger();

    void setHunger(int value);

    void readFromNbt(CompoundTag tag, HolderLookup.Provider lookup);

    void writeToNbt(CompoundTag tag, HolderLookup.Provider lookup);
}
