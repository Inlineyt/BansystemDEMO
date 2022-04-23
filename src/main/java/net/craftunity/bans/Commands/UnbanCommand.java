package net.craftunity.bans.Commands;

import com.google.common.collect.ImmutableSet;
import net.craftunity.bans.Log.Punishment;
import net.craftunity.bans.Main;
import net.craftunity.bans.Utils.BannedPlayer;
import net.craftunity.bans.Utils.MySQL;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.craftunity.bans.Punishment.Ban;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UnbanCommand extends Command implements TabExecutor {


    public UnbanCommand(Main name) {
        super("unban","moderation.command.unban");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args[0] == null){
            sender.sendMessage(new ComponentBuilder(ChatColor.AQUA + "Bitte Spieler angeben").create());
        }
        String playerUUID = args[0];
        if (sender instanceof ProxiedPlayer) {





            ProxiedPlayer senderplayer = (ProxiedPlayer)sender;
            Ban.RemovePunishment(playerUUID);
            sender.sendMessage((new ComponentBuilder("" + ChatColor.AQUA + "Craftunity System " + ChatColor.AQUA + "| " + ChatColor.DARK_GRAY + "Spieler mit Mojang UUID: " + ChatColor.GRAY + playerUUID + " wurde nun Entbannt")).create());
            for (ProxiedPlayer allplayers : ProxyServer.getInstance().getPlayers()) {

                if (allplayers.hasPermission("Report.review"))
                    allplayers.sendMessage((new ComponentBuilder("" + ChatColor.AQUA + "Log" + ChatColor.AQUA + " | " + ChatColor.GREEN + playerUUID +  ChatColor.GRAY  +  " wurde von " + ChatColor.DARK_GRAY +  sender.getName() + " entbannt")).create());
            }




            new Punishment().CreatePunishment(senderplayer,args[0], "unban","No_reason_Aplyable");
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {



        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Name FROM Ban");


            ResultSet rs = ps.executeQuery();
            if (rs.next()) BannedPlayer.setPlayers((ArrayList<String>) rs.getObject("Name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if ( args.length > 2 || args.length == 0 )
        {
            return ImmutableSet.of();
        }

        Set<String> matches = new HashSet<>();
        if ( args.length == 1 )
        {
            String search = args[0].toLowerCase();
            for (var BannedPlayer : Ban.getPlayerNames()){
                matches.add(String.valueOf(BannedPlayer));
            }
        } else
        {
            String search = args[1].toLowerCase();
            matches.add("Hacking");
            matches.add("Chat");
            matches.add("Name");
            matches.add("Hunting");

        }

        return matches;
    }
}
