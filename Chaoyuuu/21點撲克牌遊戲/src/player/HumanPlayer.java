package player;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name);
    }

    @Override
    public boolean determineWhetherTakeNextCard() {
        Scanner scanner = new Scanner(System.in);
        boolean hit = scanner.next().equalsIgnoreCase("y");
        return hit;
    }
}
