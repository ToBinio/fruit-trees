package tobinio.sweetfruits.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;
import tobinio.sweetfruits.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

/**
 * Created: 20.08.24
 *
 * @author Tobias Frischmann
 */
public class LootTableProvider extends FabricBlockLootTableProvider {
    public LootTableProvider(FabricDataOutput dataOutput,
            CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.APPLE_LEAVES, leavesDrops(ModBlocks.APPLE_LEAVES, ModBlocks.APPLE_TREE_SAPLING, 0.1f));
    }
}
