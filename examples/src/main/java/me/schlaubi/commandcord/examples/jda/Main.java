package me.schlaubi.commandcord.examples.jda;

import me.schlaubi.commandcord.command.permission.Member;
import me.schlaubi.commandcord.command.permission.PermissionProvider;
import me.schlaubi.commandcord.core.APIWrapper;
import me.schlaubi.commandcord.core.CommandManager;
import me.schlaubi.commandcord.core.CommandManagerBuilder;
import me.schlaubi.commandcord.examples.TOKEN;
import me.schlaubi.commandcord.listeners.jda.JDAListener;
import me.schlaubi.commandcord.util.helpcommands.JDAHelpCommand;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) {
        //Create API
        JDABuilder builder = new JDABuilder(AccountType.BOT)
                .setToken(TOKEN.token)
                .addEventListener(new JDAListener());
        JDA jda;
        try {
            jda = builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
            return;
        }
        //Create manager
        CommandManager manager = new CommandManagerBuilder(APIWrapper.JDA)
                .enableGuildPrefixes(true)
                .setDefaultPrefix("!")
                .setApi(jda)
                //use test as guild specific prefix
                .setPrefixProvider(s -> "test")
                .enableBlacklist(true)
                //Randomly block one channel
                .setBlacklistProvider(channelId -> channelId.equals("470167577901924352"))
                .enableTyping(true)
                .setPermissionProvider(new PermissionProvider() {
                    @Override
                    public boolean isGuildOwner(Member member) {
                        return jda.getGuildById(member.getGuildId()).getOwner().getUser().getId().equals(member.getUserId());
                    }

                    @Override
                    public boolean isBotAuthor(Member member) {
                        return member.getUserId().equals("264048760580079616");
                    }

                    @Override
                    public boolean hasPermissionNode(Member member, String s) {
                        return false;
                    }

                    @Override
                    public boolean hasPermissionLevel(Member member, int i) {
                        return false;
                    }
                }).build();
        //Register commands
        manager.registerCommands(
                new JDAHelpCommand(),
                new ErrorCommand(),
                new TestCommand()
        );
        //Register events
        manager.getEventManager().registerListener(new Listener());

    }
}
