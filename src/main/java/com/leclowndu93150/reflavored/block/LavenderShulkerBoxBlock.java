package com.leclowndu93150.reflavored.block;

import com.leclowndu93150.reflavored.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class LavenderShulkerBoxBlock extends ShulkerBoxBlock {
    public LavenderShulkerBoxBlock(Properties props) {
        super(null, props);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntities.LAVENDER_SHULKER_BOX.get().create(pos, state);
    }
}