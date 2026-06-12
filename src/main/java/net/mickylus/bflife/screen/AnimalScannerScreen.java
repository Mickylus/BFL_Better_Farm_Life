package net.mickylus.bflife.screen;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.mickylus.bflife.ModComponents;
import net.mickylus.bflife.component.AnimalDataComponent;
import net.mickylus.bflife.networking.AnimalDataPayload;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.Animal;
import org.joml.Matrix3x2fStack;

public class AnimalScannerScreen extends Screen {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID, "textures/gui/container/animal_scanner.png");
    // Dimensioni della texture
    private static final int TEXTURE_WIDTH = 176;
    private static final int TEXTURE_HEIGHT = 166;

    // Posizione centrata
    private int x;
    private int y;

    private Animal animal;

    private final AnimalDataPayload data;

    public AnimalScannerScreen(Component title, AnimalDataPayload data) {
        super(title);
        this.data = data;
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

        graphics.text(this.font, "Hunger: " + data.hunger() + "/100", 10, 20, 0xFFFFFFFF, true);
        graphics.text(this.font, "Mood: " + data.mood(), 10, 35, 0xFFFFFFFF, true);
        graphics.text(this.font, "Production: " + data.production(), 10, 50, 0xFFFFFFFF, true);

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
