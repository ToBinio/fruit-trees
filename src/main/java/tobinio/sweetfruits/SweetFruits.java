package tobinio.sweetfruits;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tobinio.sweetfruits.block.ModBlocks;
import tobinio.sweetfruits.item.ModItemGroup;
import tobinio.sweetfruits.world.ModTrees;

public class SweetFruits implements ModInitializer {
    public static final String MOD_ID = "sweet-fruits";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.initialize();
        ModItemGroup.initialize();

        ModTrees.initialize();
    }
}