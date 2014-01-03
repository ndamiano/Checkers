package edu.mccc.cos102.ds;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
public class Utility {
	private static java.util.Properties properties = new java.util.Properties();
	static {
		try {
			properties.load(new FileInputStream("dsimpl.properties"));
		} catch (Exception ex) {
			System.err.println("Bad Property File");
			System.exit(-1);
		}
	}
	public static <E> Array<E> getArray(int size) throws Exception {
		if (size <= 0) {
			throw new IllegalArgumentException("Bad Array Size Requested");
		}
		Class<?> clazz = Class.forName(properties.getProperty("array"));
		Constructor<?> ctor = clazz.getConstructor(int.class);
		@SuppressWarnings("unchecked")
		Array<E> array = (Array<E>) ctor.newInstance(size);
		return array;
	}
	public static <E> DoublyLinkedList<E> getDoublyLinkedList() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("dlist"));
		@SuppressWarnings("unchecked")
		DoublyLinkedList<E> dllist = (DoublyLinkedList<E>) clazz.newInstance();
		return dllist;
	}
	public static <E> java.util.Iterator<E> getDLLIterator(DoublyLinkedList<E> dll) throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("dlliterator"));
		Constructor<?> ctor = clazz.getConstructor(DoublyLinkedList.class);
		@SuppressWarnings("unchecked")
		java.util.Iterator<E> iterator = (java.util.Iterator<E>) ctor.newInstance(dll);
		return iterator;
	}
	public static <E> List<E> getList() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("list"));
		@SuppressWarnings("unchecked")
		List<E> list = (List<E>) clazz.newInstance();
		return list;
	}
	public static <E> Sequence<E> getSequence() throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("sequence"));
		@SuppressWarnings("unchecked")
		Sequence<E> sequence = (Sequence<E>) clazz.newInstance();
		return sequence;
	}
	public static <E> java.util.Iterator<E> getIterator(Sequence<E> sequence) throws Exception {
		Class<?> clazz = Class.forName(properties.getProperty("sequenceiterator"));
		Constructor<?> ctor = clazz.getConstructor(Sequence.class);
		@SuppressWarnings("unchecked")
		java.util.Iterator<E> iterator = (java.util.Iterator<E>) ctor.newInstance(sequence);
		return iterator;
	}
}
