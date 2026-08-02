package com.leclowndu93150.reflavored.mixin;

import com.leclowndu93150.reflavored.init.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Keeps Geothermal Taiga visually cold and snowy while geothermal heat prevents
 * snow layers and ice from being placed on its surface.
 */
@Mixin(Biome.class)
public abstract class GeothermalPrecipitationMixin {
    @Inject(
            method = "shouldSnow(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void reflavored$preventGeothermalSnowLayers(
            LevelReader level,
            BlockPos pos,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (level.getBiome(pos).is(ModBiomes.GEOTHERMAL_TAIGA)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "shouldFreeze(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Z)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void reflavored$preventGeothermalIce(
            LevelReader level,
            BlockPos pos,
            boolean mustBeAtEdge,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (level.getBiome(pos).is(ModBiomes.GEOTHERMAL_TAIGA)) {
            cir.setReturnValue(false);
        }
    }
}
