package gameCharacters;
import skills.*;
import java.util.ArrayList;

public class Protector extends GameCharacters {
    public Protector() {
        setHP(270);
        setMP(0);
        setAP(100);
        setDF(20);
        setMDF(40);
        setName("Protector");
    }

    @Override
    public Skills chooseSkills(ArrayList<GameCharacters> roles){
        System.out.println("(Protector): simpleAttack");
        Skills skill = new SimpleAttack(roles);
        return skill;
    }
}
