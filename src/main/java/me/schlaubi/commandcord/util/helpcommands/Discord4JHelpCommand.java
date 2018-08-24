package me.schlaubi.commandcord.util.helpcommands;

import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.Discord4JResult;
import sx.blah.discord.util.EmbedBuilder;

import java.awt.*;
import java.util.List;

public class Discord4JHelpCommand extends HelpCommand {

    @Override
    public Result parseListResult(List<EmbedField> embedFields, String heading) {
        EmbedBuilder builder = new EmbedBuilder()
                .withColor(Color.CYAN)
                .withTitle("Command list")
                .withDesc(heading);
        embedFields.forEach(embedField -> builder.appendField(embedField.title, embedField.description, false));
        return new Discord4JResult(builder);    }

    @Override
    public Result parseNotFound(String title, String description) {
        return new Discord4JResult(new EmbedBuilder().withColor(Color.RED).withTitle(title).withDescription(description));
    }

    @Override
    public Result parseCommandResult(String title, String description) {
        return new Discord4JResult(new EmbedBuilder().withColor(Color.CYAN).withTitle(title).withDescription(description));
    }
}
