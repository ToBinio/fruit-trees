package tobinio.fruittrees.world;

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
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import tobinio.fruittrees.FruitTrees;
import tobinio.fruittrees.block.ModBlocks;
import tobinio.fruittrees.block.custom.AppleBlock;

import java.util.List;

/**
 * Created: 18.08.24
 *
 * @author Tobias Frischmann
 */
public class ModConfiguredFeatures {

    public static final RegistryKey<ConfiguredFeature<?, ?>> APPLE = registerKey("apple");

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {

        register(context,
                APPLE,
                Feature.TREE,
                new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.OAK_LOG),
                        new ForkingTrunkPlacer(4, 2, 2),
                        BlockStateProvider.of(ModBlocks.APPLE_LEAVES),
                        new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1))
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
                                        List.of(Direction.DOWN)))).build());
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(FruitTrees.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature,
            FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
