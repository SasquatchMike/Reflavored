package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Redflavored;
import com.leclowndu93150.reflavored.item.ModBoatItem;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Redflavored.MODID);

    public static final DeferredItem<BlockItem> REDWOOD_LEAVES = ITEMS.registerSimpleBlockItem("redwood_leaves", ModBlocks.REDWOOD_LEAVES);
    public static final DeferredItem<BlockItem> REDWOOD_LOG = ITEMS.registerSimpleBlockItem("redwood_log", ModBlocks.REDWOOD_LOG);
    public static final DeferredItem<BlockItem> REDWOOD_WOOD = ITEMS.registerSimpleBlockItem("redwood_wood", ModBlocks.REDWOOD_WOOD);
    public static final DeferredItem<BlockItem> STRIPPED_REDWOOD_LOG = ITEMS.registerSimpleBlockItem("stripped_redwood_log", ModBlocks.STRIPPED_REDWOOD_LOG);
    public static final DeferredItem<BlockItem> STRIPPED_REDWOOD_WOOD = ITEMS.registerSimpleBlockItem("stripped_redwood_wood", ModBlocks.STRIPPED_REDWOOD_WOOD);
    public static final DeferredItem<BlockItem> REDWOOD_PLANKS = ITEMS.registerSimpleBlockItem("redwood_planks", ModBlocks.REDWOOD_PLANKS);
    public static final DeferredItem<BlockItem> REDWOOD_SLAB = ITEMS.registerSimpleBlockItem("redwood_slab", ModBlocks.REDWOOD_SLAB);
    public static final DeferredItem<BlockItem> REDWOOD_STAIRS = ITEMS.registerSimpleBlockItem("redwood_stairs", ModBlocks.REDWOOD_STAIRS);
    public static final DeferredItem<BlockItem> REDWOOD_DOOR = ITEMS.registerSimpleBlockItem("redwood_door", ModBlocks.REDWOOD_DOOR);
    public static final DeferredItem<BlockItem> REDWOOD_TRAPDOOR = ITEMS.registerSimpleBlockItem("redwood_trapdoor", ModBlocks.REDWOOD_TRAPDOOR);
    public static final DeferredItem<BlockItem> REDWOOD_FENCE = ITEMS.registerSimpleBlockItem("redwood_fence", ModBlocks.REDWOOD_FENCE);
    public static final DeferredItem<BlockItem> REDWOOD_FENCE_GATE = ITEMS.registerSimpleBlockItem("redwood_fence_gate", ModBlocks.REDWOOD_FENCE_GATE);
    public static final DeferredItem<BlockItem> REDWOOD_PRESSURE_PLATE = ITEMS.registerSimpleBlockItem("redwood_pressure_plate", ModBlocks.REDWOOD_PRESSURE_PLATE);
    public static final DeferredItem<BlockItem> REDWOOD_BUTTON = ITEMS.registerSimpleBlockItem("redwood_button", ModBlocks.REDWOOD_BUTTON);
    public static final DeferredItem<BlockItem> REDWOOD_SAPLING = ITEMS.registerSimpleBlockItem("redwood_sapling", ModBlocks.REDWOOD_SAPLING);

    public static final DeferredItem<SignItem> REDWOOD_SIGN = ITEMS.register("redwood_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.REDWOOD_SIGN.get(), ModBlocks.REDWOOD_WALL_SIGN.get()));

    public static final DeferredItem<HangingSignItem> REDWOOD_HANGING_SIGN = ITEMS.register("redwood_hanging_sign",
            () -> new HangingSignItem(ModBlocks.REDWOOD_HANGING_SIGN.get(), ModBlocks.REDWOOD_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));

    public static final DeferredItem<ModBoatItem> REDWOOD_BOAT = ITEMS.register("redwood_boat",
            () -> new ModBoatItem(false, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<ModBoatItem> REDWOOD_CHEST_BOAT = ITEMS.register("redwood_chest_boat",
            () -> new ModBoatItem(true, new Item.Properties().stacksTo(1)));

    public static final DeferredItem<BlockItem> DOUGLAS_IRIS = ITEMS.registerSimpleBlockItem("douglas_iris", ModBlocks.DOUGLAS_IRIS);
    public static final DeferredItem<BlockItem> TRILLIUM = ITEMS.registerSimpleBlockItem("trillium", ModBlocks.TRILLIUM);
    public static final DeferredItem<BlockItem> ALPINE_LILY = ITEMS.register("alpine_lily",
            () -> new DoubleHighBlockItem(ModBlocks.ALPINE_LILY.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> ORANGE_LILY = ITEMS.register("orange_rose_bush",
            () -> new DoubleHighBlockItem(ModBlocks.ORANGE_LILY.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> YELLOW_LILY = ITEMS.register("yellow_rose_bush",
            () -> new DoubleHighBlockItem(ModBlocks.YELLOW_LILY.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> PINK_LILY = ITEMS.register("pink_rose_bush",
            () -> new DoubleHighBlockItem(ModBlocks.PINK_LILY.get(), new Item.Properties()));

    // In your ModItems class
    public static final DeferredItem<BlockItem> LAVENDER_CANDLE = ITEMS.register("lavender_candle",
            () -> new BlockItem(ModBlocks.LAVENDER_CANDLE.get(), new Item.Properties()));

    public static final DeferredItem<BedItem> LAVENDER_BED = ITEMS.register("lavender_bed",
            () -> new BedItem(ModBlocks.LAVENDER_BED.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> LAVENDER_SHULKER_BOX = ITEMS.register("lavender_shulker_box",
            () -> new BlockItem(ModBlocks.LAVENDER_SHULKER_BOX.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> LAVENDER_WOOL = ITEMS.registerSimpleBlockItem("lavender_wool", ModBlocks.LAVENDER_WOOL);
    public static final DeferredItem<BlockItem> LAVENDER_TERRACOTTA = ITEMS.registerSimpleBlockItem("lavender_terracotta", ModBlocks.LAVENDER_TERRACOTTA);
    public static final DeferredItem<BlockItem> LAVENDER_GLAZED_TERRACOTTA = ITEMS.registerSimpleBlockItem("lavender_glazed_terracotta", ModBlocks.LAVENDER_GLAZED_TERRACOTTA);
    public static final DeferredItem<BlockItem> LAVENDER_CONCRETE = ITEMS.registerSimpleBlockItem("lavender_concrete", ModBlocks.LAVENDER_CONCRETE);
    public static final DeferredItem<BlockItem> LAVENDER_CONCRETE_POWDER = ITEMS.registerSimpleBlockItem("lavender_concrete_powder", ModBlocks.LAVENDER_CONCRETE_POWDER);


    public static final DeferredItem<BlockItem> LAVENDER = ITEMS.registerSimpleBlockItem("lavender", ModBlocks.LAVENDER);
}
