package skills;
import gameCharacters.GameCharacters;

public abstract class Skills {
    private int skillMP;

    public void setSkillMP(int skillMP) {
        this.skillMP = skillMP;
    }

    public int countMP(int charMP){
        int newMP = charMP - skillMP;
        return greaterZero(newMP);

    }

    public int countHP(int hp, int df, int ap){  ///???????????
        int hurt = ap - df;
        if(hurt < 0){   //no hurt, return hp
            return hp;
        }else{          //return newHP
            int newHP = hp - hurt;
            return greaterZero(newHP);
        }
    }

    private int greaterZero(int num){
        if(num < 0){
            return 0;
        }else{
            return num;
        }
    }

    public abstract void attack(GameCharacters role);

}
