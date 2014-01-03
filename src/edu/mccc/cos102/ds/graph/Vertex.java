package edu.mccc.cos102.ds.graph;
import java.util.HashSet;
import java.util.Set;
public class Vertex<E> {
	private Set<Edge<Vertex<E>>> edgesIn = new HashSet<>();
	private Set<Edge<Vertex<E>>> badEdgesOut = new HashSet<>();
	private Set<Edge<Vertex<E>>> edgesOut = new HashSet<>();
	private E element;
	private int distance = Integer.MAX_VALUE;
	private Vertex<E> previous = null;
	private int spaceColor;
	public static final int BLACK = 1;
	public static final int RED = 2;
	private int number;
	public Vertex(E element) {
		this.element = element;
	}
	public Vertex(E element, int number) {
		this.element = element;
		this.number = number;
	}
	public void addEdgeIn(Edge<Vertex<E>> edge) {
		this.edgesIn.add(edge);
	}
	public void addBadEdgeOut(Edge<Vertex<E>> edge) {
		this.badEdgesOut.add(edge);
	}
	public void addEdgeOut(Edge<Vertex<E>> edge) {
		this.edgesOut.add(edge);
	}
	public Set<Edge<Vertex<E>>> getEdgesIn() {
		return this.edgesIn;
	}
	public Set<Edge<Vertex<E>>> getBadEdgesOut() {
		return this.badEdgesOut;
	}
	public Set<Edge<Vertex<E>>> getEdgesOut() {
		return this.edgesOut;
	}
	public E getElement() {
		return this.element;
	}
	public void setElement(E element) {
		this.element = element;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Vertex<E> getPrevious() {
		return previous;
	}
	public void setPrevious(Vertex<E> previous) {
		this.previous = previous;
	}
	public void setColor(int color) {
		this.spaceColor = color;
	}
	public int getColor() {
		return this.spaceColor;
	}
	public int getNumber() {
		return number;
	}
}
