package Leees.Tablist;

import Leees.Tablist.Tablist.tablist;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    FileConfiguration config = this.getConfig();
    public static long starttime;
    public static Main getPlugin() {
        return (Main)getPlugin(Main.class);
    }
    public void onEnable() {
            /*
             * We register the EventListeneres here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
            this.config.getString("tablist.header");
            this.config.getString("tablist.footer");
            this.config.options().copyDefaults(true);
            this.saveConfig();
            this.getLogger().info("LeeesTablist Enabled");
            starttime = System.currentTimeMillis();
            this.getCommand("tabrconfig").setExecutor(new config());
            Bukkit.getScheduler().runTaskTimer(this, new tablist(), 0, 10L);
    }
}
