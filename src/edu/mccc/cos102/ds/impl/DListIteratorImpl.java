package edu.mccc.cos102.ds.impl;
import edu.mccc.cos102.ds.DoublyLinkedList;
import edu.mccc.cos102.ds.Utility;
public class DListIteratorImpl<T> implements java.util.Iterator<T> {
	private DoublyLinkedList<T> dll;
	private int counter = 0;
	public DListIteratorImpl(DoublyLinkedList<T> dll) {
		this.dll = dll;
	}
	@Override
	public boolean hasNext() {
		if (counter >= dll.getSize()) {
			return false;
		} else {
			counter++;
			return true;
		}
	}
	@Override
	public T next() {
		if (counter == 1) {
			return dll.getFirst();
		} else {
			return dll.getNext();
		}
	}
	@Override
	public void remove() {
		if (counter == dll.getSize()) {
			dll.removeLast();
		} else {
			dll.getNext();
			dll.removePrevious();
		}
	}
	@Override
	public String toString() {
		return dll.toString();
	}
}
