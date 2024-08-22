package tobinio.sweetfruits.world.feature;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.Feature;
import tobinio.sweetfruits.SweetFruits;
import tobinio.sweetfruits.world.feature.custom.BushFeature;
import tobinio.sweetfruits.world.feature.custom.BushFeatureConfig;

/**
 * Created: 22.08.24
 *
 * @author Tobias Frischmann
 */
public class ModFeatures {

    public static final Feature<BushFeatureConfig> BUSH_FEATURE = new BushFeature(BushFeatureConfig.CODEC);

    public static void initialize() {
        Registry.register(Registries.FEATURE, Identifier.of(SweetFruits.MOD_ID, "bush_feature"), BUSH_FEATURE);
    }
}
