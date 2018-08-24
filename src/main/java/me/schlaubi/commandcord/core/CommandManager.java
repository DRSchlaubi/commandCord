package me.schlaubi.commandcord.core;

import me.schlaubi.commandcord.command.BeforeTasks;
import me.schlaubi.commandcord.command.BlackListProvider;
import me.schlaubi.commandcord.command.PrefixProvider;
import me.schlaubi.commandcord.command.handlers.Command;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.event.EventManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class CommandManager {

    protected final Map<String, Command> commandAssociations = new HashMap<>();
    public EventManager eventManager = new EventManager();
    protected boolean useGuildPrefixes;
    protected boolean useBlacklist;
    private boolean authorIsAdmin;
    private boolean deleteInvokeMessage;
    private boolean multiThreading;
    private boolean sendTyping;
    protected PrefixProvider prefixProvider;
    protected String defaultPrefix;
    private int deleteCommandMessage;
    private PermissionProvider permissionProvider;
    private Object api;
    private CommandParser parser;
    private BeforeTasks beforeTasksHandler;
    private BlackListProvider blackListProvider;

    public CommandManager(boolean useGuildPrefixes, PermissionProvider permissionProvider, PrefixProvider prefixProvider, String defaultPrefix, CommandParser parser, Object api, BeforeTasks beforeTasksHandler, boolean useBlackList, BlackListProvider blackListProvider, boolean authorIsAdmin, boolean deleteInvokeMessage, int deleteCommandMessage, boolean multiThreading, boolean sendTyping) {
        this.useGuildPrefixes = useGuildPrefixes;
        this.permissionProvider = permissionProvider;
        this.prefixProvider = prefixProvider;
        this.defaultPrefix = defaultPrefix;
        this.parser = parser;
        this.api = api;
        this.useBlacklist = useBlackList;
        this.blackListProvider = blackListProvider;
        this.beforeTasksHandler = beforeTasksHandler;
        this.authorIsAdmin = authorIsAdmin;
        this.deleteInvokeMessage = deleteInvokeMessage;
        this.deleteCommandMessage = deleteCommandMessage;
        this.multiThreading = multiThreading;
        this.sendTyping = sendTyping;
    }

    public PermissionProvider getPermissionProvider() {
        return permissionProvider;
    }

    /**
     * Registers a command
     *
     * @param command Command{@link me.schlaubi.commandcord.command.handlers.Command } that is to be added
     */
    public void registerCommand(Command command) {
        for (String alias : command.getAliases()) {
            //Check if alias is already taken
            if (commandAssociations.containsKey(alias))
                System.err.println("Warning: Alias " + alias + " is already used by command handler " + commandAssociations.get(alias).toString());
            else
                commandAssociations.put(alias.toLowerCase(), command);
        }
    }

    /**
     * Registers more command handlers
     *
     * @param commands Commands{@link me.schlaubi.commandcord.command.handlers.Command } that is to be added
     */
    public void registerCommands(Command... commands) {
        for (Command handler : commands)
            registerCommand(handler);
    }

    /**
     * Unregisters an alias
     *
     * @param alias Alias that is to be removed
     */
    public void unregisterCommand(String alias) {
        //Check if alias is used
        if (commandAssociations.containsKey(alias))
            System.err.println("Warning: Alias " + alias + " is not registred");
        else
            commandAssociations.remove(alias);
    }

    public void unregisterCommand(Command command) {
        //Check if alias is used
        if (commandAssociations.containsValue(command))
            System.err.println("Warning: Handler " + command + " is not registred");
        else
            commandAssociations.forEach((a, h) -> {
                if (h.equals(command))
                    commandAssociations.remove(a);
            });
    }

    /**
     * Parse a command
     */
    public void parse(String message, String guildId, String textChannelId, String messageId, String authorId) {
        if(multiThreading)
            new Thread(() -> parseCommand(message, guildId, textChannelId, messageId, authorId), "CommandHandlingThread").start();
        else
            parseCommand(message, guildId, textChannelId, messageId, authorId);
    }

    private void parseCommand(String message, String guildId, String textChannelId, String messageId, String authorId){
        parser.parse(message, guildId, textChannelId, messageId, authorId);
    }

    public Object getApi() {
        return api;
    }

    public Map<String, Command> getCommandAssociations() {
        return commandAssociations;
    }

    public EventManager getEventManager() {
        return eventManager;
    }

    public boolean isAuthorAdmin() {
        return authorIsAdmin;
    }

    public boolean isDeleteInvokeMessage() {
        return deleteInvokeMessage;
    }

    public boolean isTyping() {
        return sendTyping;
    }

    public int getDeleteCommandMessage() {
        return deleteCommandMessage;
    }

    public String getDefaultPrefix() { return defaultPrefix; }

    public BeforeTasks getBeforeTasksHandler() {
        return beforeTasksHandler;
    }

    public BlackListProvider getBlackListProvider() {
        return blackListProvider;
    }
}
