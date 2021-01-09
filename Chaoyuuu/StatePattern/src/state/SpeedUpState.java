package state;

import item.Player;

//每回合可以進行兩次動作，若在期間遭受攻擊則立刻恢復至正常狀態
public class SpeedUpState extends State {
    private int count = 2;

    public SpeedUpState() {
        super(3);
    }

    @Override
    public void onRoundBegins(Player player) {
        player.turn();
        anotherTurn(player);
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
        player.setHP(player.getHP() - minusHP);
        player.setState(new NormalState());
    }

    private void anotherTurn(Player player) {
        count--;
        if (count > 0) {
            System.out.println(player.getGameMap());
            player.turn();
        }
    }

    private void changeStateIfExpires(Player player) {
        this.round--;
        if (this.round <= 0 && player.getState() instanceof SpeedUpState) {
            player.setState(new NormalState());
        }
    }
}
