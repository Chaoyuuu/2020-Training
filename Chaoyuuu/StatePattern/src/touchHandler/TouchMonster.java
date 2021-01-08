package touchHandler;

import item.Sprite;
import item.Monster;
import item.Player;

public class TouchMonster extends TouchHandler {
    public TouchMonster(TouchHandler next) {
        super(next);
    }

    @Override
    protected boolean match(Sprite sprite, Player player) {
        return sprite instanceof Monster;
    }

    @Override
    protected void handle(Sprite sprite, Player player) {
        player.updatePosition(player.getX(), player.getY());
    }
}
