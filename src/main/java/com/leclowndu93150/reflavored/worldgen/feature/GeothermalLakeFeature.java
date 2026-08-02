package com.leclowndu93150.reflavored.worldgen.feature;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class GeothermalLakeFeature extends Feature<NoneFeatureConfiguration> {
    private static final double WATER_EDGE_SQUARED = 0.82;
    private static final double SITE_EDGE_SQUARED = 1.15;
    private static final int MAX_SITE_RELIEF = 5;

    public GeothermalLakeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        RandomSource random = ctx.random();

        BlockPos origin = ctx.origin();
        int radius = 5 + random.nextInt(4);       // 5..8
        int depth  = 2 + random.nextInt(3);       // 2..4 (shallow geothermal lake)

        // Optional: slight ellipse to avoid perfect circles every time.
        double rx = radius * (0.85 + random.nextDouble() * 0.35);
        double rz = radius * (0.85 + random.nextDouble() * 0.35);

        int waterY = findWaterLevel(level, origin, rx, rz);
        if (waterY == Integer.MIN_VALUE) return false;
        BlockPos center = new BlockPos(origin.getX(), waterY, origin.getZ());

        // Carve basin and fill water.
        carveAndFill(level, random, center, rx, rz, depth);

        // Build weighted lake bed: tuff, clay, andesite, gravel.
        paintLakeBed(level, random, center, rx, rz, depth);

        // Obsidian patches around the shoreline.
        placeShoreObsidian(level, random, center, rx, rz);

        return true;
    }

    private int getSurfaceBlockY(WorldGenLevel level, int x, int z) {
        return level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z) - 1;
    }

    private int findWaterLevel(WorldGenLevel level, BlockPos origin, double rx, double rz) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int minX = (int) Math.floor(origin.getX() - rx - 2);
        int maxX = (int) Math.ceil(origin.getX() + rx + 2);
        int minZ = (int) Math.floor(origin.getZ() - rz - 2);
        int maxZ = (int) Math.ceil(origin.getZ() + rz + 2);
        int minSurfaceY = Integer.MAX_VALUE;
        int maxSurfaceY = Integer.MIN_VALUE;
        int minShoreY = Integer.MAX_VALUE;

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                double nx = (x - origin.getX()) / rx;
                double nz = (z - origin.getZ()) / rz;
                double dist = nx * nx + nz * nz;
                if (dist > SITE_EDGE_SQUARED) continue;

                int surfaceY = getSurfaceBlockY(level, x, z);
                pos.set(x, surfaceY, z);
                if (surfaceY <= level.getMinBuildHeight() + 5) return Integer.MIN_VALUE;
                if (!level.getFluidState(pos).isEmpty()) return Integer.MIN_VALUE;
                if (!level.getBlockState(pos).isFaceSturdy(level, pos, Direction.UP)) return Integer.MIN_VALUE;

                minSurfaceY = Math.min(minSurfaceY, surfaceY);
                maxSurfaceY = Math.max(maxSurfaceY, surfaceY);
                if (dist >= WATER_EDGE_SQUARED) {
                    minShoreY = Math.min(minShoreY, surfaceY);
                }
            }
        }

        if (minShoreY == Integer.MAX_VALUE || maxSurfaceY - minSurfaceY > MAX_SITE_RELIEF) {
            return Integer.MIN_VALUE;
        }

        // The lowest point in the uncarved shore ring is a natural spillway.
        // Keeping the water at that height produces a contained lake while still
        // accepting gently rolling mountain terrain.
        if (maxSurfaceY - minShoreY > MAX_SITE_RELIEF) {
            return Integer.MIN_VALUE;
        }
        return minShoreY;
    }

    private void carveAndFill(WorldGenLevel level, RandomSource random, BlockPos center,
                              double rx, double rz, int depth) {

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int minX = (int) Math.floor(center.getX() - rx - 1);
        int maxX = (int) Math.ceil(center.getX() + rx + 1);
        int minZ = (int) Math.floor(center.getZ() - rz - 1);
        int maxZ = (int) Math.ceil(center.getZ() + rz + 1);

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {

                double nx = (x - center.getX()) / rx;
                double nz = (z - center.getZ()) / rz;
                double dist = nx * nx + nz * nz;

                if (dist > WATER_EDGE_SQUARED) continue;

                // Bowl shape: deeper near center, shallow near edges.
                double edge = 1.0 - (dist / WATER_EDGE_SQUARED);
                int localDepth = 1 + (int) Math.round((depth - 1) * edge);

                int surfaceY = getSurfaceBlockY(level, x, z);
                int bottomY = center.getY() - localDepth;

                for (int y = Math.max(surfaceY, center.getY()); y > bottomY; y--) {
                    pos.set(x, y, z);
                    if (pos.getY() <= level.getMinBuildHeight() + 5) break;

                    if (y > center.getY()) {
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
                    } else if (y > bottomY) {
                        setWater(level, pos);
                    }
                }
            }
        }
    }

    private void setWater(WorldGenLevel level, BlockPos pos) {
        level.setBlock(pos, Blocks.WATER.defaultBlockState(), 2);
    }

    private void paintLakeBed(WorldGenLevel level, RandomSource random, BlockPos center,
                              double rx, double rz, int depth) {

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int minX = (int) Math.floor(center.getX() - rx - 1);
        int maxX = (int) Math.ceil(center.getX() + rx + 1);
        int minZ = (int) Math.floor(center.getZ() - rz - 1);
        int maxZ = (int) Math.ceil(center.getZ() + rz + 1);

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {

                double nx = (x - center.getX()) / rx;
                double nz = (z - center.getZ()) / rz;
                double dist = nx * nx + nz * nz;

                if (dist > WATER_EDGE_SQUARED) continue;

                int topY = center.getY();

                // Find the first water column and then set the block beneath it.
                // We scan down a small range; geothermal lakes are shallow.
                for (int y = topY; y >= topY - (depth + 6); y--) {
                    pos.set(x, y, z);

                    if (level.getBlockState(pos).is(Blocks.WATER)) {
                        BlockPos below = pos.below();
                        BlockState bed = pickBedBlock(random);

                        // Place bed if the below block is not also water (avoid painting inside water columns).
                        if (!level.getBlockState(below).is(Blocks.WATER)) {
                            level.setBlock(below, bed, 2);
                            break;
                        }
                    }
                }
            }
        }
    }

    private BlockState pickBedBlock(RandomSource random) {
        // Weighted: gravel and tuff slightly more common.
        int roll = random.nextInt(14); // 0..13
        if (roll <= 4)  return Blocks.TUFF.defaultBlockState();     // 5/14
        if (roll <= 8)  return Blocks.GRAVEL.defaultBlockState();   // 4/14
        if (roll <= 11) return Blocks.ANDESITE.defaultBlockState(); // 3/14
        return Blocks.CLAY.defaultBlockState();                     // 2/14
    }

    private void placeShoreObsidian(WorldGenLevel level, RandomSource random, BlockPos center,
                                    double rx, double rz) {

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int minX = (int) Math.floor(center.getX() - rx - 2);
        int maxX = (int) Math.ceil(center.getX() + rx + 2);
        int minZ = (int) Math.floor(center.getZ() - rz - 2);
        int maxZ = (int) Math.ceil(center.getZ() + rz + 2);

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {

                double nx = (x - center.getX()) / rx;
                double nz = (z - center.getZ()) / rz;
                double dist = nx * nx + nz * nz;

                // Shore band, a thin ring around the edge.
                if (dist < WATER_EDGE_SQUARED || dist > SITE_EDGE_SQUARED) continue;

                // Rare patches.
                if (random.nextInt(12) != 0) continue;

                int topY = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
                pos.set(x, topY, z);

                // Find ground position.
                while (pos.getY() > level.getMinBuildHeight() + 5 && level.getBlockState(pos).isAir()) {
                    pos.move(Direction.DOWN);
                }

                // Only place if water is nearby (ensures it reads as geothermal shore).
                if (!hasWaterNeighbor(level, pos)) continue;

                // Small cluster: 1..3 blocks
                int cluster = 1 + random.nextInt(3);
                for (int i = 0; i < cluster; i++) {
                    int dx = random.nextInt(3) - 1;
                    int dz = random.nextInt(3) - 1;

                    BlockPos target = pos.offset(dx, 0, dz);
                    if (hasWaterNeighbor(level, target) && isReplaceableForObsidian(level, target)) {
                        level.setBlock(target, Blocks.OBSIDIAN.defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    private boolean hasWaterNeighbor(WorldGenLevel level, BlockPos pos) {
        // 4-neighborhood check
        return level.getFluidState(pos.north()).isSource()
                || level.getFluidState(pos.south()).isSource()
                || level.getFluidState(pos.east()).isSource()
                || level.getFluidState(pos.west()).isSource();
    }

    private boolean isReplaceableForObsidian(WorldGenLevel level, BlockPos pos) {
        BlockState st = level.getBlockState(pos);
        // Allow replacing common ground. Tighten this if you want.
        return st.is(Blocks.STONE)
                || st.is(Blocks.ANDESITE)
                || st.is(Blocks.GRAVEL)
                || st.is(Blocks.GRASS_BLOCK)
                || st.is(Blocks.DIRT)
                || st.is(Blocks.PODZOL)
                || st.is(Blocks.COARSE_DIRT)
                || st.is(Blocks.SMOOTH_BASALT)
                || st.is(Blocks.TUFF)
                || st.is(Blocks.CLAY);
    }
}
