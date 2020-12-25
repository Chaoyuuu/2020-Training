package state;

import item.Player;

//受到攻擊時並不會有任何生命損失
public class InvincibleState extends State {
    public InvincibleState() {
        this.round = 2;
    }

    @Override
    public void move(Player player) {
        player.move();
        changeState(player);
    }

    @Override
    public void attack(Player player) {
        player.attack();
        changeState(player);
    }

    @Override
    public void minusHP(Player player, int minusHP) {
        player.setHP(player.getHP());
    }

    private void changeState(Player player) {
        this.round--;
        if (this.round <= 0 && player.getState() instanceof InvincibleState) {
            player.setState(new NormalState());
        }
    }
}
