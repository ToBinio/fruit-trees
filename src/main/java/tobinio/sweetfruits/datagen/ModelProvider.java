package tobinio.sweetfruits.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import tobinio.sweetfruits.block.ModBlocks;

import java.util.Optional;

/**
 * Created: 20.08.24
 *
 * @author Tobias Frischmann
 */
public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerTintableCross(ModBlocks.APPLE_SAPLING,
                BlockStateModelGenerator.TintType.NOT_TINTED);

        registerLeaves(blockStateModelGenerator, ModBlocks.APPLE_LEAVES);
    }

    private static void registerLeaves(BlockStateModelGenerator blockStateModelGenerator, Block leaves) {
        blockStateModelGenerator.registerSingleton(leaves,
                TexturedModel.makeFactory(TextureMap::all,
                        new Model(Optional.of(Identifier.ofVanilla("block/leaves")),
                                Optional.empty(),
                                TextureKey.ALL)));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
