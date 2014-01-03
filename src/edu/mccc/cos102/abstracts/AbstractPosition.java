package edu.mccc.cos102.abstracts;
import java.util.Observable;
import edu.mccc.cos102.ds.DoublyLinkedList;
import edu.mccc.cos102.interfaces.Action;
import edu.mccc.cos102.interfaces.Position;
import edu.mccc.cos102.interfaces.Player;
public abstract class AbstractPosition extends Observable implements Position {
	public void initPosition(DoublyLinkedList<? extends Player> players) {
	}
	public void update(Action action) {
	}
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}