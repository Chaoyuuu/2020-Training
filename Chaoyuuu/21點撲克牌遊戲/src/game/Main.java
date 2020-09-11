package game;
import player.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Player> players = initPlayers();
        Game game = new Game(players);

        for (int i = 0; i < 3; i++) {
            System.out.printf("- - ROUND %d - -", i + 1);
            game.startGame();
        }
        game.gameOver();
    }

    public static List<Player> initPlayers() {
        ArrayList<Player> initPlayers = new ArrayList<>();

        int humanPlayerNum, aiPlayerNum;
        Scanner scanner = new Scanner(System.in);

        //init player
        System.out.print("有幾位玩家? ");
        humanPlayerNum = scanner.nextInt();
        System.out.print("有幾位電腦玩家? ");
        aiPlayerNum = scanner.nextInt();

        scanner.nextLine(); //throw "/n"

        for (int i = 0; i < humanPlayerNum; i++) {
            System.out.printf("請輸入第%d位玩家的名字: ", i);
            String name = scanner.nextLine();
            Player humanPlayer = new HumanPlayer(new Attribute(name));
            initPlayers.add(humanPlayer);
        }

        for (int i = humanPlayerNum; i < humanPlayerNum + aiPlayerNum; i++) {
            Player AIPlayer = new AIPlayer(new Attribute("AI-" + i));
            initPlayers.add(AIPlayer);
        }

        //random player
        return shufflePlayers(initPlayers);
    }

    public static List<Player> shufflePlayers(List<Player> initPlayers) {
        List<Player> players = new ArrayList<>();
        int totalNum = initPlayers.size();
        while (totalNum != 0) {
            int randomNum = (int) (Math.random() * totalNum);
            Player player = initPlayers.remove(randomNum);
            players.add(player);
            totalNum--;
        }
        return players;
    }
}
