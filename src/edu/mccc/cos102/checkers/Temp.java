package edu.mccc.cos102.checkers;
import java.util.Iterator;
import edu.mccc.cos102.ds.List;
import edu.mccc.cos102.ds.Utility;
import edu.mccc.cos102.ds.DoublyLinkedList;
import edu.mccc.cos102.abstracts.AbstractPlayer;
import edu.mccc.cos102.interfaces.Player;
public class Temp {
	DoublyLinkedList<Player> dll;
	List<CheckersAction> list;
	public Temp() {
		try {
			list = Utility.getList();
			dll = Utility.getDoublyLinkedList();
		} catch (Exception ex) {
			return;
		}
		dll.addFirst(new CheckersPlayer());
		dll.addNext(new CheckersPlayer());
		CheckersGame game = new CheckersGame();
		game.initGame(dll);
		CheckersView view = new CheckersView(game);
	}
	public static void main(String[] sa) {
		new Temp();
	}
}