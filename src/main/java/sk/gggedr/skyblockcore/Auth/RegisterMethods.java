package sk.gggedr.skyblockcore.Auth;

import com.google.common.eventbus.EventBus;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import sk.gggedr.skyblockcore.Auth.MySql.Join;
import sk.gggedr.skyblockcore.Auth.MySql.MySQLMain;
import sk.gggedr.skyblockcore.SkyBlockCore;

public class RegisterMethods {
    MySQLMain mm = new MySQLMain();

    public void Events(){
        Bukkit.getServer().getPluginManager().registerEvents(new Join(), SkyBlockCore.getInstance());
    }

    public void Commands(){
        Bukkit.getPluginCommand("l").setExecutor(new Commands());
        Bukkit.getPluginCommand("login").setExecutor(new Commands());
        Bukkit.getPluginCommand("reg").setExecutor(new Commands());
        Bukkit.getPluginCommand("register").setExecutor(new Commands());
    }

    public void Mysql(){
        mm.mysqlSetup();
    }

    public void all(){
        Commands();
        Mysql();
        Events();
    }
}
