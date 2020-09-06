package gameCharacters;
import skills.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Mage extends GameCharacters {
    public Mage() {
        setHP(90);
        setMP(700);
        setAP(5);
        setDF(20);
        setMDF(60);
        setName("Mage");
    }

    @Override
    public Skills chooseSkills(ArrayList<GameCharacters> roles){
        Skills skill;

        if(getMP() >= 100){
            System.out.print("(Mage): 1. simpleAttack, 2. Lightning  ");
            Scanner scanner = new Scanner(System.in);
            int skillIndex = scanner.nextInt();

            switch (skillIndex){
                case 2:
                    skill = new Lightning(roles);
                    break;
                case 1:
                default:
                    skill = new SimpleAttack(roles);
                    break;
            }
        }else{
            //less MP, only simpleAttack
            System.out.println("(Mage): simpleAttack, out of MP");
            skill = new SimpleAttack(roles);
        }
        return skill;
    }
}
