package net.craftunity.bans.Commands;

import com.google.common.collect.ImmutableSet;
import net.craftunity.bans.Main;
import net.craftunity.bans.Utils.Player;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Set;

import static net.md_5.bungee.api.ProxyServer.getInstance;

public class GotoCommand extends Command implements TabExecutor {
    public GotoCommand(Main name) {
        super("Goto" , "moderator.goto");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
if (sender instanceof ProxiedPlayer){
    ProxiedPlayer target = Player.getPlayer(args[0]);
    ServerInfo TargetServer = target.getServer().getInfo();

    ((ProxiedPlayer) sender).connect(TargetServer);

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
            for ( ProxiedPlayer player : getInstance().getPlayers() )
            {
                if ( player.getName().toLowerCase().startsWith( search ) )
                {
                    matches.add( player.getName() );
                }
            }
        }
        return matches;
    }
}
