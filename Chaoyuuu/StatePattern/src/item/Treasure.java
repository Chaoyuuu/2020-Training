package item;

import game.GameMap;
import state.State;

public class Treasure extends Item implements Cloneable {
    protected State state;

    public Treasure(GameMap gameMap, State state) {
        super(gameMap);
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "◆";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
