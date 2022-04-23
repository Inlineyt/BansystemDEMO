package net.craftunity.bans.Log;
import java.util.Iterator;
import net.craftunity.bans.Utils.Moderator;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
public class Punishment {
    public void CreatePunishment(ProxiedPlayer Punisher, String PunishedPlayer, String Punishment, String Reason) {
        for (ProxiedPlayer moderators : ProxyServer.getInstance().getPlayers()) {
            if (moderators.hasPermission("punishment.log"));
                switch (Punishment) {
                    case "Ban":
                        moderators.sendMessage((new ComponentBuilder("" + ChatColor.AQUA + "Log" + ChatColor.AQUA + " | " +PunishedPlayer  + ChatColor.GRAY + " wurde von " +  Punisher.getName() + " gebannt Grund: " +Reason ).create()));
                        break;
                    case "Kick":
                        moderators.sendMessage((new ComponentBuilder("" + ChatColor.AQUA + "Log" + ChatColor.AQUA + " | "+ PunishedPlayer  + ChatColor.GRAY + " wurde von " +  Punisher.getName() + " gekickt Grund: " + Reason ).create()));
                        break;
                        case "Mute":
                        moderators.sendMessage((new ComponentBuilder("" + ChatColor.AQUA + "Log" + ChatColor.AQUA + " | " + PunishedPlayer  + ChatColor.GRAY + " wurde von " + Punisher.getName() + " gemuted Grund: " + Reason ).create()));
                        break;
                    case "Unban":
                        moderators.sendMessage((new ComponentBuilder("" + ChatColor.AQUA + "Log" + ChatColor.AQUA + " | "+ PunishedPlayer  + ChatColor.GRAY + " wurde von " + Punisher.getName() + " entbannt")).create());
                        break;
                    case "Unmute" :
                        moderators.sendMessage((new ComponentBuilder("" + ChatColor.AQUA + "Log" + ChatColor.AQUA + " | "+PunishedPlayer  + ChatColor.GRAY + " wurde von " + Punisher.getName() + " entmuted")).create());
                        break;
                }
        }
    }
    public void RemovePunishment(ProxiedPlayer Punisher, ProxiedPlayer PunishedPlayer, String Punishment) {
        for (ProxiedPlayer moderators : ProxyServer.getInstance().getPlayers()) {
            if (moderators.hasPermission("punishment.log"));
            switch (Punishment) {

            }
        }
    }
}
