package ru.azerusteam.api.noteblock.model;

public class CustomInstrument {

    private byte index;
    private String name;
    private String soundFileName;
    private net.minestom.server.sound.Sound sound;

    /**
     * Creates a CustomInstrument
     * @param index
     * @param name
     * @param soundFileName
     */
    public CustomInstrument(byte index, String name, String soundFileName) {
        this.index = index;
        this.name = name;
        this.soundFileName = soundFileName.replaceAll(".ogg", "");
        if (this.soundFileName.equalsIgnoreCase("pling")){
            this.sound = Sound.NOTE_PLING.minestomSound();
        }
    }

    /**
     * Gets index of CustomInstrument
     * @return index
     */
    public byte getIndex() {
        return index;
    }

    /**
     * Gets name of CustomInstrument
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets file name of the sound
     * @return file name
     */
    public String getSoundFileName() {
        return soundFileName;
    }

    /**
     * Gets the org.bukkit.Sound enum for this CustomInstrument
     * @return org.bukkit.Sound enum
     */
    public net.minestom.server.sound.Sound getSound() {
        return sound;
    }
}
