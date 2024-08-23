package tobinio.sweetfruits.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

/**
 * Created: 23.08.24
 *
 * @author Tobias Frischmann
 */
public class BlackBerryLeaveBlock extends LeavesBlock {
    public BlackBerryLeaveBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        
    }
}
