package net.mickylus.bflife.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.mickylus.bflife.block.ModBlocks;
import net.mickylus.bflife.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.ANIMAL_SCANNER,ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ANIMAL_TAMER,ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(Item.byBlock(ModBlocks.FEEDER),ModelTemplates.FLAT_ITEM);
    }

    private void createBlockMultipleFaces(Block block, BlockModelGenerators blockModelGenerators) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(block, "_side4"))
                .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(block,"_bottom"))
                .put(TextureSlot.UP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(block, "_side4"))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(block, "_side3"))
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(block, "_side1"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(block, "_side2"));
        blockModelGenerators.blockStateOutput
                .accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(ModelTemplates.CUBE.create(block, mapping, blockModelGenerators.modelOutput))));
    }
}
