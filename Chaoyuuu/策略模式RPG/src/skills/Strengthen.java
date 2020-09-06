package skills;
import gameCharacters.GameCharacters;
import java.util.ArrayList;

public class Strengthen extends Skills {
    private int playerMP, playerDF;
    private int plusDF;

    public Strengthen() {
        setSkillMP(40);
        this.plusDF = 30;
    }

    @Override
    public void attack(GameCharacters role) {      //role = player
        playerMP = role.getMP();
        role.setMP(countMP(playerMP));

        playerDF = role.getDF();
        role.setDF(playerDF + plusDF);
    }
}
