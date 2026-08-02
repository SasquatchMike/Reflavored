package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Reflavored;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

public final class ModJukeboxSongs {
    public static final ResourceKey<JukeboxSong> ARCHIES_LULLABY = ResourceKey.create(
            Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(Reflavored.MODID, "archies_lullaby"));

    private ModJukeboxSongs() {
    }

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        HolderGetter<SoundEvent> sounds = context.lookup(Registries.SOUND_EVENT);
        context.register(ARCHIES_LULLABY, new JukeboxSong(
                sounds.getOrThrow(ModSounds.MUSIC_DISC_ARCHIES_LULLABY.getKey()),
                Component.translatable("jukebox_song.reflavored.archies_lullaby"),
                129.7F,
                8));
    }
}
