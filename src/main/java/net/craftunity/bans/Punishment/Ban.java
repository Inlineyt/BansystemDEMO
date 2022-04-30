package net.craftunity.bans.Punishment;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import net.craftunity.bans.Discord.Channel.PunishLogChannel;
import net.craftunity.bans.Discord.Messages.Embeds;
import net.craftunity.bans.Utils.MySQL;
import net.craftunity.bans.Utils.Player;


public class Ban {

    public static void addPunishment(String proxiedPlayer, String Reason ,String Punisher) {
        String player = Player.getPlayer(proxiedPlayer.toString()).getUniqueId().toString();
        String BanID = UUID.randomUUID().toString();



        Embeds.BanEmbed(PunishLogChannel.getChannel(), Player.getPlayer(proxiedPlayer.toString()).getName() , BanID ,"http://cravatar.eu/avatar/"+Player.getPlayer(proxiedPlayer.toString()).getName() +"/512.png",Reason ,Punisher);
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Ban (UUID, BanID ,Name , REASON) VALUES (?,?,?,?);");
            ps.setString(1, player);
            ps.setString(2, BanID);
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
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT UUID FROM Ban WHERE UUID = ?");
            ps.setString(1, PlayerUUID);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? true : false;
        } catch (SQLException var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public static String getReason(String UniqueID) {
        try {
            String PlayerUUID = UniqueID.toString();
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Reason FROM Ban WHERE UUID = ?");
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

    public static void RemovePunishment(String Name) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE FROM Ban WHERE Name = ?");
            ps.setString(1, Name);
            ps.executeUpdate();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }
    public static String getBanID(String UniqueID) {
        try {
            String PlayerUUID = UniqueID.toString();
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT BanID FROM Ban WHERE UUID = ?");
            ps.setString(1, PlayerUUID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("BanID");
            }
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        return null;


}

public static ArrayList getPlayerNames(){

        ArrayList <String> Names = new ArrayList<>();
        try {
        PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Name WHERE Banned = ?");
        ps.setString(5,"true");
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {

            Names.add(rs.getString("Name"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
return null;
    }



}
