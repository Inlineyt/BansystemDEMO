package net.craftunity.bans.Utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Player {

    public static ProxiedPlayer getPlayer(String player) {
        ProxiedPlayer Player = ProxyServer.getInstance().getPlayer(player);


        return Player;
    }
}
