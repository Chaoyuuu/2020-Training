package touchHandler;

import item.Player;
import item.Sprite;

public abstract class TouchHandler {
    private TouchHandler next;

    public TouchHandler(TouchHandler next) {
        this.next = next;
    }

    public void touch(Sprite sprite, Player player) {
        if (match(sprite, player)) {
            handle(sprite, player);
        } else {
            if (next != null) {
                next.touch(sprite, player);
            }
        }
    }

    protected abstract boolean match(Sprite sprite, Player player);

    protected abstract void handle(Sprite sprite, Player player);
}
