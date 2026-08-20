package com.leclowndu93150.reflavored.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.entity.ModBoatEntity;
import com.leclowndu93150.reflavored.entity.ModChestBoatEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.stream.Stream;

public class ModBoatRenderer extends BoatRenderer {
    private final Map<ModBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public ModBoatRenderer(EntityRendererProvider.Context p_234563_, boolean p_234564_) {
        super(p_234563_, p_234564_);
        this.boatResources = Stream.of(ModBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type, type -> Pair.of(getTextureLocation(type, p_234564_), createBoatModels(p_234563_, type, p_234564_))));
    }

    private static ResourceLocation getTextureLocation(ModBoatEntity.Type type, boolean hasChest) {
        String prefix = hasChest ? "chest_" : "";
        return ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "textures/entity/" + prefix + "boat/" + type.getSerializedName() + ".png");
    }

    private static ModelLayerLocation createLocation(String path) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, path), "main");
    }

    public static ModelLayerLocation createBoatModelLocation(ModBoatEntity.Type type) {
        return createLocation("boat/" + type.getSerializedName());
    }

    public static ModelLayerLocation createChestBoatModelLocation(ModBoatEntity.Type type) {
        return createLocation("chest_boat/" + type.getSerializedName());
    }

    private ListModel<Boat> createBoatModels(EntityRendererProvider.Context context, ModBoatEntity.Type type, boolean chestBoat) {
        ModelLayerLocation layerLocation = chestBoat ? createChestBoatModelLocation(type) : createBoatModelLocation(type);
        ModelPart modelPart = context.bakeLayer(layerLocation);
        return chestBoat ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }

    @Override
    public @NotNull Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(@NotNull Boat boat) {
        if (boat instanceof ModChestBoatEntity modChestBoatEntity) {
            return this.boatResources.get(modChestBoatEntity.getModdedType());
        }
        return this.boatResources.get(((ModBoatEntity) boat).getModdedType());
    }
}
