package com.leclowndu93150.reflavored.tree.trunk;

import com.leclowndu93150.reflavored.init.ModTrunkPlacers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class BaobabTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<BaobabTrunkPlacer> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> trunkPlacerParts(instance).apply(instance, BaobabTrunkPlacer::new));

    public BaobabTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.BAOBAB_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader levelSimulatedReader, @NotNull BiConsumer<BlockPos, BlockState> biConsumer, @NotNull RandomSource randomSource, int i, @NotNull BlockPos blockPos, @NotNull TreeConfiguration treeConfiguration) {
        int y;
        short additionalIndexes = -1;
        for (y = 0; y < i; y++) {
            BlockPos currentPos = blockPos.above(y);
            if (TreeFeature.isAirOrLeaves(levelSimulatedReader, currentPos)) {
                BlockPos[] placementPositions = {currentPos, currentPos.south(), currentPos.east(), currentPos.south().east(), currentPos.north(), currentPos.west(), currentPos.south().west(), currentPos.east().north(), currentPos.east(2), currentPos.south(2), currentPos.east(2).south(), currentPos.south(2).east()};
                BlockPos[] additionalPlacementPositions = {currentPos.north().west(), currentPos.north().east(2), currentPos.west().south(2), currentPos.east(2).south(2)};
                for (int index = 0; index < placementPositions.length; index++) {
                    if (index <= 3 || y != 0 && y != i - 1 || randomSource.nextInt(3) != 0) {
                        this.placeLog(levelSimulatedReader, biConsumer, randomSource, placementPositions[index], treeConfiguration);
                    }
                }
                if (y > 0 && randomSource.nextBoolean()) {
                    additionalIndexes++;
                }
                for (short index = additionalIndexes; index > 0; index--) {
                    this.placeLog(levelSimulatedReader, biConsumer, randomSource, additionalPlacementPositions[index], treeConfiguration);
                }
            }

        }
        boolean flag = randomSource.nextBoolean();
        Direction branchDirection = flag ? Direction.NORTH : Direction.WEST;
        Direction secondBranchDirection = flag ? branchDirection.getClockWise() : branchDirection.getCounterClockWise();
        List<FoliagePlacer.FoliageAttachment> foliageAttachments = new ArrayList<>();
        foliageAttachments.add(this.placeBranch(levelSimulatedReader, biConsumer, randomSource, blockPos.above(y), branchDirection, treeConfiguration));
        foliageAttachments.add(this.placeBranch(levelSimulatedReader, biConsumer, randomSource, blockPos.above(y).relative(secondBranchDirection), secondBranchDirection, treeConfiguration));
        foliageAttachments.add(this.placeBranch(levelSimulatedReader, biConsumer, randomSource, blockPos.above(y).relative(branchDirection.getOpposite()), branchDirection.getOpposite(), treeConfiguration));
        return foliageAttachments;
    }

    private FoliagePlacer.FoliageAttachment placeBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource randomSource, BlockPos startPos, Direction direction, TreeConfiguration treeConfiguration) {
        int y;
        for (y = 0; y < 3; y++) {
            BlockPos currentPos = startPos.above(y);
            if (TreeFeature.isAirOrLeaves(level, currentPos) || level.isStateAtPosition(currentPos, state -> state.is(treeConfiguration.trunkProvider.getState(randomSource, currentPos).getBlock()))) {
                BlockPos[] placementPositions = {currentPos.relative(direction).relative(direction.getClockWise()), currentPos.relative(direction), currentPos.relative(direction.getClockWise()), currentPos};
                for (int index = 3 - y; index >= 0; index--) {
                    this.placeLog(level, blockSetter, randomSource, placementPositions[index], treeConfiguration);
                }
            }
        }
        BlockPos thinBranchPos = startPos.above(y).relative(direction, 2).relative(direction.getClockWise());
        this.placeLog(level, blockSetter, randomSource, thinBranchPos.below(), treeConfiguration);
        this.placeLog(level, blockSetter, randomSource, thinBranchPos, treeConfiguration);
        this.placeLog(level, blockSetter, randomSource, thinBranchPos.relative(direction), treeConfiguration);
        this.placeLog(level, blockSetter, randomSource, thinBranchPos.relative(direction).above(), treeConfiguration);
        this.placeLog(level, blockSetter, randomSource, thinBranchPos.relative(direction, 2).above(), treeConfiguration);
        return new FoliagePlacer.FoliageAttachment(thinBranchPos.relative(direction, 2).above(), 2, true);
    }
}
