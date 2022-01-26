package Xera.Tablist;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class ReloadCommand implements CommandExecutor {
    private final XeraTablist plugin;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp() || sender.hasPermission("xera.tablist.reload")) {
            plugin.reloadConfig();
            sender.sendMessage("Reloaded Config");
        }

        return true;
    }
}
