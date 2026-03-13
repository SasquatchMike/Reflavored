package com.leclowndu93150.reflavored.client.renderer;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.block.entity.LavenderBedBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;

public class LavenderBedRenderer implements BlockEntityRenderer<LavenderBedBlockEntity> {

    public static final Material LAVENDER_BED_TEXTURE = new Material(
            Sheets.BED_SHEET,
            ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "entity/bed/lavender_bed")
    );

    private final ModelPart headRoot;
    private final ModelPart footRoot;

    public LavenderBedRenderer(BlockEntityRendererProvider.Context context) {
        this.headRoot = context.bakeLayer(ModelLayers.BED_HEAD);
        this.footRoot = context.bakeLayer(ModelLayers.BED_FOOT);
    }

    @Override
    public void render(LavenderBedBlockEntity bedEntity, float partialTick,
                       PoseStack poseStack, MultiBufferSource bufferSource,
                       int packedLight, int packedOverlay) {

        BlockState state = bedEntity.getBlockState();
        if (!(state.getBlock() instanceof BedBlock)) {
            return;
        }

        Direction facing = state.getValue(BedBlock.FACING);
        BedPart part = state.getValue(BedBlock.PART);
        ModelPart model = (part == BedPart.HEAD) ? this.headRoot : this.footRoot;

        poseStack.pushPose();
        poseStack.translate(0.0F, 0.5625F, 0.0F);
        poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
        poseStack.translate(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.ZP.rotationDegrees(180.0F + facing.toYRot()));
        poseStack.translate(-0.5F, -0.5F, -0.5F);

        VertexConsumer vc = LAVENDER_BED_TEXTURE.buffer(bufferSource, RenderType::entitySolid);
        model.render(poseStack, vc, packedLight, packedOverlay);

        poseStack.popPose();
    }
}
