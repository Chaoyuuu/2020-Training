public class Customer implements KitchenListener {

	private Button button;
	private Kitchen kitchen;

	public Customer(Button button, Kitchen kitchen) {
		this.button = button;
		this.kitchen = kitchen;
	}

	public void order(int orderNum) {
		button.click(orderNum);
	}

	public void goHome() {
		System.out.println("go home");
	}

	@Override
	public void getOrderAndGoHome() {
		this.goHome();
	}
}
