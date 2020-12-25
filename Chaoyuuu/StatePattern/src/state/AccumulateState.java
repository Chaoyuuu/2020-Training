package state;

import item.Player;

//兩回合後進入爆發狀態，若在期間遭受攻擊則立刻恢復至正常狀態
public class AccumulateState extends State {
    public AccumulateState() {
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
        player.setHP(player.getHP() - minusHP);
        player.setState(new NormalState());
    }

    private void changeState(Player player) {
        this.round--;
        if (this.round <= 0 && player.getState() instanceof AccumulateState) {
            player.setState(new BurstState());
        }
    }
}
