package tobinio.sweetfruits.block.custom;

import net.minecraft.block.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import tobinio.sweetfruits.block.ModBlocks;
import tobinio.sweetfruits.util.VoxelRotation;

/**
 * Created: 23.08.24
 *
 * @author Tobias Frischmann
 */
public class BlackBerryBushBlock extends SweetBerryBushBlock {
    public static final DirectionProperty FACING = Properties.FACING;

    public BlackBerryBushBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(Properties.FACING, Direction.UP));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);

        builder.add(FACING);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (state.get(FACING) == Direction.UP) {
            BlockPos blockPos = pos.down();
            BlockState floor = world.getBlockState(blockPos);

            if (floor.isIn(BlockTags.DIRT) || floor.isOf(Blocks.FARMLAND)) {
                return true;
            }
        }

        BlockPos target = pos.offset(state.get(FACING).getOpposite());
        return world.getBlockState(target).isOf(ModBlocks.BLACK_BERRY_LEAVES);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        var shape = super.getOutlineShape(state, world, pos, context);

        Direction direction = state.get(FACING);
        if (direction == Direction.UP) {
            return shape;
        }

        shape = VoxelRotation.rotate(shape, VoxelRotation.VoxelShapeTransformation.ROTATE_X);

        switch (direction) {
            case SOUTH:
                shape = VoxelRotation.rotate(shape, VoxelRotation.VoxelShapeTransformation.ROTATE);
            case WEST:
                shape = VoxelRotation.rotate(shape, VoxelRotation.VoxelShapeTransformation.ROTATE);
            case NORTH:
                shape = VoxelRotation.rotate(shape, VoxelRotation.VoxelShapeTransformation.ROTATE);
        }

        return shape;
    }
}
