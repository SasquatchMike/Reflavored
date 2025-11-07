package com.leclowndu93150.reflavored.client.renderer;

import com.leclowndu93150.reflavored.RedwoodForest;
import com.leclowndu93150.reflavored.entity.ModBoatEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModBoatRenderer extends BoatRenderer {

    public ModBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context, hasChest);
    }

    @Override
    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if (boat instanceof ModBoatEntity) {
            Pair<ResourceLocation, ListModel<Boat>> vanillaPair = super.getModelWithLocation(boat);
            ResourceLocation customTexture = ResourceLocation.fromNamespaceAndPath(RedwoodForest.MODID,
                    "textures/entity/boat/redwood.png");
            return Pair.of(customTexture, vanillaPair.getSecond());
        }
        return super.getModelWithLocation(boat);
    }
}
