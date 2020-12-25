package item;

import game.GameMap;

import java.util.Random;

public class Item {

    protected GameMap gameMap;
    protected int x;
    protected int y;
    private final Random rand = new Random();

    public Item(GameMap gameMap) {
        this.gameMap = gameMap;
        setInitPosition();
    }

    private void setInitPosition() {
        this.x = rand.nextInt(GameMap.BOUNDARY);
        this.y = rand.nextInt(GameMap.BOUNDARY);
        while (hasCollision()) {
            this.x = rand.nextInt(GameMap.BOUNDARY);
            this.y = rand.nextInt(GameMap.BOUNDARY);
        }
        gameMap.addItem(this);
    }

    public void updatePosition(int x, int y) {
        gameMap.removePositionOnMap(this.x, this.y);
        gameMap.setPositionOnMap(x, y, this);
        this.x = x;
        this.y = y;
    }

    private boolean hasCollision() {
        Item item = gameMap.getTheItemByPosition(this.x, this.y);
        return item != null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

}
