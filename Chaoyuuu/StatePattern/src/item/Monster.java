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

    public Monster(State state, TouchHandler touchHandler, String name) {
        super(state, touchHandler);
        this.HP = MAX_HP;
        this.name = name;
    }

    @Override
    public void onRoundBegins() {
        state.onRoundBegins(this);
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
                trySetPosition(this.x, this.y - 1);
                break;
            case 2:
                System.out.println("DOWN");
                trySetPosition(this.x, this.y + 1);
                break;
            case 3:
                System.out.println("LEFT");
                trySetPosition(this.x - 1, this.y);
                break;
            case 0:
                System.out.println("RIGHT");
                trySetPosition(this.x + 1, this.y);
                break;
        }
    }

    @Override
    public void moveVertically() {
        System.out.print("- - monster " + name + " move ");
        int move_direction = rand.nextInt(2);
        switch (move_direction) {
            case 0:
                System.out.println("UP");
                trySetPosition(this.x, this.y - 1);
                break;
            case 1:
                System.out.println("DOWN");
                trySetPosition(this.x, this.y + 1);
                break;
        }
    }

    @Override
    public void moveHorizontally() {
        System.out.print("- - monster " + name + " move ");
        int move_direction = rand.nextInt(2);
        switch (move_direction) {
            case 0:
                System.out.println("LEFT");
                trySetPosition(this.x - 1, this.y);
                break;
            case 1:
                System.out.println("RIGHT");
                trySetPosition(this.x + 1, this.y);
                break;
        }
    }

    private void trySetPosition(int x, int y) {
        if (GameMap.isWithinBoundary(x, y)) {
            gameMap.getSpriteByPositionOptional(x, y)
                    .ifPresentOrElse(s -> touchHandler.touch(s, this), () -> updatePosition(x, y));
        }
    }

    @Override
    public void attack() {
        for (int i = 0; i < attackRangeX.length; i++) {
            int x = this.x + attackRangeX[i];
            int y = this.y + attackRangeY[i];
            if (GameMap.isWithinBoundary(x, y)) {
                gameMap.getSpriteByPositionOptional(x, y)
                        .filter(s -> s instanceof Hero)
                        .ifPresent(s -> {
                            attackHero((Hero) s);
                            isAttack = true;
                            System.out.println("- - monster " + name + " attack ");
                        });
            }
        }
    }

    private void attackHero(Hero hero) {
        hero.damage(50);
        hero.setState(new InvincibleState());
    }

    @Override
    public void attackGlobal(int minusHP) {
        gameMap.getSprites().stream()
                .filter(i -> i instanceof Hero)
                .forEach(h -> {
                    ((Player) h).damage(minusHP);
                    ((Player) h).setState(new InvincibleState());
                });
    }


    @Override
    public String toString() {
        return name + " ";
    }
}
