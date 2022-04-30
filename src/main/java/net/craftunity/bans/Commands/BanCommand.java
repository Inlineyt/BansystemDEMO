package net.craftunity.bans.Commands;

import com.google.common.collect.ImmutableSet;
import net.craftunity.bans.Log.Punishment;
import net.craftunity.bans.Main;
import net.craftunity.bans.Punishment.Ban;
import net.craftunity.bans.Punishment.GeneralPunishment;
import net.craftunity.bans.Utils.Player;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Set;

public class BanCommand extends Command implements TabExecutor {
    public BanCommand(Main main) {
        super("Ban","moderation.command.ban");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer SenderPlayer = (ProxiedPlayer) sender;
            ProxiedPlayer punishedPlayer = Player.getPlayer(args[0]);
            String reason = args[1];
            if (args[1] == null) {
                sender.sendMessage("bitte Grund angeben");
            }
            if (args[0] == null) {
                sender.sendMessage("bitte Spieler angeben");
            }
            if (args[1].length() >= 33) {
                sender.sendMessage("der Grund darf nicht größer als 32 Zeichen sein!");
            } else {




                new Punishment().CreatePunishment(SenderPlayer, punishedPlayer.getName().toString(), "Ban", reason);
                new GeneralPunishment().addToLog(punishedPlayer,"Ban",reason);
                Ban.addPunishment(args[0], reason,sender.getName());
                punishedPlayer.disconnect((new ComponentBuilder(ChatColor.STRIKETHROUGH + "                                                                 \n" + ChatColor.GRAY + " \n CraftUnity System\n" + ChatColor.AQUA + " \n Du Wurdest Gebannt \n\n" + ChatColor.GRAY + "Grund: " + ChatColor.RED + args[1] + ChatColor.RESET + ChatColor.STRIKETHROUGH + "\n\n                                                                 ")).create());
            }
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {

if (args.length == 0 ){
    sender.sendMessage(new ComponentBuilder("Verwendung: /ban <spieler> <grund>").create());
}

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
                matches.add("Clientmodifikation");
                matches.add("Autoclicker");
                matches.add("Hausverbot");
                matches.add("Hunting");
                matches.add("Bugusing");
                matches.add("Trolling");
                matches.add("Spawntrapping");
                matches.add("Teaming");

            }
            return matches;

}
}