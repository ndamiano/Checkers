package edu.mccc.cos102.ds.impl;
import java.util.Iterator;
import edu.mccc.cos102.ds.List;
import edu.mccc.cos102.ds.Sequence;
import edu.mccc.cos102.ds.Utility;
public class ListImpl<E> implements List<E>, java.lang.Iterable<E> {
	Sequence<E> sequence = null;
	public ListImpl() {
		try {
			this.sequence = Utility.getSequence();
		} catch (Exception ex) {
			System.err.println(ex);
			System.exit(-1);
		}
	}
	@Override
	public boolean add(E e) {
		return sequence.add(e);
	}
	@Override
	public boolean contains(Object o) {
		return sequence.contains(o);
	}
	@Override
	public java.util.Iterator<E> iterator() {
		return sequence.iterator();
	}
	@Override
	public boolean remove(Object o) {
		return sequence.remove(o);
	}
	@Override
	public int getSize() {
		return sequence.getSize();
	}
	@Override
	public boolean isEmpty() {
		return sequence.isEmpty();
	}
	@Override
	public String toString() {
		return sequence.toString();
	}
}
