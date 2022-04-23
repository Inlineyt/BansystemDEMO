package net.craftunity.bans.Commands;

import com.google.common.collect.ImmutableSet;
import net.craftunity.bans.Log.Report;
import net.craftunity.bans.Main;
import net.craftunity.bans.Utils.Player;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Set;

public class ReportCommand extends Command  implements TabExecutor {
    public ReportCommand(Main main) {
        super("Report");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args[0] == null){
            sender.sendMessage(new ComponentBuilder(ChatColor.AQUA + "Bitte Spieler angeben").create());
        }
        if (args[1] == null){
            sender.sendMessage(new ComponentBuilder(ChatColor.AQUA + "Bitte Grund angeben").create());
        }
        if (sender instanceof ProxiedPlayer) {









            ProxiedPlayer SenderPlayer = (ProxiedPlayer)sender;
            ProxiedPlayer reportedPlayer = Player.getPlayer(args[0]);
            if (args[0].length() == 0) {
                sender.sendMessage("bitte Spieler angeben");
            } else if (args[1].length() == 0) {
                sender.sendMessage("bitte grund angeben");
            } else {
                String Reason = args[1];
                new Report().Log(SenderPlayer, reportedPlayer, args[1]);


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
            matches.add("Hacking");
            matches.add("Chat");
            matches.add("Name");
            matches.add("Hunting");
            matches.add("Bugusing");



        }

        return matches;
    }
}
