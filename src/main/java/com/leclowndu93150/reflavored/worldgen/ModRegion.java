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
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            if (registry.containsKey(ModBiomes.REDWOOD_FOREST)) {
                builder.replaceBiome(Biomes.OLD_GROWTH_SPRUCE_TAIGA, ModBiomes.REDWOOD_FOREST);
                builder.replaceBiome(Biomes.OLD_GROWTH_PINE_TAIGA, ModBiomes.REDWOOD_FOREST);
            }

            if (registry.containsKey(ModBiomes.LAVENDER_FIELDS)) {
                builder.replaceBiome(Biomes.TAIGA, ModBiomes.LAVENDER_FIELDS);
                builder.replaceBiome(Biomes.FLOWER_FOREST, ModBiomes.LAVENDER_FIELDS);
                builder.replaceBiome(Biomes.SUNFLOWER_PLAINS, ModBiomes.LAVENDER_FIELDS);
            }

            if (registry.containsKey(ModBiomes.GEOTHERMAL_TAIGA)) {
                // Snowy taigas provide broad, connected cold-forest regions while
                // groves extend the biome into the forested high-altitude band below
                // mountain peaks.
                builder.replaceBiome(Biomes.SNOWY_TAIGA, ModBiomes.GEOTHERMAL_TAIGA);
                builder.replaceBiome(Biomes.GROVE, ModBiomes.GEOTHERMAL_TAIGA);
            }
        });
    }
}
