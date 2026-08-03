package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.init.ModBlocks;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, Reflavored.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + Reflavored.MODID, "Reflavored");

        add("biome.reflavored.redwood_forest", "Redwood Forest");
        add("biome.reflavored.lavender_fields", "Lavender Fields");
        add("biome.reflavored.geothermal_taiga", "Geothermal Taiga");

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

        addBlock(ModBlocks.OAT_GRASS, "Oat Grass");

        addBlock(ModBlocks.DOUGLAS_IRIS, "Douglas Iris");
        addBlock(ModBlocks.TRILLIUM, "Trillium");
        addBlock(ModBlocks.KING_PROTEA, "King Protea");
        addBlock(ModBlocks.HEATH_ASTER, "Heath Aster");

        addBlock(ModBlocks.ALPINE_LILY, "Alpine Lily");
        addBlock(ModBlocks.ORANGE_LILY, "Orange Rose Bush");
        addBlock(ModBlocks.YELLOW_LILY, "Yellow Rose Bush");
        addBlock(ModBlocks.PINK_LILY, "Pink Rose Bush");

        addBlock(ModBlocks.LAVENDER, "Lavender");
        addItem(ModItems.LAVENDER_DYE, "Lavender Dye");

        addBlock(ModBlocks.MOSSY_STONE, "Mossy Stone");
        addBlock(ModBlocks.MOSSY_ANDESITE, "Mossy Andesite");
        addBlock(ModBlocks.MOSSY_GRANITE, "Mossy Granite");

        addBlock(ModBlocks.GLACIER_LILY, "Glacier Lily");
        addBlock(ModBlocks.PAINTBRUSH_FLOWER, "Paintbrush Flower");

        addItem(ModItems.CUTTHROAT_TROUT, "Cutthroat Trout");
        addItem(ModItems.COOKED_CUTTHROAT_TROUT, "Cooked Cutthroat Trout");
        addItem(ModItems.CUTTHROAT_TROUT_BUCKET, "Bucket of Cutthroat Trout");
        addItem(ModItems.MUSIC_DISC_ARCHIES_LULLABY, "Music Disc");
        add("jukebox_song.reflavored.archies_lullaby", "KaktusDoesMusic - Archie’s Lullaby");
        addItem(ModItems.SKUNK_SPAWN_EGG, "Skunk Spawn Egg");
        addItem(ModItems.CUTTHROAT_TROUT_SPAWN_EGG, "Cutthroat Trout Spawn Egg");
        addItem(ModItems.BISON_SPAWN_EGG, "Bison Spawn Egg");

        add("entity." + Reflavored.MODID + ".cutthroat_trout", "Cutthroat Trout");
        add("entity." + Reflavored.MODID + ".bison", "Bison");

        add("subtitles." + Reflavored.MODID + ".entity.bison.ambient", "Bison grunts");
        add("subtitles." + Reflavored.MODID + ".entity.bison.hurt", "Bison hurts");
        add("subtitles." + Reflavored.MODID + ".entity.bison.death", "Bison dies");
        add("subtitles." + Reflavored.MODID + ".entity.bison.step", "Bison steps");

        add("entity.redwood_forest.redwood_boat", "Redwood Boat");
        add("entity.redwood_forest.redwood_chest_boat", "Redwood Boat with Chest");

        add("effect." + Reflavored.MODID + ".stinky", "Stinky");

        add("item.minecraft.potion.effect.stink_potion", "Potion of Stink");
        add("item.minecraft.splash_potion.effect.stink_potion", "Splash Potion of Stink");
        add("item.minecraft.lingering_potion.effect.stink_potion", "Lingering Potion of Stink");
    }
}
