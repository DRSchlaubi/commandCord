package me.schlaubi.commandcord.examples;

import me.schlaubi.commandcord.command.CommandType;
import me.schlaubi.commandcord.core.APIWrapper;
import me.schlaubi.commandcord.core.CommandManager;
import me.schlaubi.commandcord.core.CommandManagerBuilder;
import me.schlaubi.commandcord.listeners.jda.JDAListener;
import me.schlaubi.commandcord.util.helpcommands.JDAHelpCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Main {

    public static JDA jda = null;

    public static String[] AUHTORS = {"264048760580079616"};

    public static void main(String[] args){



        JDABuilder builder = new JDABuilder(AccountType.BOT)
                .setToken("MjczNDk5OTUzNDU0Nzc2MzIw.Dh1jzg.em4md47r4H9SEgvIEPTF65On_qA")
                .addEventListener(new JDAListener());

        try {
            jda = builder.buildBlocking();
            System.out.println(jda.getSelfUser().getId());
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }

        CommandManager manager = new CommandManagerBuilder(APIWrapper.JDA)
                .enableGuildPrefixes(false)
                .setDefaultPrefix("!")
                .setApi(new Object())
                .setPermissionProvider(new PermissionProvider())
                .setApi(jda).build();

        manager.registerCommands(new JDAHelpCommand(new String[] {"help"}, CommandType.GENERAL, "Displays all commands", "help [command]"),
            new PingCommand(),
            new FailCommand());
        manager.getEventManager().registerListener(new EventListener());
    }
}