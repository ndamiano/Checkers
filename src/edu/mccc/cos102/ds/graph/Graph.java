package edu.mccc.cos102.ds.graph;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
public class Graph<E> {
	private Set<Vertex<E>> vertices = new HashSet<>();
	public void addVertex(Vertex<E> vertex) {
		this.vertices.add(vertex);
	}
	public void addBadEdge(Edge<Vertex<E>> edge) {
		edge.getFrom().addBadEdgeOut(edge);
		edge.getTo().addEdgeIn(edge);
	}
	public void addEdge(Edge<Vertex<E>> edge) {
		edge.getFrom().addEdgeOut(edge);
		edge.getTo().addEdgeIn(edge);
	}
	public Set<Vertex<E>> getVertices() {
		return vertices;
	}
}
