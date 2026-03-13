package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType REDWOOD = WoodType.register(new WoodType(Reflavored.MODID + ":redwood", BlockSetType.OAK));
}
