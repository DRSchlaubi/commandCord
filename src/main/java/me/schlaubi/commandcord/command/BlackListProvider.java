package me.schlaubi.commandcord.command;

public interface BlackListProvider {

    /**
     * Will be called to check if a channel is blacklistet
     * @param textChannelId The id of the textchannel where the command is executed
     * @return If the channel is blacklisted
     */
    boolean isBlackListed(String textChannelId);
}
