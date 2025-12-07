package com.leclowndu93150.reflavored.client.renderer;

import com.leclowndu93150.reflavored.block.entity.LavenderShulkerBoxBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.ShulkerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.state.BlockState;

public class LavenderShulkerBoxRenderer implements BlockEntityRenderer<LavenderShulkerBoxBlockEntity> {
    private static final ResourceLocation LAVENDER_TEXTURE = ResourceLocation.fromNamespaceAndPath("reflavored", "textures/entity/shulker/lavender_shulker_box");
    private final ShulkerModel<?> model;

    public LavenderShulkerBoxRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new ShulkerModel<>(context.bakeLayer(ModelLayers.SHULKER));
    }

    @Override
    public void render(LavenderShulkerBoxBlockEntity blockEntity, float partialTick, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
        ResourceLocation texture = ResourceLocation.fromNamespaceAndPath("reflavored", "textures/entity/shulker/lavender_shulker_box");


        Direction direction = Direction.UP;
        BlockState state = blockEntity.getLevel().getBlockState(blockEntity.getBlockPos());
        if (state.getBlock() instanceof ShulkerBoxBlock) {
            direction = state.getValue(ShulkerBoxBlock.FACING);
        }

        stack.pushPose();
        stack.translate(0.5F, 0.5F, 0.5F);
        stack.scale(0.9995F, 0.9995F, 0.9995F);
        stack.mulPose(direction.getRotation());
        stack.scale(1.0F, -1.0F, -1.0F);
        stack.translate(0.0F, -1.0F, 0.0F);

        ModelPart lid = this.model.getLid();
        lid.setPos(0.0F, 24.0F - blockEntity.getProgress(partialTick) * 0.5F * 16.0F, 0.0F);
        lid.yRot = 270.0F * blockEntity.getProgress(partialTick) * ((float)Math.PI / 180F);
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(texture));
        this.model.renderToBuffer(stack, vertexConsumer, packedLight, packedOverlay);

        stack.popPose();
    }
}