package net.mickylus.bflife.block;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.mickylus.bflife.BFLBetterFarmLife;
import net.mickylus.bflife.block.custom.FeederBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {

    public static final BlockEntityType<FeederBlockEntity> FEEDER_BE =
            Registry.register(
                    BuiltInRegistries.BLOCK_ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(BFLBetterFarmLife.MOD_ID, "feeder"),
                    FabricBlockEntityTypeBuilder.create(FeederBlockEntity::new, ModBlocks.FEEDER).build()
            );

    public static void registerModBlockEntities() {
        BFLBetterFarmLife.LOGGER.info("Registering Block Entities for " + BFLBetterFarmLife.MOD_ID);
    }
}