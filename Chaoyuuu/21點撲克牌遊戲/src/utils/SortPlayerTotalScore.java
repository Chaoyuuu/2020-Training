package utils;

import player.Player;

import java.util.Comparator;

public class SortPlayerTotalScore implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p2.getPlayerTotalScore() - p1.getPlayerTotalScore();
    }
}
