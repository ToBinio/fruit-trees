package tobinio.sweetfruits;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import tobinio.sweetfruits.datagen.DynamicRegistryProvider;
import tobinio.sweetfruits.datagen.LootTableProvider;
import tobinio.sweetfruits.datagen.ModelProvider;
import tobinio.sweetfruits.world.ModConfiguredFeatures;
import tobinio.sweetfruits.world.ModPlacedFeatures;

public class SweetFruitsDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(DynamicRegistryProvider::new);
        pack.addProvider(ModelProvider::new);
        pack.addProvider(LootTableProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::boostrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::boostrap);
    }
}
