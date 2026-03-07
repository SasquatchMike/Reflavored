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

    public GeothermalLakeFeature() {
        super(NoneFeatureConfiguration.CODEC);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        WorldGenLevel level = ctx.level();
        RandomSource random = ctx.random();

        BlockPos origin = ctx.origin();

        // Pick a surface position using heightmap. This avoids placing lakes underground.
        int surfaceY = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, origin.getX(), origin.getZ());
        BlockPos center = new BlockPos(origin.getX(), surfaceY, origin.getZ());

        // Safety gates: do not place in water/ocean surfaces.
        if (!level.getFluidState(center).isEmpty()) return false;

        // Radius and depth tuning for your biome identity.
        int radius = 5 + random.nextInt(6);       // 5..10
        int depth  = 2 + random.nextInt(3);       // 2..4 (shallow geothermal lake)

        // Optional: slight ellipse to avoid perfect circles every time.
        double rx = radius * (0.85 + random.nextDouble() * 0.35);
        double rz = radius * (0.85 + random.nextDouble() * 0.35);

        // Carve basin and fill water.
        carveAndFill(level, random, center, rx, rz, depth);

        // Build weighted lake bed: tuff, clay, andesite, gravel.
        paintLakeBed(level, random, center, rx, rz, depth);

        // Obsidian patches around the shoreline.
        placeShoreObsidian(level, random, center, rx, rz, depth);

        return true;
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

                if (dist > 1.0) continue;

                // Bowl shape: deeper near center, shallow near edges.
                double edge = 1.0 - dist; // 1 at center, 0 at edge
                int localDepth = Math.max(1, (int) Math.round(depth * (0.55 + edge * 0.70)));

                // Carve downward from surface.
                int topY = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
                for (int dy = 0; dy <= localDepth; dy++) {
                    pos.set(x, topY - dy, z);

                    // Do not carve bedrock or deep layers if something goes wrong with terrain.
                    if (pos.getY() <= level.getMinBuildHeight() + 5) break;

                    // Fill with water except bottom block (leave for lake-bed painting).
                    if (dy < localDepth) {
                        // Clear any blocks to air first, then water. Helps avoid weird water logging issues.
                        level.setBlock(pos, Blocks.WATER.defaultBlockState(), 2);
                    }
                }
            }
        }
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

                if (dist > 1.0) continue;

                int topY = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);

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
                        }
                        break;
                    }
                }
            }
        }
    }

    private BlockState pickBedBlock(RandomSource random) {
        // Weighted: gravel and tuff slightly more common.
        int roll = random.nextInt(14); // 0..13
        if (roll <= 4)  return Blocks.TUFF.defaultBlockState();      // 5/14
        if (roll <= 8)  return Blocks.GRAVEL.defaultBlockState();    // 4/14
        if (roll <= 11) return Blocks.ANDESITE.defaultBlockState();  // 3/14
        return Blocks.CLAY.defaultBlockState();                      // 2/14
    }

    private void placeShoreObsidian(WorldGenLevel level, RandomSource random, BlockPos center,
                                    double rx, double rz, int depth) {

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
                if (dist < 0.78 || dist > 1.15) continue;

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
                || st.is(Blocks.DIRT)
                || st.is(Blocks.COARSE_DIRT)
                || st.is(Blocks.TUFF)
                || st.is(Blocks.CLAY);
    }
}
