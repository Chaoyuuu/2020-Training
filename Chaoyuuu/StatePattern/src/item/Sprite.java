package item;

import game.GameMap;

import java.util.Random;

public class Sprite {

    protected GameMap gameMap;
    protected int x;
    protected int y;
    private final Random rand = new Random();

    public Sprite() {
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void setRandomPosition() {
        this.x = rand.nextInt(GameMap.BOUNDARY);
        this.y = rand.nextInt(GameMap.BOUNDARY);
        while (hasCollision()) {
            this.x = rand.nextInt(GameMap.BOUNDARY);
            this.y = rand.nextInt(GameMap.BOUNDARY);
        }
        gameMap.setPositionOnMap(x, y, this);
    }

    private boolean hasCollision() {
        Sprite sprite = gameMap.getSpriteByPosition(this.x, this.y);
        return sprite != null;
    }

    public void updatePosition(int x, int y) {
        gameMap.removePositionOnMap(this.x, this.y);
        gameMap.setPositionOnMap(x, y, this);
        this.x = x;
        this.y = y;
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
