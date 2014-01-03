package edu.mccc.cos102.checkers;
import edu.mccc.cos102.abstracts.AbstractElement;
public class CheckersElement extends AbstractElement {
	public static final int BLANK_SPACE = 0;
	public static final int BLACK_DISC = 1;
	public static final int BLACK_KING = 3;
	public static final int RED_DISC = 2;
	public static final int RED_KING = 4;
	private boolean isSelected;
	private int type;
	public CheckersElement(int type){
		this.type = type;
		isSelected = false;
	}
	public int getType() {
		return type;
	}
	public void select() {
		isSelected = true;
		setChanged();
		notifyObservers();
	}
	public void deselect() {
		isSelected = false;
		setChanged();
		notifyObservers();
	}
	public void setType(int type) {
		this.type = type;
		setChanged();
		notifyObservers();
	}
	public void makeKing() {
		type = type + 2;
		setChanged();
		notifyObservers();
	}
	public boolean isKing() {
		boolean temp;
		if (type == BLACK_KING || type == RED_KING) {
			temp = true;
		} else {
			temp = false;
		}
		return temp;
	}
	public int whoOwns() {
		int temp;
		if (isKing()) {
			temp = type - 2;
		} else {
			temp = type;
		}
		return temp;
	}
}