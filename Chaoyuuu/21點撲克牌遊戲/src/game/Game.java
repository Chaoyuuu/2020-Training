package game;

import bank.Dealer;
import player.Player;
import utils.SortPlayerTotalScore;

import java.util.Collections;
import java.util.List;

import static utils.Delay.delay;

public class Game {
    private List<Player> players;
    private Dealer dealer = new Dealer();

    public Game(List<Player> players) {
        this.players = players;
    }

    public void startGame() {
        resetScore();
        for (Player player : players) {
            System.out.printf("\n輪到 %s 了 !!\n", player.getPlayerName());
            player.makeDecision(dealer);
            System.out.printf("%s 得到了 .. %d分\n", player.getPlayerName(), player.getPlayerScore());
        }

        //print winner
        Player winner = whoIsWinner();
        delay(400);
        System.out.printf("* * %s是贏家，獲得一分 * *\n\n", winner.getPlayerName());
    }

    private void resetScore() {
        for (Player player : players) {
            player.resetPlayerScore();
        }
    }

    private Player whoIsWinner() {
        Player winner;
        winner = players.get(0);
        for (Player player : players) {
            if (winner.getPlayerScore() < player.getPlayerScore())
                winner = player;
        }

        winner.setPlayerTotalScore(1);
        return winner;
    }

    public void gameOver() {
        System.out.println("- - 遊戲結束 - -");
        players.sort(new SortPlayerTotalScore());

        int rank = 1;
        for (Player player : players) {
            System.out.printf("第%d名: %s\n", rank, player.getPlayerName());
            rank++;
        }
    }

}
