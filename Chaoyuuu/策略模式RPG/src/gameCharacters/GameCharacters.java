package gameCharacters;
import skills.Skills;
import java.util.ArrayList;

public abstract class GameCharacters {
    private int HP;
    private int MP;
    private int AP;
    private int DF;
    private int MDF;
    private String name;
    private boolean Dead = false;
    private int rest = 0;

    public abstract Skills chooseSkills(ArrayList<GameCharacters> roles);

    public boolean isDead() {
        return Dead;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
//        System.out.printf("in setHP %d\n", HP);
        if (HP == 0){
            this.Dead = true;
        }
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getAP() {
        return AP;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public int getDF() {
        return DF;
    }

    public void setDF(int DF) {
        this.DF = DF;
    }

    public int getMDF() {
        return MDF;
    }

    public void setMDF(int MDF) {
        this.MDF = MDF;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
