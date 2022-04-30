package net.craftunity.bans.Commands;

import com.google.common.collect.ImmutableSet;
import net.craftunity.bans.Main;
import net.craftunity.bans.Punishment.Ban;
import net.craftunity.bans.Punishment.Mute;
import net.craftunity.bans.Utils.Chat;
import net.craftunity.bans.Utils.Player;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Set;

public class PlayerInfoCommand extends Command implements TabExecutor {
    public PlayerInfoCommand(Main name) {
        super("Info","moderation.playerinfo");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args[0] == null){
            sender.sendMessage(new ComponentBuilder(ChatColor.AQUA + "Bitte Spieler angeben").create());
        }
        if (sender instanceof ProxiedPlayer) {

            ProxiedPlayer SenderPlayer = (ProxiedPlayer) sender;
            ProxiedPlayer requestedPlayer = Player.getPlayer(args[0]);

            Server server = ((ProxiedPlayer)requestedPlayer).getServer();
            String serverName = server.getInfo().getName();


            SenderPlayer.sendMessage(new ComponentBuilder(ChatColor.AQUA + " Craftunity Spielerinfo " + ChatColor.DARK_GRAY + "| " + ChatColor.GRAY +"Spieler : " + ChatColor.RED + requestedPlayer.getName()).create());


            SenderPlayer.sendMessage(new ComponentBuilder( " ").create());
            if (requestedPlayer.isConnected()){

                SenderPlayer.sendMessage(new ComponentBuilder(new Chat().OnlineOnComponent(requestedPlayer)).create());
            } else {
                SenderPlayer.sendMessage(new ComponentBuilder(ChatColor.GRAY + " Aktuell Offline ").create());

            }
            SenderPlayer.sendMessage(new ComponentBuilder( " ").create());
            SenderPlayer.sendMessage(new ComponentBuilder(new Chat().PlayerUUIDComponent(requestedPlayer)).create());


            SenderPlayer.sendMessage(new ComponentBuilder( ChatColor.STRIKETHROUGH +  "                           "+ChatColor.RESET + ChatColor.GRAY + "| Mute Status |" +  ChatColor.RESET +ChatColor.STRIKETHROUGH +  "                     ").create());
            if (Mute.isPunished(requestedPlayer.getUniqueId().toString())){

                SenderPlayer.sendMessage(new ComponentBuilder(new Chat().MuteComponent(requestedPlayer)).create());
            } else {

                SenderPlayer.sendMessage(new ComponentBuilder( " ").create());
                SenderPlayer.sendMessage(new ComponentBuilder(ChatColor.GREEN + " Aktuell nicht Gemuted").create());
                SenderPlayer.sendMessage(new ComponentBuilder( " ").create());
            }

            SenderPlayer.sendMessage(new ComponentBuilder( ChatColor.STRIKETHROUGH +  "                           "+ChatColor.RESET + ChatColor.GRAY + "| Ban Status |" +  ChatColor.RESET +ChatColor.STRIKETHROUGH  +  "                      ").create());
            if (Ban.isPunished(requestedPlayer.getUniqueId().toString())){






                SenderPlayer.sendMessage(new ComponentBuilder(new Chat().BanComponent(requestedPlayer)).create());

            } else {

                SenderPlayer.sendMessage(new ComponentBuilder( " ").create());
                SenderPlayer.sendMessage(new ComponentBuilder(ChatColor.GREEN + " Aktuell nicht Gebannt").create());
                SenderPlayer.sendMessage(new ComponentBuilder( " ").create());
            }


            SenderPlayer.sendMessage(new ComponentBuilder( ChatColor.STRIKETHROUGH + "                                                                 ").create());




        }

    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {

        if ( args.length > 2 || args.length == 0 )
        {
            return ImmutableSet.of();
        }

        Set<String> matches = new HashSet<>();
        {
            String search = args[0].toLowerCase();
            for ( ProxiedPlayer player : ProxyServer.getInstance().getPlayers() )
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
