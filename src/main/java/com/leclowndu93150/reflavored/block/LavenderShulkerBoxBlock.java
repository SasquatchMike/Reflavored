package com.leclowndu93150.reflavored.block;

import com.leclowndu93150.reflavored.block.entity.LavenderShulkerBoxBlockEntity;
import com.leclowndu93150.reflavored.init.ModBlockEntities;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

    @Override
    public ItemStack getCloneItemStack(LevelReader p_304539_, BlockPos p_56203_, BlockState p_56204_) {
        return new ItemStack(ModItems.LAVENDER_SHULKER_BOX.get());
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ModBlockEntities.LAVENDER_SHULKER_BOX.get(), LavenderShulkerBoxBlockEntity::tick);
    }
}