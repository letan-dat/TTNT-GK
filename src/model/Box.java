package model;

public class Box implements IBox {
	private int index;
	protected int numberStone;

	public Box(int index) {
		super();
		this.index = index;
		if (index != 0 && index != 6) {
			this.numberStone = 5;
		} else {
			this.numberStone = 10;
		}
	}

	@Override
	public int getNumberStone() {
		return numberStone;
	}

	@Override
	public void setNumberStone(int numberStone) {
		this.numberStone = numberStone;
	}

	@Override
	public int getIndex() {
		return index;
	}
}
