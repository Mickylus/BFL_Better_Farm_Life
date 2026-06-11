package net.mickylus.bflife.component;

import org.ladysnake.cca.api.v3.component.ComponentV3;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public interface AnimalDataComponent extends ComponentV3, ServerTickingComponent {
    int getHunger();
    String getMood();
    float getProduction();
    boolean isWild();
    String getMother();
    String getFather();

    void setHunger(int value);
    void setMood(String mood);
    void setProduction(float value);
    void setWildStatus(boolean value);
    void setMother(String mother);
    void setFather(String father);
}
