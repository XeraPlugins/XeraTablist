package Xera.Tablist;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class ReloadCommand implements CommandExecutor {
    XeraTablist xeraTablist;
    public ReloadCommand(XeraTablist xeraTablist) {
        this.xeraTablist = xeraTablist;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            xeraTablist.reloadConfig();
            sender.sendMessage("Reloaded Config");
        }

        if (sender instanceof Player && sender.isOp()) {
            xeraTablist.reloadConfig();
            sender.sendMessage("Reloaded Config");
        }

        return true;
    }
}
