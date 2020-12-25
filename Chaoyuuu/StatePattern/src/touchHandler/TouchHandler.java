package touchHandler;

import item.Item;
import item.Player;

public abstract class TouchHandler {
    private TouchHandler next;

    public TouchHandler(TouchHandler next) {
        this.next = next;
    }

    public void touch(Item item, Player player) {
        if (next != null) {
            next.touch(item, player);
        }
    }
}
