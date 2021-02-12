/*
package ru.azerusteam.api.noteblock.model.songplayer;

import net.minestom.server.entity.Player;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;
import net.minestom.server.utils.Position;
import ru.azerusteam.api.noteblock.model.Playlist;
import ru.azerusteam.api.noteblock.model.Song;
import ru.azerusteam.api.noteblock.model.SoundCategory;

*/
/**
 * SongPlayer created at a specified NoteBlock
 *
 *//*

public class NoteBlockSongPlayer extends RangeSongPlayer {

    private Block noteBlock;

    public NoteBlockSongPlayer(Song song) {
        super(song);
    }

    public NoteBlockSongPlayer(Song song, SoundCategory soundCategory) {
        super(song, soundCategory);
    }

    public NoteBlockSongPlayer(Playlist playlist, SoundCategory soundCategory) {
        super(playlist, soundCategory);
    }

    public NoteBlockSongPlayer(Playlist playlist) {
        super(playlist);
    }

    private NoteBlockSongPlayer(SongPlayer songPlayer) {
        super(songPlayer);
    }

    @Override
    void update(String key, Object value) {
        super.update(key, value);

        switch (key){
            case "noteBlock":
                noteBlock = (Block) value;
                break;
        }
    }

    */
/**
     * Get the Block this SongPlayer is played at
     * @return Block representing a NoteBlock
     *//*

    public Block getNoteBlock() {
        return noteBlock;
    }

    */
/**
     * Set the Block this SongPlayer is played at
     *//*

    public void setNoteBlock(Block noteBlock) {
        this.noteBlock = noteBlock;
    }

    @Override
    public void playTick(Player player, int tick) {
        if (noteBlock.getName() != Material.NOTE_BLOCK) {
            return;
        }
        if (!player.getWorld().getName().equals(noteBlock.getWorld().getName())) {
            // not in same world
            return;
        }
        byte playerVolume = NoteBlockAPI.getPlayerVolume(player);
        Location loc = noteBlock.getLocation();
        loc = new Location(loc.getWorld(), loc.getX() + 0.5f, loc.getY() - 0.5f, loc.getZ() + 0.5f);

        for (Layer layer : song.getLayerHashMap().values()) {
            Note note = layer.getNote(tick);
            if (note == null) {
                continue;
            }
            player.playNote(loc, InstrumentUtils.getBukkitInstrument(note.getInstrument()),
                    new org.bukkit.Note(note.getKey() - 33));

            float volume = ((layer.getVolume() * (int) this.volume * (int) playerVolume * note.getVelocity()) / 100_00_00_00F)
                    * ((1F / 16F) * getDistance());
            float pitch = NoteUtils.getPitch(note);

            channelMode.play(player, loc, song, layer, note, soundCategory, volume, !enable10Octave);

            if (isInRange(player)) {
                if (!this.playerList.get(player.getUniqueId())) {
                    playerList.put(player.getUniqueId(), true);
                    Bukkit.getPluginManager().callEvent(new PlayerRangeStateChangeEvent(this, player, true));
                }
            } else {
                if (this.playerList.get(player.getUniqueId())) {
                    playerList.put(player.getUniqueId(), false);
                    Bukkit.getPluginManager().callEvent(new PlayerRangeStateChangeEvent(this, player, false));
                }
            }
        }
    }

    */
/**
     * Returns true if the Player is able to hear the current NoteBlockSongPlayer
     * @param player in range
     * @return ability to hear the current NoteBlockSongPlayer
     *//*

    @Override
    public boolean isInRange(Player player) {
        Position loc = noteBlock.getLocation();
        loc = new Location(loc.getWorld(), loc.getX() + 0.5f, loc.getY() - 0.5f, loc.getZ() + 0.5f);
        if (player.getLocation().distance(loc) > getDistance()) {
            return false;
        } else {
            return true;
        }
    }
}*/
