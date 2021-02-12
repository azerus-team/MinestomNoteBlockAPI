package ru.azerusteam.api.noteblock;


import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.timer.Task;
import ru.azerusteam.api.noteblock.model.songplayer.SongPlayer;
import ru.azerusteam.api.noteblock.util.MathUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Main class; contains methods for playing and adjusting songs for players
 */
public class NoteBlockAPI {

    private static NoteBlockAPI plugin;

    private final Map<UUID, ArrayList<SongPlayer>> playingSongs = new ConcurrentHashMap<>();
    private final Map<UUID, Byte> playerVolume = new ConcurrentHashMap<>();

    private boolean disabling = false;

    /**
     * Returns true if a Player is currently receiving a song
     * @param player
     * @return is receiving a song
     */
    public static boolean isReceivingSong(Player player) {
        return isReceivingSong(player.getUuid());
    }

    /**
     * Returns true if a Player with specified UUID is currently receiving a song
     * @param uuid
     * @return is receiving a song
     */
    public static boolean isReceivingSong(UUID uuid) {
        ArrayList<SongPlayer> songs = plugin.playingSongs.get(uuid);
        return (songs != null && !songs.isEmpty());
    }

    /**
     * Stops the song for a Player
     * @param player
     */
    public static void stopPlaying(Player player) {
        stopPlaying(player.getUuid());
    }

    /**
     * Stops the song for a Player
     * @param uuid
     */
    public static void stopPlaying(UUID uuid) {
        ArrayList<SongPlayer> songs = plugin.playingSongs.get(uuid);
        if (songs == null) {
            return;
        }
        for (SongPlayer songPlayer : songs) {
            songPlayer.removePlayer(uuid);
        }
    }

    /**
     * Sets the volume for a given Player
     * @param player
     * @param volume
     */
    public static void setPlayerVolume(Player player, byte volume) {
        setPlayerVolume(player.getUuid(), volume);
    }

    /**
     * Sets the volume for a given Player
     * @param uuid
     * @param volume
     */
    public static void setPlayerVolume(UUID uuid, byte volume) {
        plugin.playerVolume.put(uuid, volume);
    }

    /**
     * Gets the volume for a given Player
     * @param player
     * @return volume (byte)
     */
    public static byte getPlayerVolume(Player player) {
        return getPlayerVolume(player.getUuid());
    }

    /**
     * Gets the volume for a given Player
     * @param uuid
     * @return volume (byte)
     */
    public static byte getPlayerVolume(UUID uuid) {
        Byte byteObj = plugin.playerVolume.get(uuid);
        if (byteObj == null) {
            byteObj = 100;
            plugin.playerVolume.put(uuid, byteObj);
        }
        return byteObj;
    }

    public static ArrayList<SongPlayer> getSongPlayersByPlayer(Player player){
        return getSongPlayersByPlayer(player.getUuid());
    }

    public static ArrayList<SongPlayer> getSongPlayersByPlayer(UUID player){
        return plugin.playingSongs.get(player);
    }

    public static void setSongPlayersByPlayer(Player player, ArrayList<SongPlayer> songs){
        setSongPlayersByPlayer(player.getUuid(), songs);
    }

    public static void setSongPlayersByPlayer(UUID player, ArrayList<SongPlayer> songs){
        plugin.playingSongs.put(player, songs);
    }

    public void onEnable() {
        plugin = this;
        MinecraftServer.getSchedulerManager().buildShutdownTask(this::onDisable).schedule();
        //new NoteBlockPlayerMain().onEnable();
        /*MinecraftServer.getSchedulerManager().buildTask(() -> {
            Plugin[] plugins = getServer().getPluginManager().getPlugins();
            Type[] types = new Type[]{PlayerRangeStateChangeEvent.class, SongDestroyingEvent.class, SongEndEvent.class, SongStoppedEvent.class };
            for (Plugin plugin : plugins) {
                ArrayList<RegisteredListener> rls = HandlerList.getRegisteredListeners(plugin);
                for (RegisteredListener rl : rls) {
                    Method[] methods = rl.getListener().getClass().getDeclaredMethods();
                    for (Method m : methods) {
                        Type[] params = m.getParameterTypes();
                        param:
                        for (Type paramType : params) {
                            for (Type type : types){
                                if (paramType.equals(type)) {
                                    dependentPlugins.put(plugin, true);
                                    break param;
                                }
                            }
                        }
                    }

                }
            }
        }).schedule();*/

        new MathUtils();
    }

    public void onDisable() {
        disabling = true;
        for (Task task : MinecraftServer.getSchedulerManager().getTasks()) {
            task.cancel();
        }
        //NoteBlockPlayerMain.plugin.onDisable();
    }

    public void doSync(Runnable runnable) {
        runnable.run();
        //getServer().getScheduler().runTask(this, runnable);
    }

    public void doAsync(Runnable runnable) {
        MinecraftServer.getSchedulerManager().buildTask(runnable).schedule();
    }

    public boolean isDisabling() {
        return disabling;
    }

    public static NoteBlockAPI getAPI(){
        return plugin;
    }

}
