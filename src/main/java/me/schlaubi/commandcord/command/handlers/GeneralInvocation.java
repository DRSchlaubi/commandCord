package me.schlaubi.commandcord.command.handlers;


import java.util.Arrays;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class GeneralInvocation {
    private final String[] args;
    private final String prefix;

    public GeneralInvocation(String[] args, String prefix){
        this.args = args;
        this.prefix = prefix;
    }

    public String[] getArgs(){
        return args;
    }

    public String getPrefix(){
        return prefix;
    }

    public String getArgsString(){
        return String.join(" ", Arrays.asList(args));
    }
}
