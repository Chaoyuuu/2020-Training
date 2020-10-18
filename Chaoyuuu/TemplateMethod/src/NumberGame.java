import java.util.List;
import java.util.Scanner;

public class NumberGame extends GameContainer<Player, Integer> {

    private int specialNum;
    private int upperBound = 100;
    private int lowerBound = 0;

    public NumberGame(List<Player> players, int specialNum) {
        super(players);
        this.specialNum = specialNum;
    }

    @Override
    protected Integer onPlayerAction(Player player) {
        System.out.println("(" + player.getName() + ") " + printRange());
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    @Override
    protected void onExecuteAction(Integer guessNum, Player player) {
        if (guessNum == specialNum) {
            player.setCorrect(true);
        } else if (guessNum > specialNum) {
            upperBound = guessNum;
        } else {
            lowerBound = guessNum;
        }
    }

    @Override
    protected boolean onDeterminingIfGameOver(Player player) {
        return player.isCorrect();
    }

    @Override
    protected void onPrintGameResult(Player player) {
        System.out.println("the winner is " + player.getName());
    }

    protected void onRestart(List<Player> players) {
        resetPlayer(players);
        resetGame();
    }

    private void resetPlayer(List<Player> players){
        players.forEach(p -> p.setCorrect(false));
    }

    private void resetGame(){
        upperBound = 100;
        lowerBound = 0;
    }

    private String printRange(){
        return lowerBound + " - " + upperBound;
    }
}
