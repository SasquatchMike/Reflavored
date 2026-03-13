package com.leclowndu93150.reflavored.event;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.duck.IGreyFoxAccessor;
import com.leclowndu93150.reflavored.effect.goal.AvoidStinkyGoal;
import com.leclowndu93150.reflavored.entity.client.skunk.SkunkRenderer;
import com.leclowndu93150.reflavored.init.ModBiomes;
import com.leclowndu93150.reflavored.init.ModBlocks;
import com.leclowndu93150.reflavored.init.ModEntities;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = Reflavored.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Fox fox && !event.getLevel().isClientSide()) {
            IGreyFoxAccessor accessor = (IGreyFoxAccessor) fox;

            if (!accessor.reflavored$isGreyFox()) {
                Holder<Biome> biome = event.getLevel().getBiome(fox.blockPosition());

                if (biome.is(ModBiomes.REDWOOD_FOREST)) {
                    accessor.reflavored$setGreyFox(true);
                }
            }
        }
        if (entity instanceof PathfinderMob mob) {
            mob.goalSelector.addGoal(1, new AvoidStinkyGoal(mob));
        }
    }

    @SubscribeEvent
    public static void onBlockEntityTypeAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SHULKER_BOX, ModBlocks.LAVENDER_SHULKER_BOX.get());
        event.modify(BlockEntityType.BED, ModBlocks.LAVENDER_BED.get());
    }

    @SubscribeEvent
    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SKUNK.get(), SkunkRenderer::new);
    }

    @SubscribeEvent
    private static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SKUNK.get(), PathfinderMob.createMobAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.MOVEMENT_SPEED, 0.3F).build());
    }

}
