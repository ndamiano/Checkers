package edu.mccc.cos102.ds.impl;
import edu.mccc.cos102.ds.DoublyLinkedList;
import edu.mccc.cos102.ds.Utility;
public class DoublyLinkedListImpl<E> implements DoublyLinkedList<E>, java.lang.Iterable<E> {
	private DNode<E> head;
	private DNode<E> tail;
	private DNode<E> current;
	private int size = 0;
	public DoublyLinkedListImpl() {
		this.head = null;
		this.tail = null;
		this.current = null;
	}
	@Override
	public E getFirst() throws java.util.NoSuchElementException {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.head;
		return this.current.element;
	}
	@Override
	public E getLast() throws java.util.NoSuchElementException {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.tail;
		return this.tail.element;
	}
	@Override
	public E getNext() throws java.util.NoSuchElementException {
		if (isEmpty() || this.current.next == null) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.current.next;
		E element = this.current.element;
		return element;
	}
	@Override
	public E getPrevious() throws java.util.NoSuchElementException {
		if (isEmpty() || this.current.previous == null) {
			throw new java.util.NoSuchElementException();
		}
		this.current = this.current.previous;
		E element = this.current.element;
		return element;
	}
	@Override
	public void addFirst(E element) {
		DNode<E> node = new DNode<E>(element);
		node.next = this.head;
		if (this.head != null) {
			this.head.previous = node;
		}
		this.head = node;
		this.size++;
		this.current = this.head;
		if (this.size == 1) {
			this.tail = node;
		}
	}
	@Override
	public void addLast(E element) {
		if (isEmpty()) {
			addFirst(element);
			return;
		}
		DNode<E> last = this.tail;
		DNode<E> node = new DNode<E>(element);
		last.next = node;
		node.previous = last;
		this.tail = node;
		this.current = node;
		this.size++;
	}
	@Override
	public void addNext(E element) throws java.util.NoSuchElementException {
		if (this.current == null || this.size == 0) {
			throw new java.util.NoSuchElementException();
		}
		if (this.current.next == null) {
			addLast(element);
			return;
		}
		DNode<E> node = new DNode<E>(element);
		DNode<E> node2 = this.current.next;
		node.previous = this.current;
		node.next = node2;
		node2.previous = node;
		this.current.next = node;
		this.current = node;
		this.size++;
		if (this.size == 1) {
			this.head = node;
			this.tail = node;
		}
	}
	@Override
	public void addPrevious(E element) throws java.util.NoSuchElementException {
		if (this.current == null || this.size == 0) {
			throw new java.util.NoSuchElementException();
		} else if (this.size == 1) {
			addFirst(element);
			return;
		}
		DNode<E> node = new DNode<E>(element);
		if (this.current.previous != null) {
			DNode<E> last = this.current.previous;
			last.next = node;
			node.previous = last;
		} else {
			node.previous = null;
		}
		node.next = this.current;
		this.current.previous = node;
		this.current = node;
		this.size++;
	}
	@Override
	public void removeFirst() throws java.util.NoSuchElementException {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		DNode<E> node = this.head;
		this.head = this.head.next;
		node.reset();
		this.size--;
		if (isEmpty()) {
			this.tail = null;
		}
		this.current = this.head;
	}
	@Override
	public void removeLast() throws java.util.NoSuchElementException {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		} else if (getSize() == 1) {
			removeFirst();
			return;
		}
		DNode<E> node = this.tail;
		this.tail = this.tail.previous;
		node.reset();
		this.size--;
		if (isEmpty()) {
			this.head = null;
		}
		this.current = this.tail;
		this.current.next = null;
	}
	@Override
	public void removeNext() throws java.util.NoSuchElementException {
		if (isEmpty() || this.current.next == null) {
			throw new java.util.NoSuchElementException();
		}
		DNode<E> node = this.current.next;
		this.current.next = node.next;
		node.reset();
		if (this.current.next == null) {
			this.tail = this.current;
		}
		this.size--;
	}
	@Override
	public void removePrevious() throws java.util.NoSuchElementException {
		if (isEmpty() || this.current.previous == null) {
			throw new java.util.NoSuchElementException();
		}
		if (this.current.previous == this.head) {
			removeFirst();
			return;
		}
		DNode<E> node = this.current.previous;
		this.current.previous = node.previous;
		node.previous.next = this.current;
		if (this.current.previous == null) {
			this.head = this.current;
		} else {
			this.current = node.previous;
		}
		node.reset();
		this.size--;
	}
	@Override
	public int getSize() {
		return this.size;
	}
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("< ");
		DNode<E> node = head;
		while (node != null) {
			if (node.element != null) {
				sb.append(node.element.toString() + " ");
			} else {
				sb.append("null ");
			}
			node = node.next;
		}
		sb.append(">");
		return sb.toString();
	}
	public java.util.Iterator<E> iterator() {
		try {
			return Utility.getDLLIterator(this);
		} catch (Exception ex) {
		}
		return null;
	}
	private class DNode<T> {
		private T element;
		private DNode<T> next = null;
		private DNode<T> previous = null;
		public DNode(T element) {
			this.element = element;
		}
		private void reset() {
			this.element = null;
			this.next = null;
			this.previous = null;
		}
	}
}
