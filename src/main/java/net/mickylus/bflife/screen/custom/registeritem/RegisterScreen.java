package net.mickylus.bflife.screen.custom.registeritem;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;

import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import org.joml.Matrix3x2fStack;

public class RegisterScreen extends Screen implements MenuAccess<RegisterScreenHandler> {

    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(
            BFLBetterFarmLife.MOD_ID,"textures/gui/register.png"
    );

    private static final int TEXTURE_WIDTH = 165;
    private static final int TEXTURE_HEIGHT = 180;

    private LivingEntity targetEntity;

    private static final int STATS_START = 52;
    private static final int STATS_GAP = 12;
    private static final int ENTITY_X = 40;
    private static final int ENTITY_Y = 16;

    private final RegisterScreenHandler handler;

    public RegisterScreen(RegisterScreenHandler handler, Inventory playerInventory, Component title) {
        super(title);
        this.handler = handler;
    }

    @Override
    public RegisterScreenHandler getMenu() {
        return handler;
    }

    @Override
    protected void init(){
        super.init();
        // Get entity from passed ID
        if (Minecraft.getInstance().level != null) {
            Entity e = Minecraft.getInstance().level.getEntity(handler.getData().entityId());
            if (e instanceof LivingEntity living) {
                targetEntity = living;
            }
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        RegisterScreenHandler.Data data = handler.getData();

        Matrix3x2fStack matrices = graphics.pose();
        matrices.pushMatrix();

        matrices.translate(this.width / 2f, this.height / 2f);
        matrices.translate(-TEXTURE_WIDTH / 2f, -TEXTURE_HEIGHT / 2f);

        // Background
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, 0, 0, 0f, 0f,
                TEXTURE_WIDTH, TEXTURE_HEIGHT, 256, 256);

        // Animal data
        graphics.text(this.font, "Info", 80, 16, 0xFF0d192b,false);
        graphics.text(this.font, "Name: " + targetEntity.getPlainTextName() , 80, 16 + STATS_GAP, 0xFF0d192b,false);
        if(targetEntity.getAgeScale()<1.0){
            graphics.text(this.font, "Age: Baby", 80, 16 + STATS_GAP * 2, 0xFF0d192b,false);
        }else{
            graphics.text(this.font, "Age: Adult", 80, 16 + STATS_GAP * 2, 0xFF0d192b,false);
        }

        graphics.text(this.font, "Hunger: " + data.hunger()     + "/100",40, STATS_START, 0xFF0d192b, false);
        graphics.text(this.font, "Mood: " + data.mood(),40, STATS_START + STATS_GAP, 0xFF0d192b, false);
        graphics.text(this.font, "Production",40, STATS_START + (STATS_GAP * 2), 0xFF0d192b, false);
        graphics.text(this.font, "Rate: " + data.production(),40, STATS_START + (STATS_GAP * 3), 0xFF0d192b, false);
        graphics.text(this.font, "Products",40, STATS_START + (STATS_GAP * 4), 0xFF0d192b, false);
        drawProducts(graphics,40,STATS_START + STATS_GAP * 5, 16,true);
        if(data.tracked()) {
            graphics.text(this.font, "Tracked: Yes", 40, STATS_START + STATS_GAP * 6, 0xFF0d192b, false);
        }else {
            graphics.text(this.font, "Tracked: No", 40, STATS_START + STATS_GAP * 6, 0xFF0d192b, false);
        }

        // Preview entity
        int guiLeft = (this.width  - TEXTURE_WIDTH)  / 2;
        int guiTop  = (this.height - TEXTURE_HEIGHT) / 2;

        InventoryScreen.extractEntityInInventoryFollowsMouse(
                graphics,
                guiLeft + ENTITY_X,
                guiTop  + ENTITY_Y,
                guiLeft + ENTITY_X + 33,
                guiTop  + ENTITY_Y + 33,
                15, 0,
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

    private void drawProducts(GuiGraphicsExtractor graphics,int sectionX,int sectionY, int inBetweenGap,boolean enableBorder) {

        Identifier product_border = Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID,"textures/gui/product_border.png");

        if(targetEntity.is(EntityType.CHICKEN)){
            Identifier egg_product =  Identifier .withDefaultNamespace("textures/item/egg.png");
            Identifier chicken_product =  Identifier .withDefaultNamespace("textures/item/chicken.png");
            graphics.blit(RenderPipelines.GUI_TEXTURED,egg_product,sectionX,sectionY,0,0,16,16,16,16);
            if(enableBorder) graphics.blit(RenderPipelines.GUI_TEXTURED,product_border,sectionX,sectionY,0,0,16,16,16,16);
            graphics.blit(RenderPipelines.GUI_TEXTURED,chicken_product,sectionX + inBetweenGap,sectionY,0,0,16,16,16,16);
            if(enableBorder) graphics.blit(RenderPipelines.GUI_TEXTURED,product_border,sectionX + inBetweenGap,sectionY,0,0,16,16,16,16);
        }
        if(targetEntity.is(EntityType.PIG)){
            Identifier porkchop_product =  Identifier .withDefaultNamespace("textures/item/porkchop.png");
            graphics.blit(RenderPipelines.GUI_TEXTURED,porkchop_product,sectionX,sectionY,0,0,16,16,16,16);
            if(enableBorder) graphics.blit(RenderPipelines.GUI_TEXTURED,product_border,sectionX,sectionY,0,0,16,16,16,16);
        }
        if(targetEntity.is(EntityType.COW)){
            Identifier milk_product =  Identifier .withDefaultNamespace("textures/item/milk_bucket.png");
            Identifier beef_product =  Identifier .withDefaultNamespace("textures/item/beef.png");
            Identifier leather_product =  Identifier .withDefaultNamespace("textures/item/leather.png");
            graphics.blit(RenderPipelines.GUI_TEXTURED,milk_product,sectionX,sectionY,0,0,16,16,16,16);
            if(enableBorder) graphics.blit(RenderPipelines.GUI_TEXTURED,product_border,sectionX,sectionY,0,0,16,16,16,16);
            graphics.blit(RenderPipelines.GUI_TEXTURED,beef_product,sectionX + inBetweenGap,sectionY,0,0,16,16,16,16);
            if(enableBorder) graphics.blit(RenderPipelines.GUI_TEXTURED,product_border,sectionX + inBetweenGap,sectionY,0,0,16,16,16,16);
            graphics.blit(RenderPipelines.GUI_TEXTURED,leather_product,sectionX + inBetweenGap * 2,sectionY,0,0,16,16,16,16);
            if(enableBorder) graphics.blit(RenderPipelines.GUI_TEXTURED,product_border,sectionX + inBetweenGap * 2,sectionY,0,0,16,16,16,16);
        }
        if(targetEntity.is(EntityType.SHEEP)){
            Identifier wool_product =  Identifier .withDefaultNamespace("textures/block/white_wool.png");
            Identifier mutton_product =  Identifier .withDefaultNamespace("textures/item/mutton.png");
            graphics.blit(RenderPipelines.GUI_TEXTURED,wool_product,sectionX,sectionY,0,0,16,16,16,16);
            if(enableBorder) graphics.blit(RenderPipelines.GUI_TEXTURED,product_border,sectionX,sectionY,0,0,16,16,16,16);
            graphics.blit(RenderPipelines.GUI_TEXTURED,mutton_product,sectionX + inBetweenGap,sectionY,0,0,16,16,16,16);
            if(enableBorder) graphics.blit(RenderPipelines.GUI_TEXTURED,product_border,sectionX + inBetweenGap,sectionY,0,0,16,16,16,16);
        }
    }
}
