package tobinio.sweetfruits;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.world.biome.FoliageColors;
import tobinio.sweetfruits.block.ModBlocks;

public class SweetFruitsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APPLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.APPLE_LEAVES, RenderLayer.getCutout());


        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(
                world,
                pos) : FoliageColors.getDefaultColor(), ModBlocks.APPLE_LEAVES);

        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> {
                    BlockState blockState = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
                    return ColorProviderRegistry.BLOCK.get(ModBlocks.APPLE_LEAVES)
                            .getColor(blockState, null, null, tintIndex);
                }, ModBlocks.APPLE_LEAVES);
    }
}