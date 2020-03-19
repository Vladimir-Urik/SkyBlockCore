package sk.gggedr.skyblockcore.GreenSentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import sk.gggedr.skyblockcore.API;
import sk.gggedr.skyblockcore.SkyBlockCore;

import java.util.List;

public class GameModeGM extends Command implements CommandExecutor {

    SkyBlockCore main = new SkyBlockCore();
    FileConfiguration gll = YamlConfiguration.loadConfiguration(main.GSLaungage);
    String prefix = gll.getString("gamemode.prefix");


    public GameModeGM(String name) {
        super(name);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return false;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player p = (Player)sender;
            if(p.hasPermission("gl.gm")) {
                if (args.length == 1) {
                    if(args[0].equals("c") || args[0].equals("creative") || args[0].equals("1") || args[0].equals("cre")){
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage(API.colorMSG(API.PlaceholderGamemode(gll, p, "gamemode", GameMode.CREATIVE)));
                    }else if(args[0].equals("s") || args[0].equals("survival") || args[0].equals("sur") || args[0].equals("0")){
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage(API.colorMSG(API.PlaceholderGamemode(gll, p, "gamemode", GameMode.SURVIVAL)));
                    }else if(args[0].equals("a") || args[0].equals("adventure") || args[0].equals("adv") || args[0].equals("2")){
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(API.colorMSG(API.PlaceholderGamemode(gll, p, "gamemode", GameMode.ADVENTURE)));
                    }else if(args[0].equals("sp") || args[0].equals("spectator") || args[0].equals("spec") || args[0].equals("3")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage(API.colorMSG(API.PlaceholderGamemode(gll, p, "gamemode", GameMode.SPECTATOR)));
                    }
                } else if (args.length == 2) {
                    Player pt = Bukkit.getPlayer(args[1]);
                    if(pt == null){
                        p.sendMessage("§7[§aServer§7] Tento hráč je Offline!!");
                    } else {
                        if(args[0].equals("c") || args[0].equals("creative") || args[0].equals("1") || args[0].equals("cre")){
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(API.colorMSG(API.PlaceholderGamemode(gll, p, "gamemode", GameMode.CREATIVE)));
                        }else if(args[0].equals("s") || args[0].equals("survival") || args[0].equals("sur") || args[0].equals("0")){
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(API.colorMSG(API.PlaceholderGamemode(gll, p, "gamemode", GameMode.SURVIVAL)));
                        }else if(args[0].equals("a") || args[0].equals("adventure") || args[0].equals("adv") || args[0].equals("2")){
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(API.colorMSG(API.PlaceholderGamemode(gll, p, "gamemode", GameMode.ADVENTURE)));
                        }else if(args[0].equals("sp") || args[0].equals("spectator") || args[0].equals("spec") || args[0].equals("3")) {
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(API.colorMSG(API.PlaceholderGamemode(gll, p, "gamemode", GameMode.SPECTATOR)));
                        }//TODO: prerobi´t :D
                    }
                } else {
                    p.sendMessage("");//TODO: Použi: sdadasdassvgdfgdffdsfgdfgdgdgdfgdfgd
                }
            }
        return false;
    }
}
