package state;

import item.Player;

public class PoisonState extends State {
    public PoisonState() {
        this.round = 3;
    }

    @Override
    public void move(Player player) {
        System.out.println("in poison state HP -15");
        player.minusHP(15);
        player.move();
        changeState(player);
    }

    @Override
    public void attack(Player player) {
        System.out.println("in poison state HP -15");
        player.minusHP(15);
        player.attack();
        changeState(player);
    }

    @Override
    public void minusHP(Player player, int minusHP) {
        player.setHP(player.getHP() - minusHP);
    }

    private void changeState(Player player) {
        this.round--;
        if (this.round < 0 && player.getState() instanceof PoisonState) {
            player.setState(new NormalState());
        }
    }
}
