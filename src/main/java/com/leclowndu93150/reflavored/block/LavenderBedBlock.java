package com.leclowndu93150.reflavored.block;

import com.leclowndu93150.reflavored.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LavenderBedBlock extends BedBlock {
    public LavenderBedBlock(Properties properties) {
        super(DyeColor.PURPLE, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.LAVENDER_BED.get().create(pos, state);
    }


}
