package me.schlaubi.commandcord.examples.discord4j;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.handlers.Discord4JCommandHandler;
import me.schlaubi.commandcord.command.handlers.JDACommandHandler;
import me.schlaubi.commandcord.command.permission.Permissions;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Message;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class FailCommand extends Discord4JCommandHandler {

    /* Command to show how exception handling works*/

    public FailCommand() {
        super(new String[] {"fail"}, CommandType.FUN, Permissions.authorOnly(), "Useless command for exception handling", "fail");
    }

    @Override
    public String run(CommandInvocation commandInvocation) {
        return String.valueOf(Integer.parseInt("FAIL"));
    }
}
