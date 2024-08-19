package tobinio.fruittrees.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import tobinio.fruittrees.FruitTrees;
import tobinio.fruittrees.block.ModBlocks;

/**
 * Created: 19.08.24
 *
 * @author Tobias Frischmann
 */
public class ModItemGroup {
    public static final ItemGroup GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(FruitTrees.MOD_ID, "fruit-trees"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.fruit-trees"))
                    .icon(() -> new ItemStack(ModBlocks.APPLE_TREE_SAPLING.asItem()))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.APPLE_TREE_SAPLING.asItem());
                    })
                    .build());

    public static void initialize() {
    }
}
