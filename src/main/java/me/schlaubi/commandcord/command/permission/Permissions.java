package me.schlaubi.commandcord.command.permission;

import me.schlaubi.commandcord.CommandCord;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Permissions {

    private boolean isAuthorOnly;
    private boolean isGuildOwnerOnly;
    private boolean publicCommand;
    private String permissionNode;
    private int permissionLevel;


    private Permissions(String permissionNode, boolean isPublic, boolean isAuthorOnly, boolean isGuildOwnerOnly, int permissionLevel) {
        this.publicCommand = isPublic;
        this.isAuthorOnly = isAuthorOnly;
        this.isGuildOwnerOnly = isGuildOwnerOnly;
        this.permissionNode = permissionNode;
        this.permissionLevel = permissionLevel;
    }

    /**
     * Everybody can execute the command
     *
     * @return Permissions object
     */
    public static Permissions everyone() {
        return new Permissions("public", true, false, false, 0);
    }

    /**
     * Only the bot author can execute the command
     *
     * @return Permissions object
     */
    public static Permissions authorOnly() {
        return new Permissions("authorOnly", false, true, false, 0);
    }

    /**
     * Only the guild owner can execute the command
     *
     * @return Permissions object
     */
    public static Permissions guildOwnerOnly() {
        return new Permissions("guildOwnerOnly", false, false, true, 0);
    }

    /**
     * Only users who has the provided permissionNode can execute the command
     *
     * @param permissionNode The permissionNode, which is required to execute the command
     * @return Permissions object
     */
    public static Permissions node(String permissionNode) {
        return new Permissions(permissionNode, false, false, false, 0);
    }

    /**
     * Only users who has the provided permissionNode can execute the command
     *
     * @param permissionLevel The permissionLevel, which is required to execute the command
     * @return Permissions object
     */
    public static Permissions level(int permissionLevel) {
        return new Permissions("permLevel", false, false, false, permissionLevel);
    }

    public boolean isCovered(Member member) {
        if (publicCommand)
            return true;
        if (CommandCord.getInstance().isAuthorAdmin() && CommandCord.getInstance().getPermissionProvider().isBotAuthor(member))
            return true;
        if (isGuildOwnerOnly)
            return CommandCord.getInstance().getPermissionProvider().isGuildOwner(member);
        if (isAuthorOnly)
            return CommandCord.getInstance().getPermissionProvider().isBotAuthor(member);
        if (permissionLevel != 0)
            return CommandCord.getInstance().getPermissionProvider().hasPermissionLevel(member, permissionLevel);
        return CommandCord.getInstance().getPermissionProvider().hasPermissionNode(member, permissionNode);
    }


}
