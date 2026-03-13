package com.leclowndu93150.reflavored.entity.client.skunk;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.entity.SkunkEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SkunkModel extends GeoModel<SkunkEntity> {

    @Override
    public ResourceLocation getModelResource(SkunkEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "geo/skunk.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SkunkEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "textures/entity/skunk.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SkunkEntity animatable) {
        return null;
    }
}
