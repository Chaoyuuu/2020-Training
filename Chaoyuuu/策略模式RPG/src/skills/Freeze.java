package skills;
import gameCharacters.GameCharacters;
import java.util.ArrayList;
import java.util.Scanner;

public class Freeze extends Skills {
    private int playerMP;
    private int index;
    private ArrayList<GameCharacters> roles;

    public Freeze(ArrayList<GameCharacters> roles) {
        setSkillMP(100);
        this.roles = roles;

        //choose one monster
        System.out.print("Choose a monster index: ");
        Scanner scanner = new Scanner(System.in);
        index = scanner.nextInt();
    }

    @Override
    public void attack(GameCharacters role) {      //role = player
        playerMP = role.getMP();
        role.setMP(countMP(playerMP));

        GameCharacters m = roles.get(index);
        //freeze monster
        m.setRest(3);
    }
}
