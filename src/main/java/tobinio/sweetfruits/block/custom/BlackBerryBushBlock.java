package tobinio.sweetfruits.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
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
