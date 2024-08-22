package tobinio.sweetfruits.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import tobinio.sweetfruits.world.feature.ModPlacedFeatures;

/**
 * Created: 19.08.24
 *
 * @author Tobias Frischmann
 */
public class ModBiomeModifications {
    public static void initialize() {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.PLAINS, BiomeKeys.MEADOW),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.APPLE_TREE_PLACED);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.BLACKBERRY_BUSH_PLACED);
    }
}
