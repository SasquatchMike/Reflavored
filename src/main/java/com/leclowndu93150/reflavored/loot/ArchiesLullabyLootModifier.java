package com.leclowndu93150.reflavored.loot;

import com.leclowndu93150.reflavored.init.ModItems;
import com.leclowndu93150.reflavored.init.ModLootModifiers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public final class ArchiesLullabyLootModifier extends LootModifier {
    public static final MapCodec<ArchiesLullabyLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            codecStart(instance).apply(instance, ArchiesLullabyLootModifier::new));

    private static final float DISC_CHANCE = 0.05F;

    public ArchiesLullabyLootModifier(LootItemCondition[] conditions) {
        super(conditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        ResourceLocation lootTable = context.getQueriedLootTableId();
        boolean woodlandMansion = lootTable.equals(BuiltInLootTables.WOODLAND_MANSION.location());
        boolean zombieVillage = isVillageChest(lootTable) && isInsideZombieVillage(context);

        if ((woodlandMansion || zombieVillage) && context.getRandom().nextFloat() < DISC_CHANCE) {
            generatedLoot.add(new ItemStack(ModItems.MUSIC_DISC_ARCHIES_LULLABY.get()));
        }

        return generatedLoot;
    }

    private static boolean isVillageChest(ResourceLocation lootTable) {
        return lootTable.getNamespace().equals("minecraft")
                && lootTable.getPath().startsWith("chests/village/");
    }

    private static boolean isInsideZombieVillage(LootContext context) {
        Vec3 origin = context.getParamOrNull(LootContextParams.ORIGIN);
        if (origin == null) {
            return false;
        }

        StructureStart village = context.getLevel().structureManager()
                .getStructureWithPieceAt(BlockPos.containing(origin), StructureTags.VILLAGE);
        if (!village.isValid()) {
            return false;
        }

        return village.getPieces().stream()
                .filter(PoolElementStructurePiece.class::isInstance)
                .map(PoolElementStructurePiece.class::cast)
                .map(PoolElementStructurePiece::getElement)
                .anyMatch(element -> element.toString().contains("/zombie/"));
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return ModLootModifiers.ARCHIES_LULLABY.get();
    }
}
