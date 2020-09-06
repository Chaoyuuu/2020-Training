package skills;
import gameCharacters.GameCharacters;
import java.util.ArrayList;

public class WaterBall extends Skills {
    private int skillMDG;
    private int playerMP;
    private int monsterMDF, monsterHP;
    private ArrayList<GameCharacters> roles;

    public WaterBall(ArrayList<GameCharacters> roles) {
        setSkillMP(50);
        this.skillMDG = 50;
        this.roles = roles;
    }

    @Override
    public void attack(GameCharacters role) {   //role = player
        playerMP = role.getMP();
        role.setMP(countMP(playerMP));

        int size = roles.size();
        for(int i = 1; i < size; i++){
            GameCharacters m = roles.get(i);
            monsterMDF = m.getMDF();
            monsterHP = m.getHP();
            m.setHP(countHP(monsterHP, monsterMDF, skillMDG));
        }
    }
}
