package item;

import state.State;
import touchHandler.TouchHandler;

public abstract class Player extends Sprite {

    protected int HP;
    protected State state;
    protected TouchHandler touchHandler;

    public Player(State state, TouchHandler touchHandler) {
        super();
        this.state = state;
        this.touchHandler = touchHandler;
    }

    public abstract void onRoundBegins();

    public abstract void turn();

    public abstract void move();

    public abstract void moveVertically();

    public abstract void moveHorizontally();

    public abstract void attack();

    public abstract void attackGlobal(int minusHP);

    public void damage(int lostHp) {
        this.state.damage(this, lostHp);
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean isAlive() {
        return HP >= 0;
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
