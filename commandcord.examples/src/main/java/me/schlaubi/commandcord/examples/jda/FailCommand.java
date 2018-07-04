package me.schlaubi.commandcord.examples.jda;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.handlers.JDACommandHandler;
import me.schlaubi.commandcord.command.permission.Permissions;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class FailCommand extends JDACommandHandler {

    /* Command to show how exception handling works*/

    public FailCommand() {
        super(new String[] {"fail"}, CommandType.FUN, Permissions.authorOnly(), "Useless command for exception handling", "fail");
    }

    @Override
    public Message run(CommandInvocation commandInvocation) {
        return new MessageBuilder().setContent(String.valueOf(Integer.parseInt("FAIL"))).build();
    }
}
