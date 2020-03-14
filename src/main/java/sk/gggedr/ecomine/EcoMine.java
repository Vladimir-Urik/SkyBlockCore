package sk.gggedr.ecomine;

import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EcoMine extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(this, this);

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
                    }, 20 * 5);
                } else if (e.getBlock().getType() == Material.COBBLESTONE) {
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.COBBLESTONE);
                        }
                    }, 20 * 5);
                } else if (e.getBlock().getType() == Material.IRON_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.IRON_ORE);
                        }
                    }, 20 * 8);
                } else if (e.getBlock().getType() == Material.GOLD_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    e.setCancelled(true);
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getLocation().getWorld().dropItemNaturally(e.getBlock().getLocation(), new ItemStack(Material.GOLD_ORE));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.GOLD_ORE);
                        }
                    }, 20 * 8);
                } else if (e.getBlock().getType() == Material.DIAMOND_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.DIAMOND_ORE);
                        }
                    }, 20 * 10);
                } else if (e.getBlock().getType() == Material.LAPIS_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.LAPIS_ORE);
                        }
                    }, 20 * 8);
                } else if (e.getBlock().getType() == Material.EMERALD_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.EMERALD_ORE);
                        }
                    }, 20 * 10);
                } else if (e.getBlock().getType() == Material.REDSTONE_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.REDSTONE_ORE);
                        }
                    }, 20 * 7);
                } else if (e.getBlock().getType() == Material.COAL_ORE) {
                    e.getPlayer().playNote(e.getPlayer().getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.C));
                    Bukkit.getScheduler().runTaskLater(this, new Runnable() {
                        public void run() {
                            e.getBlock().setType(Material.COAL_ORE);
                        }
                    }, 20 * 5);
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }
}
