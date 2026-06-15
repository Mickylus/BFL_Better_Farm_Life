package net.mickylus.bflife.screen.custom.registeritem;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Matrix3x2fStack;

public class RegisterScreen extends AbstractContainerScreen<RegisterScreenHandler> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            BFLBetterFarmLife.MOD_ID,"textures/gui/register.png"
    );

    private static final int TEXTURE_WIDTH = 165;
    private static final int TEXTURE_HEIGHT = 180;

    private LivingEntity targetEntity;

    public RegisterScreen(RegisterScreenHandler handler, Inventory playerInventory, Component title) {
        super(handler, playerInventory, title);
    }

    @Override
    protected void init(){
        super.init();
        // Recupera l'entità dal mondo client tramite ID
        if (Minecraft.getInstance().level != null) {
            Entity e = Minecraft.getInstance().level.getEntity(menu.getData().entityId());
            if (e instanceof LivingEntity living) {
                targetEntity = living;
            }
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        RegisterScreenHandler.Data data = menu.getData();

        Matrix3x2fStack matrices = graphics.pose();
        matrices.pushMatrix();

        matrices.translate(this.width / 2f, this.height / 2f);
        matrices.translate(-TEXTURE_WIDTH / 2f, -TEXTURE_HEIGHT / 2f);

        // Background
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, 0, 0, 0f, 0f,
                TEXTURE_WIDTH, TEXTURE_HEIGHT, 256, 256);

        // Testo dati animale
        graphics.text(this.font, "Hunger: "    + data.hunger()     + "/100", 40, 16, 0xFF0d192b, false);
        graphics.text(this.font, "Mood: "      + data.mood(),               40, 28, 0xFF0d192b, false);
        graphics.text(this.font, "Production: "+ data.production(),          40, 40, 0xFF0d192b, false);
        graphics.text(this.font, "Mother: "    + data.mother(),              40, 52, 0xFF0d192b, false);
        graphics.text(this.font, "Father: "    + data.father(),              40, 64, 0xFF0d192b, false);

        // Preview entità
        int guiLeft = (this.width  - TEXTURE_WIDTH)  / 2;
        int guiTop  = (this.height - TEXTURE_HEIGHT) / 2;

        InventoryScreen.extractEntityInInventoryFollowsMouse(
                graphics,
                guiLeft + 1,
                guiTop  + 1,
                guiLeft + 61,
                guiTop  + 81,
                30, 0,
                mouseX, mouseY,
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
