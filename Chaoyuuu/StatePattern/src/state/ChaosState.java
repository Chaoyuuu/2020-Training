package state;

import java.util.Random;

import item.Player;

//每回合隨機取得以下其中一種效果：1. 只能進行 上下移動 2. 只能進行 左右移動
public class ChaosState extends State {
    private Random random = new Random();

    public ChaosState() {
        this.round = 3;
    }

    @Override
    public void move(Player player) {
        switch (random.nextInt(2)) {
            case 0:
                player.moveHorizontal();
                break;
            case 1:
                player.moveVertical();
                break;
        }
        changeState(player);
    }

    @Override
    public void attack(Player player) {
        player.attack();
        changeState(player);
    }

    @Override
    public void minusHP(Player player, int minusHP) {
        player.setHP(player.getHP() - minusHP);
    }

    private void changeState(Player player) {
        this.round--;
        if (this.round <= 0 && player.getState() instanceof ChaosState) {
            player.setState(new NormalState());
        }
    }
}
