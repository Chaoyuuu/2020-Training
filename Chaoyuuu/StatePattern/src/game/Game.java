package game;

import item.*;
import state.*;
import touchHandler.TouchHandler;
import touchHandler.TouchMonster;
import touchHandler.TouchObstacle;
import touchHandler.TouchTreasure;

import java.util.*;

public class Game {
    private static GameMap gameMap;
    private static Random random = new Random();

    public static void main(String[] args) {
        setUp();
        System.out.println(gameMap);
        startGame();
    }

    private static void setUp() {
        gameMap = new GameMap();
        initPlayer(3);
        initTreasure(5);
//        initObstacle(5);
    }

    private static void initPlayer(int numOfMonster) {
        TouchHandler touchHandler = initTouchHandler();
        new Hero(gameMap, new AccelerateState(), touchHandler);
        for (int i = 0; i < numOfMonster - 1; i++) {
            new Monster(gameMap, new AccelerateState(), touchHandler, Character.toString((char) (i + 65)));
        }
    }

    private static TouchHandler initTouchHandler() {
        return new TouchMonster(new TouchObstacle(new TouchTreasure(null)));
    }

    private static void initTreasure(int numOfTreasure) {
        for (int i = 0; i < numOfTreasure; i++) {
            setTreasureInRandom();
        }
    }

    private static void setTreasureInRandom() {
        int num = random.nextInt(100);
        if (num < 10) {
            new Treasure(gameMap, new InvincibleState());
        } else if (num < 35) {
            new Treasure(gameMap, new PoisonState());
        } else if (num < 55) {
            new Treasure(gameMap, new AccelerateState());
        } else if (num < 70) {
            new Treasure(gameMap, new RegainState());
        } else if (num < 80) {
            new Treasure(gameMap, new ChaosState());
        } else if (num < 90) {
            new Treasure(gameMap, new AccumulateState());
        } else if (num < 100) {
            new Treasure(gameMap, new TeleportState());
        }
    }

    private static void initObstacle(int num) {
        for (int i = 0; i < num; i++) {
            new Obstacle(gameMap);
        }
    }

    private static void startGame() {
        while (!endGame()) {
            gameMap.getPlayers().stream()
                    .filter(Objects::nonNull)
                    .forEach(m -> {
                        m.turn();
                        removeDeadPlayers();
                        System.out.println(gameMap);
                    });
        }
    }

    private static void removeDeadPlayers() {
        gameMap.getPlayers().stream()
                .filter(Objects::nonNull)
                .forEach(p -> {
                    if (p.getHP() <= 0) {
                        gameMap.removeItem(p);
                    }
                });
    }

    private static boolean endGame() {
        if (gameMap.getPlayers().stream().noneMatch(p -> p instanceof Monster)) {
            System.out.println("Hero Win !!!");
            return true;
        } else if (gameMap.getPlayers().stream().noneMatch(p -> p instanceof Hero)) {
            System.out.println("Hero loose !!!");
            return true;
        }
        return false;
    }

}
