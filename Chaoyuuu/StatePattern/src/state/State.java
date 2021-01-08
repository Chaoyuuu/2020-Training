package state;

import item.Player;

public abstract class State {

    protected int round;

    public State() {
    }

    public State(int round) {
        // TODO make subclasses use this constructor to init the round
        this.round = round;
    }

    public abstract void onRoundBegins(Player player);

    public abstract void move(Player player);

    public abstract void attack(Player player);

    public abstract void damage(Player player, int minusHP);
}
