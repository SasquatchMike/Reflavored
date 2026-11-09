package com.leclowndu93150.reflavored.tree.trunk;

import com.leclowndu93150.reflavored.init.ModTrunkPlacers;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.RotatedPillarBlock;
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

public class LargeBaobabTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<LargeBaobabTrunkPlacer> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> trunkPlacerParts(instance).apply(instance, LargeBaobabTrunkPlacer::new));

    public LargeBaobabTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrunkPlacers.LARGE_BAOBAB_TRUNK_PLACER.get();
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader levelSimulatedReader, @NotNull BiConsumer<BlockPos, BlockState> biConsumer, @NotNull RandomSource randomSource, int i, @NotNull BlockPos blockPos, @NotNull TreeConfiguration treeConfiguration) {
        int y;
        for (y = 0; y < i; y++) {
            BlockPos currentPos = blockPos.above(y);
            if (TreeFeature.isAirOrLeaves(levelSimulatedReader, currentPos) || y == 0) {
                BlockPos[] placementPositions = {currentPos, currentPos.south(), currentPos.east(), currentPos.south().east(), currentPos.north(), currentPos.west(), currentPos.south().west(), currentPos.east().north(), currentPos.east(2), currentPos.south(2), currentPos.east(2).south(), currentPos.south(2).east()};
                BlockPos[] additionalPlacementPositions = {currentPos.north().west(), currentPos.north().east(2), currentPos.west().south(2), currentPos.east(2).south(2)};
                for (int index = 0; index < placementPositions.length - (y >= i - 6 ? 8 : 0); index++) {
                    if (index <= 3 || y != i - 7 || randomSource.nextInt(2) != 0) {
                        this.placeLog(levelSimulatedReader, biConsumer, randomSource, placementPositions[index], treeConfiguration);
                    }
                }
                if (y < i / 3) {
                    for (BlockPos additionalPlacementPosition : additionalPlacementPositions) {
                        if (y != i / 3 - 1 || randomSource.nextInt(2) != 0) {
                            this.placeLog(levelSimulatedReader, biConsumer, randomSource, additionalPlacementPosition, treeConfiguration);
                        }
                    }
                }
            }
        }

        List<FoliagePlacer.FoliageAttachment> foliageAttachments = new ArrayList<>();
        foliageAttachments.addLast(new FoliagePlacer.FoliageAttachment(blockPos.above(y - 1), 4, true));
        Direction startDirection = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
        foliageAttachments.addLast(this.placeStraightBranch(levelSimulatedReader, biConsumer, randomSource, blockPos.relative(startDirection, startDirection == Direction.EAST || startDirection == Direction.SOUTH ? 2 : 1).above(y - 4), startDirection, treeConfiguration));
        foliageAttachments.addLast(this.placeCrossingBranch(levelSimulatedReader, biConsumer, randomSource, blockPos.relative(startDirection.getOpposite(), startDirection.getOpposite() == Direction.EAST || startDirection.getOpposite() == Direction.SOUTH ? 2 : 1).relative(startDirection.getCounterClockWise(), startDirection.getCounterClockWise() == Direction.EAST || startDirection.getCounterClockWise() == Direction.SOUTH ? 2 : 1).above(y - 4), Pair.of(startDirection.getOpposite(), startDirection.getCounterClockWise()), treeConfiguration));
        foliageAttachments.addLast(this.placeCrossingBranch(levelSimulatedReader, biConsumer, randomSource, blockPos.relative(startDirection.getClockWise(), startDirection.getClockWise() == Direction.EAST || startDirection.getClockWise() == Direction.SOUTH ? 2 : 1).relative(startDirection.getOpposite(), startDirection.getOpposite() == Direction.EAST || startDirection.getOpposite() == Direction.SOUTH ? 2 : 1).above(y - 4), Pair.of(startDirection.getClockWise(), startDirection.getOpposite()), treeConfiguration));
        return foliageAttachments;
    }

    private FoliagePlacer.FoliageAttachment placeStraightBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource randomSource, BlockPos startPos, Direction direction, TreeConfiguration treeConfiguration) {
        int step;
        BlockState state = treeConfiguration.trunkProvider.getState(randomSource, startPos).setValue(RotatedPillarBlock.AXIS, direction.getAxis());
        for (step = 0; step < 3; step++) {
            BlockPos currentPos = startPos.relative(direction, step);
            if (TreeFeature.isAirOrLeaves(level, currentPos) || level.isStateAtPosition(currentPos, state1 -> state1.is(treeConfiguration.trunkProvider.getState(randomSource, currentPos).getBlock()))) {
                blockSetter.accept(currentPos, state);
            }
        }
        for (step = step - 1; step < 6; step++) {
            blockSetter.accept(startPos.relative(direction, step).above(), state);
        }
        for (step = step - 1; step < 9; step++) {
            blockSetter.accept(startPos.relative(direction, step).above(2), state);
        }
        return new FoliagePlacer.FoliageAttachment(startPos.above(2).relative(direction, step - 1), 3, true);
    }

    private FoliagePlacer.FoliageAttachment placeCrossingBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource randomSource, BlockPos startPos, Pair<Direction, Direction> directions, TreeConfiguration treeConfiguration) {
        int step;
        BlockState state = treeConfiguration.trunkProvider.getState(randomSource, startPos).setValue(RotatedPillarBlock.AXIS, directions.getFirst().getAxis());
        for (step = 0; step < 3; step++) {
            BlockPos currentPos = startPos.relative(directions.getFirst(), step).relative(directions.getSecond(), step);
            if (TreeFeature.isAirOrLeaves(level, currentPos) || level.isStateAtPosition(currentPos, state1 -> state1.is(treeConfiguration.trunkProvider.getState(randomSource, currentPos).getBlock()))) {
                if (step != 2) {
                    blockSetter.accept(currentPos, state);
                }
                blockSetter.accept(currentPos.relative(directions.getFirst().getCounterClockWise()), state);
            }
        }
        int sectionStart = step - 1;
        for (step = sectionStart; step < 6; step++) {
            BlockPos currentPos = startPos.relative(directions.getFirst(), step).relative(directions.getSecond(), step).above();
            blockSetter.accept(currentPos, state);
            blockSetter.accept(currentPos.relative(directions.getFirst().getCounterClockWise()), state);
        }
        return new FoliagePlacer.FoliageAttachment(startPos.above(1).relative(directions.getFirst(), step - 1).relative(directions.getSecond(), step - 1), 3, true);
    }
}
