package com.leclowndu93150.reflavored.worldgen;

import com.leclowndu93150.reflavored.Redflavored;
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
        super(ResourceLocation.fromNamespaceAndPath(Redflavored.MODID, "overworld"), RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addBiomeSimilar(mapper, Biomes.OLD_GROWTH_PINE_TAIGA, ModBiomes.REDWOOD_FOREST);
        this.addBiomeSimilar(mapper, Biomes.SUNFLOWER_PLAINS, ModBiomes.LAVENDER_FIELDS);
        this.addBiomeSimilar(mapper, Biomes.TAIGA, ModBiomes.GEOTHERMAL_TAIGA);
    }

}
