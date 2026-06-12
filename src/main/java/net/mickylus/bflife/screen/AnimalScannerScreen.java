package net.mickylus.bflife.screen;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.mickylus.bflife.ModComponents;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.mickylus.bflife.networking.AnimalDataPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.commands.arguments.ColorArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import org.joml.Matrix3x2fStack;
import org.joml.Vector3f;

import java.lang.classfile.instruction.StoreInstruction;

public class AnimalScannerScreen extends Screen {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID, "textures/gui/container/animal_scanner.png");
    // Dimensioni della texture
    private static final int TEXTURE_WIDTH = 176;
    private static final int TEXTURE_HEIGHT = 166;

    // Posizione centrata
    private Animal animal;

    private final AnimalDataPayload data;

    private LivingEntity targetEntity;

    public AnimalScannerScreen(Component title, AnimalDataPayload data) {
        super(title);
        this.data = data;
    }

    @Override
    protected void init() {
        super.init();
        // Recupera l'entità dal mondo client tramite ID
        if (Minecraft.getInstance().level != null) {
            Entity e = Minecraft.getInstance().level.getEntity(data.entityID());
            if (e instanceof LivingEntity living) {
                targetEntity = living;
            }
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        Matrix3x2fStack matrices = graphics.pose();
        matrices.pushMatrix();

        matrices.translate(this.width / 2f, this.height / 2f);
        matrices.translate(-TEXTURE_WIDTH / 2f, -TEXTURE_HEIGHT / 2f);



        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, 0, 0, 0f, 0f,
                TEXTURE_WIDTH, TEXTURE_HEIGHT, 256, 256);

        graphics.text(this.font, "Hunger: " + data.hunger() + "/100", 80, 20, 0XFF0d192b,false);
        graphics.text(this.font, "Mood: " + data.mood(), 80, 36, 0xFF0d192b, false);
        graphics.text(this.font, "Production: " + data.production(), 80, 53, 0xFF0d192b, false);


        // Coordinate assolute dell'area entità sullo schermo
        int guiLeft = (this.width - TEXTURE_WIDTH) / 2;
        int guiTop = (this.height - TEXTURE_HEIGHT) / 2;

// L'area dell'entità parte da 8,8 nella GUI, supponiamo sia 60x80 pixel
        int entityAreaX1 = guiLeft + 1;
        int entityAreaY1 = guiTop + 1;
        int entityAreaX2 = entityAreaX1 + 60; // larghezza area
        int entityAreaY2 = entityAreaY1 + 80; // altezza area

        InventoryScreen.extractEntityInInventoryFollowsMouse(
                graphics,
                entityAreaX1,
                entityAreaY1,
                entityAreaX2,
                entityAreaY2,
                30,   // scala
                0,    // y offset
                mouseX,
                mouseY,
                targetEntity
        );

        matrices.popMatrix();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean isInGameUi() {
        return true;
    }
}
