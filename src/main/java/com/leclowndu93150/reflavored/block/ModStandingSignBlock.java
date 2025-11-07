package com.leclowndu93150.reflavored.block;

import com.leclowndu93150.reflavored.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModStandingSignBlock extends StandingSignBlock {
    public ModStandingSignBlock(Properties properties, WoodType woodType) {
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.MOD_SIGN.get().create(pos, state);
    }
}
