package sk.gggedr.skyblockcore;

import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import sk.gggedr.skyblockcore.Auth.Commands;
import sk.gggedr.skyblockcore.Auth.DeCoding;
import sk.gggedr.skyblockcore.Auth.MySql.Join;
import sk.gggedr.skyblockcore.Auth.MySql.MySQLMain;

public class SkyBlockCore extends JavaPlugin implements Listener {

    public static SkyBlockCore instance;

    @Override
    public void onEnable() {
        instance = this;
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(this, this);
        pm.registerEvents(new Join(), this);
        loadConfig();
        MySQLMain.getInstance().mysqlSetup();
        Bukkit.getPluginCommand("l").setExecutor(new Commands());
        Bukkit.getPluginCommand("login").setExecutor(new Commands());
        Bukkit.getPluginCommand("reg").setExecutor(new Commands());
        Bukkit.getPluginCommand("register").setExecutor(new Commands());
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onMine(BlockBreakEvent e){
        if(e.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase("mine")){
            if(e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                if (e.getBlock().getType() == Material.STONE) {
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.STONE);
                        }
                    }, 20 * this.getConfig().getInt("mine-regent.stone"));
                } else if (e.getBlock().getType() == Material.COBBLESTONE) {
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.COBBLESTONE);
                        }
                    }, 20 * this.getConfig().getInt("mine-regent.cobblestone"));
                } else if (e.getBlock().getType() == Material.IRON_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    if(e.getPlayer().hasPermission(this.getConfig().getString("permissions.double-drop"))) {
                        e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, 2));
                    } else {
                        e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                    }
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.IRON_ORE);
                        }
                    }, 20 * this.getConfig().getInt("mine-regent.iron_ore"));
                } else if (e.getBlock().getType() == Material.GOLD_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    if(e.getPlayer().hasPermission(this.getConfig().getString("permissions.double-drop"))) {
                        e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, 2));
                    } else {
                        e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                    }
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.GOLD_ORE);
                        }
                    }, 20 * this.getConfig().getInt("mine-regent.gold_ore"));
                } else if (e.getBlock().getType() == Material.DIAMOND_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.DIAMOND_ORE);
                        }
                    }, 20 * this.getConfig().getInt("mine-regent.diamond_ore"));
                } else if (e.getBlock().getType() == Material.LAPIS_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.LAPIS_ORE);
                        }
                    }, 20 * this.getConfig().getInt("mine-regent.lapis_ore"));
                } else if (e.getBlock().getType() == Material.EMERALD_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.EMERALD_ORE);
                        }
                    }, 20 * this.getConfig().getInt("mine-regent.emerald_ore"));
                } else if (e.getBlock().getType() == Material.REDSTONE_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.REDSTONE_ORE);
                        }
                    }, 20 * this.getConfig().getInt("mine-regent.redstone_ore"));
                } else if (e.getBlock().getType() == Material.COAL_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.COAL_ORE);
                        }
                    }, 20 * this.getConfig().getInt("mine-regent.coal_ore"));
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }


    public static SkyBlockCore getInstance(){
        return instance;
    }


    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
