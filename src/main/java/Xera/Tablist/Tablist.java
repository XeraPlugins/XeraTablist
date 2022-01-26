package Xera.Tablist;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;

@RequiredArgsConstructor
public class Tablist implements Runnable {
    private final XeraTablist plugin;

    public void run() {
        Collection<? extends Player> players = plugin.getServer().getOnlinePlayers();
        try {
            if (players.size() == 0) {
                return;
            }

            for (Player player : players) {
                player.setPlayerListHeaderFooter(
                        new ComponentBuilder(XeraTablist.parseText(player, plugin.getHeader())).create(),
                        new ComponentBuilder(XeraTablist.parseText(player, plugin.getFooter())).create());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
