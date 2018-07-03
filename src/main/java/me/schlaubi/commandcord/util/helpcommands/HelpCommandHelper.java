package me.schlaubi.commandcord.util.helpcommands;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class HelpCommandHelper {

    private static ArrayList<GeneralCommandHandler> getHandlersByType(CommandType type){
        ArrayList<GeneralCommandHandler> out = new ArrayList<>();
        CommandCord.getInstance().getCommandAssociations().values().forEach(h -> {
            if(h.type.equals(type))
                out.add(h);
        });
        return out;
    }

    static ArrayList<String> getNamesByType(CommandType type){
        ArrayList<String> out = new ArrayList<>();
        getHandlersByType(type).forEach(h -> {
            out.add(h.aliases[0]);
        });
        return out;
    }

    static GeneralCommandHandler getCommandByAlias(String alias){
        Map<String, GeneralCommandHandler> commands = CommandCord.getInstance().getCommandAssociations();
        if(!commands.containsKey(alias)) return null;
        return commands.get(alias);
    }

    static String notFoundTitle(){
        return "Not found!";
    }

    static String notFound(){
        return "This command was not found.";
    }
}
