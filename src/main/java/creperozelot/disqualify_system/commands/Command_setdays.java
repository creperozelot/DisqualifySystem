package creperozelot.disqualify_system.commands;

import creperozelot.disqualify_system.main;
import creperozelot.disqualify_system.utils.MYSQL;
import creperozelot.disqualify_system.utils.StaticCache;
import creperozelot.disqualify_system.utils.utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.geysermc.floodgate.api.FloodgateApi;

import java.sql.SQLException;
import java.util.Objects;

public class Command_setdays {
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmdfail = StaticCache.cmdfail;
        String playernotexist = StaticCache.playernotexist;
        String mysqlerr = StaticCache.mysqlerr;
        String setdays_success = StaticCache.setdays_success;
        if (args.length == 3 && utils.ifstringint(args[2])) {
            if (MYSQL.PlayerExist(args[1])) {
                try {
                    MYSQL.setdays(args[1], Integer.parseInt(args[2]));
                    sender.sendMessage(StaticCache.prefix + setdays_success.replace("{player}", args[1]).replace("{days}", args[2]));
                } catch (SQLException e) {
                    sender.sendMessage(StaticCache.prefix + mysqlerr.replace("{error}", e.getMessage()));
                    throw new RuntimeException(e);
                }
            } else {
                sender.sendMessage(StaticCache.prefix + playernotexist.replace("{player}", args[1]));
            }
        } else {
            sender.sendMessage(StaticCache.prefix + cmdfail);
        }
        return true;
    }
}
