package gameCharacters;
import skills.*;
import java.util.ArrayList;

public class Soldier extends GameCharacters {
    public Soldier() {
        setHP(150);
        setMP(0);
        setAP(60);
        setDF(10);
        setMDF(0);
        setName("Soldier");
    }

    @Override
    public Skills chooseSkills(ArrayList<GameCharacters> roles){
        System.out.println("(Solider): simpleAttack");
        Skills skill = new SimpleAttack(roles);
        return skill;
    }
}
