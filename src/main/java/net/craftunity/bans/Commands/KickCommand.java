package net.craftunity.bans.Commands;

import com.google.common.collect.ImmutableSet;
import net.craftunity.bans.Log.Punishment;
import net.craftunity.bans.Main;
import net.craftunity.bans.Punishment.GeneralPunishment;
import net.craftunity.bans.Utils.Player;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Set;

public class KickCommand extends Command implements TabExecutor {
    public KickCommand(Main main) {
        super("Kick","moderation.command.kick");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer SenderPlayer = (ProxiedPlayer)sender;
            if (args[0].length() == 0) {
                sender.sendMessage("bitte Spieler angeben");
            } if (args[1].length() == 0) {
                sender.sendMessage("bitte grund angeben");
            } else {
                ProxiedPlayer punishedPlayer = Player.getPlayer(args[0]);
                new GeneralPunishment().addToLog(punishedPlayer,"Kick",args[1]);
                new Punishment().CreatePunishment(SenderPlayer, punishedPlayer.getName().toString(), "Kick", args[1]);

                String Punishment = args[1];
                punishedPlayer.disconnect("" + ChatColor.GRAY + "--------------" + ChatColor.GRAY + " \n\n CraftUnity System" + ChatColor.AQUA + "\n \n Du Wurdest Gekickt \n\n" + ChatColor.GRAY + "Grund: " + ChatColor.RED + args[1] + "\n\n --------------");
                for (ProxiedPlayer moderators : ProxyServer.getInstance().getPlayers()) {
                    if (moderators.hasPermission("Report.Review"));
                }
            }
        } else {
            sender.sendMessage("Befehl nicht in der Konsole ausf");

        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        if ( args.length > 2 || args.length == 0 )
        {
            return ImmutableSet.of();
        }

        Set<String> matches = new HashSet<>();
        if ( args.length == 1 )
        {
            String search = args[0].toLowerCase();
            for ( ProxiedPlayer player : ProxyServer.getInstance().getPlayers() )
            {
                if ( player.getName().toLowerCase().startsWith( search ) )
                {
                    matches.add( player.getName() );
                }
            }
        } else
        {
            String search = args[1].toLowerCase();
            matches.add("Clientmodifikation-Verwarnung");
            matches.add("Autoclicker");
            matches.add("Hunting-Verwarnung");

        }

        return matches;
    }
}
