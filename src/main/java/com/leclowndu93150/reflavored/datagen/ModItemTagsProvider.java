package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Reflavored.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModItems.REDWOOD_LOG.get())
                .add(ModItems.REDWOOD_WOOD.get())
                .add(ModItems.STRIPPED_REDWOOD_LOG.get())
                .add(ModItems.STRIPPED_REDWOOD_WOOD.get())
                .add(ModItems.BAOBAB_LOG.get())
                .add(ModItems.BAOBAB_WOOD.get())
                .add(ModItems.STRIPPED_BAOBAB_LOG.get())
                .add(ModItems.STRIPPED_BAOBAB_WOOD.get())
                .add(ModItems.EUCALYPTUS_LOG.get())
                .add(ModItems.EUCALYPTUS_WOOD.get())
                .add(ModItems.RAINBOW_EUCALYPTUS_LOG.get())
                .add(ModItems.RAINBOW_EUCALYPTUS_WOOD.get())
                .add(ModItems.STRIPPED_EUCALYPTUS_LOG.get())
                .add(ModItems.STRIPPED_EUCALYPTUS_WOOD.get());

        tag(ItemTags.PLANKS)
                .add(ModItems.REDWOOD_PLANKS.get())
                .add(ModItems.BAOBAB_PLANKS.get())
                .add(ModItems.EUCALYPTUS_PLANKS.get());

        tag(ItemTags.WOODEN_STAIRS)
                .add(ModItems.REDWOOD_STAIRS.get())
                .add(ModItems.BAOBAB_STAIRS.get())
                .add(ModItems.EUCALYPTUS_STAIRS.get());

        tag(ItemTags.WOODEN_SLABS)
                .add(ModItems.REDWOOD_SLAB.get())
                .add(ModItems.BAOBAB_SLAB.get())
                .add(ModItems.EUCALYPTUS_SLAB.get());

        tag(ItemTags.WOODEN_FENCES)
                .add(ModItems.REDWOOD_FENCE.get())
                .add(ModItems.BAOBAB_FENCE.get())
                .add(ModItems.EUCALYPTUS_FENCE.get());

        tag(ItemTags.FENCE_GATES)
                .add(ModItems.REDWOOD_FENCE_GATE.get())
                .add(ModItems.BAOBAB_FENCE_GATE.get())
                .add(ModItems.EUCALYPTUS_FENCE_GATE.get());

        tag(ItemTags.WOODEN_DOORS)
                .add(ModItems.REDWOOD_DOOR.get())
                .add(ModItems.BAOBAB_DOOR.get())
                .add(ModItems.EUCALYPTUS_DOOR.get());

        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModItems.REDWOOD_TRAPDOOR.get())
                .add(ModItems.BAOBAB_TRAPDOOR.get())
                .add(ModItems.EUCALYPTUS_TRAPDOOR.get());

        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModItems.REDWOOD_PRESSURE_PLATE.get())
                .add(ModItems.BAOBAB_PRESSURE_PLATE.get())
                .add(ModItems.EUCALYPTUS_PRESSURE_PLATE.get());

        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModItems.REDWOOD_BUTTON.get())
                .add(ModItems.BAOBAB_BUTTON.get())
                .add(ModItems.EUCALYPTUS_BUTTON.get());

        tag(ItemTags.SIGNS)
                .add(ModItems.REDWOOD_SIGN.get())
                .add(ModItems.BAOBAB_SIGN.get())
                .add(ModItems.EUCALYPTUS_SIGN.get());

        tag(ItemTags.HANGING_SIGNS)
                .add(ModItems.REDWOOD_HANGING_SIGN.get())
                .add(ModItems.BAOBAB_HANGING_SIGN.get())
                .add(ModItems.EUCALYPTUS_HANGING_SIGN.get());

        tag(ItemTags.LEAVES)
                .add(ModItems.REDWOOD_LEAVES.get())
                .add(ModItems.FLOWERING_ACACIA_LEAVES.get())
                .add(ModItems.BAOBAB_LEAVES.get())
                .add(ModItems.EUCALYPTUS_LEAVES.get());

        tag(ItemTags.SAPLINGS)
                .add(ModItems.REDWOOD_SAPLING.get())
                .add(ModItems.BAOBAB_SAPLING.get())
                .add(ModItems.EUCALYPTUS_SAPLING.get());

        tag(ItemTags.COMPLETES_FIND_TREE_TUTORIAL)
                .add(ModItems.REDWOOD_LOG.get())
                .add(ModItems.BAOBAB_LOG.get())
                .add(ModItems.EUCALYPTUS_LOG.get())
                .add(ModItems.RAINBOW_EUCALYPTUS_LOG.get());

        tag(ItemTags.FISHES)
                .add(ModItems.CUTTHROAT_TROUT.get())
                .add(ModItems.COOKED_CUTTHROAT_TROUT.get());

        tag(Tags.Items.MUSIC_DISCS)
                .add(ModItems.MUSIC_DISC_ARCHIES_LULLABY.get());

        tag(ItemTags.SMALL_FLOWERS)
                .add(ModItems.DOUGLAS_IRIS.get())
                .add(ModItems.TRILLIUM.get())
                .add(ModItems.LAVENDER.get())
                .add(ModItems.GLACIER_LILY.get())
                .add(ModItems.PAINTBRUSH_FLOWER.get())
                .add(ModItems.KING_PROTEA.get())
                .add(ModItems.HEATH_ASTER.get());

        tag(ItemTags.TALL_FLOWERS)
                .add(ModItems.ALPINE_LILY.get())
                .add(ModItems.ORANGE_LILY.get())
                .add(ModItems.YELLOW_LILY.get())
                .add(ModItems.PINK_LILY.get());

        tag(ItemTags.FLOWERS)
                .add(ModItems.DOUGLAS_IRIS.get())
                .add(ModItems.TRILLIUM.get())
                .add(ModItems.LAVENDER.get())
                .add(ModItems.GLACIER_LILY.get())
                .add(ModItems.PAINTBRUSH_FLOWER.get())
                .add(ModItems.ALPINE_LILY.get())
                .add(ModItems.ORANGE_LILY.get())
                .add(ModItems.YELLOW_LILY.get())
                .add(ModItems.PINK_LILY.get())
                .add(ModItems.KING_PROTEA.get())
                .add(ModItems.HEATH_ASTER.get());

        tag(ItemTags.BOATS)
                .add(ModItems.REDWOOD_BOAT.get())
                .add(ModItems.BAOBAB_BOAT.get())
                .add(ModItems.EUCALYPTUS_BOAT.get());

        tag(ItemTags.CHEST_BOATS)
                .add(ModItems.REDWOOD_CHEST_BOAT.get())
                .add(ModItems.BAOBAB_CHEST_BOAT.get())
                .add(ModItems.EUCALYPTUS_CHEST_BOAT.get());
    }

}
