package com.leclowndu93150.reflavored.mixin;

import com.leclowndu93150.reflavored.init.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.WolfVariant;
import net.minecraft.world.entity.animal.WolfVariants;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Wolf.class)
public class WolfVariantMixin {
    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void setGeothermalVariant(ServerLevelAccessor level, DifficultyInstance difficulty,
                                      MobSpawnType spawnType, @Nullable SpawnGroupData spawnData,
                                      CallbackInfoReturnable<SpawnGroupData> cir) {
        Wolf wolf = (Wolf) (Object) this;
        Holder<Biome> biome = level.getBiome(wolf.blockPosition());
        if (biome.is(ModBiomes.GEOTHERMAL_TAIGA)) {
            Registry<WolfVariant> variants = level.getLevel().registryAccess().registryOrThrow(Registries.WOLF_VARIANT);
            wolf.setVariant(variants.getHolderOrThrow(wolf.getRandom().nextBoolean()
                    ? WolfVariants.CHESTNUT
                    : WolfVariants.BLACK));
        }
    }
}
