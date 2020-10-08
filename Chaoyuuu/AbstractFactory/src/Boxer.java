import Weapon.Attack.Glove;
import Weapon.Attackable;
import Weapon.Defense.Armor;
import Weapon.Defense.DefenseWeapon;
import Weapon.MagicPower.SkeletonGaster;

import java.util.Scanner;

public class Boxer implements RoleFactory{
    @Override
    public DefenseWeapon createDefenseWeapon() {
        return new Armor();
    }

    @Override
    public Attackable createAttackWeapon() {
        System.out.print("選擇動作 (1) 拳套 (2) Sans Gaster: ");
        Scanner in = new Scanner(System.in);
        switch (in.nextInt()) {
            case 1:
                return new Glove();
            case 2:
            default:
                return new SkeletonGaster();
        }
    }
}
