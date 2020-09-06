package skills;
import gameCharacters.GameCharacters;
import java.util.ArrayList;

public class Lightning extends Skills {
    private int skillMDG;
    private int monsterMP;
    private int playerMDF, playerHP;
    private ArrayList<GameCharacters> roles;

    public Lightning(ArrayList<GameCharacters> roles) {
        setSkillMP(100);
        this.skillMDG = 250;
        this.roles = roles;
    }

    @Override
    public void attack(GameCharacters role) {       //role = monster
        monsterMP = role.getMP();
        role.setMP(countMP(monsterMP));

        GameCharacters player = roles.get(0);
        playerMDF = player.getMDF();
        playerHP = player.getHP();
        player.setHP(countHP(playerHP, playerMDF, skillMDG));
    }
}
