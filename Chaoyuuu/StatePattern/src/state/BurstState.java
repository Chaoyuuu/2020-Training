package state;

import item.Player;

//能夠進行全場攻擊，且攻擊力為50，三回合過後取得瞬身狀態
public class BurstState extends State {
    public BurstState() {
        this.round = 3;
    }

    @Override
    public void move(Player player) {
        player.move();
        changeState(player);
    }

    @Override
    public void attack(Player player) {
        player.attackGlobal(50);
        changeState(player);
    }

    @Override
    public void minusHP(Player player, int minusHP) {
        player.setHP(player.getHP() - minusHP);
    }

    private void changeState(Player player) {
        this.round--;
        if (this.round <= 0 && player.getState() instanceof BurstState) {
            player.setState(new TeleportState());
        }
    }
}
