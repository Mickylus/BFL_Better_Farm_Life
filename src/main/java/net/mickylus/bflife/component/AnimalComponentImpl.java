package net.mickylus.bflife.component;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public class AnimalComponentImpl implements AnimalDataComponent, ServerTickingComponent {

    private int hunger;
    private String mood;
    private float production;
    private boolean wild = true;
    private String mother;
    private String father;

    private final Animal animal;

    public AnimalComponentImpl (Animal animal){
        this.animal = animal;
    }

    @Override
    public int getHunger() {
        return hunger;
    }

    @Override
    public String getMood() {
        return mood;
    }

    @Override
    public float getProduction() {
        return production;
    }

    @Override
    public boolean isWild() {
        return wild;
    }

    @Override
    public String getMother() {
        return mother;
    }

    @Override
    public String getFather() {
        return father;
    }

    @Override
    public void setHunger(int value) {
        this.hunger = Math.clamp(value,0,100);
    }

    @Override
    public void setMood(String mood) {
        this.mood = mood;
    }

    @Override
    public void setProduction(float value) {
        this.production = Math.clamp(value,0f,5f);
    }

    @Override
    public void setWildStatus(boolean value) {
        this.wild = value;
    }

    @Override
    public void setMother(String mother) {
        this.mother = mother;
    }

    @Override
    public void setFather(String father) {
        this.father = father;
    }

    @Override
    public void serverTick() {
        if(!wild) {
            if(animal.tickCount % 100 == 0){
                setHunger(hunger -1);
            }
            if(hunger == 0) {
                Level level = animal.level();
                if (level instanceof ServerLevel serverLevel){
                    animal.kill(serverLevel);
                }
            }


        }
    }

    @Override
    public void readData(ValueInput readView) {
        this.hunger = readView.getIntOr("hunger",100);
        this.mood = readView.getStringOr("mood","neutral");
        this.production = readView.getFloatOr("production",1f);
        this.wild = readView.getBooleanOr("wild",true);
        this.mother = readView.getStringOr("mother","Mother");
        this.father = readView.getStringOr("father","Father");
    }

    @Override
    public void writeData(ValueOutput writeView) {
        writeView.putInt("hunger", hunger);
        writeView.putString("mood", mood != null ? mood : "neutral");
        writeView.putFloat("production", production);
        writeView.putBoolean("wild", wild);
        writeView.putString("mother", mother != null ? mother : "");
        writeView.putString("father", father != null ? father : "");
    }
}
