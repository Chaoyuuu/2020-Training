import Weapon.Attackable;
import Weapon.Defense.DefenseWeapon;

import static java.lang.Integer.max;

public class Player {
    private String playerName;
    private int HP = 20000;
    private int MP = 200;
    private RoleFactory role;

    public Attackable myAttack;
    public DefenseWeapon myDefenseWeapon;

    public Player(String playerName, RoleFactory role) {
        this.playerName = playerName;
        this.role = role;
        myDefenseWeapon = role.createDefenseWeapon();
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = max(HP, 0);
    }

    public boolean isAlive() {
        if (HP == 0) {
            return false;
        } else {
            return true;
        }
    }

    public int attack() {
        int totalInjury = 0;
        chooseWeapon();
        try {
            checkMP();
            System.out.println(playerName + " 使用 " + myAttack.getName() + " 進行攻擊 !!");
            totalInjury = countTotalInjury();
        } catch (OutOfMpExecption e) {
            System.out.println("* * * " + e.getMessage() + " * * *");
        }

        return totalInjury;
    }

    private void chooseWeapon() {
        myAttack = role.createAttackWeapon();
    }

    private void checkMP() {
        int newMP = this.MP - myAttack.getLoseMp();
        if (newMP < 0) {
            throw new OutOfMpExecption(playerName + " out pf MP !!!");
        }
        this.MP = newMP;
    }

    private int countTotalInjury() {
        int repeat = myAttack.getReaptedTimes();
        int totalInjury = 0;
        for (int i = 0; i < repeat; i++) {
            totalInjury += myAttack.getInjury();
            System.out.println(myAttack.getInjury());
        }

        return totalInjury;
    }

    @Override
    public String toString() {
        return "\n輪到 " + playerName + "\n狀態: " + "Hp " + HP + ", Mp " + MP;
    }
}
