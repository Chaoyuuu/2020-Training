package item;

import game.GameMap;
import state.State;
import touchHandler.TouchHandler;

import java.util.List;
import java.util.Scanner;

public class Hero extends Player {

    private Direction direction;
    private final Scanner in = new Scanner(System.in);
    public static final int MAX_HP = 300;

    public Hero(State state, TouchHandler touchHandler) {
        super(state, touchHandler);
        this.HP = MAX_HP;
        this.direction = Direction.UP;
    }

    @Override
    public void onRoundBegins() {
        state.onRoundBegins(this);
    }

    @Override
    public void turn() {
        System.out.println("Hero HP = " + HP + " (" + this.state.getClass().getName() + ")");
        System.out.print("Hero choose ur step, (1) move, (2) attack : ");
        switch (in.nextInt()) {
            case 1:
                state.move(this);
                break;
            case 2:
                state.attack(this);
                break;
            default:
                System.err.println("Input error");
        }
    }

    @Override
    public void move() {
        System.out.print("Hero choose ur direction, (1) ↑, (2)→, (3)↓, (4)← : ");
        switch (in.nextInt()) {
            case 1:
                trySetPosition(this.x, this.y - 1, Direction.UP);
                break;
            case 2:
                trySetPosition(this.x + 1, this.y, Direction.RIGHT);
                break;
            case 3:
                trySetPosition(this.x, this.y + 1, Direction.DOWN);
                break;
            case 4:
                trySetPosition(this.x - 1, this.y, Direction.LEFT);
                break;
            default:
                System.out.println("Input Error");
        }
    }

    @Override
    public void moveVertically() {
        System.out.print("Hero choose ur direction, (1) ↑, (2) ↓ :");
        switch (in.nextInt()) {
            case 1:
                trySetPosition(this.x, this.y - 1, Direction.UP);
                break;
            case 2:
                trySetPosition(this.x, this.y + 1, Direction.DOWN);
                break;
        }
    }

    @Override
    public void moveHorizontally() {
        System.out.print("Hero choose ur direction, (1) →, (2) ← :");
        switch (in.nextInt()) {
            case 1:
                trySetPosition(this.x + 1, this.y, Direction.RIGHT);
                break;
            case 2:
                trySetPosition(this.x - 1, this.y, Direction.LEFT);
                break;
        }
    }

    private void trySetPosition(int x, int y, Direction direction) {
        this.direction = direction;
        if (GameMap.isWithinBoundary(x, y)) {
            gameMap.getSpriteByPositionOptional(x, y)
                    .ifPresentOrElse(s -> touchHandler.touch(s, this),
                            () -> updatePosition(x, y));
        }
    }

    @Override
    public void attack() {
        switch (this.direction) {
            case UP:
                attackForwardSprite(gameMap.getSpritesByCol(this.x), this.y, -1);
                break;
            case DOWN:
                attackForwardSprite(gameMap.getSpritesByCol(this.x), this.y, 1);
                break;
            case LEFT:
                attackForwardSprite(gameMap.getSpritesByRow(this.y), this.x, -1);
                break;
            case RIGHT:
                attackForwardSprite(gameMap.getSpritesByRow(this.y), this.x, 1);
                break;
        }
    }

    private void attackForwardSprite(List<Sprite> sprites, int currentPos, int offset) {
        int delta = currentPos - GameMap.BOUNDARY;
        for (int i = 1; i < Math.abs(delta); i++) {
            Sprite sprite = sprites.get(currentPos + i * offset);
            if (sprite instanceof Obstacle) {
                break;
            } else if (sprite instanceof Monster) {
                ((Monster) sprite).damage(50);
            }
        }
    }

    @Override
    public void attackGlobal(int minusHP) {
        gameMap.getSprites().stream()
                .filter(m -> m instanceof Monster)
                .forEach(m -> ((Player)m).damage(minusHP));
    }

    @Override
    public String toString() {
        switch (this.direction) {
            case RIGHT:
                return "→ ";
            case LEFT:
                return "← ";
            case DOWN:
                return "↓ ";
            case UP:
                return "↑ ";
        }
        return null;
    }

}
