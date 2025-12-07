package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.Redflavored;
import com.leclowndu93150.reflavored.init.ModBlocks;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, Redflavored.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + Redflavored.MODID, "Reflavored");

        add("biome.reflavored.redwood_forest", "Redwood Forest");
        add("biome.reflavored.lavender_fields", "Lavender Fields");

        addBlock(ModBlocks.REDWOOD_LOG, "Redwood Log");
        addBlock(ModBlocks.REDWOOD_WOOD, "Redwood Wood");
        addBlock(ModBlocks.STRIPPED_REDWOOD_LOG, "Stripped Redwood Log");
        addBlock(ModBlocks.STRIPPED_REDWOOD_WOOD, "Stripped Redwood Wood");
        addBlock(ModBlocks.REDWOOD_PLANKS, "Redwood Planks");
        addBlock(ModBlocks.REDWOOD_LEAVES, "Redwood Leaves");
        addBlock(ModBlocks.REDWOOD_SAPLING, "Redwood Sapling");
        addBlock(ModBlocks.REDWOOD_STAIRS, "Redwood Stairs");
        addBlock(ModBlocks.REDWOOD_SLAB, "Redwood Slab");
        addBlock(ModBlocks.REDWOOD_FENCE, "Redwood Fence");
        addBlock(ModBlocks.REDWOOD_FENCE_GATE, "Redwood Fence Gate");
        addBlock(ModBlocks.REDWOOD_DOOR, "Redwood Door");
        addBlock(ModBlocks.REDWOOD_TRAPDOOR, "Redwood Trapdoor");
        addBlock(ModBlocks.REDWOOD_PRESSURE_PLATE, "Redwood Pressure Plate");
        addBlock(ModBlocks.REDWOOD_BUTTON, "Redwood Button");

        addBlock(ModBlocks.LAVENDER_WOOL, "Lavender Wool");
        addBlock(ModBlocks.LAVENDER_TERRACOTTA, "Lavender Terracotta");
        addBlock(ModBlocks.LAVENDER_GLAZED_TERRACOTTA, "Lavender Glazed Terracotta");
        addBlock(ModBlocks.LAVENDER_CONCRETE, "Lavender Concrete");
        addBlock(ModBlocks.LAVENDER_CONCRETE_POWDER, "Lavender Concrete Powder");

        addBlock(ModBlocks.LAVENDER_BED, "Lavender Bed");
        addBlock(ModBlocks.LAVENDER_CANDLE, "Lavender Candle");
        addBlock(ModBlocks.LAVENDER_SHULKER_BOX, "Lavender Shulker Box");

        addItem(ModItems.REDWOOD_SIGN, "Redwood Sign");
        addItem(ModItems.REDWOOD_HANGING_SIGN, "Redwood Hanging Sign");
        addItem(ModItems.REDWOOD_BOAT, "Redwood Boat");
        addItem(ModItems.REDWOOD_CHEST_BOAT, "Redwood Boat with Chest");

        addBlock(ModBlocks.DOUGLAS_IRIS, "Douglas Iris");
        addBlock(ModBlocks.TRILLIUM, "Trillium");
        addBlock(ModBlocks.ALPINE_LILY, "Alpine Lily");

        addBlock(ModBlocks.ORANGE_LILY, "Orange Rose Bush");
        addBlock(ModBlocks.YELLOW_LILY, "Yellow Rose Bush");
        addBlock(ModBlocks.PINK_LILY, "Pink Rose Bush");

        addBlock(ModBlocks.LAVENDER, "Lavender");
        addItem(ModItems.LAVENDER_DYE, "Lavender Dye");

        addBlock(ModBlocks.MOSSY_STONE, "Mossy Stone");
        addBlock(ModBlocks.MOSSY_ANDESITE, "Mossy Andesite");
        
        add("entity.redwood_forest.redwood_boat", "Redwood Boat");
        add("entity.redwood_forest.redwood_chest_boat", "Redwood Boat with Chest");
    }
}
