package net.craftunity.bans.Utils;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Moderator {
    public Moderator() {
    }

    public static Boolean getReportLoginState(String UniqueID) {
        String PlayerUUID = UniqueID.toString();

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Report FROM ReportLog WHERE UUID = ?");
            ps.setString(1, PlayerUUID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("State");
            }
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return false;
    }

    public static Boolean getLogLoginState(String UniqueID) {
        String PlayerUUID = UniqueID.toString();

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Log FROM PunishLog WHERE UUID = ?");
            ps.setString(1, PlayerUUID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("State");
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return false;
    }

    public static void setLogLoginState(String UniqueID, Boolean state) {
        String PlayerUUID = UniqueID.toString();

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO PunishLog (UUID , State) Values (?,?)");
            ps.setString(1, PlayerUUID);
            ps.setBoolean(2, state);
            ps.executeUpdate();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public static void setReportLoginState(String UniqueID, Boolean state) {
        String PlayerUUID = UniqueID.toString();

        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO ReportLog (UUID , State) Values (?,?)");
            ps.setString(1, PlayerUUID);
            ps.setBoolean(2, state);
            ps.executeUpdate();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }
}
