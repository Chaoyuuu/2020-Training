package player;
import bank.*;

public abstract class Player {
    private Attribute attribute;

    public Player(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getPlayerName() {
        return attribute.getName();
    }

    public void resetPlayerScore() {
        attribute.setScore(0);
    }

    public int getPlayerScore() {
        return  attribute.getScore();
    }

    public void setPlayerScore(int cardNum) {
        int newScore = attribute.getScore() + cardNum;
        attribute.setScore(newScore);
    }

    public int getPlayerTotalScore() {
        return  attribute.getTotalScore();
    }

    public void setPlayerTotalScore(int score) {
        int newScore = attribute.getTotalScore() + score;
        attribute.setTotalScore(newScore);
    }

    public boolean isOverTwentyOne(){
        int score = attribute.getScore();
        if(score > 21){
            attribute.setScore(0);
            return true;
        }
        return false;
    }

    public abstract void makeDecision(Dealer dealer);
}
