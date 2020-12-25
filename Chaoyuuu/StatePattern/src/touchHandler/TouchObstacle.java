package touchHandler;

import item.Item;
import item.Obstacle;
import item.Player;

public class TouchObstacle extends TouchHandler {

    public TouchObstacle(TouchHandler next) {
        super(next);
    }

    @Override
    public void touch(Item item, Player player) {
        if (isObstacle(item)) {
            player.updatePosition(player.getX(), player.getY());
        } else {
            super.touch(item, player);
        }

    }

    private boolean isObstacle(Item item) {
        return item instanceof Obstacle;
    }
}
