package game;

import dealer.Card;
import dealer.Dealer;
import player.Player;
import utils.SortPlayerTotalScore;

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
            System.out.printf("\n輪到 %s 了 !!\n", player.getName());
            takeTurns(player);
            System.out.printf("%s 得到了 .. %d分\n", player.getName(), player.getScore());
        }

        //print winner
        Player winner = whoIsWinner();
        delay(400);
        System.out.printf("* * %s是贏家，獲得一分 * *\n\n", winner.getName());
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
            if (winner.getScore() < player.getScore())
                winner = player;
        }

        winner.setTotalScore(1);
        return winner;
    }

    private void takeTurns(Player player) {
        Card card;
        boolean hit = true;  //get one card in the first round
        boolean alive = true;

        while (hit && alive) {
            card = dealer.deal();
            System.out.printf("翻開了 .... %s %d\n", card.getSuit(), card.getNumber());
            player.setScore(card.getNumber());

            if (player.isOverTwentyOne()) {  //over, get zero point
                alive = false;
            } else {
                System.out.printf("請問 %s 要翻開下一張牌嗎(y/n)? ", player.getName());
                hit = player.determineWhetherTakeNextCard();
            }
        }

    }

    public void gameOver() {
        System.out.println("- - 遊戲結束 - -");
        players.sort(new SortPlayerTotalScore());

        int rank = 1;
        for (Player player : players) {
            System.out.printf("第%d名: %s\n", rank, player.getName());
            rank++;
        }
    }

}
