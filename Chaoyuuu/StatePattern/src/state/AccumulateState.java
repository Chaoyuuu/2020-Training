package state;

import item.Player;

//兩回合後進入爆發狀態，若在期間遭受攻擊則立刻恢復至正常狀態
public class AccumulateState extends State {
    public AccumulateState() {
        this.round = 2;
    }

    @Override
    public void onRoundBegins(Player player) {
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
        player.setState(new NormalState());
    }

    private void changeState(Player player) {
        this.round--;
        if (this.round <= 0 && player.getState() instanceof AccumulateState) {
            player.setState(new BurstState());
        }
    }
}
