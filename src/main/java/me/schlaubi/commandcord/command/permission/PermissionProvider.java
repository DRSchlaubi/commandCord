package me.schlaubi.commandcord.command.permission;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public interface PermissionProvider {

    /**
     * Will be callen by Permissions#guildOwnerOnly() {@link me.schlaubi.commandcord.command.permission.Permissions}
     * @param member The member object of the user who performs the command {@link me.schlaubi.commandcord.command.permission.Member}
     * @return If the user has the permission
     */
    boolean isGuildOwner(Member member);

    /**
     * Will be callen by Permissions#authorOnly() {@link me.schlaubi.commandcord.command.permission.Permissions}
     * @param member The member object of the user who performs the command {@link me.schlaubi.commandcord.command.permission.Member}
     * @return If the user has the permission
     */
    boolean isBotAuthor(Member member);

    /**
     * Will be callen by Permissions#node() {@link me.schlaubi.commandcord.command.permission.Permissions}
     * @param member The member object of the user who performs the command {@link me.schlaubi.commandcord.command.permission.Member}
     * @param permissionNode The required permission node
     * @return If the user has the permission
     */
    boolean hasPermissionNode(Member member, String permissionNode);

    /**
     * Will be callen by Permissions#level() {@link me.schlaubi.commandcord.command.permission.Permissions}
     * @param member The member object of the user who performs the command {@link me.schlaubi.commandcord.command.permission.Member}
     * @param permissionLevel The reburied permission level
     * @return If the user has the permission
     */
    boolean hasPermissionLevel(Member member, int permissionLevel);
}
