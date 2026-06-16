package net.mickylus.bflife.component;

import org.ladysnake.cca.api.v3.component.ComponentV3;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public interface AnimalDataComponent extends ComponentV3, ServerTickingComponent {
    int getHunger();
    String getMood();
    int getProduction();
    boolean isWild();
    int getBaseMultiplier();
    boolean isTracked();
    String getMother();
    String getFather();


    void setHunger(int value);
    void setMood(String mood);
    void setProduction(int value);
    void setWildStatus(boolean value);
    void setBaseMultiplier(int value);
    void setTrackStatus(boolean value);
    void setMother(String mother);
    void setFather(String father);
}
