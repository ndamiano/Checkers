package edu.mccc.cos102.interfaces;
import edu.mccc.cos102.ds.DoublyLinkedList;
public interface Board {
	void initBoard(DoublyLinkedList<? extends Player> players);
	Position getPosition();
}