package me.schlaubi.commandcord.command.permission;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Member {

    private String guildId;
    private String userId;

    public Member(String userId, String guildId) {
        this.guildId = guildId;
        this.userId = userId;
    }

    public String getGuildId() {
        return guildId;
    }

    public String getUserId() {
        return userId;
    }
}
