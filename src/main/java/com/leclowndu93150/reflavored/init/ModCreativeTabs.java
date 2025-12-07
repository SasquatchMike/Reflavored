package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Redflavored;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Redflavored.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> REDWOOD_TAB = CREATIVE_MODE_TABS.register("redwood_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + Redflavored.MODID))
                    .icon(() -> new ItemStack(ModBlocks.REDWOOD_LOG.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.REDWOOD_LOG.get());
                        output.accept(ModItems.REDWOOD_WOOD.get());
                        output.accept(ModItems.STRIPPED_REDWOOD_LOG.get());
                        output.accept(ModItems.STRIPPED_REDWOOD_WOOD.get());
                        output.accept(ModItems.REDWOOD_PLANKS.get());
                        output.accept(ModItems.REDWOOD_STAIRS.get());
                        output.accept(ModItems.REDWOOD_SLAB.get());
                        output.accept(ModItems.REDWOOD_FENCE.get());
                        output.accept(ModItems.REDWOOD_FENCE_GATE.get());
                        output.accept(ModItems.REDWOOD_DOOR.get());
                        output.accept(ModItems.REDWOOD_TRAPDOOR.get());
                        output.accept(ModItems.REDWOOD_PRESSURE_PLATE.get());
                        output.accept(ModItems.REDWOOD_BUTTON.get());
                        output.accept(ModItems.REDWOOD_SIGN.get());
                        output.accept(ModItems.REDWOOD_HANGING_SIGN.get());
                        output.accept(ModItems.REDWOOD_LEAVES.get());
                        output.accept(ModItems.REDWOOD_SAPLING.get());
                        output.accept(ModItems.DOUGLAS_IRIS.get());
                        output.accept(ModItems.TRILLIUM.get());
                        output.accept(ModItems.ALPINE_LILY.get());
                        output.accept(ModItems.REDWOOD_BOAT.get());
                        output.accept(ModItems.REDWOOD_CHEST_BOAT.get());
                        output.accept(ModItems.LAVENDER.get());
                        output.accept(ModItems.LAVENDER_BED.get());
                        output.accept(ModItems.LAVENDER_CANDLE.get());
                        output.accept(ModItems.LAVENDER_SHULKER_BOX.get());
                        output.accept(ModItems.LAVENDER_WOOL.get());
                        output.accept(ModItems.LAVENDER_TERRACOTTA.get());
                        output.accept(ModItems.LAVENDER_GLAZED_TERRACOTTA.get());
                        output.accept(ModItems.LAVENDER_CONCRETE.get());
                        output.accept(ModItems.LAVENDER_CONCRETE_POWDER.get());
                    })
                    .build());
}
