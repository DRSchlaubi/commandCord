package me.schlaubi.commandcord.examples;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Main {

    public void main(String[] args){



        JDABuilder builder = new JDABuilder(AccountType.BOT)
                .setToken("MjczNDk5OTUzNDU0Nzc2MzIw.Dh1jzg.em4md47r4H9SEgvIEPTF65On_qA");
    }
}
