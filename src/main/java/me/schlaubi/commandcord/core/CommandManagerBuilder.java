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

    public CommandManagerBuilder(APIWrapper apiWrapper) {
        this.wrapper = apiWrapper;
    }

    /**
     * Sets the provider for permissions
     *
     * @param permissionProvider Provider for permissions {@link me.schlaubi.commandcord.command.permission.PermissionProvider}
     */
    public CommandManagerBuilder setPermissionProvider(PermissionProvider permissionProvider) {
        this.permissionProvider = permissionProvider;
        return this;
    }

    /**
     * Sets the provider for prefixes
     *
     * @param prefixProvider Provider for permissions {@link me.schlaubi.commandcord.command.PrefixProvider}
     */
    public CommandManagerBuilder setPrefixProvider(PrefixProvider prefixProvider) {
        this.prefixProvider = prefixProvider;
        return this;
    }

    /**
     * Sets the default prefix
     *
     * @param defaultPrefix prefix that is used on every guild
     */
    public CommandManagerBuilder setDefaultPrefix(String defaultPrefix) {
        this.defaultPrefix = defaultPrefix;
        return this;
    }

    /**
     * Sets the Discord api wrapper which is needed to get guild information
     *
     * @param api Your api instance {@link net.dv8tion.jda.core.JDA}, {@link de.btobastian.javacord.DiscordAPI}, {@link sx.blah.discord.api.IDiscordClient}
     */
    public CommandManagerBuilder setApi(Object api) {
        this.api = api;
        return this;
    }

    /**
     * Enabled guild specific prefrixes
     */
    public CommandManagerBuilder enableGuildPrefixes(boolean useGuildPrefixes) {
        this.useGuildPrefixes = useGuildPrefixes;
        return this;
    }

    /**
     * Enabled task that were run before every command
     */
    public CommandManagerBuilder setBeforeTasksHandler(BeforeTasks beforeTasksHandler) {
        this.beforeTasksHandler = beforeTasksHandler;
        return this;
    }

    /**
     * Enabled blacklist
     */
    public CommandManagerBuilder enableBlacklist(boolean enable) {
        this.useBlackList = enable;
        return this;
    }

    /**
     * Sets blacklist provider (is necessary to use blacklist)
     *
     * @param blacklistProvider Provider for blacklist {@link me.schlaubi.commandcord.command.BlackListProvider}
     */
    public CommandManagerBuilder setBlacklistProvider(BlackListProvider blacklistProvider) {
        this.blackListProvider = blacklistProvider;
        return this;
    }

    /**
     * Gives bot auhtors admin rights for every command on all servers
     */
    public CommandManagerBuilder authorIsAdmin(boolean enable) {
        this.authorIsAdmin = enable;
        return this;
    }

    /**
     * Deleted messages that invokes commands
     */
    public CommandManagerBuilder deleteInvokeMessages(boolean enable) {
        this.deleteInvokeMessage = enable;
        return this;
    }

    /**
     * Deletes bot's response to command
     *
     * @param deleteTime Delay till message should be deleted
     */
    public CommandManagerBuilder deleteCommandMessages(int deleteTime) {
        this.deleteCommandMessage = deleteTime;
        return this;
    }

    public CommandManager build() {
        runChecks();
        CommandManager out = new CommandManager(useGuildPrefixes, permissionProvider, prefixProvider, defaultPrefix, getParser(), api, beforeTasksHandler, useBlackList, blackListProvider, authorIsAdmin, deleteInvokeMessage, deleteCommandMessage);
        CommandCord.setInstance(out);
        return out;
    }

    private void runChecks() {
        if (permissionProvider == null)
            throw new IllegalArgumentException("PermissionProvider cannot be null");
        if (useGuildPrefixes && prefixProvider == null)
            throw new IllegalArgumentException("PrefixProvider cannot be null when using guild prefixes");
        if (defaultPrefix == null)
            throw new IllegalArgumentException("Default prefix cannot be null");
        if (api == null)
            throw new IllegalArgumentException("API cannot be null");
        if (useBlackList && blackListProvider == null)
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
