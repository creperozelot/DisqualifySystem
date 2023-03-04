package creperozelot.disqualify_system;

import creperozelot.disqualify_system.commands.*;
import creperozelot.disqualify_system.listener.PlayerJoinListener;
import creperozelot.disqualify_system.tasks.CheckTime;
import creperozelot.disqualify_system.tasks.KEEPALIVE;
import creperozelot.disqualify_system.utils.MYSQL;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class main extends JavaPlugin {

    private static main instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    public static main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getLogger().info("§f===================");
        this.getLogger().info("§cDisqualify System §aEnabled");
        this.getLogger().info("§aby creperozelot");
        this.getLogger().info("§f===================");
        if (!MYSQL.isConnected()) {
            MYSQL.connect();
            MYSQL.update("CREATE TABLE IF NOT EXISTS DATA (`PLAYERNAME` varchar(32), JOINED varchar(8), DAYS int, DISQUALIFIED varchar(8));");
        }


        registerCommand();
        registerListener();
        KEEPALIVE.run();
        if (main.getInstance().getConfig().getBoolean("autodisqualify")) {
            try {
                CheckTime.run();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onDisable() {
        this.getLogger().info("§f===================");
        this.getLogger().info("§cDisqualify System Disabled");
        this.getLogger().info("§aby creperozelot");
        this.getLogger().info("§f===================");

        MYSQL.disconnect();
    }

    private void registerCommand() {
        //this.getCommand("heal").setExecutor(new CommandHeal());
        this.getCommand("ds").setExecutor(new subcommand());
    }

    private void registerListener() {
        // getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

}
