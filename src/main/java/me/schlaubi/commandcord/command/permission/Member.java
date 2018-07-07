package me.schlaubi.commandcord.command.permission;


import de.btobastian.javacord.entities.Server;
import de.btobastian.javacord.entities.User;
import sx.blah.discord.handle.impl.obj.Guild;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Member {

    private String guildId;
    private String userId;

    private Member(String userId, String guildId) {
        this.guildId = guildId;
        this.userId = userId;
    }

    public static Member fromJavaCord(User user, Server server) {
        return new Member(user.getId(), server.getId());
    }

    public static Member fromDiscord4J(sx.blah.discord.handle.impl.obj.User user, Guild guild) {
        return new Member(user.getStringID(), guild.getStringID());
    }

    public static Member fromJDA(net.dv8tion.jda.core.entities.Member member) {
        return new Member(member.getUser().getId(), member.getUser().getId());
    }

    public String getGuildId() {
        return guildId;
    }

    public String getUserId() {
        return userId;
    }
}
