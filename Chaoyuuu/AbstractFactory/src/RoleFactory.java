import Weapon.Attackable;
import Weapon.Defense.DefenseWeapon;

public interface RoleFactory {

    public DefenseWeapon createDefenseWeapon();

    public Attackable createAttackWeapon();
}



