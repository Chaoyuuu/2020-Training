package game;
import player.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Player> players = initPlayer();
        Game game = new Game(players);

        for(int i = 0 ; i < 3; i++){
            System.out.printf("- - ROUND %d - -", i + 1);
            game.startGame();
        }
        game.gameOver();
    }

    public static ArrayList<Player> initPlayer(){
        ArrayList<Player> initPlayers = new ArrayList<>();

        int humanPlayerNum, AIPlayerNum;
        Scanner scanner = new Scanner(System.in);

        //init player
        System.out.print("有幾位玩家? ");
        humanPlayerNum = scanner.nextInt();
        System.out.print("有幾位電腦玩家? ");
        AIPlayerNum = scanner.nextInt();

        scanner.nextLine(); //throw "/n"

        for (int i = 0; i < humanPlayerNum; i++) {
            System.out.printf("請輸入第%d位玩家的名字: ", i);
            String name = scanner.nextLine();
            Player humanPlayer = new HumanPlayer(new Attribute(name));
            initPlayers.add(humanPlayer);
        }

        for (int i = humanPlayerNum; i < humanPlayerNum + AIPlayerNum; i++){
            Player AIPlayer = new AIPlayer(new Attribute("AI-" + i));
            initPlayers.add(AIPlayer);
        }

        //random player
        return randomPlayer(initPlayers);
    }

    public static ArrayList<Player> randomPlayer(ArrayList<Player> initPlayers){
        //random player
        ArrayList<Player> players = new ArrayList<>();
        int totalNum = initPlayers.size();
        while (totalNum != 0){
            int randomNum = (int) (Math.random() * totalNum);
            Player player = initPlayers.remove(randomNum);
            players.add(player);
            totalNum--;
        }
        return players;
    }
}
