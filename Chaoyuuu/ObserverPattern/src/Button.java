public class Button {

	private OnClickListener onClickListener;

	public void setOnClickListener(OnClickListener onClickListener) {
		this.onClickListener = onClickListener;
	}

	public void click(int orderNum) {
		onClickListener.onClick(orderNum);
	}

}
