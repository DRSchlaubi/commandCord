package me.schlaubi.commandcord.util.helpcommands;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;
import me.schlaubi.commandcord.command.handlers.JDACommandHandler;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.core.CommandManager;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

import java.awt.*;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class JDAHelpCommand extends JDACommandHandler {


    public JDAHelpCommand(String[] aliases, CommandType type, String description, String usage) {
        super(aliases, type, Permissions.everyone(), description, usage);
    }

    @Override
    public Message run(CommandInvocation invocation) {
        CommandManager manager = CommandCord.getInstance();
        String[] args = invocation.getArgs();
        if(args.length == 0) {
            EmbedBuilder builder = new EmbedBuilder()
                    .setColor(Color.cyan);
            for (Object o : CommandType.class.getDeclaringClass().getEnumConstants()) {
                CommandType category = ((CommandType) o);
                builder.addField(category.getDisplayName(), HelpCommandHelper.getNamesByType(category).toString(), false);
            }
            return new MessageBuilder().setEmbed(builder.build()).build();
        } else {
            if(!manager.getCommandAssociations().containsKey(args[0]))
                return new MessageBuilder().setEmbed(new EmbedBuilder().setTitle(HelpCommandHelper.notFoundTitle()).setDescription(HelpCommandHelper.notFound()).build()).build();
            GeneralCommandHandler handler = HelpCommandHelper.getCommandByAlias(args[0]);
            return new MessageBuilder().setEmbed(new EmbedBuilder().setTitle(handler.aliases[0]).setDescription("__" + handler.description + "__\n`" + handler.usage + "`").build()).build();
        }
    }

}
