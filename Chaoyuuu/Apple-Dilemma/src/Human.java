/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Human {
    private final Apple apple;
    private Lifecycle lifecycle;

    public Human(Apple apple) {
        this.apple = apple;
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public void start() {
        for (int i = 0; i < 3; i++ ) {
            lifecycle.onBeforeEat();
            apple.eat();
            lifecycle.onAfterEat();
        }
    }


    public interface Lifecycle {
        void onBeforeEat();

        void onAfterEat();
    }
}
