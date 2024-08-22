package tobinio.sweetfruits.world.feature.custom;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.WallMountedBlock;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.block.WallMountedBlock.FACE;

/**
 * Created: 22.08.24
 *
 * @author Tobias Frischmann
 */
public class BushFeature extends Feature<BushFeatureConfig> {
    public BushFeature(Codec<BushFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<BushFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();

        Random random = context.getRandom();
        BushFeatureConfig config = context.getConfig();

        Block bush = Registries.BLOCK.get(config.bush());
        Block leave = Registries.BLOCK.get(config.leave());

        var height = world.getTopY(Heightmap.Type.MOTION_BLOCKING, origin.getX(), origin.getZ());

        var pos = new BlockPos(origin.getX(), height, origin.getZ());
        if (world.getBlockState(pos.down()).isIn(BlockTags.DIRT) && world.getBlockState(pos).isIn(BlockTags.AIR)) {
            return false;
        }

        var leaveBlocks = new ArrayList<BlockPos>();
        generateLeaves(world, pos, leaveBlocks, random);

        if (leaveBlocks.size() <= 5) {
            return false;
        }

        for (BlockPos leaveBlock : leaveBlocks) {
            world.setBlockState(leaveBlock, leave.getDefaultState(), Block.NOTIFY_NEIGHBORS);
        }

        for (BlockPos leaveBlock : leaveBlocks) {
            Direction direction = Direction.random(random);

            var bushPos = leaveBlock.offset(direction);

            if (world.getBlockState(bushPos).isFullCube(world, bushPos)) {
                continue;
            }

            BlockState blockState;
            if (direction.getAxis() == Direction.Axis.Y) {
                blockState = bush.getDefaultState()
                        .with(Properties.BLOCK_FACE, direction == Direction.UP ? BlockFace.FLOOR : BlockFace.CEILING);
            } else {
                blockState = bush.getDefaultState()
                        .with(Properties.BLOCK_FACE, BlockFace.WALL)
                        .with(Properties.HORIZONTAL_FACING, direction);
            }

            world.setBlockState(bushPos, blockState, Block.NOTIFY_NEIGHBORS);
        }

        return false;
    }

    private void generateLeaves(StructureWorldAccess world, BlockPos pos, ArrayList<BlockPos> leaveBlocks,
            Random random) {
        leaveBlocks.add(pos);
        leaveBlocks.add(pos.up());

        for (BlockPos blockPos : BlockPos.iterateRandomly(random, 10, pos, 1)) {

            var nextPos = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());

            if (this.isValidLeaveLocation(world, nextPos, leaveBlocks)) {
                leaveBlocks.add(nextPos);
            }
        }
    }

    private boolean isValidLeaveLocation(BlockView world, BlockPos pos, List<BlockPos> leaveBlocks) {
        if (world.getBlockState(pos).isFullCube(world, pos) || leaveBlocks.contains(pos)) {
            return false;
        }

        if (!world.getBlockState(pos.down()).isIn(BlockTags.DIRT) && !leaveBlocks.contains(pos.down())) {
            return false;
        }

        for (Direction value : Direction.values()) {
            if (leaveBlocks.contains(pos.offset(value))) {
                return true;
            }
        }

        return false;
    }
}
