package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.tree.trunk.BaobabTrunkPlacer;
import com.leclowndu93150.reflavored.tree.trunk.LargeBaobabTrunkPlacer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTrunkPlacers {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACERS = DeferredRegister.create(BuiltInRegistries.TRUNK_PLACER_TYPE, Reflavored.MODID);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<BaobabTrunkPlacer>> BAOBAB_TRUNK_PLACER = TRUNK_PLACERS.register("baobab_trunk_placer", () -> new TrunkPlacerType<>(BaobabTrunkPlacer.MAP_CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<LargeBaobabTrunkPlacer>> LARGE_BAOBAB_TRUNK_PLACER = TRUNK_PLACERS.register("large_baobab_trunk_placer", () -> new TrunkPlacerType<>(LargeBaobabTrunkPlacer.MAP_CODEC));
}
