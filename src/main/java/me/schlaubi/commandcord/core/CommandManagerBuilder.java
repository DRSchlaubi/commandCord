package me.schlaubi.commandcord.core;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.BeforeTasks;
import me.schlaubi.commandcord.command.BlackListProvider;
import me.schlaubi.commandcord.command.DefaultBeforeTasks;
import me.schlaubi.commandcord.command.PrefixProvider;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.core.parser.Discord4JParser;
import me.schlaubi.commandcord.core.parser.JDAParser;
import me.schlaubi.commandcord.core.parser.JavaCordParser;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class CommandManagerBuilder {

    private APIWrapper wrapper;
    private boolean useGuildPrefixes = false;
    private boolean useBlackList = false;
    private boolean authorIsAdmin = true;
    private boolean deleteInvokeMessage = true;
    private int deleteCommandMessage = 0;
    private String defaultPrefix;
    private PermissionProvider permissionProvider;
    private PrefixProvider prefixProvider;
    private BlackListProvider blackListProvider;
    private Object api;

    private BeforeTasks beforeTasksHandler = new DefaultBeforeTasks();

    public CommandManagerBuilder(APIWrapper apiWrapper){
        this.wrapper = apiWrapper;
    }

    public CommandManagerBuilder setPermissionProvider(PermissionProvider permissionProvider) {
        this.permissionProvider = permissionProvider;
        return this;
    }

    public CommandManagerBuilder setPrefixProvider(PrefixProvider prefixProvider) {
        this.prefixProvider = prefixProvider;
        return this;
    }

    public CommandManagerBuilder setDefaultPrefix(String defaultPrefix) {
        this.defaultPrefix = defaultPrefix;
        return this;
    }

    public CommandManagerBuilder setApi(Object api) {
        this.api = api;
        return this;
    }

    public CommandManagerBuilder enableGuildPrefixes(boolean useGuildPrefixes) {
        this.useGuildPrefixes = useGuildPrefixes;
        return this;
    }

    public CommandManagerBuilder setBeforeTasksHandler(BeforeTasks beforeTasksHandler) {
        this.beforeTasksHandler = beforeTasksHandler;
        return this;
    }

    public CommandManagerBuilder enableBlacklist(boolean enable){
        this.useBlackList = enable;
        return this;
    }

    public CommandManagerBuilder setBlacklistProvider(BlackListProvider blacklistProvider){
        this.blackListProvider = blacklistProvider;
        return this;
    }

    public CommandManagerBuilder authorIsAdmin(boolean enable){
        this.authorIsAdmin = enable;
        return this;
    }

    public CommandManagerBuilder deleteInvokeMessages(boolean enable){
        this.deleteInvokeMessage = enable;
        return this;
    }

    public CommandManagerBuilder deleteCommandMessages(int deleteTime){
        this.deleteCommandMessage = deleteTime;
        return this;
    }

    public CommandManager build() {
        runChecks();
        CommandManager out = new CommandManager(useGuildPrefixes, permissionProvider, prefixProvider, defaultPrefix, getParser(), api, beforeTasksHandler, useBlackList, blackListProvider, authorIsAdmin, deleteInvokeMessage, deleteCommandMessage);
        CommandCord.setInstance(out);
        return out;
    }

    private void runChecks(){
        if(permissionProvider == null)
            throw new IllegalArgumentException("PermissionProvider cannot be null");
        if(useGuildPrefixes && prefixProvider == null)
            throw new IllegalArgumentException("PrefixProvider cannot be null when using guild prefixes");
        if(defaultPrefix == null)
            throw new IllegalArgumentException("Default prefix cannot be null");
        if(api == null)
            throw new IllegalArgumentException("API cannot be null");
        if(useBlackList && blackListProvider == null)
            throw new IllegalArgumentException("Blacklistprovider cannot be null when using blacklist");

    }

    private CommandParser getParser() {
        if (wrapper.equals(APIWrapper.JDA))
            return new JDAParser();
        else if (wrapper.equals(APIWrapper.DISCORD4J))
            return new Discord4JParser();
        else if (wrapper.equals(APIWrapper.JAVACORD))
            return new JavaCordParser();
        else
            return null;
    }


}
