package state;

import item.Player;

import java.util.Random;

//一回合後主角的位置將被隨機移動至任一空地
public class TeleportationState extends State {
    private final Random random = new Random();

    public TeleportationState() {
        super(1);
    }

    @Override
    public void onRoundBegins(Player player) {
        teleport(player);
        System.out.println(player.getGameMap());
        player.turn();
        changeStateIfExpires(player);
    }

    @Override
    public void move(Player player) {
        player.move();
    }

    @Override
    public void attack(Player player) {
        player.attack();
    }

    @Override
    public void damage(Player player, int minusHP) {
        player.setHP(player.getHP() - minusHP);
    }

    private void teleport(Player player) {
        player.setRandomPosition();
        player.updatePosition(player.getX(), player.getY());
    }

    private void changeStateIfExpires(Player player) {
        this.round--;
        if (this.round <= 0 && player.getState() instanceof TeleportationState) {
            player.setState(new NormalState());
        }
    }
}
