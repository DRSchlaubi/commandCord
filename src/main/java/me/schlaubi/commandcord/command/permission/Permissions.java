package me.schlaubi.commandcord.command.permission;

import me.schlaubi.commandcord.CommandCord;
import me.schlaubi.commandcord.command.handlers.GeneralCommandHandler;

/**
 * @author Schlaubi / Michael Rittmeister
 */

public class Permissions {

   private boolean isAuthorOnly;
   private boolean isGuildOwnerOnly;
   private boolean publicCommand;
   private String permissionNode;
   private int permissionLevel;


   private Permissions(String permissionNode, boolean isPublic, boolean isAuthorOnly, boolean isGuildOwnerOnly, int permissionLevel){
       this.publicCommand = isPublic;
       this.isAuthorOnly = isAuthorOnly;
       this.isGuildOwnerOnly = isGuildOwnerOnly;
       this.permissionNode = permissionNode;
       this.permissionLevel = permissionLevel;
   }

   public boolean isCovered(Member member){
       if(publicCommand)
           return true;
       if(CommandCord.getInstance().isAuthorAdmin() && CommandCord.getInstance().getPermissionProvider().isBotAuthor(member))
           return true;
       if(isGuildOwnerOnly)
           return CommandCord.getInstance().getPermissionProvider().isGuildOwner(member);
       if(isAuthorOnly)
           return CommandCord.getInstance().getPermissionProvider().isBotAuthor(member);
       if(permissionLevel != 0)
           return CommandCord.getInstance().getPermissionProvider().hasPermissionLevel(member, permissionLevel);
       return CommandCord.getInstance().getPermissionProvider().hasPermissionNode(member, permissionNode);
   }

   public static Permissions everyone(){
       return new Permissions("public", true, false, false, 0);
   }

   public static Permissions authorOnly(){
       return new Permissions("authorOnly", false, true, false, 0);
   }

   public static Permissions guildOwnerOnly(){
       return new Permissions("guildOwnerOnly", false, false, true, 0);
   }

   public static Permissions node(String permissionNode){
       return new Permissions(permissionNode, false, false, false, 0);
   }

   public static Permissions level(int permissionLevel){
       return new Permissions("permLevel", false, false,false, permissionLevel);
   }



}
