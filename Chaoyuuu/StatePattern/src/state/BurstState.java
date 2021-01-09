package state;

import item.Player;

//能夠進行全場攻擊，且攻擊力為50，三回合過後取得瞬身狀態
public class BurstState extends State {
    public BurstState() {
        super(3);
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
        player.attackGlobal(50);
    }

    @Override
    public void damage(Player player, int minusHP) {
        player.setHP(player.getHP() - minusHP);
    }

    private void changeStateIfExpires(Player player) {
        this.round--;
        if (this.round <= 0 && player.getState() instanceof BurstState) {
            player.setState(new TeleportationState());
        }
    }
}
