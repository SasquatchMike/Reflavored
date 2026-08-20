package com.leclowndu93150.reflavored.entity;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.init.ModEntities;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ModChestBoatEntity extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModChestBoatEntity.class, EntityDataSerializers.INT);

    public ModChestBoatEntity(EntityType<? extends Boat> entityType, Level level) {
        super(ModEntities.MOD_CHEST_BOAT.get(), level);
    }

    public ModChestBoatEntity(Level level, double x, double y, double z) {
        this(ModEntities.MOD_CHEST_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, ModBoatEntity.Type.REDWOOD.ordinal());
    }

    public ModBoatEntity.Type getModdedType() {
        return ModBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    public void setModdedVariant(ModBoatEntity.Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag p_219908_) {
        super.addAdditionalSaveData(p_219908_);
        p_219908_.putInt("Type", this.getModdedType().ordinal());
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag p_219901_) {
        super.readAdditionalSaveData(p_219901_);
        if (p_219901_.contains("Type")) {
            this.setModdedVariant(ModBoatEntity.Type.byId(p_219901_.getInt("Type")));
        }
    }

    @Override
    public @NotNull Item getDropItem() {
        return switch (this.getModdedType()) {
            case REDWOOD -> ModItems.REDWOOD_CHEST_BOAT.get();
            case BAOBAB -> ModItems.BAOBAB_CHEST_BOAT.get();
        };
    }

    @Override
    public @NotNull Component getName() {
        return Component.translatable("entity." + Reflavored.MODID + ".mod_chest_boat." + this.getModdedType().getSerializedName());
    }
}
