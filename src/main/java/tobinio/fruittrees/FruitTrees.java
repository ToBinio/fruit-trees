package tobinio.fruittrees;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tobinio.fruittrees.block.ModBlocks;
import tobinio.fruittrees.item.ModItemGroup;
import tobinio.fruittrees.world.ModPlacedFeatures;
import tobinio.fruittrees.world.ModTrees;

public class FruitTrees implements ModInitializer {
    public static final String MOD_ID = "fruit-trees";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
        ModItemGroup.initialize();

        ModTrees.initialize();
    }
}