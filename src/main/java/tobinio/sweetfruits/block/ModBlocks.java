package tobinio.sweetfruits.block;

import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import tobinio.sweetfruits.SweetFruits;
import tobinio.sweetfruits.block.custom.AppleBlock;
import tobinio.sweetfruits.block.custom.BlackBerryBushBlock;
import tobinio.sweetfruits.block.custom.BlackBerryLeaveBlock;
import tobinio.sweetfruits.world.feature.ModConfiguredFeatures;

import java.util.Optional;

/**
 * Created: 19.08.24
 *
 * @author Tobias Frischmann
 */
public class ModBlocks {
    public static final Block APPLE_SAPLING = registerBlock("apple_sapling",
            new SaplingBlock(new SaplingGenerator("apple",
                    0.1F,
                    Optional.empty(),
                    Optional.empty(),
                    Optional.of(ModConfiguredFeatures.APPLE_TREE),
                    Optional.empty(),
                    Optional.empty(),
                    Optional.empty()), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)),
            true);

    public static final Block APPLE = registerBlock("apple",
            new AppleBlock(AbstractBlock.Settings.create()
                    .noCollision()
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.GRASS)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .breakInstantly()
                    .offset(AbstractBlock.OffsetType.XZ)),
            false);

    public static final Block APPLE_LEAVES = registerBlock("apple_leaves",
            Blocks.createLeavesBlock(BlockSoundGroup.GRASS),
            true);

    public static final Block BLACK_BERRY_BUSH = registerBlock("black_berry_bush",
            new BlackBerryBushBlock(AbstractBlock.Settings.create()
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.GRASS)
                    .nonOpaque()
                    .allowsSpawning(Blocks::canSpawnOnLeaves)
                    .suffocates(Blocks::never)
                    .blockVision(Blocks::never)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .noCollision()
                    .solidBlock(Blocks::never)), false);
    public static final Block BLACK_BERRY_LEAVES = registerBlock("black_berry_leaves", new BlackBerryLeaveBlock(
            AbstractBlock.Settings.create()
                    .strength(0.2F)
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.GRASS)
                    .nonOpaque()
                    .allowsSpawning(Blocks::canSpawnOnLeaves)
                    .suffocates(Blocks::never)
                    .blockVision(Blocks::never)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .solidBlock(Blocks::never)), true);

    private static Block registerBlock(String name, Block block, boolean blockItem) {
        if (blockItem) {
            registerBlockItem(name, block);
        }
        return Registry.register(Registries.BLOCK, Identifier.of(SweetFruits.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM,
                Identifier.of(SweetFruits.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void initialize() {
    }
}
