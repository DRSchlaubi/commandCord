package me.schlaubi.commandcord.util.helpcommands;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.handlers.Discord4JCommandHandler;
import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.core.CommandManager;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Discord4JHelpCommand extends Discord4JCommandHandler {

    public Discord4JHelpCommand(String[] aliases, CommandType type, String description, String usage) {
        super(aliases, type, Permissions.everyone(), description, usage);
    }

    @Override
    public String run(CommandInvocation invocation) {
        CommandManager manager = CommandCord.getInstance();
        String[] args = invocation.getArgs();
        if(args.length == 0) {
            EmbedBuilder builder = new EmbedBuilder()
                    .withColor(Color.cyan);
            for (Object o : CommandType.class.getDeclaringClass().getEnumConstants()) {
                CommandType category = ((CommandType) o);
                builder.appendField(category.getDisplayName(), HelpCommandHelper.getNamesByType(category).toString(), false);
            }
            invocation.getChannel().sendMessage(builder.build());
        } else {
            if(!manager.getCommandAssociations().containsKey(args[0]))
                invocation.getChannel().sendMessage(new EmbedBuilder().withTitle(HelpCommandHelper.notFoundTitle()).withDesc(HelpCommandHelper.notFound()).build());
            GeneralCommandHandler handler = HelpCommandHelper.getCommandByAlias(args[0]);
            invocation.getChannel().sendMessage(new EmbedBuilder().withTitle(handler.aliases[0]).withDesc("__" + handler.description + "__\n`" + handler.usage + "`").build());
        }
        return null;
    }
}
