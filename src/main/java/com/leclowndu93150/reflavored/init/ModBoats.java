package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.RedwoodForest;
import com.leclowndu93150.reflavored.entity.ModBoatEntity;
import com.leclowndu93150.reflavored.entity.ModChestBoatEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBoats {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = 
            DeferredRegister.create(Registries.ENTITY_TYPE, RedwoodForest.MODID);

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
}
