import Weapon.Attack.Darts;
import Weapon.Attackable;
import Weapon.Defense.DefenseWeapon;
import Weapon.Defense.Skeleton;
import Weapon.MagicPower.ThunderBall;

import java.util.Scanner;

public class Ninja implements RoleFactory {

    @Override
    public DefenseWeapon createDefenseWeapon() {
        return new Skeleton();
    }

    @Override
    public Attackable createAttackWeapon() {
        System.out.print("選擇動作 (1) 飛鏢 (2) 雷電球: ");
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()) {
            case 1:
                return new Darts();
            case 2:
            default:
                return new ThunderBall();
        }
    }

}
