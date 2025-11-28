package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Redflavored;
import com.leclowndu93150.reflavored.block.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Redflavored.MODID);

    public static final DeferredBlock<LeavesBlock> REDWOOD_LEAVES = BLOCKS.register("redwood_leaves", 
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredBlock<RotatedPillarBlock> REDWOOD_LOG = BLOCKS.register("redwood_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final DeferredBlock<RotatedPillarBlock> REDWOOD_WOOD = BLOCKS.register("redwood_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_REDWOOD_LOG = BLOCKS.register("stripped_redwood_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_REDWOOD_WOOD = BLOCKS.register("stripped_redwood_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final DeferredBlock<Block> REDWOOD_PLANKS = BLOCKS.registerSimpleBlock("redwood_planks",
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredBlock<SlabBlock> REDWOOD_SLAB = BLOCKS.register("redwood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));

    public static final DeferredBlock<StairBlock> REDWOOD_STAIRS = BLOCKS.register("redwood_stairs",
            () -> new StairBlock(REDWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));

    public static final DeferredBlock<DoorBlock> REDWOOD_DOOR = BLOCKS.register("redwood_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));

    public static final DeferredBlock<TrapDoorBlock> REDWOOD_TRAPDOOR = BLOCKS.register("redwood_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));

    public static final DeferredBlock<FenceBlock> REDWOOD_FENCE = BLOCKS.register("redwood_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));

    public static final DeferredBlock<FenceGateBlock> REDWOOD_FENCE_GATE = BLOCKS.register("redwood_fence_gate",
            () -> new FenceGateBlock(ModWoodTypes.REDWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));

    public static final DeferredBlock<PressurePlateBlock> REDWOOD_PRESSURE_PLATE = BLOCKS.register("redwood_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));

    public static final DeferredBlock<ButtonBlock> REDWOOD_BUTTON = BLOCKS.register("redwood_button",
            () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));

    public static final DeferredBlock<ModStandingSignBlock> REDWOOD_SIGN = BLOCKS.register("redwood_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodTypes.REDWOOD));

    public static final DeferredBlock<ModWallSignBlock> REDWOOD_WALL_SIGN = BLOCKS.register("redwood_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), ModWoodTypes.REDWOOD));

    public static final DeferredBlock<ModCeilingHangingSignBlock> REDWOOD_HANGING_SIGN = BLOCKS.register("redwood_hanging_sign",
            () -> new ModCeilingHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.REDWOOD));

    public static final DeferredBlock<ModWallHangingSignBlock> REDWOOD_WALL_HANGING_SIGN = BLOCKS.register("redwood_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.REDWOOD));

    public static final DeferredBlock<SaplingBlock> REDWOOD_SAPLING = BLOCKS.register("redwood_sapling",
            () -> new SaplingBlock(ModTreeGrowers.REDWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<FlowerBlock> DOUGLAS_IRIS = BLOCKS.register("douglas_iris",
            () -> new FlowerBlock(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.ABSORPTION.value()), 6.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));

    public static final DeferredBlock<FlowerBlock> TRILLIUM = BLOCKS.register("trillium",
            () -> new FlowerBlock(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.REGENERATION.value()), 4.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));

    public static final DeferredBlock<AlpineLilyBlock> ALPINE_LILY = BLOCKS.register("alpine_lily",
            () -> new AlpineLilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER)));

    public static final DeferredBlock<OrangeLilyBlock> ORANGE_LILY = BLOCKS.register("orange_rose_bush",
            () -> new OrangeLilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER)));

    public static final DeferredBlock<YellowLilyBlock> YELLOW_LILY = BLOCKS.register("yellow_rose_bush",
            () -> new YellowLilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER)));

    public static final DeferredBlock<PinkLilyBlock> PINK_LILY = BLOCKS.register("pink_rose_bush",
            () -> new PinkLilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER)));

    public static final DeferredBlock<BushBlock> LAVENDER = BLOCKS.register(
            "lavender",
            () -> new BushBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)) {
                @Override
                protected MapCodec<? extends BushBlock> codec() {
                    return null;
                }
            }
    );


//    public static final DeferredBlock<Block> LAVENDER_WOOL = BLOCKS.registerSimpleBlock(
//            "lavender_wool",
//            BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)
//    );
//
//    public static final DeferredBlock<Block> LAVENDER_TERRACOTTA = BLOCKS.registerSimpleBlock(
//            "lavender_terracotta",
//            BlockBehaviour.Properties.ofFullCopy(Blocks.TERRACOTTA)
//    );
//
//    public static final DeferredBlock<Block> LAVENDER_CONCRETE = BLOCKS.registerSimpleBlock(
//            "lavender_concrete",
//            BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE)
//    );
//
//    public static final DeferredBlock<ConcretePowderBlock> LAVENDER_CONCRETE_POWDER = BLOCKS.register(
//            "lavender_concrete_powder",
//            () -> new ConcretePowderBlock(
//                    Blocks.WHITE_CONCRETE,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CONCRETE_POWDER)
//            )
//    );
//
//    public static final DeferredBlock<StainedGlassBlock> LAVENDER_GLASS = BLOCKS.register(
//            "lavender_glass",
//            () -> new StainedGlassBlock(
//                    DyeColor.PURPLE,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)
//            )
//    );
//
//    public static final DeferredBlock<StainedGlassBlock> LAVENDER_GLASS_TOP = BLOCKS.register(
//            "lavender_glass_top",
//            () -> new StainedGlassBlock(
//                    DyeColor.PURPLE,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_STAINED_GLASS)
//            )
//    );
//
//    public static final DeferredBlock<GlazedTerracottaBlock> LAVENDER_GLAZED_TERRACOTTA = BLOCKS.register(
//            "lavender_glazed_terracotta",
//            () -> new GlazedTerracottaBlock(
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_GLAZED_TERRACOTTA)
//            )
//    );
//
//    public static final DeferredBlock<CandleBlock> LAVENDER_CANDLE = BLOCKS.register(
//            "lavender_candle",
//            () -> new CandleBlock(
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.CANDLE)
//            )
//    );
//
//    public static final DeferredBlock<BedBlock> LAVENDER_BED = BLOCKS.register(
//            "lavender_bed",
//            () -> new BedBlock(
//                    DyeColor.PURPLE,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_BED)
//            )
//    );
//
//    public static final DeferredBlock<ShulkerBoxBlock> LAVENDER_SHULKER_BOX = BLOCKS.register(
//            "lavender_shulker_box",
//            () -> new ShulkerBoxBlock(
//                    DyeColor.PURPLE,
//                    BlockBehaviour.Properties.ofFullCopy(Blocks.SHULKER_BOX)
//            )
//    );



    public static final DeferredBlock<FlowerPotBlock> POTTED_DOUGLAS_IRIS =
            BLOCKS.register("potted_douglas_iris",
                    () -> new FlowerPotBlock(
                            () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                            ModBlocks.DOUGLAS_IRIS,
                            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY)));

    public static final DeferredBlock<FlowerPotBlock> POTTED_TRILLIUM =
            BLOCKS.register("potted_trillium",
                    () -> new FlowerPotBlock(
                            () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                            ModBlocks.TRILLIUM,
                            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY)));

    public static final DeferredBlock<FlowerPotBlock> POTTED_LAVENDER =
            BLOCKS.register("potted_lavender",
                    () -> new FlowerPotBlock(
                            () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                            ModBlocks.LAVENDER,
                            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY)));

    public static final DeferredBlock<Block> MOSSY_STONE = BLOCKS.registerSimpleBlock("mossy_stone",
            BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));

    public static final DeferredBlock<Block> MOSSY_ANDESITE = BLOCKS.registerSimpleBlock("mossy_andesite",
            BlockBehaviour.Properties.ofFullCopy(Blocks.ANDESITE));

}
