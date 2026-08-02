package com.leclowndu93150.reflavored.client.renderer;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.entity.CutthroatTroutEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CutthroatTroutModel extends GeoModel<CutthroatTroutEntity> {
    @Override
    public ResourceLocation getModelResource(CutthroatTroutEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "geo/cutthroat_trout.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CutthroatTroutEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "textures/entity/cutthroat_trout.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CutthroatTroutEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "animations/cutthroat_trout.animation.json");
    }
}
