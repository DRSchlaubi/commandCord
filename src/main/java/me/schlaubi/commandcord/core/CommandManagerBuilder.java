package me.schlaubi.commandcord.core;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.PrefixProvider;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.core.managers.JDAManager;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class CommandManagerBuilder {

    private APIWrapper wrapper;
    private boolean parseEdits = false;
    private boolean useGuildPrefixes = false;
    private PermissionProvider permissionProvider;
    private PrefixProvider prefixProvider;
    private String defaultPrefix;
    private Object api;

    public CommandManagerBuilder(APIWrapper apiWrapper){
        this.wrapper = apiWrapper;
    }

    public void enableMessageEditParsing(boolean parseEdits) {
        this.parseEdits = parseEdits;
    }

    public void setPermissionProvider(PermissionProvider permissionProvider) {
        this.permissionProvider = permissionProvider;
    }

    public void setPrefixProvider(PrefixProvider prefixProvider) {
        this.prefixProvider = prefixProvider;
    }

    public void setDefaultPrefix(String defaultPrefix) {
        this.defaultPrefix = defaultPrefix;
    }

    public void setApi(Object api) {
        this.api = api;
    }

    public void enableGuildPrefixes(boolean useGuildPrefixes) {
        this.useGuildPrefixes = useGuildPrefixes;
    }

    public CommandManager build() {
        runChecks();
        CommandManager out = new CommandManager(useGuildPrefixes, permissionProvider, prefixProvider, defaultPrefix);
        CommandCord.setInstance(out);
        return out;
    }

    public CommandManager buildJDA(){
        runChecks();
        if(!wrapper.equals(APIWrapper.JDA))
            throw new IllegalStateException("You cannot build an JDA manager when APIwrapper is defined as " + wrapper.name);
        return new JDAManager(useGuildPrefixes, permissionProvider, prefixProvider, defaultPrefix);
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


}
