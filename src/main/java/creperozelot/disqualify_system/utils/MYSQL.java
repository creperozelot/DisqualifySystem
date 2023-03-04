package creperozelot.disqualify_system.utils;

import creperozelot.disqualify_system.main;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class MYSQL {
    public static String host = main.getInstance().getConfig().getString("database.host");

    public static String port = main.getInstance().getConfig().getString("database.port");

    public static String database = main.getInstance().getConfig().getString("database.db");

    public static String username = main.getInstance().getConfig().getString("database.user");

    public static String password = main.getInstance().getConfig().getString("database.passwd");

    public static Connection con;

    public static void connect() {
        if (!isConnected())
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoreonnect=true", username, password);
                System.out.println(StaticCache.prefix + "Connected to MYSQL Database.");
            } catch (SQLException e) {
                main.getInstance().getLogger().log(Level.SEVERE ,StaticCache.prefix + "Can't Connect to MYSQL Database. MYSQL IS NEEDED");
                main.getInstance().getPluginLoader().disablePlugin(main.getInstance());
            }
    }

    public static void disconnect() {
        if (isConnected())
            try {
                if (MYSQL.isConnected()) {
                    con.close();
                    System.out.println(StaticCache.prefix + "Disconnected from MYSQL Connection.");
                } else {
                    System.out.println(StaticCache.prefix + "Can't disconnect from MYSQL Connection because no connection initiated.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void forcedisconnect() {
        if (isConnected())
            try {
                con.close();
                System.out.println(StaticCache.prefix + "MYSQL Connection Disconnected. (force)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static boolean isConnected() {
        if (con == null)
            return false;
        return true;
    }

    public static void update(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean PlayerExist(Player player) {

        try {


            Statement stmt = con.createStatement();
            return stmt.executeQuery("SELECT * FROM `DATA` WHERE `PLAYERNAME`='" + player.getName() + "';").next();
        } catch (SQLException e){

            e.printStackTrace();

        }
        return false;
    }

    public static boolean PlayerExist(String playername) {

        try {


            Statement stmt = con.createStatement();
            return stmt.executeQuery("SELECT * FROM `DATA` WHERE `PLAYERNAME`='" + playername + "';").next();
        } catch (SQLException e){

            e.printStackTrace();

        }
        return false;
    }

    public static void addPlayer(Player player) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("INSERT INTO DATA (PLAYERNAME, JOINED, DAYS, DISQUALIFIED) VALUES  ('" + player.getName() + "', 'true', '0', 'false');");
    }

    public static void addPlayer(String PLAYERNAME) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("INSERT INTO DATA (PLAYERNAME, JOINED, DAYS, DISQUALIFIED) VALUES  ('" + PLAYERNAME + "', 'true', '0', 'false');");
    }

    public static void addday(Player player) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery("SELECT DAYS FROM `DATA` WHERE PLAYERNAME='" + player.getName() + "';");
        result.first();
        int result2 = result.getInt("DAYS");

        int daysfinale = result2 + 1;

        stmt.execute("UPDATE DATA SET DAYS='" + daysfinale + "' WHERE PLAYERNAME='" + player.getName() + "'");
    }

    public static void addday(String playername) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery("SELECT DAYS FROM `DATA` WHERE PLAYERNAME='" + playername + "';");
        result.first();
        int result2 = result.getInt("DAYS");

        int daysfinale = result2 + 1;

        stmt.execute("UPDATE DATA SET DAYS='" + daysfinale + "' WHERE PLAYERNAME='" + playername + "'");
    }

    public static void setdays(Player player, int days) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE DATA SET DAYS='" + days + "' WHERE PLAYERNAME='" + player.getName() + "'");
    }

    public static void setdays(String playername, int days) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("UPDATE DATA SET DAYS='" + days + "' WHERE PLAYERNAME='" + playername + "'");
    }

    public static int getdays(Player player) throws SQLException {
        int days = 0;
        if (con.createStatement().executeQuery("SELECT DAYS FROM `DATA` WHERE PLAYERNAME='" + player.getName() + "';").next()) {

            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT DAYS FROM `DATA` WHERE PLAYERNAME='" + player.getName() + "';");
            result.first();


            days = result.getInt("DAYS");
        }
        return days;
    }

    public static void deleterow(Player player) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("DELETE FROM DATA WHERE PLAYERNAME='" + player.getName() + "'");
    }

    public static void deleterow(String PLAYERNAME) throws SQLException {
        Statement stmt = con.createStatement();
        stmt.execute("DELETE FROM DATA WHERE PLAYERNAME='" + PLAYERNAME + "'");
    }

    public static void keepalive() throws SQLException {
        Statement stmt = con.createStatement();
        stmt.executeQuery("SELECT 'KEEP_ALIVE';").next();
    }

    public static void setDisqualified(Player player, boolean state) throws SQLException {
        Statement stmt = con.createStatement();
        if (!state) {
            stmt.execute("UPDATE DATA SET DISQUALIFIED='" + state + "' WHERE PLAYERNAME='" + player.getName() + "'");
        } else {
            stmt.execute("UPDATE DATA SET DISQUALIFIED='" + state + "' WHERE PLAYERNAME='" + player.getName() + "'");
        }
    }

    public static void setDisqualified(String PLAYERNAME, boolean state) throws SQLException {
        Statement stmt = con.createStatement();
        if (!state) {
            stmt.execute("UPDATE DATA SET DISQUALIFIED='" + state + "' WHERE PLAYERNAME='" + PLAYERNAME + "'");
        } else {
            stmt.execute("UPDATE DATA SET DISQUALIFIED='" + state + "' WHERE PLAYERNAME='" + PLAYERNAME + "'");
        }
    }

    public static void setPlayingState(Player player, boolean state) throws SQLException {
        Statement stmt = con.createStatement();
        if (!state) {
            stmt.execute("UPDATE DATA SET JOINED='" + state + "' WHERE PLAYERNAME='" + player.getName() + "'");
        } else {
            stmt.execute("UPDATE DATA SET JOINED='" + state + "' WHERE PLAYERNAME='" + player.getName() + "'");
        }
    }

    public static void setPlayingState(String playername, boolean state) throws SQLException {
        Statement stmt = con.createStatement();
        if (!state) {
            stmt.execute("UPDATE DATA SET JOINED='" + state + "' WHERE PLAYERNAME='" + playername + "'");
        } else {
            stmt.execute("UPDATE DATA SET JOINED='" + state + "' WHERE PLAYERNAME='" + playername + "'");
        }
    }

    public static boolean isdisqualified(Player player) throws SQLException {
        boolean bol = false;

        if (con.createStatement().executeQuery("SELECT DISQUALIFIED FROM `DATA` WHERE PLAYERNAME='" + player.getName() + "';").next()) {

            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery("SELECT DISQUALIFIED FROM `DATA` WHERE PLAYERNAME='" + player.getName() + "';");
            result.first();
            String result2 = result.getString("DISQUALIFIED");


            switch (result2) {
                case "false":
                    bol = false;
                    break;
                case "true":
                    bol = true;
                    break;
                default:
                    bol = false;
                    break;
            }
            return bol;
        }

        return false;
    }

    public static List<String> getNotJoinedPlayers() throws SQLException {
        Statement stmt = con.createStatement();

        ResultSet result = stmt.executeQuery("SELECT * FROM `DATA` WHERE `JOINED`='false';");

        result.first();

        List<String> players = new ArrayList<>();

        if (con.createStatement().executeQuery("SELECT * FROM `DATA` WHERE `JOINED`='false';").next()) {

            while (!result.isAfterLast()) {

                String team = result.getString("PLAYERNAME");

                players.add(team);

                result.next();

            }
        }
        return players;
    }

    public static List<String> getJoinedPlayers() throws SQLException {
        Statement stmt = con.createStatement();

        ResultSet result = stmt.executeQuery("SELECT * FROM `DATA` WHERE `JOINED`='true';");

        result.first();

        List<String> players = new ArrayList<>();

        if (con.createStatement().executeQuery("SELECT * FROM `DATA` WHERE `JOINED`='true';").next()) {

            while (!result.isAfterLast()) {

                String team = result.getString("PLAYERNAME");

                players.add(team);

                result.next();

            }
        }
        return players;
    }
}
