package Leees.Tablist;

import Leees.Tablist.Tablist.Tablist;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    FileConfiguration config = this.getConfig();
    public static long starttime;

    public static Main getPlugin() {
        return (Main)getPlugin(Main.class);
    }

    public void onEnable() {
        this.config.getString("tablist.name");
        this.config.getString("tablist.message");
        this.config.getString("tablist.contact");
        this.config.getString("tablist.discussion");
        this.config.getString("tablist.website");
        this.config.getString("tablist.official");

        this.config.options().copyDefaults(true);
        this.saveConfig();
        this.getLogger().info("LeeesTablist Enabled");
        starttime = System.currentTimeMillis();
        this.getCommand("tabrconfig").setExecutor(new config());
        this.getServer().getScheduler().runTaskTimer(this, new Tablist(), 0L, 20L);
    }

    public void onDisable() {
        this.getLogger().info("LeeesTablist Disabled");
    }
}
