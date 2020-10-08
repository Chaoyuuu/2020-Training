import Weapon.Attack.Gaster;
import Weapon.Attack.Sword;
import Weapon.Attackable;
import Weapon.Defense.DefenseWeapon;
import Weapon.Defense.Shield;
import Weapon.MagicPower.FireBall;

import java.util.Scanner;

public class Warrior implements RoleFactory {
    public Warrior() {

    }

    @Override
    public DefenseWeapon createDefenseWeapon() {
        return new Shield();
    }

    @Override
    public Attackable createAttackWeapon() {
        System.out.print("選擇動作 (1) 長劍 (2) 水球 (3) 激光大砲 : ");
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()) {
            case 1:
                return new Sword();
            case 2:
                return new FireBall();
            case 3:
            default:
                return new Gaster();
        }
    }
}
