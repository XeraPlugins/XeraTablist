package Leees.Tablist.Tablist;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import Leees.Tablist.Main;
import net.minecraft.server.v1_12_R1.ChatComponentText;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Tablist extends BukkitRunnable {
    Object header;

    public void run() {
        boolean hascontact = false;
        boolean hasdiscussion = false;
        boolean haswebsite = false;
        if (!Main.getPlugin().getConfig().getString("tablist.contact").equalsIgnoreCase("none")) {
            hascontact = true;
        } else {
            hascontact = false;
        }

        if (!Main.getPlugin().getConfig().getString("tablist.discussion").equalsIgnoreCase("none")) {
            hasdiscussion = true;
        } else {
            hasdiscussion = false;
        }

        if (!Main.getPlugin().getConfig().getString("tablist.website").equalsIgnoreCase("none")) {
            haswebsite = true;
        } else {
            haswebsite = false;
        }

        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        String uptime = TabUtil.GetFormattedInterval(System.currentTimeMillis() - Main.starttime);
        String foot = "\n §7tps: " + TabUtil.getTps() + " §7ping: §fplayerping §7players: §f" + Bukkit.getServer().getOnlinePlayers().size() + " §7uptime: §f" + uptime + "\n";

        try {
            Field a = packet.getClass().getDeclaredField("a");
            a.setAccessible(true);
            Field b = packet.getClass().getDeclaredField("b");
            b.setAccessible(true);
            if (Bukkit.getOnlinePlayers().size() == 0) {
                return;
            }


            if (!Main.getPlugin().getConfig().getString("tablist.message").replace("&", "§").equalsIgnoreCase("none")) {
                this.header = new ChatComponentText("\n" + Main.getPlugin().getConfig().getString("tablist.name").replace("&", "§") + "\n\n§6" +
                        Main.getPlugin().getConfig().getString("tablist.message").replace("&", "§") + "\n");
            } else {
                this.header = new ChatComponentText("\n" + Main.getPlugin().getConfig().getString("tablist.name").replace("&", "§") + "\n");
            }

            Iterator var11 = Bukkit.getOnlinePlayers().iterator();

            while(var11.hasNext()) {
                Player player = (Player)var11.next();
                a.set(packet, this.header);

                int ping;
                try {
                    Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
                    ping = (Integer)entityPlayer.getClass().getField("ping").get(entityPlayer);
                } catch (IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException | IllegalAccessException var13) {
                    var13.printStackTrace();
                    ping = 0;
                }

                foot = foot.replace("playerping", Integer.toString(ping));
                Object footer = new ChatComponentText(foot + "\n" + (hascontact ? "§7contact: " + Main.getPlugin().getConfig().getString("tablist.contact").replace("&", "§") + "\n" : "") +
                        (hasdiscussion ? "§7discussion: " + Main.getPlugin().getConfig().getString("tablist.discussion").replace("&", "§") + "\n" : "") + (haswebsite ? "§7website: " +
                        Main.getPlugin().getConfig().getString("tablist.website").replace("&", "§") +
                        "\n§7" + Main.getPlugin().getConfig().getString("tablist.official").replace("&", "§") + "\n" : ""));
                b.set(packet, footer);
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
            }
        } catch (IllegalAccessException | NoSuchFieldException var14) {
            var14.printStackTrace();
        }

    }
}
