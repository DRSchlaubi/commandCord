package me.schlaubi.commandcord;

import me.schlaubi.commandcord.core.CommandManager;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class CommandCord {

    public static final String VERSION = "1.0.0 ";
    public static final String AUTHOR = "Schlaubi";
    public static final String LICENSE = "GPL-3.0";
    public static final String GITHUB_URL = "http://github.com/DRschlaubi/commandCord";
    private static CommandManager instance;

    public static CommandManager getInstance() {
        return instance;
    }

    public static void setInstance(CommandManager manager) {
        instance = manager;
    }

    public static void main(String[] args) {

    }
}
