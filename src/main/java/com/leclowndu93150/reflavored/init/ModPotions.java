package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, Reflavored.MODID);

    public static final DeferredHolder<Potion, Potion> STINK_POTION = POTIONS.register("stink_potion", registryName -> new Potion(registryName.getPath(), new MobEffectInstance(ModEffects.STINK_EFFECT, 900)));


}
