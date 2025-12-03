package com.leclowndu93150.reflavored.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class LavenderCandleBlock extends CandleBlock {
    public LavenderCandleBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack held = player.getItemInHand(hand);
        if (!state.getValue(LIT) && held.getItem() == Items.FLINT_AND_STEEL) {
            if (!level.isClientSide) {
                level.setBlock(pos, state.setValue(LIT, true), 3);
                held.hurtAndBreak(1, player, held.getEquipmentSlot());
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        } else if (state.getValue(LIT) && held.getItem() == Items.WATER_BUCKET) {
            if (!level.isClientSide) {
                level.setBlock(pos, state.setValue(LIT, false), 3);
            }
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.useItemOn(stack, state, level, pos, player, hand, hit);
    }
}
