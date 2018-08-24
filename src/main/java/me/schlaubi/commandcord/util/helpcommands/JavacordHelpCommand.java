package me.schlaubi.commandcord.util.helpcommands;

import me.schlaubi.commandcord.command.result.Result;
import me.schlaubi.commandcord.command.result.impl.JavacordResult;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;
import java.util.List;

public class JavacordHelpCommand extends HelpCommand {

    @Override
    public Result parseListResult(List<EmbedField> embedFields, String heading) {
        EmbedBuilder builder = new EmbedBuilder()
                .setColor(Color.CYAN)
                .setTitle("Command list")
                .setDescription(heading);
        embedFields.forEach(embedField -> builder.addField(embedField.title, embedField.description, false));
        return new JavacordResult(builder);
    }

    @Override
    public Result parseNotFound(String title, String description) {
        return new JavacordResult(new EmbedBuilder().setColor(Color.RED).setTitle(title).setDescription(description));
    }

    @Override
    public Result parseCommandResult(String title, String description) {
        return new JavacordResult(new EmbedBuilder().setColor(Color.CYAN).setTitle(title).setDescription(description));
    }
}
