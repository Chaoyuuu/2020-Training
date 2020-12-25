package item;

import game.GameMap;


public class Obstacle extends Item {
    public Obstacle(GameMap gameMap) {
        super(gameMap);
    }

    @Override
    public String toString() {
        return "□";
    }
}
