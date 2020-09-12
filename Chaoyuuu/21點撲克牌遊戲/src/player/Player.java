package player;

public abstract class Player {
    private int totalScore = 0;
    private int score = 0;
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int cardNum) {
        int newScore = score + cardNum;
        this.score = newScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int score) {
        int newScore = totalScore + score;
        this.totalScore = newScore;
    }

    public void resetPlayerScore() {
        score = 0;
    }

    public boolean isOverTwentyOne() {
        if (score > 21) {
            resetPlayerScore();
            return true;
        }
        return false;
    }

    public abstract boolean determineWhetherTakeNextCard();
}
