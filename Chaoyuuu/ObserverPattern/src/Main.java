public class Main {
    public static void main(String[] args) {
        Button button = new Button();
        Kitchen kitchen = new Kitchen();
        button.setOnClickListener(kitchen);


        for (int i = 0; i < 3; i++) {
            Customer customer = new Customer(button, kitchen);
            kitchen.setKitchenListener(customer, i);
            customer.order(i);
        }



    }
}
