package item;

import game.GameMap;
import state.State;
import touchHandler.TouchHandler;

import java.util.Scanner;

public class Hero extends Player {

    private Direction direction;
    private final Scanner in = new Scanner(System.in);
    public static final int MAX_HP = 300;

    public Hero(GameMap gameMap, State state, TouchHandler touchHandler) {
        super(gameMap, state, touchHandler);
        this.HP = MAX_HP;
        this.direction = Direction.UP;
    }

    @Override
    public void turn() {
        System.out.println("Hero HP = " + HP + " (" + this.state.getClass().getName() + ")");
        System.out.print("Hero choose ur step, (1) move, (2) attack :");
        switch (in.nextInt()) {
            case 1:
                state.move(this);
                break;
            case 2:
                state.attack(this);
                break;
            default:
                System.out.println("Input error");
        }
    }

    @Override
    public void move() {
        System.out.print("Hero choose ur direction, (1) ↑, (2)→, (3)↓, (4)← :");
        switch (in.nextInt()) {
            case 1:
                nextMove(this.x, this.y - 1, Direction.UP);
                break;
            case 2:
                nextMove(this.x + 1, this.y, Direction.RIGHT);
                break;
            case 3:
                nextMove(this.x, this.y + 1, Direction.DOWN);
                break;
            case 4:
                nextMove(this.x - 1, this.y, Direction.LEFT);
                break;
            default:
                System.out.println("Input Error");
        }
    }

    @Override
    public void moveVertical() {
        System.out.print("Hero choose ur direction, (1) ↑, (2) ↓ :");
        switch (in.nextInt()) {
            case 1:
                nextMove(this.x, this.y - 1, Direction.UP);
                break;
            case 2:
                nextMove(this.x, this.y + 1, Direction.DOWN);
                break;
        }
    }

    @Override
    public void moveHorizontal() {
        System.out.print("Hero choose ur direction, (1) →, (2) ← :");
        switch (in.nextInt()) {
            case 1:
                nextMove(this.x + 1, this.y, Direction.RIGHT);
                break;
            case 2:
                nextMove(this.x - 1, this.y, Direction.LEFT);
                break;
        }
    }

    private void nextMove(int x, int y, Direction direction) {
        this.direction = direction;
        if (!isOutOfBound(x, y)) {
            Item item = gameMap.getTheItemByPosition(x, y);
            if (item == null) {
                updatePosition(x, y);
            } else {
                touchHandler.touch(item, this);
            }
        }
    }

    //TODO :remove to static method in GameMap
    private boolean isOutOfBound(int x, int y) {
        return x >= GameMap.BOUNDARY || x < 0 || y >= GameMap.BOUNDARY || y < 0;
    }

    @Override
    public void attack() {
        int attackItemPosX = this.x;
        int attackItemPosY = this.y;
        Item attackItem = gameMap.getTheItemByPosition(attackItemPosX, attackItemPosY);

        switch (this.direction) {
            case UP:
                while (launchAttack(attackItem) && !isOutOfBound(attackItemPosX, --attackItemPosY)) {
                    attackItem = gameMap.getTheItemByPosition(attackItemPosX, attackItemPosY);
                }
                break;
            case DOWN:
                while (launchAttack(attackItem) && !isOutOfBound(attackItemPosX, ++attackItemPosY)) {
                    attackItem = gameMap.getTheItemByPosition(attackItemPosX, attackItemPosY);
                }
                break;
            case LEFT:
                while (launchAttack(attackItem) && !isOutOfBound(--attackItemPosX, attackItemPosY)) {
                    attackItem = gameMap.getTheItemByPosition(attackItemPosX, attackItemPosY);
                }
                break;
            case RIGHT:
                while (launchAttack(attackItem) && !isOutOfBound(++attackItemPosX, attackItemPosY)) {
                    attackItem = gameMap.getTheItemByPosition(attackItemPosX, attackItemPosY);
                }
                break;
        }
    }

    @Override
    public void attackGlobal(int minusHP) {
        gameMap.getPlayers().stream()
                .filter(m -> m instanceof Monster)
                .forEach(m -> m.minusHP(minusHP));
    }

    private boolean launchAttack(Item item) {
        if (item instanceof Obstacle) {
            return false;
        } else if (item instanceof Monster) {
            ((Monster) item).minusHP(50);
        }
        return true;
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
