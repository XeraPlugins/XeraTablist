package Leees.Tablist;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    Main main;
    public ReloadCommand(Main main) {
        this.main = main;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            main.reloadConfig();
            sender.sendMessage("Reloaded Config");
        }

        if (sender instanceof Player && sender.isOp()) {
            main.reloadConfig();
            sender.sendMessage("Reloaded Config");
        }

        return true;
    }
}
