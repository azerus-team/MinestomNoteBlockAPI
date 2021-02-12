package ru.azerusteam.api.noteblock.model.songplayer;


import net.minestom.server.entity.Player;
import net.minestom.server.utils.Position;
import ru.azerusteam.api.noteblock.NoteBlockAPI;
import ru.azerusteam.api.noteblock.model.*;

/**
 * SongPlayer created at a specified Location
 *
 */
public class PositionSongPlayer extends RangeSongPlayer {

    private Position targetLocation;

    public PositionSongPlayer(Song song) {
        super(song);
    }

    public PositionSongPlayer(Song song, SoundCategory soundCategory) {
        super(song, soundCategory);
    }
    /*
    private PositionSongPlayer(SongPlayer songPlayer) {
        super(songPlayer);
    }*/

    public PositionSongPlayer(Playlist playlist, SoundCategory soundCategory) {
        super(playlist, soundCategory);
    }

    public PositionSongPlayer(Playlist playlist) {
        super(playlist);
    }

    @Override
    void update(String key, Object value) {
        super.update(key, value);

        switch (key){
            case "targetLocation":
                targetLocation = (Position) value;
                break;
        }
    }

    /**
     * Gets location on which is the PositionSongPlayer playing
     * @return {@link Position}
     */
    public Position getTargetLocation() {
        return targetLocation;
    }

    /**
     * Sets location on which is the PositionSongPlayer playing
     */
    public void setTargetLocation(Position targetLocation) {
        this.targetLocation = targetLocation;
        CallUpdate("targetLocation", targetLocation);
    }

    @Override
    public void playTick(Player player, int tick) {
        /*if (!player.getW ().getName().equals(targetLocation.getWorld().getName())) {
            return; // not in same world
        }*/

        byte playerVolume = NoteBlockAPI.getPlayerVolume(player);

        for (Layer layer : song.getLayerHashMap().values()) {
            Note note = layer.getNote(tick);
            if (note == null) continue;

            float volume = ((layer.getVolume() * (int) this.volume * (int) playerVolume * note.getVelocity()) / 100_00_00_00F)
                    * ((1F / 16F) * getDistance());

            channelMode.play(player, targetLocation, song, layer, note, soundCategory, volume, !enable10Octave);

            if (isInRange(player)) {
                if (!this.playerList.get(player.getUuid())) {
                    playerList.put(player.getUuid(), true);
                    //Bukkit.getPluginManager().callEvent(new PlayerRangeStateChangeEvent(this, player, true));
                }
            } else {
                if (this.playerList.get(player.getUuid())) {
                    playerList.put(player.getUuid(), false);
                    //Bukkit.getPluginManager().callEvent(new PlayerRangeStateChangeEvent(this, player, false));
                }
            }
        }
    }

    /**
     * Returns true if the Player is able to hear the current PositionSongPlayer
     * @param player in range
     * @return ability to hear the current PositionSongPlayer
     */
    @Override
    public boolean isInRange(Player player) {
        return player.getPosition().getDistance(targetLocation) <= getDistance();
    }
}
