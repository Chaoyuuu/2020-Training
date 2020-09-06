package skills;
import gameCharacters.*;
import java.util.ArrayList;

public class Summon extends Skills{
    private int monsterMP;
    private ArrayList<GameCharacters> roles;

    public Summon(ArrayList<GameCharacters> roles) {
        setSkillMP(50);
        this.roles = roles;
    }

    @Override
    public void attack(GameCharacters role) {   //role = monster
//        System.out.println("this is summon");

        int random = (int)(Math.random() *10);
        GameCharacters newMonster;

        switch (random % 3){
            case 1:
                newMonster = new Soldier();
                roles.add(newMonster);
                System.out.println("(Boss): summon Soldier");
                break;
            case 2:
                newMonster = new Protector();
                roles.add(newMonster);
                System.out.println("(Boss): summon Protector");
                break;
            case 0:
            default:
                newMonster = new Mage();
                roles.add(newMonster);
                System.out.println("(Boss): summon Mage");
                break;
        }

        //set newMP
        monsterMP = role.getMP();
        role.setMP(countMP(monsterMP));
    }
}
