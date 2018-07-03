package me.schlaubi.commandcord.command.permission;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public interface PermissionProvider {

    boolean isGuildOwner(Member member);

    boolean isBotAuthor(Member member);

    boolean hasPermissionNode(Member member, String permissionNode);

    boolean hasPermissionLevel(Member member, int permissionLevel);
}
