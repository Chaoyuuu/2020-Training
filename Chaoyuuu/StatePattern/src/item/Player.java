package item;

import game.GameMap;
import state.State;
import touchHandler.TouchHandler;

public abstract class Player extends Item {

    protected int HP;
    protected State state;
    protected TouchHandler touchHandler;

    public Player(GameMap gameMap, State state, TouchHandler touchHandler) {
        super(gameMap);
        this.state = state;
        this.touchHandler = touchHandler;
    }

    public abstract void turn();

    public abstract void move();

    public abstract void moveVertical();

    public abstract void moveHorizontal();

    public abstract void attack();

    public abstract void attackGlobal(int minusHP);

    public void minusHP(int minusHP) {
        this.state.minusHP(this, minusHP);
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHP() {
        return HP;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
