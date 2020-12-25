package touchHandler;


import game.GameMap;
import item.Item;
import item.Player;
import item.Treasure;

public class TouchTreasure extends TouchHandler {
    public TouchTreasure(TouchHandler next) {
        super(next);
    }

    @Override
    public void touch(Item item, Player player) {
        if (isTreasure(item)) {
            player.updatePosition(player.getX(), player.getY());
            player.setState(((Treasure) item).getState());
            System.out.println("player get the treasure");
            removeTreasure(item);
        } else {
            super.touch(item, player);
        }

    }

    private boolean isTreasure(Item item) {
        return item instanceof Treasure;
    }

    private void removeTreasure(Item item) {
        GameMap gameMap = item.getGameMap();
        gameMap.removeItem(item);
    }
}
