package edu.mccc.cos102.interfaces;
import edu.mccc.cos102.ds.DoublyLinkedList;
public interface Position extends Cloneable {
	void initPosition(DoublyLinkedList<? extends Player> players);
	void update(Action action);
	Object clone() throws CloneNotSupportedException;
}