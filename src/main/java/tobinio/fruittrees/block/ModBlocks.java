package tobinio.fruittrees.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tobinio.fruittrees.FruitTrees;
import tobinio.fruittrees.block.custom.AppleBlock;
import tobinio.fruittrees.world.ModConfiguredFeatures;

import java.util.Optional;

/**
 * Created: 19.08.24
 *
 * @author Tobias Frischmann
 */
public class ModBlocks {
    public static final Block APPLE_TREE_SAPLING = registerBlock("apple_tree_sapling",
            new SaplingBlock(new SaplingGenerator(
                    "apple",
                    0.1F,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(ModConfiguredFeatures.APPLE),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()
            ), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)), true);

    public static final Block APPLE = registerBlock("apple", new AppleBlock(AbstractBlock.Settings.create()
            .noCollision()
            .ticksRandomly()
            .breakInstantly()
            .offset(AbstractBlock.OffsetType.XZ)), false);

    private static Block registerBlock(String name, Block block, boolean blockItem) {
        if (blockItem) {
            registerBlockItem(name, block);
        }
        return Registry.register(Registries.BLOCK, Identifier.of(FruitTrees.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(FruitTrees.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void initialize() {
    }
}
