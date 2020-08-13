package Leees.Tablist.Tablist;

import Leees.Tablist.Main;
import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_12_R1.ChatComponentText;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public class tablist implements Runnable {

    public void run() {

        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

        try {
            Field a = packet.getClass().getDeclaredField("a");
            a.setAccessible(true);
            Field b = packet.getClass().getDeclaredField("b");
            b.setAccessible(true);
            if (Bukkit.getOnlinePlayers().size() == 0) {
                return;
            }

            for (Player player : Bukkit.getOnlinePlayers()) {
                Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
                int ping = (Integer) entityPlayer.getClass().getField("ping").get(entityPlayer);

                a.set(packet, new ChatComponentText(Main.parseText(player, Main.getPlugin().getConfig().getString("tablist.header").replace("&", "§").replace("<line>", "\n").replace("<tps>", TabUtil.getTps())
                        .replace("<uptime>", TabUtil.GetFormattedInterval(System.currentTimeMillis() - Main.starttime))
                        .replace("<ping>", String.valueOf(ping)).replace("<players>", Integer.toString(Bukkit.getServer().getOnlinePlayers().size())))));
                b.set(packet, new ChatComponentText(Main.parseText(player, Main.getPlugin().getConfig().getString("tablist.footer").replace("&", "§").replace("<line>", "\n").replace("<tps>", TabUtil.getTps())
                        .replace("<ping>", String.valueOf(ping)).replace("<uptime>", TabUtil.GetFormattedInterval(System.currentTimeMillis() - Main.starttime))
                        .replace("<players>", Integer.toString(Bukkit.getServer().getOnlinePlayers().size())))));
                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
