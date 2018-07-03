package me.schlaubi.commandcord.command.handlers;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.permission.Permissions;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public abstract class GeneralCommandHandler {

    public String[] aliases;
    public CommandType type;
    public Permissions permissions;
    public String description;
    public String usage;


    /**
     * Handler for every command
     * @param aliases String[] All aliases to invoke that command
     * @param type CommandType Type of the Command
     * @param permissions Permissions The permissions of the command
     * @param description The description of the command
     * @param usage The usage of that command
     */
    public GeneralCommandHandler(String[] aliases, CommandType type, Permissions permissions, String description, String usage){
        this.aliases = aliases;
        this.type = type;
        this.permissions = permissions;
        this.description = description;
        this.usage = usage;
    }
}
