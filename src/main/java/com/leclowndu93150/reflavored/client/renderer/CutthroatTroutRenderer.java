package com.leclowndu93150.reflavored.client.renderer;

import com.leclowndu93150.reflavored.entity.CutthroatTroutEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CutthroatTroutRenderer extends GeoEntityRenderer<CutthroatTroutEntity> {
    public CutthroatTroutRenderer(EntityRendererProvider.Context context) {
        super(context, new CutthroatTroutModel());
        this.shadowRadius = 0.35F;
    }
}
