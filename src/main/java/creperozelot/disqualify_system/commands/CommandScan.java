package creperozelot.disqualify_system.commands;

import creperozelot.disqualify_system.utils.MYSQL;
import creperozelot.disqualify_system.utils.StaticCache;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.sql.SQLException;
import java.util.List;

public class CommandScan  {
    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> playerPLAYERNAMEnotjoined = null;
        List<String> playerPLAYERNAMEjoined = null;
        try {
            playerPLAYERNAMEnotjoined = MYSQL.getNotJoinedPlayers();
        } catch (SQLException e) {
            sender.sendMessage(StaticCache.prefix + StaticCache.mysqlerr.replace("{error}", e.getMessage()));
            throw new RuntimeException(e);
        }

        try {
            playerPLAYERNAMEjoined = MYSQL.getJoinedPlayers();
        } catch (SQLException e) {
            sender.sendMessage(StaticCache.prefix + StaticCache.mysqlerr.replace("{error}", e.getMessage()));
            throw new RuntimeException(e);
        }


        for (String PLAYERNAME : playerPLAYERNAMEnotjoined) {
            try {
                MYSQL.addday(PLAYERNAME);
            } catch (SQLException e) {
                sender.sendMessage(StaticCache.prefix + StaticCache.mysqlerr.replace("{error}", e.getMessage()));
                throw new RuntimeException(e);
            }
        }

        for (String PLAYERNAME : playerPLAYERNAMEjoined) {
            try {
                MYSQL.setPlayingState(PLAYERNAME, false);
            } catch (SQLException e) {
                sender.sendMessage(StaticCache.prefix + StaticCache.mysqlerr.replace("{error}", e.getMessage()));
                throw new RuntimeException(e);
            }

            try {
                MYSQL.setdays(PLAYERNAME, 0);
            } catch (SQLException e) {
                sender.sendMessage(StaticCache.prefix + StaticCache.mysqlerr.replace("{error}", e.getMessage()));
                throw new RuntimeException(e);
            }
        }
        sender.sendMessage(StaticCache.prefix + StaticCache.scan_success);
        Bukkit.broadcastMessage(StaticCache.prefix + StaticCache.scan_performed_broadcast);
        return true;
    }
}
