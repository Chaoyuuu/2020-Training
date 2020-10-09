import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.max;

public class StartGame {
    private int turn = 0;
    private List<Player> players = new LinkedList<>();

    public StartGame() {
        players.add(createPlayer());
        players.add(createPlayer());
    }

    private Player getCurrentPlayer() {
        return players.get(turn % 2);
    }

    private Player getAnotherPlayer() {
        return players.get((turn + 1)% 2);
    }

    public void battle() {

        while (!gameOver()) {
            executeRound();
            turn += 1;
        }
        System.out.println(getAnotherPlayer().getPlayerName() + " Win !!!");
    }

    private boolean gameOver() {
        return getCurrentPlayer().getHP() == 0;
    }

    private void executeRound() {
        System.out.println(getCurrentPlayer());
        int totalInjury = getCurrentPlayer().attack();
        setDefensePlayerHP(getAnotherPlayer(), totalInjury);
    }

    private void setDefensePlayerHP(Player defensePlayer, int totalInjury) {
        int playerHP = defensePlayer.getHP();
        int defense = defensePlayer.myDefenseWeapon.getDefense(); //?????????
        int injury = max(0, totalInjury - defense);
        defensePlayer.setHP(playerHP - injury);
    }

    private static Player createPlayer() {
        Scanner in = new Scanner(System.in);

        System.out.print("請輸入名子: ");
        String playerName = in.nextLine();
        System.out.print("選擇角色 (1) 忍者 (2) 武士 (3) 拳皇 :");
        RoleFactory role = createRole(in.nextInt());
        return new Player(playerName, role);
    }

    private static RoleFactory createRole(int index) {
        switch (index) {
            case 1:
                return new Ninja();
            case 2:
                return new Warrior();
            case 3:
            default:
                return new Boxer();
        }
    }
}
