package state;

import item.Player;

public class PoisonState extends State {
    public PoisonState() {
        this.round = 3;
    }

    @Override
    public void onRoundBegins(Player player) {
        player.damage(15);
        System.out.println("in poison state HP -15");
        player.turn();
        changeState(player);
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

    private void changeState(Player player) {
        this.round--;
        if (this.round < 0 && player.getState() instanceof PoisonState) {
            player.setState(new NormalState());
        }
    }
}
