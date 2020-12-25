package item;

import game.GameMap;
import state.InvincibleState;
import state.State;
import touchHandler.TouchHandler;

import java.util.Random;


public class Monster extends Player {

    private final Integer[] attackRangeX = {1, 0, -1, 0};
    private final Integer[] attackRangeY = {0, 1, 0, -1};
    private final Random rand = new Random();
    private final String name;
    private boolean isAttack = false;
    public static final int MAX_HP = 1;

    public Monster(GameMap gameMap, State state, TouchHandler touchHandler, String name) {
        super(gameMap, state, touchHandler);
        this.HP = MAX_HP;
        this.name = name;
    }

    @Override
    public void turn() {
        System.out.println("monster " + name + " (" + this.state.getClass().getName() + ")");
        state.attack(this);
        if (!isAttack) {
            state.move(this);
        }
        isAttack = false;
    }

    @Override
    public void move() {
        System.out.print("- - monster " + name + " move ");
        int move_direction = rand.nextInt(4);
        switch (move_direction) {
            case 1:
                System.out.println("UP");
                nextMove(this.x, this.y - 1);
                break;
            case 2:
                System.out.println("DOWN");
                nextMove(this.x, this.y + 1);
                break;
            case 3:
                System.out.println("LEFT");
                nextMove(this.x - 1, this.y);
                break;
            case 0:
                System.out.println("RIGHT");
                nextMove(this.x + 1, this.y);
                break;
        }
    }

    @Override
    public void moveVertical() {
        System.out.print("- - monster " + name + " move ");
        int move_direction = rand.nextInt(2);
        switch (move_direction) {
            case 0:
                System.out.println("UP");
                nextMove(this.x, this.y - 1);
                break;
            case 1:
                System.out.println("DOWN");
                nextMove(this.x, this.y + 1);
                break;
        }
    }

    @Override
    public void moveHorizontal() {
        System.out.print("- - monster " + name + " move ");
        int move_direction = rand.nextInt(2);
        switch (move_direction) {
            case 0:
                System.out.println("LEFT");
                nextMove(this.x - 1, this.y);
                break;
            case 1:
                System.out.println("RIGHT");
                nextMove(this.x + 1, this.y);
                break;
        }
    }

    private void nextMove(int x, int y) {
        if (!isOutOfBound(x, y)) {
            Item item = gameMap.getTheItemByPosition(x, y);
            if (item == null) {
                updatePosition(x, y);
            } else {
                touchHandler.touch(item, this);
            }
        }
    }


    @Override
    public void attack() {
        for (int i = 0; i < attackRangeX.length; i++) {
            int x = this.x + attackRangeX[i];
            int y = this.y + attackRangeY[i];
            if (!isOutOfBound(x, y)) {
                Item item = gameMap.getTheItemByPosition(x, y);
                if (item instanceof Hero) {
                    ((Hero) item).minusHP(50);
                    ((Hero) item).setState(new InvincibleState());
                    isAttack = true;
                    System.out.println("- - monster " + name + " attack ");
                    break;
                }
            }
        }
    }

    @Override
    public void attackGlobal(int minusHP) {
        gameMap.getPlayers().stream()
                .filter(i -> i instanceof Hero)
                .forEach(h -> {
                    h.minusHP(minusHP);
                    h.setState(new InvincibleState());
                });
    }

    private boolean isOutOfBound(int x, int y) {
        return x >= GameMap.BOUNDARY || x < 0 || y >= GameMap.BOUNDARY || y < 0;
    }

    @Override
    public void addHP(int addHP) {
        this.HP += addHP;
        if (this.HP >= MAX_HP) {
            this.HP = MAX_HP;
        }
    }

    @Override
    public String toString() {
        return name + " ";
    }
}
