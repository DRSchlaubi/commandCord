package me.schlaubi.commandcord.util.helpcommands;

import de.btobastian.javacord.entities.message.embed.EmbedBuilder;
import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.handlers.JavaCordHandler;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.core.CommandManager;

import java.awt.*;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class JavaCordHelpCommand extends JavaCordHandler {


    public JavaCordHelpCommand(String[] aliases, CommandType type, String description, String usage) {
        super(aliases, type, Permissions.everyone(), description, usage);
    }

    @Override
    public String run(CommandInvocation invocation) {
        CommandManager manager = CommandCord.getInstance();
        String[] args = invocation.getArgs();
        if(args.length == 0) {
            EmbedBuilder builder = new EmbedBuilder()
                    .setColor(Color.cyan);
            for (Object o : CommandType.class.getDeclaringClass().getEnumConstants()) {
                CommandType category = ((CommandType) o);
                builder.addField(category.getDisplayName(), HelpCommandHelper.getNamesByType(category).toString(), false);
            }
            invocation.getChannel().sendMessage("", builder);
        } else {
            if(!manager.getCommandAssociations().containsKey(args[0]))
                invocation.getChannel().sendMessage("", new EmbedBuilder().setTitle(HelpCommandHelper.notFoundTitle()).setDescription(HelpCommandHelper.notFound()));
            GeneralCommandHandler handler = HelpCommandHelper.getCommandByAlias(args[0]);
            invocation.getChannel().sendMessage("", new EmbedBuilder().setTitle("'" + handler.getAliases()[0] + "' command help").setDescription(handler.getDescription()).addField("Usage", handler.getUsage(), false));
        }
        return null;
    }
}
