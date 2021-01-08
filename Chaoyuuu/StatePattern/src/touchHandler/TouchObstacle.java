package touchHandler;

import item.Obstacle;
import item.Player;
import item.Sprite;

public class TouchObstacle extends TouchHandler {

    public TouchObstacle(TouchHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Sprite sprite, Player player) {
        return sprite instanceof Obstacle;
    }

    @Override
    protected void handle(Sprite sprite, Player player) {
        player.updatePosition(player.getX(), player.getY());
    }

}
