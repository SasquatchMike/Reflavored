package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Reflavored.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SKUNK_SPRAY = PARTICLES_TYPES.register("skunk_spray",
            () -> new SimpleParticleType(false));
}
