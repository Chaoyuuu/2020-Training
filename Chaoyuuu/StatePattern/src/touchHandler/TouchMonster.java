package touchHandler;

import item.Item;
import item.Monster;
import item.Player;

public class TouchMonster extends TouchHandler {
    public TouchMonster(TouchHandler next) {
        super(next);
    }

    @Override
    public void touch(Item item, Player player) {
        if (isMonster(item)) {
            player.updatePosition(player.getX(), player.getY());
        } else {
            super.touch(item, player);
        }

    }

    private boolean isMonster(Item item) {
        return item instanceof Monster;
    }
}
