package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.RedwoodForest;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, RedwoodForest.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModItems.REDWOOD_LOG.get())
                .add(ModItems.REDWOOD_WOOD.get())
                .add(ModItems.STRIPPED_REDWOOD_LOG.get())
                .add(ModItems.STRIPPED_REDWOOD_WOOD.get());

        tag(ItemTags.PLANKS)
                .add(ModItems.REDWOOD_PLANKS.get());

        tag(ItemTags.WOODEN_STAIRS)
                .add(ModItems.REDWOOD_STAIRS.get());

        tag(ItemTags.WOODEN_SLABS)
                .add(ModItems.REDWOOD_SLAB.get());

        tag(ItemTags.WOODEN_FENCES)
                .add(ModItems.REDWOOD_FENCE.get());

        tag(ItemTags.FENCE_GATES)
                .add(ModItems.REDWOOD_FENCE_GATE.get());

        tag(ItemTags.WOODEN_DOORS)
                .add(ModItems.REDWOOD_DOOR.get());

        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModItems.REDWOOD_TRAPDOOR.get());

        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModItems.REDWOOD_PRESSURE_PLATE.get());

        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModItems.REDWOOD_BUTTON.get());

        tag(ItemTags.SIGNS)
                .add(ModItems.REDWOOD_SIGN.get());

        tag(ItemTags.HANGING_SIGNS)
                .add(ModItems.REDWOOD_HANGING_SIGN.get());

        tag(ItemTags.LEAVES)
                .add(ModItems.REDWOOD_LEAVES.get());

        tag(ItemTags.SAPLINGS)
                .add(ModItems.REDWOOD_SAPLING.get());

        tag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL)
                .add(ModItems.REDWOOD_LOG.get());
    }
}
