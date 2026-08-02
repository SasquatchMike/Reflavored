package com.leclowndu93150.reflavored.client.renderer;

import com.leclowndu93150.reflavored.entity.BisonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BisonRenderer extends GeoEntityRenderer<BisonEntity> {
    public BisonRenderer(EntityRendererProvider.Context context) {
        super(context, new BisonModel());
        this.shadowRadius = 0.85F;
    }

    @Override
    public void preRender(PoseStack poseStack, BisonEntity animatable, BakedGeoModel model,
                          MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender,
                          float partialTick, int packedLight, int packedOverlay, int colour) {
        if (animatable.isBaby()) {
            poseStack.scale(0.55F, 0.55F, 0.55F);
            this.shadowRadius = 0.45F;
        } else {
            this.shadowRadius = 0.85F;
        }

        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
