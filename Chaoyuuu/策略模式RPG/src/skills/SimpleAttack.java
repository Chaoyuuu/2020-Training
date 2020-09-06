package skills;
import gameCharacters.GameCharacters;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleAttack extends Skills {
    private int playerAP, monsterDF, monsterHP;
    private int playerHP, playerDF, monsterAP;
    private int index;
    private ArrayList<GameCharacters> roles;

    public SimpleAttack(ArrayList<GameCharacters> roles) {
        this.roles = roles;
    }

    @Override
    public void attack(GameCharacters role) {  //role = player or monster
        //monster or player
        if(role.getName() == "Player"){
            //choose one monster
            System.out.print("Choose a monster index: ");
            Scanner scanner = new Scanner(System.in);
            index = scanner.nextInt();

            playerAP = role.getAP();
            GameCharacters monster = roles.get(index);
            monsterDF = monster.getDF();
            monsterHP = monster.getHP();
            monster.setHP(countHP(monsterHP, monsterDF, playerAP));
        }else{
            GameCharacters player = roles.get(0);
            playerHP = player.getHP();
            playerDF = player.getDF();
            monsterAP = role.getAP();
            player.setHP(countHP(playerHP, playerDF, monsterAP));
        }
    }
}
