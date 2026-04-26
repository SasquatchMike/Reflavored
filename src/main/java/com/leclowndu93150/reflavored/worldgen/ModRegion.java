package com.leclowndu93150.reflavored.worldgen;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.init.ModBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModRegion extends Region {
    public ModRegion(int weight) {
        super(ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "overworld"), RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        addBiomeIfPresent(registry, mapper, Biomes.OLD_GROWTH_PINE_TAIGA, ModBiomes.REDWOOD_FOREST);
        addBiomeIfPresent(registry, mapper, Biomes.SUNFLOWER_PLAINS, ModBiomes.LAVENDER_FIELDS);
        addBiomeIfPresent(registry, mapper, Biomes.TAIGA, ModBiomes.GEOTHERMAL_TAIGA);
    }

    private void addBiomeIfPresent(Registry<Biome> registry,
                                   Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper,
                                   ResourceKey<Biome> similarTo,
                                   ResourceKey<Biome> targetBiome) {
        if (registry.containsKey(targetBiome)) {
            this.addBiomeSimilar(mapper, similarTo, targetBiome);
        }
    }
}
