package me.schlaubi.commandcord.util.helpcommands;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.command.event.CommandEvent;
import me.schlaubi.commandcord.command.handlers.Command;
import me.schlaubi.commandcord.command.permission.Permissions;
import me.schlaubi.commandcord.command.result.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class HelpCommand extends Command {

    private String heading;

    protected HelpCommand(String[] aliases, CommandType commandType, Permissions permissions, String description, String usage, String heading) {
        super(aliases, commandType, permissions, description, usage);
        this.heading = heading;
    }

    protected HelpCommand() {
        super(new String[] {"help"}, CommandType.GENERAL, Permissions.everyone(), "Displays a list of all commands", "help [command]");
        this.heading = "For a detailed usage of one command run `!help [command]`";
    }

    @Override
    public Result run(String[] args, CommandEvent event) {
        List<EmbedField> fields = new ArrayList<>();
        if (args.length > 0) {
            if (!CommandCord.getInstance().getCommandAssociations().containsKey(args[0]))
                return parseNotFound(":warning: Not found", "That command could not be found");
            Command command = getCommandByAlias(args[0]);
            return parseCommandResult("**" + command.getAliases()[0] + " - Help**", command.getUsageMessage());
        }
        Arrays.asList(CommandType.class.getEnumConstants()).forEach(commandType -> {
            if(!getNamesByType(commandType).isEmpty())
            fields.add(new EmbedField(commandType.getDisplayName(), listToString(getNamesByType(commandType))));
        });
        return parseListResult(fields, heading);
    }

    protected class EmbedField {

        protected String title;
        protected String description;

        public EmbedField(String title, String description) {
            this.title = title;
            this.description = description;
        }
    }

    private ArrayList<Command> getCommandsByType(CommandType type) {
        ArrayList<Command> out = new ArrayList<>();
        CommandCord.getInstance().getCommandAssociations().values().forEach(h -> {
            if (h.getCommandType().equals(type))

                out.add(h);
        });
        return out;
    }
    private ArrayList<String> getNamesByType(CommandType type) {
        ArrayList<String> out = new ArrayList<>();
        getCommandsByType(type).forEach(h -> {
            out.add(h.getAliases()[0]);
        });
        return out;
    }

    private Command getCommandByAlias(String alias) {
        return CommandCord.getInstance().getCommandAssociations().get(alias);
    }

    private String listToString(ArrayList<String> strings) {
        StringBuilder builder = new StringBuilder();
        strings.forEach(s -> {
            builder.append(s).append(", ");
        });
        String out = builder.toString();
        if (out.endsWith(", "))
            out = builder.replace(builder.lastIndexOf(", "), builder.lastIndexOf(", ") + 1, "").toString();
        return "`" + out + "`";
    }

    public abstract Result parseListResult(List<EmbedField> embedFields, String heading);
    public abstract Result parseNotFound(String title, String description);
    public abstract Result parseCommandResult(String title, String description);

}
