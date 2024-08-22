package tobinio.sweetfruits.world.feature;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import tobinio.sweetfruits.SweetFruits;
import tobinio.sweetfruits.block.ModBlocks;

import java.util.List;

/**
 * Created: 18.08.24
 *
 * @author Tobias Frischmann
 */
public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> APPLE_TREE_PLACED = registerKey("apple");
    public static final RegistryKey<PlacedFeature> BLACKBERRY_BUSH_PLACED = registerKey("blackberry");

    public static void boostrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context,
                APPLE_TREE_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.APPLE_TREE),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0,
                        0.05f,
                        1), ModBlocks.APPLE_SAPLING));

        register(context,
                BLACKBERRY_BUSH_PLACED,
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.BLACKBERRY_BUSH),
                List.of(SquarePlacementModifier.of()));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key,
            RegistryEntry<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(SweetFruits.MOD_ID, name));
    }
}
