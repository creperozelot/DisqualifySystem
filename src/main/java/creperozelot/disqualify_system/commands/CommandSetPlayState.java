package creperozelot.disqualify_system.commands;

import creperozelot.disqualify_system.utils.MYSQL;
import creperozelot.disqualify_system.utils.StaticCache;
import creperozelot.disqualify_system.utils.utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;

public class CommandSetPlayState {
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 3) {
            if (MYSQL.PlayerExist(args[1])) {
                switch (args[2]) {
                    case "true":
                        try {
                            MYSQL.setPlayingState(args[1], true);
                            sender.sendMessage(StaticCache.prefix + StaticCache.setplaystate_success.replace("{player}", args[1]).replace("{state}", "true"));
                        } catch (SQLException e) {
                            sender.sendMessage(StaticCache.prefix + StaticCache.mysqlerr.replace("{error}", e.getMessage()));
                            throw new RuntimeException(e);
                        }
                        break;
                    case "false":
                        try {
                            MYSQL.setPlayingState(args[1], false);
                            sender.sendMessage(StaticCache.prefix + StaticCache.setplaystate_success.replace("{player}", args[1]).replace("{state}", "false"));
                        } catch (SQLException e) {
                            sender.sendMessage(StaticCache.prefix + StaticCache.mysqlerr.replace("{error}", e.getMessage()));
                            throw new RuntimeException(e);
                        }
                        break;
                    default:
                        sender.sendMessage(StaticCache.prefix + StaticCache.cmdfail);
                        break;
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
