package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.Redflavored;
import com.leclowndu93150.reflavored.init.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Redflavored.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock(ModBlocks.REDWOOD_LOG.get());
        woodBlock(ModBlocks.REDWOOD_WOOD.get(), "redwood_log");

        logBlock(ModBlocks.STRIPPED_REDWOOD_LOG.get());
        woodBlock(ModBlocks.STRIPPED_REDWOOD_WOOD.get(), "stripped_redwood_log");

        simpleBlockWithItem(ModBlocks.REDWOOD_PLANKS.get(), cubeAll(ModBlocks.REDWOOD_PLANKS.get()));

        simpleBlock(ModBlocks.REDWOOD_LEAVES.get(),
                models().singleTexture("redwood_leaves",
                                mcLoc("block/leaves"),
                                "all",
                                blockTexture(ModBlocks.REDWOOD_LEAVES.get()))
                        .renderType("cutout_mipped")
                        .element()
                        .from(0, 0, 0).to(16, 16, 16)
                        .allFaces((direction, faceBuilder) -> faceBuilder
                                .texture("#all")
                                .tintindex(0)
                                .cullface(direction))
                        .end());

        slabBlock(((SlabBlock) ModBlocks.REDWOOD_SLAB.get()),
                blockTexture(ModBlocks.REDWOOD_PLANKS.get()),
                blockTexture(ModBlocks.REDWOOD_PLANKS.get()));

        stairsBlock(ModBlocks.REDWOOD_STAIRS.get(), blockTexture(ModBlocks.REDWOOD_PLANKS.get()));

        buttonBlock(ModBlocks.REDWOOD_BUTTON.get(), blockTexture(ModBlocks.REDWOOD_PLANKS.get()));
        pressurePlateBlock(ModBlocks.REDWOOD_PRESSURE_PLATE.get(), blockTexture(ModBlocks.REDWOOD_PLANKS.get()));

        fenceBlock(ModBlocks.REDWOOD_FENCE.get(), blockTexture(ModBlocks.REDWOOD_PLANKS.get()));
        fenceGateBlock(ModBlocks.REDWOOD_FENCE_GATE.get(), blockTexture(ModBlocks.REDWOOD_PLANKS.get()));

        doorBlockWithRenderType(ModBlocks.REDWOOD_DOOR.get(),
                modLoc("block/redwood_door_bottom"),
                modLoc("block/redwood_door_top"),
                "cutout");

        trapdoorBlockWithRenderType(ModBlocks.REDWOOD_TRAPDOOR.get(),
                modLoc("block/redwood_trapdoor"),
                true,
                "cutout");

        simpleBlock(ModBlocks.REDWOOD_SAPLING.get(),
                models().cross(blockTexture(ModBlocks.REDWOOD_SAPLING.get()).getPath(),
                        blockTexture(ModBlocks.REDWOOD_SAPLING.get())).renderType("cutout"));

        signBlock(ModBlocks.REDWOOD_SIGN.get(),
                ModBlocks.REDWOOD_WALL_SIGN.get(),
                blockTexture(ModBlocks.REDWOOD_PLANKS.get()));

        hangingSignBlock(ModBlocks.REDWOOD_HANGING_SIGN.get(),
                ModBlocks.REDWOOD_WALL_HANGING_SIGN.get(),
                blockTexture(ModBlocks.REDWOOD_PLANKS.get()));

        simpleBlock(ModBlocks.DOUGLAS_IRIS.get(),
                models().cross(blockTexture(ModBlocks.DOUGLAS_IRIS.get()).getPath(),
                        blockTexture(ModBlocks.DOUGLAS_IRIS.get())).renderType("cutout"));

        simpleBlock(ModBlocks.TRILLIUM.get(),
                models().cross(blockTexture(ModBlocks.TRILLIUM.get()).getPath(),
                        blockTexture(ModBlocks.TRILLIUM.get())).renderType("cutout"));

        getVariantBuilder(ModBlocks.ALPINE_LILY.get())
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models().cross("alpine_lily_bottom",
                        modLoc("block/alpine_lily_bottom")).renderType("cutout")).addModel()
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models().cross("alpine_lily_top",
                        modLoc("block/alpine_lily_top")).renderType("cutout")).addModel();

        getVariantBuilder(ModBlocks.ORANGE_LILY.get())
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models().cross("orange_rose_bush_bottom",
                        modLoc("block/orange_rose_bush_bottom")).renderType("cutout")).addModel()
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models().cross("orange_rose_bush_top",
                        modLoc("block/orange_rose_bush_top")).renderType("cutout")).addModel();

        getVariantBuilder(ModBlocks.YELLOW_LILY.get())
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models().cross("yellow_rose_bush_bottom",
                        modLoc("block/yellow_rose_bush_bottom")).renderType("cutout")).addModel()
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models().cross("yellow_rose_bush_top",
                        modLoc("block/yellow_rose_bush_top")).renderType("cutout")).addModel();

        getVariantBuilder(ModBlocks.PINK_LILY.get())
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)
                .modelForState().modelFile(models().cross("pink_rose_bush_bottom",
                        modLoc("block/pink_rose_bush_bottom")).renderType("cutout")).addModel()
                .partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER)
                .modelForState().modelFile(models().cross("pink_rose_bush_top",
                        modLoc("block/pink_rose_bush_top")).renderType("cutout")).addModel();

        simpleBlock(ModBlocks.LAVENDER.get(),
                models().cross(blockTexture(ModBlocks.LAVENDER.get()).getPath(),
                        blockTexture(ModBlocks.LAVENDER.get())).renderType("cutout"));

//        // Normal lavender glass
//        simpleBlockWithItem(ModBlocks.LAVENDER_GLASS.get(),
//                models().cubeAll(name(ModBlocks.LAVENDER_GLASS.get()), blockTexture(ModBlocks.LAVENDER_GLASS.get()))
//                        .renderType("translucent"));
//
//        // Top-tex lavender glass, you will likely use a custom model instead of cubeAll later
//        simpleBlockWithItem(ModBlocks.LAVENDER_GLASS_TOP.get(),
//                models().cubeAll(name(ModBlocks.LAVENDER_GLASS_TOP.get()), blockTexture(ModBlocks.LAVENDER_GLASS_TOP.get()))
//                        .renderType("translucent"));
//
        simpleBlockWithItem(ModBlocks.LAVENDER_WOOL.get(), cubeAll(ModBlocks.LAVENDER_WOOL.get()));
        simpleBlockWithItem(ModBlocks.LAVENDER_TERRACOTTA.get(), cubeAll(ModBlocks.LAVENDER_TERRACOTTA.get()));
        simpleBlockWithItem(ModBlocks.LAVENDER_GLAZED_TERRACOTTA.get(), cubeAll(ModBlocks.LAVENDER_GLAZED_TERRACOTTA.get()));
        simpleBlockWithItem(ModBlocks.LAVENDER_CONCRETE.get(), cubeAll(ModBlocks.LAVENDER_CONCRETE.get()));
        simpleBlockWithItem(ModBlocks.LAVENDER_CONCRETE_POWDER.get(), cubeAll(ModBlocks.LAVENDER_CONCRETE_POWDER.get()));

        pottedPlantBlock(ModBlocks.POTTED_DOUGLAS_IRIS.get(), blockTexture(ModBlocks.DOUGLAS_IRIS.get()));
        pottedPlantBlock(ModBlocks.POTTED_TRILLIUM.get(), blockTexture(ModBlocks.TRILLIUM.get()));
        pottedPlantBlock(ModBlocks.POTTED_LAVENDER.get(), blockTexture(ModBlocks.LAVENDER.get()));

        simpleBlockWithItem(ModBlocks.MOSSY_STONE.get(), cubeAll(ModBlocks.MOSSY_STONE.get()));
        simpleBlockWithItem(ModBlocks.MOSSY_ANDESITE.get(), cubeAll(ModBlocks.MOSSY_ANDESITE.get()));


//        // FD cabinet only if bound
//        if (com.leclowndu93150.reflavored.compat.fd.FDCompat.REDWOOD_CABINET.isBound()) {
//            cabinetBlock(com.leclowndu93150.reflavored.compat.fd.FDCompat.REDWOOD_CABINET.get(), "redwood_cabinet");
//        }
    }

    private void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private void woodBlock(RotatedPillarBlock block, String logTextureName) {
        ResourceLocation barkTexture = modLoc("block/" + logTextureName);
        axisBlock(block, barkTexture, barkTexture);
    }

    private void pottedPlantBlock(Block potBlock, ResourceLocation plantTexture) {
        ModelFile model = models()
                .withExistingParent(name(potBlock), mcLoc("block/flower_pot_cross"))
                .texture("plant", plantTexture)
                .renderType("cutout");
        simpleBlock(potBlock, model);
    }


//    private void cabinetBlock(Block block, String modelBaseName) {
//        ModelFile closed = models().withExistingParent(modelBaseName, modLoc("block/" + modelBaseName));
//        ModelFile open   = models().withExistingParent(modelBaseName + "_open", modLoc("block/" + modelBaseName + "_open"));
//
//        getVariantBuilder(block).forAllStates(state -> {
//            var facing = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
//            boolean isOpen = state.getValue(BlockStateProperties.OPEN);
//            ModelFile mf = isOpen ? open : closed;
//            int y = switch (facing) {
//                case SOUTH -> 180;
//                case WEST  -> 270;
//                case EAST  -> 90;
//                default    -> 0;
//            };
//            return ConfiguredModel.builder().modelFile(mf).rotationY(y).build();
//        });
//    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
}
