package Xera.Tablist.Tablist;

import Xera.Tablist.XeraTablist;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Tablist implements Runnable {
    XeraTablist xeraTablist;

    public Tablist(XeraTablist xeraTablist) {
        this.xeraTablist = xeraTablist;
    }

    public void run() {
        try {
            if (Bukkit.getOnlinePlayers().size() == 0) {
                return;
            }

            for (Player player : Bukkit.getOnlinePlayers()) {
                StringBuilder head = new StringBuilder();
                StringBuilder footer = new StringBuilder();
                List<String> headerlist = xeraTablist.getConfig().getStringList("tablist.header");
                List<String> footerlist = xeraTablist.getConfig().getStringList("tablist.footer");

                for (int i = 0; i < headerlist.size(); i++) {
                    if (i == (headerlist.size() - 1)) {
                        head.append(headerlist.get(i));
                    } else {
                        head.append(headerlist.get(i)).append("\n");
                    }
                }

                for (int i = 0; i < footerlist.size(); i++) {
                    if (i == (footerlist.size() - 1)) {
                        footer.append(footerlist.get(i));
                    } else {
                        footer.append(footerlist.get(i)).append("\n");
                    }
                }

                player.setPlayerListHeaderFooter(new ComponentBuilder(XeraTablist.parseText(player, head.toString())).create(), new ComponentBuilder(XeraTablist.parseText(player, footer.toString())).create());
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
