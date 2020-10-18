import java.util.List;
import java.util.Scanner;

public abstract class GameContainer<T, G> {

    private List<T> players;
    private T currentPlayer;
    private int next;
    private Scanner scanner = new Scanner(System.in);

    public GameContainer(List<T> players) {
        this.players = players;
    }

    public void startGame() {
        onCreate();
        do {
            onRestart(players);
            onConfig();
            next = 0;
            while (!onDeterminingIfGameOver(currentPlayer)) {
                onOneRound();
                onEachPlayerTurn();
                next++;
            }
            onPrintGameResult(currentPlayer);
        } while (onDecideReplay());
    }


    // hook
    protected void onCreate() {
        System.out.println("start game");
    }

    protected void onConfig() {
        this.currentPlayer = players.get(0);
    }

    protected void onOneRound() {
        int size = players.size();
        this.currentPlayer = players.get(next % size);
    }

    public void onEachPlayerTurn() {
        G playerAction = onPlayerAction(currentPlayer);
        onExecuteAction(playerAction, currentPlayer);
    }

    public boolean onDecideReplay() {
        System.out.println("Replay (Y/N)?");
        return scanner.next().equals("Y");
    }

    protected T getDefensePlayer(){
        int size = players.size();
        return players.get((next + 1) % size);
    }

    /*
    protected boolean onStart() {
        System.out.println("Start Game (Y/N)?");
        Scanner scanner = new Scanner(System.in);
        return scanner.next().equals("Y");
    }

    protected abstract void onRestart();
    */

    protected abstract G onPlayerAction(T player);

    protected abstract void onExecuteAction(G playerAction, T player);

    protected abstract boolean onDeterminingIfGameOver(T player);

    protected abstract void onPrintGameResult(T player);

    protected abstract void onRestart(List<T> players);

}
