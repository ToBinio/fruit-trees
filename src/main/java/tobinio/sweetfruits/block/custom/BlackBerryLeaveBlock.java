package tobinio.sweetfruits.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

/**
 * Created: 23.08.24
 *
 * @author Tobias Frischmann
 */
public class BlackBerryLeaveBlock extends LeavesBlock implements Fertilizable {
    public static final IntProperty AGE = Properties.AGE_2;

    public BlackBerryLeaveBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AGE);
    }

    @Override
    protected ItemActionResult onUseWithItem(
            ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockHitResult hit) {
        return !isFullyGrown(state) && stack.isOf(Items.BONE_MEAL)
                ? ItemActionResult.SKIP_DEFAULT_BLOCK_INTERACTION
                : super.onUseWithItem(stack, state, world, pos, player, hand, hit);
    }

    private boolean isFullyGrown(BlockState state) {
        return state.get(AGE) == 2;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int age = state.get(AGE);
        if (age < 2 && random.nextInt(5) == 0 && world.getBaseLightLevel(pos.up(), 0) >= 9) {
            BlockState blockState = state.with(AGE, age + 1);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(blockState));
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        int age = state.get(AGE);
        if (age > 0) {
            int count = 1 + world.random.nextInt(2);
            dropStack(world, pos, new ItemStack(Items.SWEET_BERRIES, count + (isFullyGrown(state) ? 1 : 0)));
            world.playSound(null,
                    pos,
                    SoundEvents.BLOCK_SWEET_BERRY_BUSH_PICK_BERRIES,
                    SoundCategory.BLOCKS,
                    1.0F,
                    0.8F + world.random.nextFloat() * 0.4F);
            BlockState blockState = state.with(AGE, 0);
            world.setBlockState(pos, blockState, Block.NOTIFY_LISTENERS);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, blockState));
            return ActionResult.success(world.isClient);
        } else {
            return super.onUse(state, world, pos, player, hit);
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return !isFullyGrown(state);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int age = Math.min(2, state.get(AGE) + 1);
        world.setBlockState(pos, state.with(AGE, age), Block.NOTIFY_LISTENERS);
    }
}
