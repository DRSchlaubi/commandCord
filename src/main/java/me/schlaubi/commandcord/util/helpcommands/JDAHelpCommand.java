package me.schlaubi.commandcord.util.helpcommands;

import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.JDAResult;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;
import java.util.List;

public class JDAHelpCommand extends HelpCommand {

    @Override
    public Result parseListResult(List<EmbedField> embedFields, String heading) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.CYAN)
                .setTitle("Command list")
                .setDescription(heading);
        embedFields.forEach(embedField -> builder.addField(embedField.title, embedField.description, false));
        return new JDAResult(builder);
    }

    @Override
    public Result parseNotFound(String title, String description) {
        return new JDAResult(new EmbedBuilder().setColor(Color.RED).setTitle(title).setDescription(description));
    }

    @Override
    public Result parseCommandResult(String title, String description) {
        return new JDAResult(new EmbedBuilder().setColor(Color.CYAN).setTitle(title).setDescription(description));
    }
}
