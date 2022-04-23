package net.craftunity.bans.Log;
import net.craftunity.bans.Utils.Moderator;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
public class Report {
    public void Log(ProxiedPlayer Reporter, ProxiedPlayer Reported, String Reason) {
        for (ProxiedPlayer moderators : ProxyServer.getInstance().getPlayers()) {
            if (moderators.hasPermission("punishment.log"))
                moderators.sendMessage((new ComponentBuilder("" + ChatColor.AQUA + "Report" + ChatColor.AQUA + " | " + ChatColor.GRAY + " wurde von " + Reported.getName() + " gemeldet Grund: " + Reason)).create());
        }
    }
}
