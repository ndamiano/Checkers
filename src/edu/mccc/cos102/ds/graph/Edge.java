package edu.mccc.cos102.ds.graph;
public class Edge<V> {
	private V from;
	private V to;
	private int weight;
	public Edge(V from, V to, int weight) {
		this.from = from;
		this.to = to;
		this.setWeight(weight);
	}
	public V getFrom() {
		return this.from;
	}
	public void setFrom(V from) {
		this.from = from;
	}
	public V getTo() {
		return this.to;
	}
	public void setTo(V to) {
		this.to = to;
	}
	public int getWeight() {
		return this.weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
