import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Kitchen implements OnClickListener {

    private Map<Integer, KitchenListener> kitchenListeners = new HashMap<>();

    public void cook(int orderNum) {
        Thread t = new Thread(() -> {
            System.out.println("cooking" + orderNum);
            KitchenListener kitchenListener = kitchenListeners.get(orderNum);
            try {
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kitchenListener.getOrderAndGoHome();
        });

        t.start();

    }

    public void setKitchenListener(KitchenListener kitchenListener, int orderNum) {
        kitchenListeners.put(orderNum, kitchenListener);
    }

    @Override
    public void onClick(int orderNum) {
        cook(orderNum);
    }
}
