package com.leclowndu93150.reflavored.block.entity;

import com.leclowndu93150.reflavored.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LavenderShulkerBoxBlockEntity extends ShulkerBoxBlockEntity {
    public LavenderShulkerBoxBlockEntity(BlockPos pos, BlockState state) {
        super(null, pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.LAVENDER_SHULKER_BOX.get();
    }

}