package me.schlaubi.commandcord.core;

import me.schlaubi.commandcord.CommandCord;
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
    private PermissionProvider permissionProvider;
    private PrefixProvider prefixProvider;
    private String defaultPrefix;
    private Object api;

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

    public CommandManager build() {
        runChecks();
        CommandManager out = new CommandManager(useGuildPrefixes, permissionProvider, prefixProvider, defaultPrefix, getParser(), api);
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
    }

    private CommandParser getParser(){
        if(wrapper.equals(APIWrapper.JDA))
            return new JDAParser();
        else if(wrapper.equals(APIWrapper.DISCORD4J))
            return new Discord4JParser();
        else
            return new JavaCordParser();
    }


}
