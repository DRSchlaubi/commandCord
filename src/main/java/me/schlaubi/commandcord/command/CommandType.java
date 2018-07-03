package me.schlaubi.commandcord.command;

public enum CommandType {

    ADMIN("ADMIN"),
    GUILDADMIN("GUILDADMIN"),
    MODERATION("MODERATION"),
    FUN("FUN"),
    SETTINGS("SETTINGS"),
    CHAT("CHAT"),
    GENERAL("GENERAL"),
    GAMES("GAMES"),
    MISC("MISC");

    private String displayName;

    CommandType(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }
}
