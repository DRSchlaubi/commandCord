package me.schlaubi.commandcord.command.result;

public interface Result {

    /**
     * The method will be invoked to send results from command
     * @param textChannel Object of the text channel where the command got invoked
     * @param guild Object of the guild where the command got invoked
     */
    void sendMessage(Object textChannel, Object guild);
}
