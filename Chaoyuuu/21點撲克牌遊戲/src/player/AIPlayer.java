package player;

import static utils.Delay.delay;

public class AIPlayer extends Player {

    public AIPlayer(String name) {
        super(name);
    }

    @Override
    public boolean determineWhetherTakeNextCard() {
        int random = (int) (Math.random() * 100);
        int mod = random % 2;
        delay(200);

        if (mod == 1)
            System.out.println("y");
        else
            System.out.println("n");

        return (mod == 1);
    }

}
