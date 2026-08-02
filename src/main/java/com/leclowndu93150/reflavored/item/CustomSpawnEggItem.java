package com.leclowndu93150.reflavored.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredHolder;

public class CustomSpawnEggItem extends SpawnEggItem {
    public CustomSpawnEggItem(DeferredHolder<EntityType<?>, ? extends EntityType<? extends Mob>> entityType,
                              int primaryColor, int secondaryColor, Item.Properties properties) {
        super(entityType.get(), primaryColor, secondaryColor, properties);
    }

    @Override
    public int getColor(int tintIndex) {
        return 0xFFFFFF;
    }
}
