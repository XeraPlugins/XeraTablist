package Xera.Tablist;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class XeraTablist extends JavaPlugin implements Listener {
    public static long starttime;

    public void onEnable() {
        Logger log = getLogger();

        log.info("Loading config");
        saveDefaultConfig();

        starttime = System.currentTimeMillis();
        this.getCommand("tabrconfig").setExecutor(new ReloadCommand(this));
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Tablist(this), 0, 20);

        log.info("XeraTablist enabled");
    }

    public static String parseText(Player player, String text) {
        String newtext;

        // PAPI
        newtext = PlaceholderAPI.setPlaceholders(player, text);

        // ChatColor
        newtext = ChatColor.translateAlternateColorCodes('&', newtext);

        return newtext;
    }
}
