package sk.gggedr.skyblockcore;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import sk.gggedr.skyblockcore.Auth.RegisterMethods;
import sk.gggedr.skyblockcore.GreenSentials.Commands.GameModeGM;
import sk.gggedr.skyblockcore.GreenSentials.Events.ChatFormat;
import sk.gggedr.skyblockcore.GreenSentials.Events.JoinLeave;

import java.io.File;
import java.io.IOException;

public class SkyBlockCore extends JavaPlugin implements Listener {

    public static SkyBlockCore instance;
    public File GSLaungage = new File(getDataFolder()+"/GS-Laungage.yml");
    RegisterMethods auth = new RegisterMethods();
    sk.gggedr.skyblockcore.GreenSentials.RegisterMethods ess = new sk.gggedr.skyblockcore.GreenSentials.RegisterMethods();
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;
    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        loadConfig();
        //EcoSentialss
        FileConfiguration gll = YamlConfiguration.loadConfiguration(GSLaungage);
        gll.options().copyDefaults(true);
        Bukkit.getServer().getCommandMap().register("gl", new GameModeGM("gm"));
        Bukkit.getServer().getCommandMap().register("gl", new GameModeGM("gamemode"));
        Bukkit.getServer().getPluginManager().registerEvents(new JoinLeave(), SkyBlockCore.getInstance());
        Bukkit.getServer().getPluginManager().registerEvents(new ChatFormat(), SkyBlockCore.getInstance());
        //ess.all();
        //Auth
        auth.all();
        try {
            gll.save(GSLaungage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!setupEconomy() ) {
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
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
