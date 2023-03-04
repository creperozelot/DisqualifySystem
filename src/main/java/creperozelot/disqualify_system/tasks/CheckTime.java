package creperozelot.disqualify_system.tasks;

import creperozelot.disqualify_system.main;
import creperozelot.disqualify_system.utils.MYSQL;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CheckTime {
    public static void run() throws SQLException {
        new BukkitRunnable() {
            @Override
            public void run() {
                String timestamp = new SimpleDateFormat("HH-mm-ss").format(Calendar.getInstance().getTime());
                String time = main.getInstance().getConfig().getString("endtime");
                if(timestamp.equals(time)) {
                    List<String> playerPLAYERNAMEnotjoined = null;
                    List<String> playerPLAYERNAMEjoined = null;
                    try {
                        playerPLAYERNAMEnotjoined = MYSQL.getNotJoinedPlayers();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        playerPLAYERNAMEjoined = MYSQL.getJoinedPlayers();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }


                    for (String PLAYERNAME : playerPLAYERNAMEnotjoined) {
                        try {
                            MYSQL.addday(PLAYERNAME);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    for (String PLAYERNAME : playerPLAYERNAMEjoined) {
                        try {
                            MYSQL.setPlayingState(PLAYERNAME, false);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }

                        try {
                            MYSQL.setdays(PLAYERNAME, 0);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }


                }
            }
        }.runTaskTimer(main.getInstance(), 0, 20);
    }
}
