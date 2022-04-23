package net.craftunity.bans.Utils;

import java.util.ArrayList;

public class BannedPlayer {

    public static ArrayList<String> Players;

    public static ArrayList<String> getPlayers() {
        return Players;
    }

    public static void setPlayers(ArrayList<String> players) {
        Players = players;
    }
}
