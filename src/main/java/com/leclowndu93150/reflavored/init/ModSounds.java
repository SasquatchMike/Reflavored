package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(Registries.SOUND_EVENT, Reflavored.MODID);

    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BISON_AMBIENT = register("entity.bison.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BISON_HURT = register("entity.bison.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BISON_DEATH = register("entity.bison.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ENTITY_BISON_STEP = register("entity.bison.step");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_REDWOOD_FOREST = register("music.redwood_forest");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_LAVENDER_FIELDS = register("music.lavender_fields");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_ARCHIES_LULLABY = register("music_disc.archies_lullaby");

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name,
                () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, name)));
    }
}
