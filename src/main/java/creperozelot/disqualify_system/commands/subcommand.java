package creperozelot.disqualify_system.commands;

import creperozelot.disqualify_system.utils.StaticCache;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class subcommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "setdays":
                Command_setdays.onCommand(sender, command, label, args);
                break;
            case "addplayer":
                CommandAddPlayer.onCommand(sender, command, label, args);
                break;
            case "removeplayer":
                CommandRemovePlayer.onCommand(sender, command, label, args);
                break;
            case "setplaystate":
                CommandSetPlayState.onCommand(sender, command, label, args);
                break;
            case "scan":
                CommandScan.onCommand(sender, command, label, args);
                break;
            case "help":
                sender.sendMessage("§a----------<" + StaticCache.prefix + "§a>----------");
                sender.sendMessage(StaticCache.prefix + "§chelp - Shows this Menu");
                sender.sendMessage(StaticCache.prefix + "§cds - The Sub Command");
                sender.sendMessage(StaticCache.prefix + "§csetdays <playername> <days> - Set the Days from the Player");
                sender.sendMessage(StaticCache.prefix + "§csetplaystate <playername> <true | false> - Set the Days Playstate for the Player");
                sender.sendMessage(StaticCache.prefix + "§caddplayer <playername> - Add a Player to the Database");
                sender.sendMessage(StaticCache.prefix + "§cremoveplayer <playername> - Removes a Player from the Database");
                sender.sendMessage(StaticCache.prefix + "§cscan - Run the Autoscan manually");
                break;
            default:
                sender.sendMessage(StaticCache.prefix + StaticCache.cmdfail);
                break;
        }
        return true;
    }
}
