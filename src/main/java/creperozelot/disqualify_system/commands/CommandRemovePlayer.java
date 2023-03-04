package creperozelot.disqualify_system.commands;

import creperozelot.disqualify_system.utils.MYSQL;
import creperozelot.disqualify_system.utils.StaticCache;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;

public class CommandRemovePlayer {
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            if (MYSQL.PlayerExist(args[1])) {
                try {
                    MYSQL.deleterow(args[1]);
                    sender.sendMessage(StaticCache.prefix + StaticCache.delete_success.replace("{player}", args[1]));
                } catch (SQLException e) {
                    sender.sendMessage(StaticCache.prefix + StaticCache.mysqlerr.replace("{error}", e.getMessage()));
                    throw new RuntimeException(e);
                }
            } else {
                sender.sendMessage(StaticCache.prefix + StaticCache.playernotexist.replace("{player}", args[1]));
            }
        } else {
            sender.sendMessage(StaticCache.prefix + StaticCache.cmdfail);
        }
        return true;
    }
}
