package Xera.Tablist;

import Xera.Tablist.Tablist.TabUtil;
import Xera.Tablist.Tablist.Tablist;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class XeraTablist extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();
    public static long starttime;
    public static boolean haspapi = false;

    public void onEnable() {
        Logger log = getLogger();

        log.info("Loading config");
        config.getString("tablist.header");
        config.getString("tablist.footer");
        config.options().copyDefaults(true);
        saveConfig();

        starttime = System.currentTimeMillis();
        this.getCommand("tabrconfig").setExecutor(new ReloadCommand(this));
        Bukkit.getScheduler().runTaskTimer(this, new Tablist(this), 0, 10L);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            haspapi = true;
        }

        log.info("XeraTablist enabled");
    }

    public static String parseText(Player player, String text) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String newtext = text;
        Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
        int ping = (Integer) entityPlayer.getClass().getField("ping").get(entityPlayer);

        // PAPI
        if (haspapi) {
            newtext = PlaceholderAPI.setPlaceholders(player, text);
        }

        // ChatColor
        newtext = ChatColor.translateAlternateColorCodes('&', newtext);

        // Custom Placeholders
        newtext = newtext
                .replaceAll("%tps%", TabUtil.getTps())
                .replaceAll("%ping%", String.valueOf(ping))
                .replaceAll("%uptime%", TabUtil.GetFormattedInterval(System.currentTimeMillis() - XeraTablist.starttime))
                .replaceAll("%players%", Integer.toString(Bukkit.getServer().getOnlinePlayers().size()));

        return newtext;
    }
}
