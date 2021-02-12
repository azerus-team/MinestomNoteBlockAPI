package ru.azerusteam.api.noteblock.util;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.sound.Sound;
import net.minestom.server.utils.Position;
import ru.azerusteam.api.noteblock.model.CustomInstrument;
import ru.azerusteam.api.noteblock.model.SoundCategory;

import java.util.ArrayList;

/**
 * Fields/methods for reflection &amp; version checking
 */
public class CompatibilityUtils {
    
    private static Class<? extends Enum> soundCategoryClass;
    private static float serverVersion = -1;

    /**
     * Returns whether the version of Bukkit is or is after 1.12
     * @return version is after 1.12
     * @deprecated Compare {@link #getServerVersion()} with 0.0112f
     */
    public static boolean isPost1_12() {
        return getServerVersion() >= 0.0112f;
    }

    protected static boolean isSoundCategoryCompatible() {
        return getServerVersion() >= 0.0111f;
    }

    /**
     * Plays a sound using NMS &amp; reflection
     * @param player
     * @param position
     * @param sound
     * @param category
     * @param volume
     * @param pitch
     *
     * @deprecated use {@link #playSound(Player, Position, String, SoundCategory, float, float, float)}
     */
    public static void playSound(Player player, Position position, String sound,
                                 SoundCategory category, float volume, float pitch) {
        playSound(player, position, sound, category, volume, pitch, 0);
    }

    /**
     * Plays a sound using NMS &amp; reflection
     * @param player
     * @param position
     * @param sound
     * @param category
     * @param volume
     * @param pitch
     * @deprecated use {@link #playSound(Player, Position, String, SoundCategory, float, float, float)}
     */
    public static void playSound(Player player, Position position, String sound,
                                 SoundCategory category, float volume, float pitch, boolean stereo) {
        playSound(player, position, sound, category, volume, pitch, stereo ? 2 : 0);
    }

    /**
     * Plays a sound using NMS &amp; reflection
     * @param player
     * @param position
     * @param sound
     * @param category
     * @param volume
     * @param pitch
     *
     * @deprecated use {@link #playSound(Player, Position, Sound, SoundCategory, float, float, float)}
     */
    public static void playSound(Player player, Position position, Sound sound,
                                 SoundCategory category, float volume, float pitch) {
        playSound(player, position, sound, category, volume, pitch, 0);
    }

    /**
     * Plays a sound using NMS &amp; reflection
     * @param player
     * @param position
     * @param sound
     * @param category
     * @param volume
     * @param pitch
     * @deprecated use {@link #playSound(Player, Position, Sound, SoundCategory, float, float, float)}
     */
    public static void playSound(Player player, Position position, Sound sound,
                                 SoundCategory category, float volume, float pitch, boolean stereo) {
        playSound(player, position, sound, category, volume, pitch, stereo ? 2 : 0);
    }

    @Deprecated
    public static void playSound(Player player, Position position, String sound,
                                 SoundCategory category, float volume, float pitch, float distance) {
        playSoundUniversal(player, position, sound, category, volume, pitch, distance);
    }

    /**
     * Plays a sound using NMS &amp; reflection
     * @param player
     * @param position
     * @param sound
     * @param category
     * @param volume
     * @param pitch
     * @param distance
     */
    public static void playSound(Player player, Position position, Sound sound,
                                 SoundCategory category, float volume, float pitch, float distance) {
        playSoundUniversal(player, position, sound, category, volume, pitch, distance);
    }

    private static void playSoundUniversal(Player player, Position position, Object sound,
                                           SoundCategory category, float volume, float pitch, float distance) {
        Position soundLocation = MathUtils.stereoPan(position, distance);
        net.minestom.server.sound.SoundCategory soundCategory = net.minestom.server.sound.SoundCategory.valueOf(category.name());
        player.playSound(((Sound) sound), soundCategory, ((int) soundLocation.getX()), ((int) soundLocation.getY()), ((int) soundLocation.getZ()), volume, pitch);
    }

    /**
     * Gets instruments which were added post-1.12
     * @return ArrayList of instruments
     * @deprecated Use {@link #getVersionCustomInstruments(float)}
     */
    public static ArrayList<CustomInstrument> get1_12Instruments(){
        return getVersionCustomInstruments(0.0112f);
    }

    /**
     * Return list of instuments which were added in specified version
     * @param serverVersion 1.12 = 0.0112f, 1.14 = 0.0114f,...
     * @return list of custom instruments, if no instuments were added in specified version returns empty list
     */
    public static ArrayList<CustomInstrument> getVersionCustomInstruments(float serverVersion){
        ArrayList<CustomInstrument> instruments = new ArrayList<>();
        if (serverVersion == 0.0112f){
            instruments.add(new CustomInstrument((byte) 0, "Guitar", "block.note_block.guitar.ogg"));
            instruments.add(new CustomInstrument((byte) 0, "Flute", "block.note_block.flute.ogg"));
            instruments.add(new CustomInstrument((byte) 0, "Bell", "block.note_block.bell.ogg"));
            instruments.add(new CustomInstrument((byte) 0, "Chime", "block.note_block.icechime.ogg"));
            instruments.add(new CustomInstrument((byte) 0, "Xylophone", "block.note_block.xylobone.ogg"));
            return instruments;
        }

        if (serverVersion == 0.0114f){
            instruments.add(new CustomInstrument((byte) 0, "Iron Xylophone", "block.note_block.iron_xylophone.ogg"));
            instruments.add(new CustomInstrument((byte) 0, "Cow Bell", "block.note_block.cow_bell.ogg"));
            instruments.add(new CustomInstrument((byte) 0, "Didgeridoo", "block.note_block.didgeridoo.ogg"));
            instruments.add(new CustomInstrument((byte) 0, "Bit", "block.note_block.bit.ogg"));
            instruments.add(new CustomInstrument((byte) 0, "Banjo", "block.note_block.banjo.ogg"));
            instruments.add(new CustomInstrument((byte) 0, "Pling", "block.note_block.pling.ogg"));
            return instruments;
        }
        return instruments;
    }

    /**
     * Return list of custom instruments based on song first custom instrument index and server version
     * @param firstCustomInstrumentIndex
     * @return
     */
    public static ArrayList<CustomInstrument> getVersionCustomInstrumentsForSong(int firstCustomInstrumentIndex){
        ArrayList<CustomInstrument> instruments = new ArrayList<>();

        if (getServerVersion() < 0.0112f){
            if (firstCustomInstrumentIndex == 10) {
                instruments.addAll(getVersionCustomInstruments(0.0112f));
            } else if (firstCustomInstrumentIndex == 16){
                instruments.addAll(getVersionCustomInstruments(0.0112f));
                instruments.addAll(getVersionCustomInstruments(0.0114f));
            }
        } else if (getServerVersion() < 0.0114f){
            if (firstCustomInstrumentIndex == 16){
                instruments.addAll(getVersionCustomInstruments(0.0114f));
            }
        }

        return instruments;
    }

    /**
     * Returns server version as float less than 1 with two digits for each version part
     * @return e.g. 0.011401f for 1.14.1
     */
    public static float getServerVersion(){
        String versionName = MinecraftServer.VERSION_NAME;

        String[] versionParts = versionName.split("\\.");

        String versionString = "0.";
        for (String part : versionParts){
            if (part.length() == 1){
                versionString += "0";
            }

            versionString += part;
        }
        serverVersion = Float.parseFloat(versionString);
        return serverVersion;
    }

}
