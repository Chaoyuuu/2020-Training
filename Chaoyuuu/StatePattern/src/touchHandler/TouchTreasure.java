package touchHandler;


import game.GameMap;
import item.Sprite;
import item.Player;
import item.Treasure;

public class TouchTreasure extends TouchHandler {
    public TouchTreasure(TouchHandler next) {
        super(next);
    }


    @Override
    protected boolean match(Sprite sprite, Player player) {
        return sprite instanceof Treasure;
    }

    @Override
    protected void handle(Sprite sprite, Player player) {
        player.updatePosition(player.getX(), player.getY());
        player.setState(((Treasure) sprite).getState());
        System.out.println("player get the treasure");
        removeTreasure(sprite);
    }

    private void removeTreasure(Sprite sprite) {
        GameMap gameMap = sprite.getGameMap();
        gameMap.removeSprite(sprite);
    }
}
