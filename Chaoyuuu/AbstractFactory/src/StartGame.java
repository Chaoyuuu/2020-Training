import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.max;

public class StartGame {

    List<Player> players = new LinkedList<>();

    public StartGame() {
        players.add(creatPlayer());
        players.add(creatPlayer());
    }

    public void battle() {
        int n = 0;
        while (gameOver(players.get(n % 2))) {
            round(players.get(n % 2), players.get((n + 1) % 2));
            n += 1;
        }
        System.out.println(players.get((n + 1) % 2).getPlayerName() + " Win !!!");
    }

    private boolean gameOver(Player player) {
        if (player.getHP() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private void round(Player attackPlayer, Player defensePlayer) {
        System.out.println(attackPlayer);
        int totalInjury = attackPlayer.attack();
        setDefensePlayerHP(defensePlayer, totalInjury);
    }

    private void setDefensePlayerHP(Player defensePlayer, int totalInjury) {
        int playerHP = defensePlayer.getHP();
        int defense = defensePlayer.myDefenseWeapon.getDefense(); //?????????
        int injury = max(0, totalInjury - defense);
        defensePlayer.setHP(playerHP - injury);
    }

    private static Player creatPlayer() {
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
