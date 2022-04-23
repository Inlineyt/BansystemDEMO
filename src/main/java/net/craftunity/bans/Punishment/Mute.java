package net.craftunity.bans.Punishment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import net.craftunity.bans.Utils.MySQL;
import net.craftunity.bans.Utils.Player;
public class Mute {

    public static void addPunishment(String proxiedPlayer, String Reason) {
        String player = Player.getPlayer(proxiedPlayer.toString()).getUniqueId().toString();
        String MuteID = UUID.randomUUID().toString();

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Mute (UUID, MuteID ,Name , REASON) VALUES (?,?,?,?);");
            ps.setString(1, player);
            ps.setString(2, MuteID);
            ps.setString(3, Player.getPlayer(proxiedPlayer.toString()).getName());
            ps.setString(4, Reason);
            ps.executeUpdate();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

    }

    public static Boolean isPunished(String UniqueUID) {
        String PlayerUUID = UniqueUID.toString();

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT UUID FROM Mute WHERE UUID = ?");
            ps.setString(1, PlayerUUID);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? true : false;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public static String getReason(String UniqueID) {
        try {
            String PlayerUUID = UniqueID.toString();
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Reason FROM Mute WHERE UUID = ?");
            ps.setString(1, PlayerUUID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Reason");
            }
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return null;
    }
    public static String getMuteID(String UniqueID) {
        try {
            String PlayerUUID = UniqueID;
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT MuteID FROM Mute WHERE UUID = ?");
            ps.setString(1, PlayerUUID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("MuteID");
            }
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return null;
    }

    public static void RemovePunishment(String Name) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM Mute WHERE Name = ?");

            ps.setString(1, Name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
