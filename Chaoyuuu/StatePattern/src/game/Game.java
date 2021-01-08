package game;

import item.*;
import state.*;
import touchHandler.TouchHandler;
import touchHandler.TouchMonster;
import touchHandler.TouchObstacle;
import touchHandler.TouchTreasure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private static GameMap gameMap;
    private static final Random random = new Random();
    private static List<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        setUp();
        System.out.println(gameMap);
        startGame();
    }

    private static void setUp() {
        gameMap = new GameMap();
        TouchHandler touchHandler = initTouchHandler();
        initHero(touchHandler);
        initMonster(3, touchHandler);
        initTreasure(5);
        initObstacle(5);
    }

    private static void initHero(TouchHandler touchHandler) {
        Player hero = new Hero(new NormalState(), touchHandler);
        players.add(hero);
        addSpriteToGameMap(hero);
    }

    private static void initMonster(int numOfMonster, TouchHandler touchHandler) {
        for (int i = 0; i <= numOfMonster - 1; i++) {
            Monster monster = new Monster(new NormalState(), touchHandler, Character.toString((char) (i + 'A')));
            addSpriteToGameMap(monster);
            players.add(monster);
        }
    }

    private static TouchHandler initTouchHandler() {
        return new TouchMonster(new TouchObstacle(new TouchTreasure(null)));
    }

    private static void addSpriteToGameMap(Sprite sprite) {
        gameMap.addSprite(sprite);
        sprite.setRandomPosition();
    }

    private static void initTreasure(int numOfTreasure) {
        for (int i = 0; i < numOfTreasure; i++) {
            addTreasureInRandom();
        }
    }

    private static void addTreasureInRandom() {
        int num = random.nextInt(100);
        if (num < 10) {
            addSpriteToGameMap(new Treasure(new InvincibleState()));
        } else if (num < 35) {
            addSpriteToGameMap(new Treasure(new PoisonState()));
        } else if (num < 55) {
            addSpriteToGameMap(new Treasure(new AccelerateState()));
        } else if (num < 70) {
            addSpriteToGameMap(new Treasure(new RestoreState()));
        } else if (num < 80) {
            addSpriteToGameMap(new Treasure(new ChaosState()));
        } else if (num < 90) {
            addSpriteToGameMap(new Treasure(new AccumulateState()));
        } else {
            addSpriteToGameMap(new Treasure(new TeleportationState()));
        }
    }

    private static void initObstacle(int numOfObstacle) {
        for (int i = 0; i < numOfObstacle; i++) {
            addSpriteToGameMap(new Obstacle());
        }
    }

    private static void startGame() {
        while (!endGame()) {
            players.stream()
                    .filter(Player::isAlive)
                    .forEach(p -> {
                        p.onRoundBegins();
                        removeDeadPlayers();
                        System.out.println(gameMap);
                    });
        }
    }

    private static void removeDeadPlayers() {
        players.stream()
                .filter(p -> !p.isAlive())
                .forEach(p -> gameMap.removeSprite(p));
    }

    private static boolean endGame() {
        if (players.stream().filter(Player::isAlive).noneMatch(p -> p instanceof Monster)) {
            System.out.println("Hero Win !!!");
            return true;
        } else if (players.stream().filter(Player::isAlive).noneMatch(p -> p instanceof Hero)) {
            System.out.println("Hero loose !!!");
            return true;
        }
        return false;
    }

}
