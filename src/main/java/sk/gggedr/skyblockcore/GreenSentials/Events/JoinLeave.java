package sk.gggedr.skyblockcore.GreenSentials.Events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import sk.gggedr.skyblockcore.API;
import sk.gggedr.skyblockcore.SkyBlockCore;

public class JoinLeave implements Listener {
    SkyBlockCore main = new SkyBlockCore();
    FileConfiguration gll = YamlConfiguration.loadConfiguration(main.GSLaungage);
    @EventHandler
    public void Join(PlayerJoinEvent e){
        e.setJoinMessage(API.colorMSG(API.PlaceHolder(gll, e.getPlayer(), "join-msg")));
    }

    @EventHandler
    public void Leave(PlayerQuitEvent e){
        e.setQuitMessage(API.colorMSG(API.PlaceHolder(gll, e.getPlayer(), "leave-msg")));
    }

    @EventHandler
    public void NewPlayer(PlayerLoginEvent e){
        if(!e.getPlayer().hasPlayedBefore()){
            Bukkit.broadcastMessage(API.PlaceHolder(gll, e.getPlayer(), "new-player-msg"));
        }
    }

}
