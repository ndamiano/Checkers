package edu.mccc.cos102.ds;
public interface DoublyLinkedList<E> {
	E getFirst();
	E getLast();
	E getNext();
	E getPrevious();
	void addFirst(E element);
	void addLast(E element);
	void addNext(E element);
	void addPrevious(E element);
	void removeFirst();
	void removeLast();
	void removeNext();
	void removePrevious();
	int getSize();
	java.util.Iterator<E> iterator();
	boolean isEmpty();
}
