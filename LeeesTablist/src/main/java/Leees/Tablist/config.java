package Leees.Tablist;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class config implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            Main.getPlugin().reloadConfig();
            sender.sendMessage("Reloaded config");
        }

        if (sender instanceof Player && sender.isOp()) {
            Main.getPlugin().reloadConfig();
            sender.sendMessage("Reloaded config");
        }

        return true;
    }
}
