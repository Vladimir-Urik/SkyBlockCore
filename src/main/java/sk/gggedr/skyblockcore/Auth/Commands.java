package sk.gggedr.skyblockcore.Auth;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sk.gggedr.skyblockcore.Auth.MySql.Join;
import sk.gggedr.skyblockcore.Auth.MySql.Login;
import sk.gggedr.skyblockcore.Auth.MySql.PlayerControl;
import sk.gggedr.skyblockcore.Auth.MySql.Register;

public class Commands implements CommandExecutor {

    Join js = new Join();
    PlayerControl pc = new PlayerControl();
    Register reg = new Register();
    Login l = new Login();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("register") || cmd.getName().equalsIgnoreCase("reg")){
            if(js.queue.contains(player)) {
                if(!pc.isRegister(player.getUniqueId())) {
                    if (args.length == 2) {
                        if(args[0].equalsIgnoreCase(args[1])){
                            reg.newPlayer(player.getUniqueId(), player, args[0]);
                            player.sendMessage("§7[§aServer§7] §7Bol si zaregistrovaný a automaticky prihlásení!!");
                            js.queue.remove(js.queue.get(js.queue.indexOf(player)));
                        } else {
                            player.sendMessage("§7[§aServer§7] §7Heslá sa nezhodujú!!");
                        }
                    } else {
                        player.sendMessage("§7[§aServer§7] §7Použi: §a/register <heslo> <znovu_heslo>");
                    }
                } else {
                    player.sendMessage("§7[§aServer§7] Už si zaregistrovaný!!!");
                }
            } else {
                player.sendMessage("§7[§aServer§7] Už si prihlásenný!!!");
            }
        }

        if (cmd.getName().equalsIgnoreCase("login") || cmd.getName().equalsIgnoreCase("l")){
            if(js.queue.contains(player)) {
                    if (args.length == 1) {
                        if(l.PasswordSucceful(player, args[0])){
                            player.sendMessage("§7[§aServer§7] §aÚspešne si sa prihlásil!!");
                            js.queue.remove(js.queue.get(js.queue.indexOf(player)));
                        } else {
                            player.sendMessage("§7[§aServer§7] §7Skús to znova... Heslo je zlé!!!");
                        }
                    } else {
                        player.sendMessage("§7[§aServer§7] §7Použi: §a/login <heslo>");
                    }
                } else {
                    player.sendMessage("§7[§aServer§7] Už si prihlásenný!!!");
                }
        }
        return false;
    }
}
