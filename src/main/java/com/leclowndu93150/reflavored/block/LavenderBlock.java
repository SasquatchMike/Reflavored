package com.leclowndu93150.reflavored.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class LavenderBlock extends BushBlock {
    public static final MapCodec<LavenderBlock> CODEC = simpleCodec(LavenderBlock::new);

    public LavenderBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }
}
