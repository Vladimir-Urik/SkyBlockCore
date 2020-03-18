package sk.gggedr.skyblockcore.GreenSentials;

import org.bukkit.Bukkit;
import sk.gggedr.skyblockcore.GreenSentials.Events.ChatFormat;
import sk.gggedr.skyblockcore.GreenSentials.Events.JoinLeave;
import sk.gggedr.skyblockcore.SkyBlockCore;

public class RegisterMethods {

    public void Events(){
        Bukkit.getServer().getPluginManager().registerEvents(new JoinLeave(), SkyBlockCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new ChatFormat(), SkyBlockCore.getInstance());
    }

    public void Commands(){
        //Bukkit.getPluginCommand("l").setExecutor(new Commands());
    }

    public void all(){
        Commands();
        Events();
    }
}
