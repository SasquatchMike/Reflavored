package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final BlockSetType REDWOOD_SET = BlockSetType.register(new BlockSetType(Reflavored.MODID + ":redwood"));
    public static final WoodType REDWOOD = WoodType.register(new WoodType(Reflavored.MODID + ":redwood", REDWOOD_SET));

    public static final BlockSetType BAOBAB_SET = BlockSetType.register(new BlockSetType(Reflavored.MODID + ":baobab"));
    public static final WoodType BAOBAB = WoodType.register(new WoodType(Reflavored.MODID + ":baobab", BAOBAB_SET));
}
