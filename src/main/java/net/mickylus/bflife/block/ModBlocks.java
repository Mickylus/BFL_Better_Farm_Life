package net.mickylus.bflife.block;

import net.mickylus.bflife.BFLBetterFarmLife;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
    /*
    Example Block

    public static final Block BLOCK_NAME = registerBlock("block_name", properties -> new Block(properties
            .strength(3f)
            .requiresCorrectToolForDrops()
            .sound(SoundType.CROP)
    ));
    */



    private static Block registerBlock (String name, Function<BlockBehaviour.Properties, Block> function){
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID,name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID,name),toRegister);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID,name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM,Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID,name)))));
    }

    public static void registerModBlocks(){
        BFLBetterFarmLife.LOGGER.info("Registering Mod Blocks for " + BFLBetterFarmLife.MOD_ID);
    }
}
