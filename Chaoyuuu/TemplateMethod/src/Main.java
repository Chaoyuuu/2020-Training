import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> players = new LinkedList<>();
        players.add(new Player("A"));
        players.add(new Player("B"));

        NumberGame numberGame = new NumberGame(players,26);
        numberGame.startGame();
    }
}
