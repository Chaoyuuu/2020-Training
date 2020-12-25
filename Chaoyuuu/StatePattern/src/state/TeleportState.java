package state;

import game.GameMap;
import item.Item;
import item.Player;

import java.util.Random;

//一回合後主角的位置將被隨機移動至任一空地
public class TeleportState extends State {
    private final Random random = new Random();

    public TeleportState() {
        this.round = 1;
    }

    @Override
    public void move(Player player) {
        player.move();
        System.out.println(player.getGameMap());
        teleport(player);
        changeState(player);
    }

    @Override
    public void attack(Player player) {
        player.attack();
        teleport(player);
        changeState(player);
    }

    @Override
    public void minusHP(Player player, int minusHP) {
        player.setHP(player.getHP() - minusHP);
    }

    private void teleport(Player player) {
        GameMap gameMap = player.getGameMap();
        int x = random.nextInt(GameMap.BOUNDARY);
        int y = random.nextInt(GameMap.BOUNDARY);
        while (hasCollision(x, y, gameMap)) {
            x = random.nextInt(GameMap.BOUNDARY);
            y = random.nextInt(GameMap.BOUNDARY);
        }
        player.updatePosition(x, y);

    }

    private boolean hasCollision(int x, int y, GameMap gameMap) {
        Item item = gameMap.getTheItemByPosition(x, y);
        return item != null;
    }

    private void changeState(Player player) {
        this.round--;
        if (this.round <= 0 && player.getState() instanceof TeleportState) {
            player.setState(new NormalState());
        }
    }
}
