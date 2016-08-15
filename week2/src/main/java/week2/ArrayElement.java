package week2;

public class ArrayElement {

	private int position;
	private int value;
	
	public ArrayElement(int position, int value){
		this.position = position;
		this.value = value;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
