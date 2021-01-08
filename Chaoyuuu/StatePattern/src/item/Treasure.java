package item;

import game.GameMap;
import state.State;

public class Treasure extends Sprite implements Cloneable {
    protected State state;

    public Treasure(State state) {
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
