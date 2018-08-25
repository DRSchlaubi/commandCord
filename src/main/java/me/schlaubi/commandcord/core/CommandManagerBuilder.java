package me.schlaubi.commandcord.core;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.BeforeTasks;
import me.schlaubi.commandcord.command.BlackListProvider;
import me.schlaubi.commandcord.command.DefaultBeforeTasks;
import me.schlaubi.commandcord.command.PrefixProvider;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.core.parser.Discord4JParser;
import me.schlaubi.commandcord.core.parser.JDAParser;
import me.schlaubi.commandcord.core.parser.JavacordParser;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class CommandManagerBuilder {

    private APIWrapper wrapper;
    private boolean useGuildPrefixes = false;
    private boolean useBlackList = false;
    private boolean authorIsAdmin = true;
    private boolean deleteInvokeMessage = true;
    private boolean multiThreading = false;
    private boolean sendTyping = false;
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
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder setPermissionProvider(PermissionProvider permissionProvider) {
        this.permissionProvider = permissionProvider;
        return this;
    }

    /**
     * Sets the provider for prefixes
     *
     * @param prefixProvider Provider for permissions {@link me.schlaubi.commandcord.command.PrefixProvider}
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder setPrefixProvider(PrefixProvider prefixProvider) {
        this.prefixProvider = prefixProvider;
        return this;
    }

    /**
     * Sets the default prefix
     *
     * @param defaultPrefix prefix that is used on every guild
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder setDefaultPrefix(String defaultPrefix) {
        this.defaultPrefix = defaultPrefix;
        return this;
    }

    /**
     * Sets the Discord api wrapper which is needed to get guild information
     *
     * @param api Your api instance {@link net.dv8tion.jda.core.JDA}, {@link org.javacord.api.DiscordApi}, {@link sx.blah.discord.api.IDiscordClient}
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder setApi(Object api) {
        this.api = api;
        return this;
    }

    /**
     * Enabled guild specific prefixes
     * @param useGuildPrefixes Enable the option
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder enableGuildPrefixes(boolean useGuildPrefixes) {
        this.useGuildPrefixes = useGuildPrefixes;
        return this;
    }

    /**
     * Enabled task that were run before every command
     * @param beforeTasksHandler Your BeforeTaskHandler {@link me.schlaubi.commandcord.command.BeforeTasks}
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder setBeforeTasksHandler(BeforeTasks beforeTasksHandler) {
        this.beforeTasksHandler = beforeTasksHandler;
        return this;
    }

    /**
     * Enabled blacklist
     * @param enable Enable the option
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder enableBlacklist(boolean enable) {
        this.useBlackList = enable;
        return this;
    }

    /**
     * Sets blacklist provider (is necessary to use blacklist)
     *
     * @param blacklistProvider Provider for blacklist {@link me.schlaubi.commandcord.command.BlackListProvider}
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder setBlacklistProvider(BlackListProvider blacklistProvider) {
        this.blackListProvider = blacklistProvider;
        return this;
    }

    /**
     * Gives bot auhtors admin rights for every command on all servers
     * @param enable Enable the option
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder authorIsAdmin(boolean enable) {
        this.authorIsAdmin = enable;
        return this;
    }

    /**
     * Deleted messages that invokes commands
     * @param enable Enable the option
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder deleteInvokeMessages(boolean enable) {
        this.deleteInvokeMessage = enable;
        return this;
    }

    /**
     * Deletes bot's response to command
     *
     * @param deleteTime Delay till message should be deleted
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder deleteCommandMessages(int deleteTime) {
        this.deleteCommandMessage = deleteTime;
        return this;
    }

    /**
     * Enables that every command will be run in a new Thread
     * @param enable Enable the option
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder enableMultiThreading(boolean enable){
        this.multiThreading = enable;
        return this;
    }

    /**
     * Enables the sendTyping method before every command
     * @param enable Enable the option
     * @return The actual CommandManagerBuilder
     */
    public CommandManagerBuilder enableTyping(boolean enable) {
        this.sendTyping = enable;
        return this;
    }

    public CommandManager build() {
        runChecks();
        CommandManager out = new CommandManager(useGuildPrefixes, permissionProvider, prefixProvider, defaultPrefix, getParser(), api, beforeTasksHandler, useBlackList, blackListProvider, authorIsAdmin, deleteInvokeMessage, deleteCommandMessage, multiThreading, sendTyping);
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
            return new JavacordParser();
        else
            return null;
    }


}
