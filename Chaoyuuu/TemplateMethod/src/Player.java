public class Player {
    private String name;
    private boolean correct = false;

    public Player(String name) {
        this.name = name;
    }

    public void setCorrect(boolean correct){
        this.correct = correct;
    }

    public boolean isCorrect() {
        return this.correct;
    }

    public String getName() {
        return name;
    }
}
