package tobinio.sweetfruits.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryWrapper;
import tobinio.sweetfruits.block.ModBlocks;
import tobinio.sweetfruits.block.custom.AppleBlock;

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
        addDrop(ModBlocks.APPLE,
                LootTable.builder()
                        .pool(LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(BlockStatePropertyLootCondition.builder(ModBlocks.APPLE)
                                        .properties(StatePredicate.Builder.create().exactMatch(AppleBlock.GROWN, true)))
                                .with(ItemEntry.builder(Items.APPLE))));
    }
}
