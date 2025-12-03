package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Redflavored;
import com.leclowndu93150.reflavored.block.entity.LavenderBedBlockEntity;
import com.leclowndu93150.reflavored.block.entity.LavenderShulkerBoxBlockEntity;
import com.leclowndu93150.reflavored.block.entity.ModHangingSignBlockEntity;
import com.leclowndu93150.reflavored.block.entity.ModSignBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = 
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Redflavored.MODID);

    public static final Supplier<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = BLOCK_ENTITIES.register("mod_sign",
            () -> BlockEntityType.Builder.of(ModSignBlockEntity::new,
                    ModBlocks.REDWOOD_SIGN.get(), ModBlocks.REDWOOD_WALL_SIGN.get()).build(null));

    public static final Supplier<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITIES.register("mod_hanging_sign",
            () -> BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                    ModBlocks.REDWOOD_HANGING_SIGN.get(), ModBlocks.REDWOOD_WALL_HANGING_SIGN.get()).build(null));

    public static final Supplier<BlockEntityType<LavenderShulkerBoxBlockEntity>> LAVENDER_SHULKER_BOX =
            BLOCK_ENTITIES.register(
                    "lavender_shulker_box",
                    () -> BlockEntityType.Builder.of(
                            LavenderShulkerBoxBlockEntity::new,
                            ModBlocks.LAVENDER_SHULKER_BOX.get()
                    ).build(null)
            );


    public static final Supplier<BlockEntityType<LavenderBedBlockEntity>> LAVENDER_BED =
            BLOCK_ENTITIES.register(
                    "lavender_bed",
                    () -> BlockEntityType.Builder.of(
                            LavenderBedBlockEntity::new,
                            ModBlocks.LAVENDER_BED.get()
                    ).build(null)
            );

}
