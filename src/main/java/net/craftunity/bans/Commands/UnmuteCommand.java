package net.craftunity.bans.Commands;

import net.craftunity.bans.Log.Punishment;
import net.craftunity.bans.Main;
import net.craftunity.bans.Punishment.Mute;
import net.craftunity.bans.Utils.Player;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
public class UnmuteCommand extends Command {

    public UnmuteCommand(Main name) {
        super("unmute","moderation.command.unmute");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args[0] == null){
            sender.sendMessage(new ComponentBuilder(ChatColor.AQUA + "Bitte Spieler angeben").create());
        }
        ProxiedPlayer senderplayer = (ProxiedPlayer)sender;
        ProxiedPlayer punishedPlayer = Player.getPlayer(args[0]);


        Mute.RemovePunishment(args[0]);
        new Punishment().CreatePunishment(senderplayer,args[0] ,"unmute","no_reason_applyable");
    }
}
