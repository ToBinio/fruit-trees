package tobinio.sweetfruits.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import tobinio.sweetfruits.SweetFruits;
import tobinio.sweetfruits.block.ModBlocks;
import tobinio.sweetfruits.block.custom.AppleBlock;
import tobinio.sweetfruits.world.feature.custom.BushFeatureConfig;

import java.util.List;
import java.util.OptionalInt;

/**
 * Created: 18.08.24
 *
 * @author Tobias Frischmann
 */
public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE_TREE = registerKey("apple");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLACKBERRY_BUSH = registerKey("blackberry");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {

        register(context,
                APPLE_TREE,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.OAK_LOG),
                        new LargeOakTrunkPlacer(5, 2, 2),
                        BlockStateProvider.of(ModBlocks.APPLE_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(3), 3),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines()
                        .decorators(List.of(new AttachedToLeavesTreeDecorator(0.2F,
                                        0,
                                        0,
                                        BlockStateProvider.of(ModBlocks.APPLE.getDefaultState().with(AppleBlock.GROWN, true)),
                                        2,
                                        List.of(Direction.DOWN)),
                                new AttachedToLeavesTreeDecorator(0.3F,
                                        0,
                                        0,
                                        BlockStateProvider.of(ModBlocks.APPLE.getDefaultState()
                                                .with(AppleBlock.GROWN, false)),
                                        2,
                                        List.of(Direction.DOWN))))
                        .build());

        register(context,
                BLACKBERRY_BUSH,
                ModFeatures.BUSH_FEATURE,
                new BushFeatureConfig(Identifier.of(SweetFruits.MOD_ID, "black_berry_leaves"),
                        Identifier.of(SweetFruits.MOD_ID, "black_berry_bush")));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(SweetFruits.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature,
            FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
