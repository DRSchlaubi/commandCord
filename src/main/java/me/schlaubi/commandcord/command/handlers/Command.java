package me.schlaubi.commandcord.command.handlers;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;

import java.util.*;

public abstract class Command {

    private String[] aliases;
    private CommandType commandType;
    private Permissions permissions;
    private String description;
    private String usage;
    private Map<String, SubCommand> subCommandAssociations;

    public Command(String[] aliases, CommandType commandType, Permissions permissions, String description, String usage) {
        this.aliases = aliases;
        this.commandType = commandType;
        this.permissions = permissions;
        this.description = description;
        this.usage = usage;
        this.subCommandAssociations = new HashMap<>();
    }

    public abstract Result run(String[] args, CommandEvent event);

    public void registerSubCommand(SubCommand subCommand) {
        Arrays.asList(subCommand.getAliases()).forEach(alias -> subCommandAssociations.put(alias, subCommand));
    }

    public void registerSubCommands(SubCommand... subCommands) {
        Arrays.asList(subCommands).forEach(this::registerSubCommand);
    }

    public String[] getAliases() {
        return aliases;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public Map<String, SubCommand> getSubCommandAssociations() {
        return subCommandAssociations;
    }
}
