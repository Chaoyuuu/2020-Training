package skills;
import gameCharacters.GameCharacters;
import java.util.ArrayList;
import java.util.Scanner;

public class FireBall extends Skills {
    private int skillMDG;
    private int playerMP;
    private int monsterMDF, monsterHP;
    private int index;
    private ArrayList<GameCharacters> roles;

    public FireBall(ArrayList<GameCharacters> roles) {
        setSkillMP(50);
        this.skillMDG = 150;
        this.roles = roles;

        //choose one monster
        System.out.print("Choose a monster index: ");
        Scanner scanner = new Scanner(System.in);
        index = scanner.nextInt();
    }

    @Override
    public void attack(GameCharacters role) {       //role = player
        playerMP = role.getMP();
        role.setMP(countMP(playerMP));

        GameCharacters m = roles.get(index);
        monsterMDF = m.getMDF();
        monsterHP = m.getHP();
        m.setHP(countHP(monsterHP, monsterMDF, skillMDG));
    }
}
