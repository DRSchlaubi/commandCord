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

    /**
     * Create a command
     * @param aliases The aliases, which the command will listen to
     * @param commandType The type of the command
     * @param permissions The permissions of the command {@link me.schlaubi.commandcord.command.permission.Permissions}
     * @param description The description of the command (is needed for help message)
     * @param usage The usage of the command (is needed for help message)
     */
    public Command(String[] aliases, CommandType commandType, Permissions permissions, String description, String usage) {
        this.aliases = aliases;
        this.commandType = commandType;
        this.permissions = permissions;
        this.description = description;
        this.usage = usage;
        this.subCommandAssociations = new HashMap<>();
    }

    /**
     * Method that will be invoked when the command is invoked
     * @param args Arguments of the command
     * @param event The command event {@link me.schlaubi.commandcord.command.event.CommandEvent}
     * @return Your Result {@link me.schlaubi.commandcord.command.result.Result}
     */
    public abstract Result run(String[] args, CommandEvent event);

    /**
     * Register a sub command
     * @param subCommand The subcommand command handler
     */
    public void registerSubCommand(SubCommand subCommand) {
        Arrays.asList(subCommand.getAliases()).forEach(alias -> subCommandAssociations.put(alias, subCommand));
    }

    /**
     * Register sub commands
     * @param subCommands The subcommands command handler
     */
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
