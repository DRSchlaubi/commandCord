package me.schlaubi.commandcord.command;

public interface BeforeTasks {

    /**
     * Here you can check permissions or something like that
     * @return true to run the command or false to stop
     */
    boolean run(String message, String guildId, String textChannelId, String messageId);
}
