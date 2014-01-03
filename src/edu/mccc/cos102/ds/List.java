package edu.mccc.cos102.ds;
import edu.mccc.cos102.ds.List;
public interface List<E> extends java.lang.Iterable<E> {
	boolean add(E e);
	boolean contains(Object o);
	java.util.Iterator<E> iterator();
	boolean remove(Object o);
	int getSize();
	boolean isEmpty();
}
