package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.RedwoodForest;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower REDWOOD = new TreeGrower(
            RedwoodForest.MODID + ":redwood",
            0.1F,
            Optional.of(ModConfiguredFeatures.MEGA_REDWOOD),
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.REDWOOD),
            Optional.empty(),
            Optional.empty(),
            Optional.empty()
    );
}
