package state;

import item.Player;

//受到攻擊時並不會有任何生命損失
public class InvincibleState extends State {
    public InvincibleState() {
        super(2);
    }

    @Override
    public void onRoundBegins(Player player) {
        player.turn();
        changeStateIfExpires(player);
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
        player.setHP(player.getHP());
    }

    private void changeStateIfExpires(Player player) {
        this.round--;
        if (this.round <= 0 && player.getState() instanceof InvincibleState) {
            player.setState(new NormalState());
        }
    }
}
