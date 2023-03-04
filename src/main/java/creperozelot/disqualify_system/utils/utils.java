package creperozelot.disqualify_system.utils;

public class utils {
    public static boolean ifstringint(String string) {
        try {
           Integer.parseInt(string);
           return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
