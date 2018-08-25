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

    /**
     * Returns the user id as a String
     * @return String userId
     */
    public String getGuildId() {
        return guildId;
    }

    /**
     * Returns the guild id as a String
     * @return String guildId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Returns the user id as a Long
     * @return Long userId
     */
    public Long getUserIdLong() {
        return Long.parseLong(userId);
    }

    /**
     * Returns the guild id as a Long
     * @return Long guildId
     */
    public Long getGuildIdLong() {
        return Long.parseLong(guildId);
    }
}
