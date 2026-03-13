package com.leclowndu93150.reflavored.entity.client.skunk;

import com.leclowndu93150.reflavored.entity.SkunkEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkunkRenderer extends GeoEntityRenderer<SkunkEntity> {
    public SkunkRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SkunkModel());
    }
}
