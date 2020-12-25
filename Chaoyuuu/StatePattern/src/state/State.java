package state;

import item.Player;

public abstract class State {

    protected int round;

//    public abstract void effectState(Player player);
    public abstract void move(Player player);

    public abstract void attack(Player player);

    public abstract void minusHP(Player player, int minusHP);
}
