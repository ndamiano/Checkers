package edu.mccc.cos102.abstracts;
import java.util.Observable;
import edu.mccc.cos102.interfaces.Board;
import edu.mccc.cos102.interfaces.Player;
import edu.mccc.cos102.interfaces.Position;
import edu.mccc.cos102.ds.DoublyLinkedList;
public abstract class AbstractBoard extends Observable implements Board {
	protected Position position;
	public void initBoard(DoublyLinkedList<? extends Player> players) {
	}
	public Position getPositon() {
		return position;
	}
}