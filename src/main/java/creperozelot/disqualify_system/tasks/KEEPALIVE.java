package creperozelot.disqualify_system.tasks;

import creperozelot.disqualify_system.main;
import creperozelot.disqualify_system.utils.MYSQL;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.sql.Statement;

public class KEEPALIVE {
    public static void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    MYSQL.keepalive();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }.runTaskTimer(main.getInstance(), 0, 10 * 60 * 20);
    }
}
