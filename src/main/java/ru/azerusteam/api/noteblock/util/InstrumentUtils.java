package ru.azerusteam.api.noteblock.util;

import ru.azerusteam.api.noteblock.model.Instrument;
import ru.azerusteam.api.noteblock.model.Sound;

/**
 * Various methods for working with instruments
 */
public class InstrumentUtils {

    /**
     * Returns the net.minestom.server.sound.Sound enum for the current server version
     *
     * @param instrument
     * @return Sound enum (for the current server version)
     * @see Sound
     */
    public static net.minestom.server.sound.Sound getInstrument(byte instrument) {
        return Sound.getFromMinestomName(getInstrumentName(instrument));
    }

    /**
     * Add suffix to vanilla instrument to use sound outside 2 octave range
     * @param instrument instrument id
     * @param key sound key
     * @param pitch
     * @return warped name
     */
    public static String warpNameOutOfRange(byte instrument, byte key, short pitch) {
        return warpNameOutOfRange(getSoundNameByInstrument(instrument), key, pitch);
    }

    /**
     * Add suffix to qualified name to use sound outside 2 octave range
     *
     * @param name qualified name
     * @param key sound key
     * @param pitch
     * @return warped name
     */
    public static String warpNameOutOfRange(String name, byte key, short pitch) {
        key = NoteUtils.applyPitchToKey(key, pitch);
        // -15 base_-2
        // 9 base_-1
        // 33 base
        // 57 base_1
        // 81 base_2
        // 105 base_3
        if(key < 9) name += "_-2";
        else if(key < 33) name += "_-1";
        else //noinspection StatementWithEmptyBody
            if(key < 57);
            else if(key < 81) name += "_1";
            else if(key < 105) name += "_2";
        return name;
    }

    /**
     * Returns the name of vanilla instrument
     *
     * @param instrument instrument identifier
     * @return Sound name with full qualified name
     */
    public static String getSoundNameByInstrument(byte instrument) {
        //noinspection RedundantSuppression
        switch (instrument) {
            case 0:
                //noinspection DuplicateBranchesInSwitch
                return "minecraft:block.note_block.harp";
            case 1:
                return "minecraft:block.note_block.bass";
            case 2:
                //noinspection SpellCheckingInspection
                return "minecraft:block.note_block.basedrum";
            case 3:
                return "minecraft:block.note_block.snare";
            case 4:
                return "minecraft:block.note_block.hat";
            case 5:
                return "minecraft:block.note_block.guitar";
            case 6:
                return "minecraft:block.note_block.flute";
            case 7:
                return "minecraft:block.note_block.bell";
            case 8:
                return "minecraft:block.note_block.chime";
            case 9:
                return "minecraft:block.note_block.xylophone";
            case 10:
                return "minecraft:block.note_block.iron_xylophone";
            case 11:
                return "minecraft:block.note_block.cow_bell";
            case 12:
                return "minecraft:block.note_block.didgeridoo";
            case 13:
                return "minecraft:block.note_block.bit";
            case 14:
                return "minecraft:block.note_block.banjo";
            case 15:
                //noinspection SpellCheckingInspection
                return "minecraft:block.note_block.pling";
            default:
                return "minecraft:block.note_block.harp";
        }
    }

    /**
     * Returns the name of the org.bukkit.Sound enum for the current server version
     *
     * @param instrument
     * @return Sound enum name (for the current server version)
     * @see Sound
     */
    public static String getInstrumentName(byte instrument) {
        switch (instrument) {
            case 0:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_HARP").name();
            case 1:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_BASS").name();
            case 2:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_BASEDRUM").name();
            case 3:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_SNARE").name();
            case 4:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_HAT").name();
            case 5:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_GUITAR").name();
            case 6:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_FLUTE").name();
            case 7:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_BELL").name();
            case 8:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_CHIME").name();
            case 9:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_XYLOPHONE").name();
            case 10:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_IRON_XYLOPHONE").name();
            case 11:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_COW_BELL").name();
            case 12:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_DIDGERIDOO").name();
            case 13:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_BIT").name();
            case 14:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_BANJO").name();
            case 15:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_PLING").name();
            default:
                return Sound.getFromMinestomName("BLOCK_NOTE_BLOCK_HARP").name();
        }
    }

    /**
     * Returns the name of the org.bukkit.Instrument enum for the current server version
     * @param instrument
     * @return Instrument enum (for the current server version)
     */
    public static Instrument getBukkitInstrument(byte instrument) {
        switch (instrument) {
            case 0:
                return Instrument.PIANO;
            case 1:
                return Instrument.BASS_GUITAR;
            case 2:
                return Instrument.BASS_DRUM;
            case 3:
                return Instrument.SNARE_DRUM;
            case 4:
                return Instrument.STICKS;
            case 5:
                return Instrument.GUITAR;
            case 6:
                return Instrument.FLUTE;
            case 7:
                return Instrument.BELL;
            case 8:
                return Instrument.CHIME;
            case 9:
                return Instrument.XYLOPHONE;
            case 10:
                return Instrument.IRON_XYLOPHONE;
            case 11:
                return Instrument.COW_BELL;
            case 12:
                return Instrument.DIDGERIDOO;
            case 13:
                return Instrument.BIT;
            case 14:
                return Instrument.BANJO;
            case 15:
                return Instrument.PLING;
            default:
                return Instrument.PIANO;
        }
    }

    /**
     * If true, the byte given represents a custom instrument
     * @param instrument
     * @return whether the byte represents a custom instrument
     */
    public static boolean isCustomInstrument(byte instrument) {
        return instrument >= getCustomInstrumentFirstIndex();
    }

    /**
     * Gets the first index in which a custom instrument
     * can be added to the existing list of instruments
     * @return index where an instrument can be added
     */
    public static byte getCustomInstrumentFirstIndex() {
        return 16;
    }

}
