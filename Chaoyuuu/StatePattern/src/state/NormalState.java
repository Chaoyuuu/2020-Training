package state;


import item.Player;

public class NormalState extends State {
    public NormalState() {
        super(1);
    }

    @Override
    public void onRoundBegins(Player player) {
        player.turn();
    }

    @Override
    public void move(Player player) {
        player.move();
    }

    @Override
    public void attack(Player player) {
        player.attack();
    }

    @Override
    public void damage(Player player, int minusHP) {
        player.setHP(player.getHP() - minusHP);
    }
}
