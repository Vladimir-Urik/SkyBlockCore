package sk.gggedr.skyblockcore.Auth.MySql;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import sk.gggedr.skyblockcore.SkyBlockCore;

import java.util.ArrayList;
import java.util.List;

public class Join implements Listener {
    public int login, register;

    PlayerControl pc = new PlayerControl();

    public List<Player> queue = new ArrayList<Player>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        queue.add(e.getPlayer());//Pridá hráča do fronty
        if(pc.isRegister(e.getPlayer().getUniqueId())){
            login = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlockCore.getInstance(), new Runnable() {
                @Override
                public void run() {
                    e.getPlayer().sendMessage("§7[§aServer§7] Prosím prihlás sa pomocou §a/login <heslo>");
                }
            }, 0L, 40L);
        } else {
            register = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlockCore.getInstance(), new Runnable() {
                @Override
                public void run() {
                    e.getPlayer().sendMessage("§7[§aServer§7] Prosím zaregistruj sa pomocou §a/register <heslo> <znovu_heslo>");
                }
            }, 0L, 40L);
        }

        int m = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyBlockCore.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(!queue.contains(e.getPlayer())){
                    Bukkit.getScheduler().cancelTask(login);
                    Bukkit.getScheduler().cancelTask(register);
                }
            }
        }, 0L, 5L);
    }

    @EventHandler
    public void onchat(AsyncPlayerChatEvent e){
        if(queue.contains(e.getPlayer())){
            e.setMessage(" ");
        }
    }

    @EventHandler
    public void oncmd(PlayerCommandSendEvent e){
        if(queue.contains(e.getPlayer())){
            e.getCommands().clear();
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(queue.contains(e.getPlayer())){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        if(queue.contains(e.getPlayer())){
            queue.remove(queue.indexOf(e.getPlayer()));
        }
    }

}
