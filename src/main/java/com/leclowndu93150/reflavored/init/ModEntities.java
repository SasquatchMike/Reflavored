package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.entity.BisonEntity;
import com.leclowndu93150.reflavored.entity.CutthroatTroutEntity;
import com.leclowndu93150.reflavored.entity.ModBoatEntity;
import com.leclowndu93150.reflavored.entity.ModChestBoatEntity;
import com.leclowndu93150.reflavored.entity.SkunkEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
            DeferredRegister.create(Registries.ENTITY_TYPE, Reflavored.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<ModBoatEntity>> REDWOOD_BOAT = 
            ENTITY_TYPES.register("redwood_boat", 
                    () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build("redwood_boat"));

    public static final DeferredHolder<EntityType<?>, EntityType<ModChestBoatEntity>> REDWOOD_CHEST_BOAT = 
            ENTITY_TYPES.register("redwood_chest_boat", 
                    () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build("redwood_chest_boat"));

    public static final DeferredHolder<EntityType<?>, EntityType<SkunkEntity>> SKUNK =
            ENTITY_TYPES.register("skunk",
                    () -> EntityType.Builder.of(SkunkEntity::new, MobCategory.CREATURE)
                            .sized(1.25F, 0.625F)
                            .eyeHeight(0.34375F)
                            .clientTrackingRange(10)
                            .build("skunk"));

    public static final DeferredHolder<EntityType<?>, EntityType<CutthroatTroutEntity>> CUTTHROAT_TROUT =
            ENTITY_TYPES.register("cutthroat_trout",
                    () -> EntityType.Builder.of(CutthroatTroutEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.7F, 0.4F)
                            .clientTrackingRange(8)
                            .build("cutthroat_trout"));

    public static final DeferredHolder<EntityType<?>, EntityType<BisonEntity>> BISON =
            ENTITY_TYPES.register("bison",
                    () -> EntityType.Builder.of(BisonEntity::new, MobCategory.CREATURE)
                            .sized(1.4F, 1.8F)
                            .clientTrackingRange(10)
                            .build("bison"));
}
