package game;

import item.Item;
import item.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameMap {

    public static final int BOUNDARY = 4;
    private Set<Item> items = new HashSet<>();
    private List<Player> players = new ArrayList<>();
    private Item[][] map = new Item[BOUNDARY][BOUNDARY];

    public GameMap() {
        initMap();
    }

    private void initMap() {
        for (int i = 0; i < BOUNDARY; i++) {
            for (int k = 0; k < BOUNDARY; k++) {
                map[i][k] = null;
            }
        }
    }

    public void setPositionOnMap(int x, int y, Item item) {
        map[x][y] = item;
    }

    public void removePositionOnMap(int x, int y) {
        map[x][y] = null;
    }

    public Item getTheItemByPosition(int x, int y) {
        return map[x][y];
    }

    public void addItem(Item item) {
        setPositionOnMap(item.getX(), item.getY(), item);
        items.add(item);
        if (item instanceof Player) {
            players.add((Player) item);
        }
    }

    public void removeItem(Item item) {
        removePositionOnMap(item.getX(), item.getY());
        items.remove(item);
        if (item instanceof Player) {
            int index = players.indexOf(item);
            players.set(index, null);
        }
    }



    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  0 1 2 3 4 5 6 7 8 9 \n");

        for (int i = 0; i < BOUNDARY; i++) {
            stringBuilder.append(i).append(" ");
            for (int k = 0; k < BOUNDARY; k++) {
                Item item = map[k][i];
                if (item == null) {
                    stringBuilder.append("- ");
                } else {
                    stringBuilder.append(item.toString());
                }
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
