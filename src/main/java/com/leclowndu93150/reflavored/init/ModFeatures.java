package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.worldgen.feature.GeothermalLakeFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, Reflavored.MODID);

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> GEOTHERMAL_LAKE =
            FEATURES.register("geothermal_lake", GeothermalLakeFeature::new);
}
