package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.MiscOverworldPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomes {
    public static final ResourceKey<Biome> REDWOOD_FOREST = createKey("redwood_forest");
    public static final ResourceKey<Biome> LAVENDER_FIELDS = createKey("lavender_fields");
    public static final ResourceKey<Biome> GEOTHERMAL_TAIGA = createKey("geothermal_taiga");

    public static ResourceKey<Biome> createKey(String name) {
        return ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, name));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> worldCarvers = context.lookup(Registries.CONFIGURED_CARVER);

        context.register(REDWOOD_FOREST, redwoodForest(placedFeatures, worldCarvers));
        context.register(LAVENDER_FIELDS, lavenderFields(placedFeatures, worldCarvers));
        context.register(GEOTHERMAL_TAIGA, geothermalTaiga(placedFeatures, worldCarvers));
    }

    private static Biome redwoodForest(HolderGetter<PlacedFeature> placedFeatures,
                                       HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder generationBuilder =
                new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);

        addDefaultOverworldGeneration(generationBuilder);
        BiomeDefaultFeatures.addDefaultSeagrass(generationBuilder);
        generationBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.DISK_CLAY);
        generationBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.DISK_SAND);
        generationBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.DISK_GRAVEL);

        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.REDWOOD_TREES));
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.PATCH_DOUGLAS_IRIS));
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.PATCH_TRILLIUM));
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.PATCH_ALPINE_LILY));
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.BROWN_MUSHROOM_REDWOOD));
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.RED_MUSHROOM_REDWOOD));
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.PATCH_GRASS_REDWOOD));

        generationBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS,placedFeatures.getOrThrow(ModPlacedFeatures.GRANITE_BOULDER));

        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        // The dense redwood canopy blocks light, leaving few valid surface spawn
        // spots, so bump the creature generation probability to keep the biome
        // from feeling empty.
        spawnBuilder.creatureGenerationProbability(0.2F);

        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(EntityType.FOX, 90, 2, 5));
        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 110, 3, 6));
        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(ModEntities.SKUNK.get(), 95, 2, 5));

        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT,
                new MobSpawnSettings.SpawnerData(EntityType.SALMON, 56, 2, 6));

        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 95, 4, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.9F)
                .downfall(0.9F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(8168447)
                        .grassColorOverride(0x819e5b)
                        .foliageColorOverride(0x819e5b)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.MUSIC_REDWOOD_FOREST))
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }


    private static Biome lavenderFields(HolderGetter<PlacedFeature> placedFeatures,
                                        HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder generationBuilder =
                new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);

        addDefaultOverworldGeneration(generationBuilder);
        generationBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.DISK_CLAY);
        generationBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.DISK_SAND);
        generationBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, MiscOverworldPlacements.DISK_GRAVEL);

        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.LAVENDER_PATCH));
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_TALL_GRASS);
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_LARGE_FERN);
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.PATCH_FIELD_FLOWERS));
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.CYPRESSE_TREES));
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, placedFeatures.getOrThrow(ModPlacedFeatures.LAVENDER_ROCK));
        BiomeDefaultFeatures.addDefaultSeagrass(generationBuilder);

        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);
        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(EntityType.BEE, 25, 3, 5));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.85F)
                .downfall(0.4F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(8168447)
                        .grassColorOverride(0x86b783)
                        .foliageColorOverride(0x86b783)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.MUSIC_LAVENDER_FIELDS))
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }

    private static Biome geothermalTaiga(HolderGetter<PlacedFeature> placedFeatures,
                                         HolderGetter<ConfiguredWorldCarver<?>> worldCarvers) {
        BiomeGenerationSettings.Builder generationBuilder =
                new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers);

        // Preserve a snowy-taiga climate and precipitation visuals without running
        // the vanilla surface-freezing worldgen pass. Runtime snow/ice placement is
        // also suppressed by GeothermalPrecipitationMixin.
        addDefaultOverworldGeneration(generationBuilder, false);

        generationBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS,
                placedFeatures.getOrThrow(ModPlacedFeatures.GEOTHERMAL_LAKE_PLACED));

        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeatures.getOrThrow(ModPlacedFeatures.SPARSE_OLD_GROWTH_SPRUCE_TREES));

        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                VegetationPlacements.PATCH_GRASS_TAIGA_2);

        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeatures.getOrThrow(ModPlacedFeatures.PATCH_GLACIER_LILY));
        generationBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,
                placedFeatures.getOrThrow(ModPlacedFeatures.PATCH_PAINTBRUSH_FLOWER));


        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.caveSpawns(spawnBuilder);

        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(ModEntities.BISON.get(), 22, 2, 5));

        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(EntityType.WOLF, 6, 2, 4));

        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 1, 2));

/*        spawnBuilder.addSpawn(MobCategory.CREATURE,
                new MobSpawnSettings.SpawnerData(ModEntities.DEER.get(), 10, 2, 6)); */

        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT,
                new MobSpawnSettings.SpawnerData(EntityType.SALMON, 8, 1, 5));
        spawnBuilder.addSpawn(MobCategory.WATER_AMBIENT,
                new MobSpawnSettings.SpawnerData(ModEntities.CUTTHROAT_TROUT.get(), 12, 2, 6));

        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 95, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 100, 2, 4));
        spawnBuilder.addSpawn(MobCategory.MONSTER,
                new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 2, 4));

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(-0.5F)
                .downfall(0.4F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(4020182)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(8625919)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_OLD_GROWTH_TAIGA))
                        .build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(generationBuilder.build())
                .build();
    }

    private static void addDefaultOverworldGeneration(BiomeGenerationSettings.Builder generationBuilder) {
        addDefaultOverworldGeneration(generationBuilder, true);
    }

    private static void addDefaultOverworldGeneration(BiomeGenerationSettings.Builder generationBuilder, boolean addSurfaceFreezing) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationBuilder);
        BiomeDefaultFeatures.addDefaultSprings(generationBuilder);
        BiomeDefaultFeatures.addDefaultOres(generationBuilder);
        if (addSurfaceFreezing) {
            BiomeDefaultFeatures.addSurfaceFreezing(generationBuilder);
        }
    }



}
