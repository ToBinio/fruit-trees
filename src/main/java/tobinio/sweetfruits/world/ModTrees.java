package tobinio.sweetfruits.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

/**
 * Created: 19.08.24
 *
 * @author Tobias Frischmann
 */
public class ModTrees {
    public static void initialize() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.FOREST), GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.APPLE_PLACED_KEY);
    }
}
