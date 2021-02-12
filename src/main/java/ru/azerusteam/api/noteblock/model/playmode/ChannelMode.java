package ru.azerusteam.api.noteblock.model.playmode;


import net.minestom.server.entity.Player;
import net.minestom.server.utils.Position;
import ru.azerusteam.api.noteblock.model.Note;
import ru.azerusteam.api.noteblock.model.Song;
import ru.azerusteam.api.noteblock.model.Layer;
import ru.azerusteam.api.noteblock.model.SoundCategory;

/**
 * Decides how is {@link Note} played to {@link Player}
 */
public abstract class ChannelMode {

    @Deprecated
    public abstract void play(Player player, Position location, Song song, Layer layer, Note note,
                              SoundCategory soundCategory, float volume, float pitch);

    public abstract void play(Player player, Position location, Song song, Layer layer, Note note,
                              SoundCategory soundCategory, float volume, boolean doTranspose);
}
