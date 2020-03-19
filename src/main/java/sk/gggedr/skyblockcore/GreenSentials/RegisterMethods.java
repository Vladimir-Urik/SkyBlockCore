package sk.gggedr.skyblockcore.GreenSentials;

import org.bukkit.Bukkit;
import sk.gggedr.skyblockcore.GreenSentials.Commands.GameModeGM;
import sk.gggedr.skyblockcore.GreenSentials.Events.ChatFormat;
import sk.gggedr.skyblockcore.GreenSentials.Events.JoinLeave;
import sk.gggedr.skyblockcore.SkyBlockCore;

public class RegisterMethods {

    public void events(){
        Bukkit.getServer().getPluginManager().registerEvents(new JoinLeave(), SkyBlockCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new ChatFormat(), SkyBlockCore.getInstance());
    }

    public void commands(){
        Bukkit.getServer().getCommandMap().register("gl", new GameModeGM("gm"));
        Bukkit.getServer().getCommandMap().register("gamemode", new GameModeGM("gm"));
    }

    public void all(){
        commands();
        events();
    }
}
