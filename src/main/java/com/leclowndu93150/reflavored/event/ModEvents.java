package com.leclowndu93150.reflavored.event;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.duck.IGreyFoxAccessor;
import com.leclowndu93150.reflavored.effect.goal.AvoidStinkyGoal;
import com.leclowndu93150.reflavored.entity.BisonEntity;
import com.leclowndu93150.reflavored.init.ModBiomes;
import com.leclowndu93150.reflavored.init.ModBlocks;
import com.leclowndu93150.reflavored.init.ModEffects;
import com.leclowndu93150.reflavored.init.ModEntities;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Salmon;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

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
        if (entity instanceof Rabbit rabbit && !event.getLevel().isClientSide()) {
            Holder<Biome> biome = event.getLevel().getBiome(rabbit.blockPosition());
            if (biome.is(ModBiomes.REDWOOD_FOREST)) {
                rabbit.setVariant(Rabbit.Variant.BROWN);
            }
        }
        if (entity instanceof PathfinderMob mob && !(entity instanceof Warden)) {
            boolean hasAvoidStinky = mob.goalSelector.getAvailableGoals().stream()
                    .anyMatch(goal -> goal.getGoal() instanceof AvoidStinkyGoal);
            if (!hasAvoidStinky) {
                mob.goalSelector.addGoal(2, new AvoidStinkyGoal(mob));
            }
        }
        if (entity instanceof Wolf wolf) {
            boolean huntsBison = wolf.targetSelector.getAvailableGoals().stream()
                    .anyMatch(goal -> goal.getGoal() instanceof BisonHuntGoal);
            if (!huntsBison) {
                wolf.targetSelector.addGoal(5, new BisonHuntGoal(wolf));
            }
        }
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof Warden warden) || warden.level().isClientSide() || warden.tickCount % 20 != 0) {
            return;
        }

        LivingEntity currentTarget = warden.getTarget();
        if (currentTarget != null && currentTarget.hasEffect(ModEffects.STINK_EFFECT)) {
            warden.increaseAngerAt(currentTarget, 35, true);
            return;
        }

        LivingEntity livingEntity = warden.level().getNearestEntity(
                warden.level().getEntitiesOfClass(LivingEntity.class,
                        warden.getBoundingBox().inflate(32.0D, 8.0D, 32.0D),
                        candidate -> candidate != warden
                                && candidate.hasEffect(ModEffects.STINK_EFFECT)
                                && warden.canTargetEntity(candidate)),
                TargetingConditions.forCombat().range(32.0D),
                warden,
                warden.getX(),
                warden.getY(),
                warden.getZ());

        if (livingEntity != null) {
            warden.increaseAngerAt(livingEntity, 35, true);
        }
    }

    @SubscribeEvent
    public static void onBlockEntityTypeAddBlocks(BlockEntityTypeAddBlocksEvent event) {
        event.modify(BlockEntityType.SHULKER_BOX, ModBlocks.LAVENDER_SHULKER_BOX.get());
        event.modify(BlockEntityType.BED, ModBlocks.LAVENDER_BED.get());
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SKUNK.get(), PathfinderMob.createMobAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.MOVEMENT_SPEED, 0.125F).build());
        event.put(ModEntities.CUTTHROAT_TROUT.get(), Salmon.createAttributes().build());
        event.put(ModEntities.BISON.get(), Cow.createAttributes().add(Attributes.MAX_HEALTH, 22.0).add(Attributes.MOVEMENT_SPEED, 0.22).build());
    }

    @SubscribeEvent
    public static void registerSpawnRules(RegisterSpawnPlacementsEvent event) {
        event.register(ModEntities.SKUNK.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, serverLevelAccessor, mobSpawnType, blockPos, randomSource) -> canSpawnSkunkOnNaturalSoil(serverLevelAccessor, blockPos), RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.BISON.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, serverLevelAccessor, mobSpawnType, blockPos, randomSource) -> canSpawnBisonOnNaturalSoil(serverLevelAccessor, blockPos), RegisterSpawnPlacementsEvent.Operation.REPLACE);
        event.register(ModEntities.CUTTHROAT_TROUT.get(), SpawnPlacementTypes.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, serverLevelAccessor, mobSpawnType, blockPos, randomSource) -> serverLevelAccessor.getFluidState(blockPos).is(FluidTags.WATER) && serverLevelAccessor.getFluidState(blockPos.below()).is(FluidTags.WATER), RegisterSpawnPlacementsEvent.Operation.OR);
        event.register(EntityType.BEE, SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (entityType, serverLevelAccessor, mobSpawnType, blockPos, randomSource) -> true, RegisterSpawnPlacementsEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void addItemsToVanillaTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.SPAWN_EGGS)) {
            event.accept((ItemLike) ModItems.SKUNK_SPAWN_EGG.get());
            event.accept((ItemLike) ModItems.CUTTHROAT_TROUT_SPAWN_EGG.get());
            event.accept((ItemLike) ModItems.BISON_SPAWN_EGG.get());
        }
    }

    private static boolean canSpawnBisonOnNaturalSoil(ServerLevelAccessor level, BlockPos pos) {
        return level.getRawBrightness(pos, 0) >= 7 && isNaturalSoil(level.getBlockState(pos.below()));
    }

    private static boolean canSpawnSkunkOnNaturalSoil(ServerLevelAccessor level, BlockPos pos) {
        return isNaturalSoil(level.getBlockState(pos.below()));
    }

    private static boolean isNaturalSoil(BlockState below) {
        return below.is(BlockTags.ANIMALS_SPAWNABLE_ON)
                || below.is(Blocks.PODZOL)
                || below.is(Blocks.COARSE_DIRT)
                || below.is(Blocks.MYCELIUM);
    }

    private static class BisonHuntGoal extends NearestAttackableTargetGoal<BisonEntity> {
        public BisonHuntGoal(Wolf wolf) {
            super(wolf, BisonEntity.class, true);
        }
    }
}
