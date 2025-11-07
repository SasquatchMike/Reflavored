package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.RedwoodForest;
import com.leclowndu93150.reflavored.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RedwoodForest.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.REDWOOD_LOG.get())
                .add(ModBlocks.REDWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_REDWOOD_WOOD.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.REDWOOD_PLANKS.get());

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.REDWOOD_STAIRS.get());

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.REDWOOD_SLAB.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.REDWOOD_FENCE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.REDWOOD_FENCE_GATE.get());

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.REDWOOD_DOOR.get());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.REDWOOD_TRAPDOOR.get());

        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.REDWOOD_PRESSURE_PLATE.get());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.REDWOOD_BUTTON.get());

        tag(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.REDWOOD_SIGN.get());

        tag(BlockTags.WALL_SIGNS)
                .add(ModBlocks.REDWOOD_WALL_SIGN.get());

        tag(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.REDWOOD_HANGING_SIGN.get());

        tag(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.REDWOOD_WALL_HANGING_SIGN.get());

        tag(BlockTags.LEAVES)
                .add(ModBlocks.REDWOOD_LEAVES.get());

        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.REDWOOD_SAPLING.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.REDWOOD_LOG.get())
                .add(ModBlocks.REDWOOD_WOOD.get())
                .add(ModBlocks.STRIPPED_REDWOOD_LOG.get())
                .add(ModBlocks.STRIPPED_REDWOOD_WOOD.get())
                .add(ModBlocks.REDWOOD_PLANKS.get())
                .add(ModBlocks.REDWOOD_STAIRS.get())
                .add(ModBlocks.REDWOOD_SLAB.get())
                .add(ModBlocks.REDWOOD_FENCE.get())
                .add(ModBlocks.REDWOOD_FENCE_GATE.get())
                .add(ModBlocks.REDWOOD_DOOR.get())
                .add(ModBlocks.REDWOOD_TRAPDOOR.get())
                .add(ModBlocks.REDWOOD_PRESSURE_PLATE.get())
                .add(ModBlocks.REDWOOD_BUTTON.get());

        tag(BlockTags.COMPLETES_FIND_TREE_TUTORIAL)
                .add(ModBlocks.REDWOOD_LOG.get());
    }
}
