package creperozelot.disqualify_system.commands;

import creperozelot.disqualify_system.utils.MYSQL;
import creperozelot.disqualify_system.utils.StaticCache;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;

public class CommandSetDisqualified {
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (MYSQL.PlayerExist(args[1])) {
            switch (args[2]) {
                case "true":
                    try {
                        MYSQL.setDisqualified(args[1], true);
                        sender.sendMessage(StaticCache.prefix + StaticCache.setdisqualified_success.replace("{player}", args[1]).replace("{state}", "true"));
                    } catch (SQLException e) {
                        sender.sendMessage(StaticCache.prefix + StaticCache.mysqlerr.replace("{error}", e.getMessage()));
                        throw new RuntimeException();
                    }
                    break;
                case "false":
                    try {
                        MYSQL.setDisqualified(args[1], false);
                        sender.sendMessage(StaticCache.prefix + StaticCache.setdisqualified_success.replace("{player}", args[1]).replace("{state}", "false"));
                    } catch (SQLException e) {
                        sender.sendMessage(StaticCache.prefix + StaticCache.mysqlerr.replace("{error}", e.getMessage()));
                        throw new RuntimeException();
                    }
                    break;
                default:
                    sender.sendMessage(StaticCache.prefix + StaticCache.cmdfail);
                    break;
            }
        } else {
            sender.sendMessage(StaticCache.prefix + StaticCache.playernotexist);
        }
        return true;
    }
}
