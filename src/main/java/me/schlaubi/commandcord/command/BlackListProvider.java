package me.schlaubi.commandcord.command;

public interface BlackListProvider {

    boolean isBlackListed(String textChannelId);
}
