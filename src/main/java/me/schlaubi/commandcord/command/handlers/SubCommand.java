package me.schlaubi.commandcord.command.handlers;

import me.schlaubi.commandcord.command.permission.Permissions;

public abstract class SubCommand extends Command {

    /**
     * Create a sub command for a normal command {@link me.schlaubi.commandcord.command.handlers.Command}
     * @param aliases The aliases of the sub command (e.g <prefix><mainCommandAlias> <subCommandAlias> [args]
     * @param permissions The permissions of the command {@link me.schlaubi.commandcord.command.permission.Permissions}
     * @param description The description of the command (is needed for help message)
     * @param usage The usage of the command (is needed for help message)
     */
    public SubCommand(String[] aliases,  Permissions permissions, String description, String usage) {
        super(aliases, null, permissions, description, usage);
    }

}
