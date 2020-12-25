package state;


import item.Player;

public class NormalState extends State {
    public NormalState() {
        this.round = 1;
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
    public void minusHP(Player player, int minusHP) {
        player.setHP(player.getHP() - minusHP);
    }
}
