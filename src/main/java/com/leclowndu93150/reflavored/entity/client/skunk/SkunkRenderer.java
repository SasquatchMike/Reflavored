package com.leclowndu93150.reflavored.entity.client.skunk;

import com.leclowndu93150.reflavored.entity.SkunkEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SkunkRenderer extends GeoEntityRenderer<SkunkEntity> {
    public SkunkRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SkunkModel());
    }

    @Override
    public void preRender(PoseStack poseStack, SkunkEntity animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay, int colour) {
        if (animatable.isBaby()) {
            poseStack.scale(0.65F, 0.65F, 0.65F);
            this.shadowRadius = 0.25F;
        } else {
            this.shadowRadius = 0.4F;
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
