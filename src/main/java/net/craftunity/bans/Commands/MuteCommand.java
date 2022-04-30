package net.craftunity.bans.Commands;

import com.google.common.collect.ImmutableSet;
import net.craftunity.bans.Log.Punishment;
import net.craftunity.bans.Main;
import net.craftunity.bans.Punishment.GeneralPunishment;
import net.craftunity.bans.Punishment.Mute;
import net.craftunity.bans.Utils.Player;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.TabExecutor;

import java.util.HashSet;
import java.util.Set;

public class MuteCommand extends Command implements TabExecutor {
    public MuteCommand(Main main) {
        super("Mute","moderation.commands.mute");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args[0].length() == 0)
            sender.sendMessage("bitte Spieler angeben");
        if (args[1].length() <= 1)
            sender.sendMessage("bitte grund angeben");
        if (args[1].length() >= 33)
            sender.sendMessage("der Grund darf nicht grals 32 Zeichen sein!");
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer punishedPlayer = Player.getPlayer(args[0]);
            ProxiedPlayer SenderPlayer = (ProxiedPlayer)sender;
            Mute.addPunishment(punishedPlayer.toString(), args[1], sender.getName());
            new GeneralPunishment().addToLog(punishedPlayer,"Mute",args[1]);
            punishedPlayer.sendMessage((new ComponentBuilder("" + ChatColor.AQUA + "Craftunity System" + ChatColor.AQUA + "|" + ChatColor.DARK_GRAY + "Du wurdest stummgeschaltet")).create());
            punishedPlayer.sendMessage((new ComponentBuilder("" + ChatColor.GRAY + "Grund: " + ChatColor.GRAY)).create());

            new Punishment().CreatePunishment(SenderPlayer, punishedPlayer.getName().toString(), "Mute", args[1]);

        } else {
            sender.sendMessage("Befehl nicht in der Konsole ausfürbar");
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
            matches.add("Chatverhalten-Rassismus");
            matches.add("Werbung");
            matches.add("Chatverhalten-Spamming");
            matches.add("Chatverhalten-CapsLock");



        }

        return matches;
    }
}
