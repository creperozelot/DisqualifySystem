package creperozelot.disqualify_system.listener;

import creperozelot.disqualify_system.main;
import creperozelot.disqualify_system.utils.MYSQL;
import creperozelot.disqualify_system.utils.StaticCache;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class PlayerJoinListener implements Listener {
    @EventHandler

    public void PlayerJoinListener(PlayerJoinEvent event) throws SQLException {
        boolean active = main.getInstance().getConfig().getBoolean("onjoin");
        boolean deletionactive = main.getInstance().getConfig().getBoolean("delete");
        int days = main.getInstance().getConfig().getInt("days");
        String kicktitle = main.getInstance().getConfig().getString("kickmessage.title");
        String kickmessage = main.getInstance().getConfig().getString("kickmessage.message");
        Player player = event.getPlayer();

        if (!MYSQL.PlayerExist(player)) {
            MYSQL.addPlayer(player);
        }

        if (active) {
            if (!MYSQL.isdisqualified(player)) {
                if (!(MYSQL.getdays(player) > days)) {
                    MYSQL.setPlayingState(player, true);
                    MYSQL.setdays(player, 0);
                } else {
                    MYSQL.setDisqualified(player, true);
                    if (deletionactive) {
                        MYSQL.deleterow(player);
                    }
                    player.kickPlayer(kicktitle + "\n" + kickmessage.replace("{name}", player.getName()));
                }
            } else {
                player.kickPlayer(kicktitle + "\n" + kickmessage.replace("{name}", player.getName()));
            }
        }
    }
}
