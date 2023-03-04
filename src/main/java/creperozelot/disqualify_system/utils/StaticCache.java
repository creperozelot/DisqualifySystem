package creperozelot.disqualify_system.utils;

import creperozelot.disqualify_system.main;

public class StaticCache {
    public static void init() {} //init

    public static String prefix = main.getInstance().getConfig().getString("prefix");

    public static String cmdfail = main.getInstance().getConfig().getString("messages.invalidcommand");
    public static String playernotexist = main.getInstance().getConfig().getString("messages.playernotexist");
    public static String playeralreadyexist = main.getInstance().getConfig().getString("messages.playeralreadyexist");
    public static String mysqlerr = main.getInstance().getConfig().getString("messages.mysqlerr");

    public static String setdays_success = main.getInstance().getConfig().getString("messages.setdays");
    public static String delete_success = main.getInstance().getConfig().getString("messages.deleteplayer");
    public static String addplayer_success = main.getInstance().getConfig().getString("messages.addplayer_success");
    public static String setplaystate_success = main.getInstance().getConfig().getString("messages.setplaystate_success");
    public static String scan_success = main.getInstance().getConfig().getString("messages.scan_success");
    public static String scan_performed_broadcast = main.getInstance().getConfig().getString("messages.scan_performed_broadcast");
}
