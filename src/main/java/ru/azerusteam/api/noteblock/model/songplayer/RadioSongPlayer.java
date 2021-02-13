package ru.azerusteam.api.noteblock.model.songplayer;

import net.minestom.server.entity.Player;
import ru.azerusteam.api.noteblock.NoteBlockAPI;
import ru.azerusteam.api.noteblock.model.*;
import ru.azerusteam.api.noteblock.model.playmode.ChannelMode;
import ru.azerusteam.api.noteblock.model.playmode.MonoMode;
import ru.azerusteam.api.noteblock.model.playmode.MonoStereoMode;

/**
 * SongPlayer playing to everyone added to it no matter where he is
 *
 */
public class RadioSongPlayer extends SongPlayer {

    protected boolean stereo = true;

    public RadioSongPlayer(Song song) {
        super(song);
    }

    public RadioSongPlayer(Song song, SoundCategory soundCategory) {
        super(song, soundCategory);
    }/*
    @Deprecated
    private RadioSongPlayer(SongPlayer songPlayer) {
        super(songPlayer);

    }*/

    public RadioSongPlayer(Playlist playlist, SoundCategory soundCategory) {
        super(playlist, soundCategory);
    }

    public RadioSongPlayer(Playlist playlist) {
        super(playlist);
    }

    @Override
    public void playTick(Player player, int tick) {
        byte playerVolume = NoteBlockAPI.getPlayerVolume(player);

        for (Layer layer : song.getLayerHashMap().values()) {
            Note note = layer.getNote(tick);
            if (note == null) {
                continue;
            }

            float volume = (layer.getVolume() * (int) this.volume * (int) playerVolume * note.getVelocity()) / 100_00_00_00F;
            channelMode.play(player, player.getPosition().clone().add(0, player.getEyeHeight(), 0), song, layer, note, soundCategory, volume, !enable10Octave);
            /*
             * public abstract void play(Player player, Position location, Song song, Layer layer, Note note,
                              SoundCategory soundCategory, float volume, boolean doTranspose);
             */
        }
    }

    /**
     * Returns if the SongPlayer will play Notes from two sources as stereo
     * @return if is played stereo
     * @deprecated
     */
    @Deprecated
    public boolean isStereo(){
        return !(channelMode instanceof MonoMode);
    }

    /**
     * Sets if the SongPlayer will play Notes from two sources as stereo
     * @param stereo
     * @deprecated
     */
    @Deprecated
    public void setStereo(boolean stereo){
        channelMode = stereo ? new MonoMode() : new MonoStereoMode();
    }

    /**
     * Sets how will be {@link Note} played to {@link Player} (eg. mono or stereo). Default is {@link MonoMode}.
     * @param mode
     */
    public void setChannelMode(ChannelMode mode){
        channelMode = mode;
    }
}
