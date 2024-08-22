package tobinio.sweetfruits.world.feature.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;

/**
 * Created: 22.08.24
 *
 * @author Tobias Frischmann
 */
public record BushFeatureConfig(Identifier leave, Identifier bush) implements FeatureConfig {
    public static final Codec<BushFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                    Identifier.CODEC.fieldOf("leaveID").forGetter(BushFeatureConfig::leave),
                    Identifier.CODEC.fieldOf("bushID").forGetter(BushFeatureConfig::bush))
            .apply(instance, BushFeatureConfig::new));
}
