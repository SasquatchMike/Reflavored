package com.leclowndu93150.reflavored.client.renderer;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.entity.BisonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class BisonModel extends GeoModel<BisonEntity> {
    @Override
    public ResourceLocation getModelResource(BisonEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "geo/bison.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BisonEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "textures/entity/bison.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BisonEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "animations/bison.animation.json");
    }
}
