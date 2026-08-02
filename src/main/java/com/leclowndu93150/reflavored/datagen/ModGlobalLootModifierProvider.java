package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.Reflavored;
import com.leclowndu93150.reflavored.loot.ArchiesLullabyLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Reflavored.MODID);
    }

    @Override
    protected void start() {
        add("archies_lullaby", new ArchiesLullabyLootModifier(new LootItemCondition[0]));
    }
}
