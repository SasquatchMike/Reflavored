package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.RedwoodForest;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType REDWOOD = WoodType.register(new WoodType(RedwoodForest.MODID + ":redwood", BlockSetType.OAK));
}
