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

    private static ArrayList<GeneralCommandHandler> getHandlersByType(CommandType type) {
        ArrayList<GeneralCommandHandler> out = new ArrayList<>();
        CommandCord.getInstance().getCommandAssociations().values().forEach(h -> {
            if (h.getType().equals(type))
                out.add(h);
        });
        return out;
    }

    static ArrayList<String> getNamesByType(CommandType type) {
        ArrayList<String> out = new ArrayList<>();
        getHandlersByType(type).forEach(h -> {
            out.add(h.getAliases()[0]);
        });
        return out;
    }

    static GeneralCommandHandler getCommandByAlias(String alias) {
        Map<String, GeneralCommandHandler> commands = CommandCord.getInstance().getCommandAssociations();
        if (!commands.containsKey(alias)) return null;
        return commands.get(alias);
    }

    static String notFoundTitle() {
        return "Not found!";
    }

    static String notFound() {
        return "This command was not found.";
    }

    static String listToString(ArrayList<String> strings) {
        StringBuilder builder = new StringBuilder();
        strings.forEach(s -> {
            builder.append(s).append(", ");
        });
        String out = builder.toString();
        if (out.endsWith(", "))
            out = builder.replace(builder.lastIndexOf(", "), builder.lastIndexOf(", ") + 1, "").toString();
        return "`" + out + "`";
    }

    static String getUsage(GeneralCommandHandler handler){
        return CommandCord.getInstance().getDefaultPrefix() + handler.getAliases()[0] + " " + handler.getUsage();
    }
}
