package edu.mccc.cos102.ds.impl;
import java.util.Iterator;
import edu.mccc.cos102.ds.Sequence;
import edu.mccc.cos102.ds.Utility;
import edu.mccc.cos102.ds.Array;
public class SequenceImpl<E> implements Sequence<E>, java.lang.Iterable<E> {
	private Array<E> array, tempArray;
	private Sequence<E> subSequence;
	private final int arrayStartSize = 10;
	private int currentArraySize = arrayStartSize;
	private int size = 0;
	public SequenceImpl() {
		try {
			array = Utility.getArray(arrayStartSize);
		} catch (Exception ex) {
			System.err.println("Bad Array file");
			System.exit(-1);
		}
	}
	@Override
	public boolean add(E e) {
		if (needsToBeBigger()) {
			makeBigger();
		}
		try {
			array.set(size, e);
			size++;
			return true;
		} catch (IndexOutOfBoundsException ex) {
			return false;
		}
	}
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (needsToBeBigger()) {
			makeBigger();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = size; i > index; i--) {
			array.set(i, array.get(i-1));
		}
		array.set(index, element);
		size++;
	}
	@Override
	public boolean contains(Object o) {
		for (int i = 0 ; i < size ; i++) {
			if (array.get(i).equals(o)) {
				return true;
			}
		}
		return false;

	}
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0 || isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		try {
			return array.get(index);
		} catch (IndexOutOfBoundsException ex) {
			throw ex;
		}
	}
	@Override
	public int indexOf(Object o) {
		for (int i = 0 ; i < size ; i++) {
			if (array.get(i) == o) {
				return i;
			}
		}
		return -1;
	}
	@Override
	public java.util.Iterator<E> iterator() {
		try {
			return Utility.getIterator(this);
		} catch (Exception ex) {
		}
		return null;
	}
	@Override
	public int lastIndexOf(Object o) {
		for (int i = size - 1; i >= 0; i++) {
			if (array.get(i) == o) {
				return i;
			}
		}
		return -1;
	}
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index >= size || index < 0 || isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		E element = array.get(index);
		for (int i = index; i < size; i++) {
			array.set(i, array.get(i+1));
		}
		size--;
		return element;
	}
	@Override
	public boolean remove(Object o) {
		if (this.contains(o)){
			E e = this.remove(indexOf(o));
			return true;
		}
		return false;
	}
	@Override
	public E set(int index, E element) throws IndexOutOfBoundsException {
		if (index >= size || index < 0 || isEmpty()) {
			throw new IndexOutOfBoundsException();
		}
		E e = array.get(index);
		try {
			array.set(index, element);
		} catch (IndexOutOfBoundsException ex) {
			throw ex;
		}
		return e;
	}
	@Override
	public int getSize() {
		return this.size;
	}
	@Override
	public boolean isEmpty() {
		return (this.size == 0);
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		int i = 0;
		while (array.get(i) != null) {
			if (array.get(i) != null) {
				sb.append(array.get(i).toString() + " ");
			} else {
				sb.append("null ");
			}
			i++;
		}
		sb.append("]");
		return sb.toString();
	}
	@Override
	public Sequence<E> subSequence(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
		if (fromIndex > toIndex) {
			return null;
		}
		if (fromIndex >= size || toIndex >= size) {
			throw new IndexOutOfBoundsException();
		}
		try {
			subSequence = Utility.getSequence();
		} catch (Exception ex) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = fromIndex; i < toIndex; i++) {
			subSequence.add(array.get(i));
		}
		return subSequence;
	}
	private boolean needsToBeBigger() {
		return (size + 5 >= currentArraySize);
	}
	private void makeBigger() {
		try {
			tempArray = Utility.getArray(array.getSize());
		} catch (Exception ex) {
			System.err.println("Bad Array file");
			System.exit(-1);
		}
		for (int i = 0; i < currentArraySize; i++) {
			tempArray.set(i, array.get(i));
		}
		currentArraySize *= 3;
		try {
			array = Utility.getArray(currentArraySize);
		} catch (Exception ex) {
			System.err.println("Bad Array file");
			System.exit(-1);
		}
		for (int i = 0; i < tempArray.getSize(); i++) {
			array.set(i, tempArray.get(i));
			tempArray.set(i, null);
		}
	}
}
