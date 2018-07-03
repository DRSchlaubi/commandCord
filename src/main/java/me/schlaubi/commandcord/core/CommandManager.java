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

    private boolean useGuildPrefixes;
    private PermissionProvider permissionProvider;
    private PrefixProvider prefixProvider;
    private String defaultPrefix;
    private Object api;
    public EventManager eventManager = new EventManager();
    private final Map<String, GeneralCommandHandler> commandAssociations = new HashMap<>();

    public CommandManager(boolean useGuildPrefixes, PermissionProvider permissionProvider, PrefixProvider prefixProvider, String defaultPrefix){
        this.useGuildPrefixes = useGuildPrefixes;
        this.permissionProvider = permissionProvider;
        this.prefixProvider = prefixProvider;
        this.defaultPrefix = defaultPrefix;
    }

    public PermissionProvider getPermissionProvider(){
        return permissionProvider;
    }

    /**
     * Registers a command
     * @param handler CommandHandler that is to be added
     */
    public void register(GeneralCommandHandler handler){
        for(String alias : handler.aliases){
            //Check if alias is already taken
            if(commandAssociations.containsKey(alias))
                System.err.println("Warning: Alias " + alias + " is already used by command handler " + commandAssociations.get(alias).toString());
            else
                commandAssociations.put(alias, handler);
        }
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

    protected GeneralCommandHandler getHandlerByAlias(String alias){
        return commandAssociations.get(alias);
    }

    public void parse(String message, String guildId, String textChannelId, String messageId){

    }

    protected boolean isCommand(String message, String guildId){
        if(useGuildPrefixes)
            return message.startsWith(defaultPrefix) || message.startsWith(prefixProvider.getPrefix(guildId));
        else
            return message.startsWith(defaultPrefix);
    }

    protected String[] getArgs(String message, String guildId){
        String rawArgs = replacePrefix(message, guildId);
        return rawArgs.replace(rawArgs.split(" ")[0], "").split(" ");
    }

    protected String getAlias(String message, String guildId){
        return replacePrefix(message,guildId).split(" ")[0];
    }

    private String replacePrefix(String message, String guildId){
        if(useGuildPrefixes) {
            if(message.startsWith(defaultPrefix))
                message = message.replaceFirst(defaultPrefix, "");
            else
                message = message.replaceFirst(prefixProvider.getPrefix(guildId), "");
        } else
            message = message.replaceFirst(defaultPrefix, "");
        return message;
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
