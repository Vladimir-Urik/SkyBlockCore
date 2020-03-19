package sk.gggedr.skyblockcore;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class API {


    public static String colorMSG(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String PlaceHolder(FileConfiguration fc, Player p, String address){
        return colorMSG(fc.getString(address).replaceAll("<player>", p.getName()).replaceAll("<prefix>", SkyBlockCore.getChat().getPlayerPrefix(p)));
    }

    public static String PlaceholderChatFormat(FileConfiguration fc, Player p, String address, String message){
        return  PlaceHolder(fc, p, address).replaceAll("<msg>", message);
    }

    public static String PlaceholderGamemode(FileConfiguration fc, Player p, String address, GameMode gm){
        return  PlaceHolder(fc, p, address).replaceAll("<mode>", gm.name());
    }
}
