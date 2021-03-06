package net.craftunity.bans.Utils;

import net.craftunity.bans.Punishment.Ban;
import net.craftunity.bans.Punishment.Mute;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;

public class Chat {


    public ComponentBuilder MuteComponent(ProxiedPlayer player){


        String Reason = Mute.getReason(player.getUniqueId().toString());

        ComponentBuilder MuteComponent = new ComponentBuilder();

        TextComponent MuteID = new TextComponent( "\n\n" +ChatColor.RED + "  Aktuell Gemuted | Grund : " + Reason +"\n\n" + ChatColor.AQUA + "  MuteID: " + Mute.getMuteID(player.getUniqueId().toString()) +"\n\n");

        MuteID.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, Mute.getMuteID(player.getUniqueId().toString())));
        MuteID.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("MuteID kopieren").create()));
        MuteComponent.append(MuteID);


return MuteComponent;
    }


    public ComponentBuilder BanComponent(ProxiedPlayer player){


        String Reason = Ban.getReason(player.getUniqueId().toString());

        ComponentBuilder BanComponent = new ComponentBuilder();

        TextComponent BanID = new TextComponent( "\n\n" +ChatColor.RED + "  Aktuell Gebannt | Grund : " + Reason +"\n\n" + ChatColor.AQUA + "  BanID: " + Ban.getBanID(player.getUniqueId().toString()) +"\n\n");

        BanID.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, Ban.getBanID(player.getUniqueId().toString())));
        BanID.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("BanID kopieren").create()));
        BanComponent.append(BanID);


        return BanComponent;
    }


    public ComponentBuilder OnlineOnComponent(ProxiedPlayer player) {

        ComponentBuilder JumpComponent = new ComponentBuilder();
        Server onServer = (Server) player.getServer();
        TextComponent OnlineOnComponent = new TextComponent(ChatColor.AQUA + "Aktuell auf " + onServer.getInfo().getName() + " (Jump) " );

        OnlineOnComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND , "/Goto " + player.getName()));
        JumpComponent.append(OnlineOnComponent);
        return JumpComponent;
    }


    public ComponentBuilder PlayerUUIDComponent(ProxiedPlayer player) {

        ComponentBuilder UUIDComponent = new ComponentBuilder();
        TextComponent UUIDTextComponent = new TextComponent(ChatColor.AQUA + "UUID:  " + player.getUniqueId().toString() + " (Kopieren) " );

        UUIDTextComponent.setClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD ,  player.getUniqueId().toString()));
        UUIDComponent.append(UUIDTextComponent);
        return UUIDComponent;
    }

}
