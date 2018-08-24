package me.schlaubi.commandcord.examples;

public class Utils {

    public static String excpetionToString(Throwable t) {
        return t.getClass().getCanonicalName() + ":" + t.getMessage();
    }
}
