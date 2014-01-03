package edu.mccc.cos102.checkers;
import com.cbthinkx.util.Debug;
import edu.mccc.cos102.interfaces.Player;
import edu.mccc.cos102.abstracts.AbstractBoard;
import edu.mccc.cos102.ds.DoublyLinkedList;
public class CheckersBoard extends AbstractBoard {
	private CheckersPosition position;
	//Initializes the board, calling init of the position
	public void initBoard(DoublyLinkedList<? extends Player> players) {
		Debug.println("Board began initializing");
		position = new CheckersPosition();
		position.initPosition(players);
		Debug.println("Board finished initializing");
	}
	//Returns the current position
	public CheckersPosition getPosition() {
		return position;
	}
}
