/**
 * @author chaoyulee chaoyu2330@gmail.com
 */
public class Apple {
    private int eatCount = 0;
    public void eat() {
        if (eatCount ++ >= 1) {
            throw new IllegalStateException("Apple!! Only ONE!");
        }
        System.out.println("Eat...");
    }
}
