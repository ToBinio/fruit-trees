package tobinio.sweetfruits.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import tobinio.sweetfruits.block.ModBlocks;

/**
 * Created: 19.08.24
 *
 * @author Tobias Frischmann
 */
public class AppleBlock extends Block {
    public static final BooleanProperty GROWN = BooleanProperty.of("grown");

    private static final VoxelShape GROWN_SHAPE = VoxelShapes.cuboid(5 / 16f,
            4 / 16f,
            5 / 16f,
            11 / 16f,
            16 / 16f,
            11 / 16f);
    private static final VoxelShape NOT_GROWN_SHAPE = VoxelShapes.cuboid(6 / 16f,
            12 / 16f,
            6 / 16f,
            10 / 16f,
            16 / 16f,
            10 / 16f);


    public AppleBlock(Settings settings) {
        super(settings);

        setDefaultState(getDefaultState().with(GROWN, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(GROWN);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.get(GROWN)) {
            if (random.nextInt(7) == 0) {
                world.setBlockState(pos, state.with(GROWN, true), NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape voxelShape = state.get(GROWN) ? GROWN_SHAPE : NOT_GROWN_SHAPE;

        Vec3d vec3d = state.getModelOffset(world, pos);
        return voxelShape.offset(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (state.get(GROWN)) {
            dropStack(world, pos, new ItemStack(Items.APPLE, 1));
            world.playSound(null,
                    pos,
                    SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,
                    SoundCategory.BLOCKS,
                    1.0F,
                    0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = state.with(GROWN, false);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.success(world.isClient);
        }

        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.up()).isOf(ModBlocks.APPLE_LEAVES);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
            WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.UP && !state.canPlaceAt(world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}
