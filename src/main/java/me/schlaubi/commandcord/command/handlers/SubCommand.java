package me.schlaubi.commandcord.command.handlers;

import me.schlaubi.commandcord.command.permission.Permissions;

public abstract class SubCommand extends Command {

    public SubCommand(String[] aliases,  Permissions permissions, String description, String usage) {
        super(aliases, null, permissions, description, usage);
    }

}
