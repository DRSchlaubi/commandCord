package me.schlaubi.commandcord.core;

import me.schlaubi.commandcord.command.PrefixProvider;
import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.event.EventManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class CommandManager {

    protected boolean useGuildPrefixes;
    private PermissionProvider permissionProvider;
    protected PrefixProvider prefixProvider;
    protected String defaultPrefix;
    private Object api;
    public EventManager eventManager = new EventManager();
    private CommandParser parser;
    protected final Map<String, GeneralCommandHandler> commandAssociations = new HashMap<>();

    public CommandManager(boolean useGuildPrefixes, PermissionProvider permissionProvider, PrefixProvider prefixProvider, String defaultPrefix, CommandParser parser, Object api){
        this.useGuildPrefixes = useGuildPrefixes;
        this.permissionProvider = permissionProvider;
        this.prefixProvider = prefixProvider;
        this.defaultPrefix = defaultPrefix;
        this.parser = parser;
        this.api = api;
    }

    public PermissionProvider getPermissionProvider(){
        return permissionProvider;
    }

    /**
     * Registers a command
     * @param handler CommandHandler that is to be added
     */
    public void registerCommand(GeneralCommandHandler handler){
        for(String alias : handler.getAliases()){
            //Check if alias is already taken
            if(commandAssociations.containsKey(alias))
                System.err.println("Warning: Alias " + alias + " is already used by command handler " + commandAssociations.get(alias).toString());
            else
                commandAssociations.put(alias.toLowerCase(), handler);
        }
    }

    /**
     * Registers more command handlers
     * @param handlers CommandHandler that is to be added
     */
    public void registerCommands(GeneralCommandHandler... handlers){
        for (GeneralCommandHandler handler : handlers)
            registerCommand(handler);
    }

    /**
     * Unregisters an alias
     * @param alias Alias that is to be removed
     */
    public void unregister(String alias){
        //Check if alias is used
        if(commandAssociations.containsKey(alias))
            System.err.println("Warning: Alias " + alias + " is not registred");
        else
            commandAssociations.remove(alias);
    }

    public void unregister(GeneralCommandHandler handler){
        //Check if alias is used
        if(commandAssociations.containsValue(handler))
            System.err.println("Warning: Handler " + handler+ " is not registred");
        else
           commandAssociations.forEach((a, h) -> {
               if(h.equals(handler))
                   commandAssociations.remove(a);
           });
    }

    public void parse(String message, String guildId, String textChannelId, String messageId){
        parser.parse(message, guildId, textChannelId, messageId);
    }

    public Object getApi(){
        return api;
    }

    public Map<String, GeneralCommandHandler> getCommandAssociations(){
        return commandAssociations;
    }

    public EventManager getEventManager(){
        return eventManager;
    }


}
