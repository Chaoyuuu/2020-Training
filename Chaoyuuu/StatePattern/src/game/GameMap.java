package game;

import item.Sprite;

import java.util.*;

public class GameMap {

    public static final int BOUNDARY = 5;
    private Set<Sprite> sprites = new HashSet<>();
    private Sprite[][] map = new Sprite[BOUNDARY][BOUNDARY];

    public void setPositionOnMap(int x, int y, Sprite sprite) {
        map[x][y] = sprite;
    }

    public void removePositionOnMap(int x, int y) {
        map[x][y] = null;
    }

    public Optional<Sprite> getSpriteByPositionOptional(int x, int y) {
        return Optional.ofNullable(map[x][y]);

    }
    public Sprite getSpriteByPosition(int x, int y) {
        return map[x][y];
    }

    public List<Sprite> getSpritesByCol(int col) {
        return Arrays.asList(map[col].clone());
    }

    public List<Sprite> getSpritesByRow(int row) {
        List<Sprite> sprites = new ArrayList<>();
        for (int i = 0; i < BOUNDARY; i++ ) {
            sprites.add(getSpriteByPosition(i, row));
        }
        return sprites;
    }

    public static boolean isWithinBoundary(int x, int y) {
        return x < GameMap.BOUNDARY && x >= 0 && y < GameMap.BOUNDARY && y >= 0;
    }


    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
        sprite.setGameMap(this);
    }

    public void removeSprite(Sprite sprite) {
        removePositionOnMap(sprite.getX(), sprite.getY());
        sprites.remove(sprite);
    }

    public Set<Sprite> getSprites() {
        return sprites;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  0 1 2 3 4 5 6 7 8 9 \n");

        for (int i = 0; i < BOUNDARY; i++) {
            stringBuilder.append(i).append(" ");
            for (int k = 0; k < BOUNDARY; k++) {
                Sprite sprite = map[k][i];
                if (sprite == null) {
                    stringBuilder.append("- ");
                } else {
                    stringBuilder.append(sprite.toString());
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
