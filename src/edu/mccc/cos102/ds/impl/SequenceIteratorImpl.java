package edu.mccc.cos102.ds.impl;
import edu.mccc.cos102.ds.Sequence;
import edu.mccc.cos102.ds.Utility;
public class SequenceIteratorImpl<T> implements java.util.Iterator<T> {
	private Sequence<T> sequence;
	private int counter = 0;
	public SequenceIteratorImpl(Sequence<T> sequence) {
		this.sequence = sequence;
	}
	@Override
	public boolean hasNext() {
		if (counter >= sequence.getSize()) {
			return false;
		} else {
			counter++;
			return true;
		}
	}
	@Override
	public T next() {
		return sequence.get(counter - 1);
	}
	@Override
	public void remove() {
		sequence.remove(sequence.get(counter));
	}
	@Override
	public String toString() {
		return sequence.toString();
	}
}