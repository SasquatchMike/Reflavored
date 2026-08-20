package com.leclowndu93150.reflavored.entity;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.init.ModBlocks;
import com.leclowndu93150.reflavored.init.ModEntities;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

public class ModBoatEntity extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.INT);

    public ModBoatEntity(EntityType<? extends Boat> entityType, Level level) {
        super(ModEntities.MOD_BOAT.get(), level);
    }

    public ModBoatEntity(Level level, double x, double y, double z) {
        this(ModEntities.MOD_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, Type.REDWOOD.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag p_38359_) {
        super.addAdditionalSaveData(p_38359_);
        p_38359_.putInt("Type", this.getModdedType().ordinal());
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag p_38338_) {
        super.readAdditionalSaveData(p_38338_);
        if (p_38338_.contains("Type")) {
            this.setModdedType(ModBoatEntity.Type.byId(p_38338_.getInt("Type")));
        }
    }

    @Override
    public @NotNull Item getDropItem() {
        return switch (this.getModdedType()) {
            case REDWOOD -> ModItems.REDWOOD_BOAT.get();
            case BAOBAB -> ModItems.BAOBAB_BOAT.get();
        };
    }

    @Override
    public @NotNull EntityType<?> getType() {
        return super.getType();
    }

    public ModBoatEntity.Type getModdedType() {
        return ModBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    public void setModdedType(Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    @Override
    public @NotNull Component getName() {
        return Component.translatable("entity." + Reflavored.MODID + ".mod_boat." + this.getModdedType().getSerializedName());
    }

    public enum Type implements StringRepresentable {
        REDWOOD(ModBlocks.REDWOOD_PLANKS.get(), "redwood"),
        BAOBAB(ModBlocks.BAOBAB_PLANKS.get(), "baobab");

        private static final IntFunction<ModBoatEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, ModBoatEntity.Type.values(), ByIdMap.OutOfBoundsStrategy.ZERO);
        private final Block planks;
        private final String name;

        Type(Block planks, String name) {
            this.planks = planks;
            this.name = name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public static ModBoatEntity.Type byId(int id) {
            return BY_ID.apply(id);
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }
}
