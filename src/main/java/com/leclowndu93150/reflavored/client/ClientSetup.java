package com.leclowndu93150.reflavored.client;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.client.renderer.*;
import com.leclowndu93150.reflavored.entity.ModBoatEntity;
import com.leclowndu93150.reflavored.entity.client.skunk.SkunkRenderer;
import com.leclowndu93150.reflavored.init.ModBlockEntities;
import com.leclowndu93150.reflavored.init.ModBlocks;
import com.leclowndu93150.reflavored.init.ModEntities;
import com.leclowndu93150.reflavored.init.ModWoodTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = Reflavored.MODID, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodTypes.REDWOOD);
            Sheets.addWoodType(ModWoodTypes.BAOBAB);
        });
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.MOD_BOAT.get(),
                context -> new ModBoatRenderer(context, false));
        event.registerEntityRenderer(ModEntities.MOD_CHEST_BOAT.get(),
                context -> new ModBoatRenderer(context, true));
        event.registerEntityRenderer(ModEntities.CUTTHROAT_TROUT.get(), CutthroatTroutRenderer::new);
        event.registerEntityRenderer(ModEntities.BISON.get(), BisonRenderer::new);
        event.registerEntityRenderer(ModEntities.SKUNK.get(), SkunkRenderer::new);
        
        event.registerEntityRenderer(EntityType.FOX, ModFoxRenderer::new);
        
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);


        event.registerBlockEntityRenderer(ModBlockEntities.LAVENDER_SHULKER_BOX.get(), LavenderShulkerBoxRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.LAVENDER_BED.get(), LavenderBedRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tint) -> 
            level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(),
            ModBlocks.REDWOOD_LEAVES.get()
        );
        event.register((state, level, pos, tint) ->
            level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(),
            ModBlocks.BAOBAB_LEAVES.get()
        );
        event.register((state, level, pos, tint) ->
            level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.getDefaultColor(),
            ModBlocks.OAT_GRASS.get()
        );
        event.register((state, level, pos, tint) ->
            tint != 0 ? -1 : level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(),
            ModBlocks.FLOWERING_ACACIA_LEAVES.get()
        );
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tint) -> FoliageColor.getDefaultColor(),
            ModBlocks.REDWOOD_LEAVES.get()
        );
        event.register((stack, tint) -> FoliageColor.getDefaultColor(),
            ModBlocks.BAOBAB_LEAVES.get()
        );
        event.register((stack, tint) -> GrassColor.getDefaultColor(),
            ModBlocks.OAT_GRASS.get()
        );
        event.register((stack, tint) -> FoliageColor.getDefaultColor(),
            ModBlocks.FLOWERING_ACACIA_LEAVES.get()
        );
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (ModBoatEntity.Type type : ModBoatEntity.Type.values()) {
            event.registerLayerDefinition(ModBoatRenderer.createBoatModelLocation(type), BoatModel::createBodyModel);
            event.registerLayerDefinition(ModBoatRenderer.createChestBoatModelLocation(type), ChestBoatModel::createBodyModel);
        }
    }
}
