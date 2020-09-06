package gameCharacters;
import skills.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Boss extends GameCharacters {
    public Boss() {
        setHP(1000);
        setMP(300);
        setAP(90);
        setDF(40);
        setMDF(40);
        setName("Boss");
    }

    @Override
    public Skills chooseSkills(ArrayList<GameCharacters> roles){
        Skills skill;

        if(getMP() >= 50){
            System.out.print("(Boss): 1. simpleAttack, 2. Summon  ");
            Scanner scanner = new Scanner(System.in);
            int skillIndex = scanner.nextInt();

            switch (skillIndex){
                case 2:
                    skill = new Summon(roles);
                    break;
                case 1:
                default:
                    skill = new SimpleAttack(roles);
                    break;
            }
        }else{
            //less MP, only simpleAttack
            System.out.println("(Boss): simpleAttack, out of MP");
            skill = new SimpleAttack(roles);
        }
        return skill;
    }
}
