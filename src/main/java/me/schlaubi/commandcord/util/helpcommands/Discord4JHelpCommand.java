package me.schlaubi.commandcord.util.helpcommands;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.handlers.Discord4JCommandHandler;
import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.core.CommandManager;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Discord4JHelpCommand extends Discord4JCommandHandler {

    public Discord4JHelpCommand(String[] aliases, CommandType type, String description, String usage) {
        super(aliases, type, Permissions.everyone(), description, usage);
    }

    public Discord4JHelpCommand(){
        super(new String[] {"help"}, CommandType.GENERAL, Permissions.everyone(), "Displays a list of all commands", "[command]");
    }

    @Override
    public String run(CommandInvocation invocation) {
        CommandManager manager = CommandCord.getInstance();
        String[] args = invocation.getArgs();
        if (args.length == 0) {
            EmbedBuilder builder = new EmbedBuilder()
                    .withColor(Color.cyan);
            for (CommandType commandType : CommandType.class.getEnumConstants()) {
                ArrayList<String> commandNames = HelpCommandHelper.getNamesByType(commandType);
                if (commandNames.isEmpty()) continue;
                builder.appendField(commandType.getDisplayName(), HelpCommandHelper.listToString(commandNames), false);
            }
            invocation.getChannel().sendMessage(builder.build());
        } else {
            if (!manager.getCommandAssociations().containsKey(args[0]))
                invocation.getChannel().sendMessage(new EmbedBuilder().withTitle(HelpCommandHelper.notFoundTitle()).withDesc(HelpCommandHelper.notFound()).build());
            GeneralCommandHandler handler = HelpCommandHelper.getCommandByAlias(args[0]);
            invocation.getChannel().sendMessage(new EmbedBuilder().withTitle("'" + handler.getAliases()[0] + "' command help").withDesc(handler.getDescription()).appendField("Usage", HelpCommandHelper.getUsage(handler), false).build());

        }
        return null;
    }
}
