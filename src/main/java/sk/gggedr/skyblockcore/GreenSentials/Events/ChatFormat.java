package sk.gggedr.skyblockcore.GreenSentials.Events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import sk.gggedr.skyblockcore.API;
import sk.gggedr.skyblockcore.SkyBlockCore;

public class ChatFormat implements Listener {

    SkyBlockCore main = new SkyBlockCore();
    FileConfiguration gll = YamlConfiguration.loadConfiguration(main.GSLaungage);

    @EventHandler
    public void onchat(AsyncPlayerChatEvent e){
        if(!e.getPlayer().getName().equals("GGGEDR")) {
            e.setFormat(API.PlaceholderChatFormat(gll, e.getPlayer(), "chat-format", e.getMessage()));
        } else if(e.getPlayer().hasPermission("*")){
            e.setFormat(API.PlaceholderChatFormat(gll, e.getPlayer(), "admin-chat-format", e.getMessage()));
        } else if(e.getPlayer().hasPermission("group.helper")){
            e.setFormat(API.PlaceholderChatFormat(gll, e.getPlayer(), "helper-chat-format", e.getMessage()));
        } else if(e.getPlayer().hasPermission("group.builder")){
            e.setFormat(API.PlaceholderChatFormat(gll, e.getPlayer(), "builder-chat-format", e.getMessage()));
        } else if(e.getPlayer().hasPermission("group.friend")){
            e.setFormat(API.PlaceholderChatFormat(gll, e.getPlayer(), "friend-chat-format", e.getMessage()));
        } else if(e.getPlayer().hasPermission("group.vip")){
            e.setFormat(API.PlaceholderChatFormat(gll, e.getPlayer(), "vip-chat-format", e.getMessage()));
        }
    }
}
