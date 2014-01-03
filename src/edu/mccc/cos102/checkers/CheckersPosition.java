package edu.mccc.cos102.checkers;
import com.cbthinkx.util.Debug;
import edu.mccc.cos102.interfaces.Player;
import edu.mccc.cos102.ds.graph.Graph;
import edu.mccc.cos102.ds.graph.Edge;
import edu.mccc.cos102.ds.graph.Vertex;
import edu.mccc.cos102.ds.Sequence;
import edu.mccc.cos102.ds.DoublyLinkedList;
import edu.mccc.cos102.abstracts.AbstractPosition;
import java.util.Iterator;
public class CheckersPosition extends AbstractPosition {
	private CheckersRuleSet rules;
	private Graph<CheckersElement> positionGraph;
	public void initPosition(DoublyLinkedList<? extends Player> players) {
		positionGraph = new Graph<CheckersElement>();
		initGraph();
		rules = new CheckersRuleSet();
	}
	//Tinkering with the idea of being able to throw any graph into the position for debugging/different types of checkers
	//This method would allow us to make the graph elsewhere and just stick it into a position to test with.
	public void setGraph(Graph<CheckersElement> graph) {
		positionGraph = graph;
	}
	public boolean update(CheckersAction action) {
		boolean status = false;
		if (rules.checkValidAction(this, action)) {
			Iterator<Vertex<CheckersElement>> it = positionGraph.getVertices().iterator();
			Vertex<CheckersElement> temp, from, jumped, to;
			int numberTo, numberFrom, difference;
			numberTo = action.getTo();
			numberFrom = action.getFrom();
			difference = numberTo - numberFrom;
			int jumpedNumber = 1000;
			jumpedNumber = (difference == 14) ? (action.getFrom() + 7) : jumpedNumber;
			jumpedNumber = (difference == 18) ? (action.getFrom() + 9) : jumpedNumber;
			jumpedNumber = (difference == -14) ? (action.getFrom() - 7) : jumpedNumber;
			jumpedNumber = (difference == -18) ? (action.getFrom() - 9) : jumpedNumber;
			from = new Vertex<CheckersElement>(new CheckersElement(CheckersElement.BLANK_SPACE), 0);
			to = from;
			jumped = from;
			while(it.hasNext()) {
				temp = it.next();
				if (temp.getNumber() == numberFrom) {
					from = temp;
				} else {
					if (temp.getNumber() == numberTo) {
						to = temp;
					} else {
						if (temp.getNumber() == jumpedNumber) {
							jumped = temp;
						}
					}
				}
			}
			to.getElement().setType(from.getElement().getType());
			from.getElement().setType(CheckersElement.BLANK_SPACE);
			if (jumpedNumber != 1000) {
				jumped.getElement().setType(CheckersElement.BLANK_SPACE);
			}
			status = true;
			setChanged();
			notifyObservers();
    	    Debug.println("Fired event");
		}
		return status;
	}
	public Graph<CheckersElement> getGraph() {
		return positionGraph;
	}
	public Vertex<CheckersElement> findVertex(int number) {
		Iterator<Vertex<CheckersElement>> it = positionGraph.getVertices().iterator();
		Vertex<CheckersElement> temp, returned;
		returned = null;
		while (it.hasNext()) {
			temp = it.next();
			if (temp.getNumber() == number) {
				returned = temp;
			}
		}
		return returned;
	}
	private void initGraph() {
		Debug.println("Creating these vertices: ");
		for (int i = 0; i < 64; i++) {
			if ((i % 2) == 0 && (i <= 23) && ((i / 8) % 2) == 1) {
				positionGraph.addVertex(new Vertex<CheckersElement>(new CheckersElement(CheckersElement.BLACK_DISC), i));
				Debug.print("1 ");
			} else {
				if ((i % 2) == 0 && (i >= 39) && ((i / 8) % 2) == 1) {
					positionGraph.addVertex(new Vertex<CheckersElement>(new CheckersElement(CheckersElement.RED_DISC), i));
					Debug.print("2 ");
				} else {
					if ((i % 2) == 1 && (i <= 23) && ((i / 8) % 2) == 0) {
						positionGraph.addVertex(new Vertex<CheckersElement>(new CheckersElement(CheckersElement.BLACK_DISC), i));
						Debug.print("1 ");
					} else {
						if ((i % 2) == 1 && (i >= 40) && ((i / 8) % 2) == 0) {
							positionGraph.addVertex(new Vertex<CheckersElement>(new CheckersElement(CheckersElement.RED_DISC), i));
							Debug.print("2 ");
						} else {
							positionGraph.addVertex(new Vertex<CheckersElement>(new CheckersElement(CheckersElement.BLANK_SPACE), i));
							Debug.print("0 ");
						}
					}
				}
			}
			if (i == 7 || i == 15 || i == 23 || i == 31 || i == 39 || i == 47 || i == 55 || i == 63) {
				Debug.println();
			}
		}
		Debug.println("Creating these edges: ");
		Iterator<Vertex<CheckersElement>> it = positionGraph.getVertices().iterator();
		Iterator<Vertex<CheckersElement>> it2;
		while (it.hasNext()) {
			Vertex<CheckersElement> temp = it.next();
			it2 = positionGraph.getVertices().iterator();
			while (it2.hasNext()) {
				Vertex<CheckersElement> temp2 = it2.next();
				if (temp2.getNumber() == (temp.getNumber() + 1) || temp2.getNumber() == (temp.getNumber() - 1) || temp2.getNumber() == (temp.getNumber() + 8)  || temp2.getNumber() == (temp.getNumber() - 8)) {
					positionGraph.addBadEdge(new Edge<Vertex<CheckersElement>>(temp, temp2, 0));
					Debug.print(temp.getNumber() + " " + temp2.getNumber() + " ");
				} else {
					if ((temp2.getNumber() == (temp.getNumber() + 7) && (temp.getNumber() % 8 != 0)) || (temp2.getNumber() == (temp.getNumber() + 9) && (temp.getNumber() % 8 != 7))) {
						positionGraph.addEdge(new Edge<Vertex<CheckersElement>>(temp, temp2, 1));
						Debug.print(temp.getNumber() + " " + temp2.getNumber() + " ");
					} else {
						if ((temp2.getNumber() == (temp.getNumber() - 7) && (temp.getNumber() % 8 != 7)) || (temp2.getNumber() == (temp.getNumber() - 9) && (temp.getNumber() % 8 != 0))) {
							positionGraph.addEdge(new Edge<Vertex<CheckersElement>>(temp, temp2, 0));
							Debug.print(temp.getNumber() + " " + temp2.getNumber() + " ");
						}
					}
				}
			}
			Debug.println();
		}
		Debug.println("Finished generating graph");
		setChanged();
		notifyObservers();
	}
	public void debugPosition() {
		Iterator<Vertex<CheckersElement>> it = this.getGraph().getVertices().iterator();
		Vertex<CheckersElement> temp;
		int[] array = new int[64];
		while (it.hasNext()) {
			temp = it.next();
			array[temp.getNumber()] = temp.getElement().getType();
		}
		for (int i = 0; i < 64; i++) {
			System.out.print(array[i] + " ");
			if (i == 7 || i == 15 || i == 23 || i == 31 || i == 39 || i == 47 || i == 55 || i == 63) {
				System.out.println();
			}
		}
	}
}
