package state;

import item.Hero;
import item.Monster;
import item.Player;

//每回合將恢復30點生命值，直到滿血，滿血後立刻恢復至正常狀態
public class RegainState extends State {
    public RegainState() {
        this.round = 5;
    }

    @Override
    public void move(Player player) {
        addHP(player, 30);
        player.move();
        changeState(player);
    }

    @Override
    public void attack(Player player) {
        addHP(player, 30);
        player.attack();
        changeState(player);
    }

    @Override
    public void minusHP(Player player, int minusHP) {
        player.setHP(player.getHP() - minusHP);
    }

    private void addHP(Player player, int addHP) {
        player.setHP(player.getHP() + addHP);
        if (player.getHP() == getMaxHpFromPlayer(player)) {
            player.setState(new NormalState());
        }
    }

    private int getMaxHpFromPlayer(Player player) {
        if (player instanceof Monster) {
            return Monster.MAX_HP;
        } else if (player instanceof Hero) {
            return Hero.MAX_HP;
        } else {
            System.out.println("error type");
            return 0;
        }
    }

    private void changeState(Player player) {
        this.round--;
        if (this.round < 0 && player.getState() instanceof RegainState) {
            player.setState(new NormalState());
        }
    }
}
