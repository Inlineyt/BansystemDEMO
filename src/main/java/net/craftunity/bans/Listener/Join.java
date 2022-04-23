package net.craftunity.bans.Listener;

import net.craftunity.bans.LogLoginState;
import net.craftunity.bans.Main;
import net.craftunity.bans.Punishment.Ban;
import net.craftunity.bans.Punishment.Mute;
import net.craftunity.bans.Utils.Moderator;
import net.craftunity.bans.Utils.MySQL;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.craftunity.bans.ReportLoginState;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Join implements Listener {
    public Join(Main main) {

    }
UUID CTUYID = UUID.randomUUID();
    public void onPostLogin(PostLoginEvent event) {
        String UUIDplayer = event.getPlayer().getUniqueId().toString();
        ProxiedPlayer player = event.getPlayer();

        if (Ban.isPunished(UUIDplayer).booleanValue()) {
            if (event.getPlayer().hasPermission("Ban.Bypass"))
                return;
            event.getPlayer().disconnect((new ComponentBuilder("" + ChatColor.GRAY + "--------------\n" + ChatColor.GRAY + " \n CraftUnity System\n" + ChatColor.AQUA + " \n Du bist Aktuell noch gebannt \n\n" + ChatColor.GRAY + "Grund: " + ChatColor.RED + Ban.getReason(UUIDplayer) + "\n\n --------------")).create());
        }



        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Playerinfo (UUID , CTUYID , ACTIVEBAN , ACTIVEMUTE) VALUES(?,?,?,?)");

            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2,CTUYID.toString());
            ps.setBoolean(3,Ban.isPunished(UUIDplayer));
            ps.setBoolean(4, Mute.isPunished(UUIDplayer));

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (player.hasPermission("punishment.log")){
            LogLoginState.setState("Eingeloggt");
        }else {
            LogLoginState.setState("Ausgeloggt");
        }
        if (player.hasPermission("punishment.log")){
            ReportLoginState.setState("Eingeloggt");
        } else {
            ReportLoginState.setState("Ausgeloggt");
        }

        if (player.hasPermission("punishment.log")) {
            player.sendMessage((new ComponentBuilder(ChatColor.AQUA + "Craftunity System " + ChatColor.AQUA + " |" + ChatColor.DARK_GRAY + " Du bist Aktuell eingeloggt in:")).create());
            player.sendMessage(new ComponentBuilder(ChatColor.AQUA + "Craftunity Punishment Log" + ChatColor.DARK_GRAY + " |"+ ReportLoginState.getState()).create());
            player.sendMessage(new ComponentBuilder(ChatColor.AQUA + "Craftunity Reports" + ChatColor.DARK_GRAY + " |"+  ReportLoginState.getState()).create());
        }

    }
}
