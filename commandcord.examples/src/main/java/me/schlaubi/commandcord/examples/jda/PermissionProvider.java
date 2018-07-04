package me.schlaubi.commandcord.examples.jda;

import me.schlaubi.commandcord.command.permission.Member;

import java.util.Arrays;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class PermissionProvider implements me.schlaubi.commandcord.command.permission.PermissionProvider {

    @Override
    public boolean isGuildOwner(Member member) {
        return false;
    }

    @Override
    public boolean isBotAuthor(Member member) {
        return Arrays.asList(Main.AUHTORS).contains(member.getUserId());
    }

    @Override
    public boolean hasPermissionNode(Member member, String s) {
        return true;
    }

    @Override
    public boolean hasPermissionLevel(Member member, int i) {
        return false;
    }
}
