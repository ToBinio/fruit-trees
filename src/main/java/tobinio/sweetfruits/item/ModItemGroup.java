package tobinio.sweetfruits.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tobinio.sweetfruits.SweetFruits;
import tobinio.sweetfruits.block.ModBlocks;

/**
 * Created: 19.08.24
 *
 * @author Tobias Frischmann
 */
public class ModItemGroup {
    public static final ItemGroup GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SweetFruits.MOD_ID, "sweet-fruits"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.sweet-fruits"))
                    .icon(() -> new ItemStack(ModBlocks.APPLE_SAPLING.asItem()))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.APPLE_SAPLING.asItem());
                        entries.add(ModBlocks.APPLE_LEAVES.asItem());
                    })
                    .build());

    public static void initialize() {
    }
}
