package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.tree.foliage.BaobabFoliagePlacer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Reflavored.MODID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<BaobabFoliagePlacer>> BAOBAB_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("baobab_foliage_placer", () -> new FoliagePlacerType<>(BaobabFoliagePlacer.MAP_CODEC));
}
