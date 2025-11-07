package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.RedwoodForest;
import com.leclowndu93150.reflavored.block.entity.ModHangingSignBlockEntity;
import com.leclowndu93150.reflavored.block.entity.ModSignBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = 
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, RedwoodForest.MODID);

    public static final Supplier<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = BLOCK_ENTITIES.register("mod_sign",
            () -> BlockEntityType.Builder.of(ModSignBlockEntity::new,
                    ModBlocks.REDWOOD_SIGN.get(), ModBlocks.REDWOOD_WALL_SIGN.get()).build(null));

    public static final Supplier<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITIES.register("mod_hanging_sign",
            () -> BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                    ModBlocks.REDWOOD_HANGING_SIGN.get(), ModBlocks.REDWOOD_WALL_HANGING_SIGN.get()).build(null));
}
