package com.leclowndu93150.reflavored.event;

import com.leclowndu93150.reflavored.Redflavored;
import com.leclowndu93150.reflavored.duck.IGreyFoxAccessor;
import com.leclowndu93150.reflavored.init.ModBiomes;
import com.leclowndu93150.reflavored.init.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = Redflavored.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Fox fox && !event.getLevel().isClientSide()) {
            IGreyFoxAccessor accessor = (IGreyFoxAccessor) fox;

            if (!accessor.reflavored$isGreyFox()) {
                Holder<Biome> biome = event.getLevel().getBiome(fox.blockPosition());

                if (biome.is(ModBiomes.REDWOOD_FOREST)) {
                    accessor.reflavored$setGreyFox(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBlockEntityTypeAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SHULKER_BOX, ModBlocks.LAVENDER_SHULKER_BOX.get());
        event.modify(BlockEntityType.BED, ModBlocks.LAVENDER_BED.get());
    }


}
