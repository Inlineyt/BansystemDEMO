package net.craftunity.bans.Punishment;

import net.craftunity.bans.Utils.MySQL;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GeneralPunishment {

    public void addToLog(ProxiedPlayer player , String Type , String Reason) {

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement(" INSERT INTO PunishLog (UUID , Name , Type , Reason) VALUES (?,?,?,?)");
            ps.setString(1,player.getUniqueId().toString());
            ps.setString(2,player.getName());
            ps.setString(3,Type);
            ps.setString(4,Reason);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
