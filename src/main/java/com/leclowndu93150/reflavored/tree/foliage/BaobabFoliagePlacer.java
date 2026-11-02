package com.leclowndu93150.reflavored.tree.foliage;

import com.leclowndu93150.reflavored.init.ModFoliagePlacers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import org.jetbrains.annotations.NotNull;

public class BaobabFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<BaobabFoliagePlacer> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> foliagePlacerParts(instance).apply(instance, BaobabFoliagePlacer::new));

    public BaobabFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected @NotNull FoliagePlacerType<?> type() {
        return ModFoliagePlacers.BAOBAB_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(@NotNull LevelSimulatedReader levelSimulatedReader, @NotNull FoliageSetter foliageSetter, @NotNull RandomSource randomSource, @NotNull TreeConfiguration treeConfiguration, int i, @NotNull FoliageAttachment foliageAttachment, int i1, int i2, int i3) {
        BlockPos pos = foliageAttachment.pos().above(i3);
        for (int y = 0; y <= i1; y++) {
            this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, pos, i2 + foliageAttachment.radiusOffset() - y, y, foliageAttachment.doubleTrunk());
        }
        this.placeLeavesRow(levelSimulatedReader, foliageSetter, randomSource, treeConfiguration, pos, i2 + foliageAttachment.radiusOffset(), -1, foliageAttachment.doubleTrunk());
    }

    @Override
    public int foliageHeight(@NotNull RandomSource randomSource, int i, @NotNull TreeConfiguration treeConfiguration) {
        return 3;
    }

    @Override
    protected boolean shouldSkipLocation(@NotNull RandomSource randomSource, int i, int i1, int i2, int i3, boolean b) {
        return i == i3 && i2 == i3 || i1 == -1 && randomSource.nextInt(2) != 0;
    }
}
