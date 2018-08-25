package me.schlaubi.commandcord.command;

public interface BeforeTasks {

    /**
     * Here you can check permissions or something like that
     * @param message Content of the message
     * @param guildId The ID of the guild
     * @param textChannelId The ID of the textChannel
     * @param messageId The ID of the message
     * @return true to run the command or false to stop
     */
    boolean run(String message, String guildId, String textChannelId, String messageId);
}
