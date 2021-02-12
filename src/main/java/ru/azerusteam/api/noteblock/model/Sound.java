package ru.azerusteam.api.noteblock.model;

import java.util.HashMap;
import java.util.Map;

public enum Sound {

    NOTE_PIANO("NOTE_PIANO", "BLOCK_NOTE_BLOCK_HARP"),
    NOTE_BASS("NOTE_BASS", "BLOCK_NOTE_BLOCK_BASS"),
    NOTE_BASS_DRUM("NOTE_BASS_DRUM", "BLOCK_NOTE_BLOCK_BASEDRUM"),
    NOTE_SNARE_DRUM("NOTE_SNARE_DRUM", "BLOCK_NOTE_BLOCK_SNARE"),
    NOTE_STICKS("NOTE_STICKS", "BLOCK_NOTE_BLOCK_HAT"),
    NOTE_BASS_GUITAR("NOTE_BASS_GUITAR", "BLOCK_NOTE_BLOCK_GUITAR"),
    NOTE_FLUTE("NOTE_FLUTE", "BLOCK_NOTE_BLOCK_FLUTE"),
    NOTE_BELL("NOTE_BELL", "BLOCK_NOTE_BLOCK_BELL"),
    NOTE_CHIME("NOTE_CHIME", "BLOCK_NOTE_BLOCK_CHIME"),
    NOTE_XYLOPHONE("NOTE_XYLOPHONE", "BLOCK_NOTE_BLOCK_XYLOPHONE"),
    NOTE_PLING("NOTE_PLING", "BLOCK_NOTE_BLOCK_PLING"),
    NOTE_IRON_XYLOPHONE("NOTE_IRON_XYLOPHONE", "BLOCK_NOTE_BLOCK_IRON_XYLOPHONE"),
    NOTE_COW_BELL("NOTE_COW_BELL", "BLOCK_NOTE_BLOCK_COW_BELL"),
    NOTE_DIDGERIDOO("NOTE_DIDGERIDOO", "BLOCK_NOTE_BLOCK_DIDGERIDOO"),
    NOTE_BIT("NOTE_BIT", "BLOCK_NOTE_BLOCK_BIT"),
    NOTE_BANJO("NOTE_BANJO", "BLOCK_NOTE_BLOCK_BANJO");

    private final String[] versionDependentNames;
    private net.minestom.server.sound.Sound cached = null;
    private static final Map<String, net.minestom.server.sound.Sound> cachedSoundMap = new HashMap<>();

    Sound(String... versionDependentNames) {
        this.versionDependentNames = versionDependentNames;
    }

    /**
     * Attempts to retrieve the org.bukkit.Sound equivalent of a version dependent enum name
     * @param bukkitSoundName
     * @return net.minestom.server.sound.Sound enum
     */
    public static net.minestom.server.sound.Sound getFromMinestomName(String bukkitSoundName) {
        net.minestom.server.sound.Sound sound = cachedSoundMap.get(bukkitSoundName.toUpperCase());
        if (sound != null)
            return sound;
        return net.minestom.server.sound.Sound.valueOf(bukkitSoundName);
    }

    private net.minestom.server.sound.Sound getSound() {
        if (cached != null) return cached;
        for (String name : versionDependentNames) {
            try {
                return cached = net.minestom.server.sound.Sound.valueOf(name);
            } catch (IllegalArgumentException ignore2) {
                // try next
            }
        }
        return null;
    }

    /**
     * Get the bukkit sound for current server version
     *
     * Caches sound on first call
     * @return corresponding {@link net.minestom.server.sound.Sound}
     */
    public net.minestom.server.sound.Sound minestomSound() {
        if (getSound() != null) {
            return getSound();
        }
        throw new IllegalArgumentException("Found no valid sound name for " + this.name());
    }

    static {
        // Cache sound access.
        for (Sound sound : values())
            for (String soundName : sound.versionDependentNames)
                cachedSoundMap.put(soundName.toUpperCase(), sound.getSound());
    }
}
