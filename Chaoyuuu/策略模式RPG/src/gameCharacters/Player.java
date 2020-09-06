package gameCharacters;
import skills.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends GameCharacters {
    public Player() {
        setHP(500);
        setMP(500);
        setAP(70);
        setDF(40);
        setMDF(10);
        setName("Player");
    }

    @Override
    public Skills chooseSkills(ArrayList<GameCharacters> roles){
        System.out.print("(Player): 1. simpleAttack, 2. WaterBall, 3. FireBall, 4. Strengthen, 5. Freeze  ");
        Scanner scanner = new Scanner(System.in);
        Skills skill;
        int skillIndex = scanner.nextInt();
        int playerMP = getMP();

        switch (skillIndex){
            case 2:
                if(playerMP >= 50) {
                    skill = new WaterBall(roles);
                    break;
                }
            case 3:
                if(playerMP >= 50) {
                    skill = new FireBall(roles);
                    break;
                }
            case 4:
                if(playerMP >= 40) {
                    skill = new Strengthen();
                    break;
                }
            case 5:
                if(playerMP >= 100) {
                    skill = new Freeze(roles);
                    break;
                }
            case 1:
            default:
                skill = new SimpleAttack(roles);
                break;
        }

        return skill;
    }
}
