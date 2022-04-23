package net.craftunity.bans.Commands;

import net.craftunity.bans.Main;
import net.craftunity.bans.Utils.MySQL;
import net.craftunity.bans.Utils.Player;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModSettingCommand extends Command {
    public ModSettingCommand(Main main) {
        super("Modsettings","moderation.logsettings");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args[0].length() == 0)
            sender.sendMessage("bitte Kategorie angeben");
        if (args[1].length() == 0)
            sender.sendMessage("bitte login oder logout angeben angeben");
        if (args[0].equalsIgnoreCase("report"))
            if (args[1].equalsIgnoreCase("logout")) {
                String player = Player.getPlayer(sender.toString()).getUniqueId().toString();
                try {
                    PreparedStatement deleteps = MySQL.getConnection().prepareStatement("DELETE FROM ModeratingPlayers WHERE UUID = ?");
                    deleteps.setString(1, player);
                    deleteps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (args[1].equalsIgnoreCase("login")) {
                String player = Player.getPlayer(sender.toString()).getUniqueId().toString();
                try {
                    PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO ModeratingPlayers (UUID) VALUES (?);");
                    ps.setString(1, player);
                    ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }
}
