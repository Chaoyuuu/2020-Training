package state;

import item.Hero;
import item.Monster;
import item.Player;

//每回合將恢復30點生命值，直到滿血，滿血後立刻恢復至正常狀態
public class RestoreState extends State {
    public RestoreState() {
        super(5);
    }

    @Override
    public void onRoundBegins(Player player) {
        addHP(player, 30);
        System.out.println("in regain state HP + 30");
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
        player.setHP(player.getHP() - minusHP);
    }

    private void addHP(Player player, int addHP) {
        int playerHP = player.getHP() + addHP;
        int maxHP = getMaxHpFromPlayer(player);
        if (playerHP >= maxHP) {
            player.setHP(maxHP);
            player.setState(new NormalState());
        } else {
            player.setHP(playerHP);
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

    private void changeStateIfExpires(Player player) {
        this.round--;
        if (this.round < 0 && player.getState() instanceof RestoreState) {
            player.setState(new NormalState());
        }
    }
}
