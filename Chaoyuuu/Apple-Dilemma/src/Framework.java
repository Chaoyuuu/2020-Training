
/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Framework implements Human.Lifecycle {

    private Apple apple;
    private StubApple stubApple;

    public Apple apple() {
        this.stubApple = new StubApple();
        this.stubApple.renewApple();
        return this.stubApple;
    }

    @Override
    public void onBeforeEat() {

    }

    @Override
    public void onAfterEat() {
        stubApple.renewApple();
    }

    public class StubApple extends Apple {

        public void renewApple() {
            apple = new Apple();
        }

        @Override
        public void eat() {
            apple.eat();
        }
    }


    public static void main(String[] args) {
        Framework framework = new Framework();
        Human human = new Human(framework.apple());
        human.setLifecycle(framework);
        human.start();

    }
}
