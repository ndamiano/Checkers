package edu.mccc.cos102.checkers;
import java.util.Iterator;
import com.cbthinkx.util.Debug;
import edu.mccc.cos102.ds.List;
import edu.mccc.cos102.ds.Utility;
import edu.mccc.cos102.ds.Sequence;
import edu.mccc.cos102.ds.graph.Edge;
import edu.mccc.cos102.ds.graph.Graph;
import edu.mccc.cos102.ds.graph.Vertex;
import edu.mccc.cos102.abstracts.AbstractRuleSet;
public class CheckersRuleSet extends AbstractRuleSet {
	/**
	 *
	 *
	 *
	 */
	public boolean checkForWinner(CheckersPosition position, int playerNumber) {
		Debug.println("Something is being checked for winners");
		if (getValidActions(position, playerNumber).isEmpty()) {
			Debug.println("There is a winner");
			return true;
		} else {
			Debug.println("There is not a winner");
			return false;
		}
	}
	/* Returns the player number of the winner
	 *
	 *
	 *
	 */
	public int getWinner(CheckersPosition position, int playerNumber) {
		if (checkForWinner(position, playerNumber)) {
			return (playerNumber + 1 % 2);
		} else {
			return -1;
		}
	}
	/**
	 * Returns a boolean value dictating wether or not the given action is legal
	 * in the given position. Does not take into account which player's turn
	 * it is.
	 *
	 * @param position	the position used to check the action's validity
	 * @param action	the action in question
	 * @return			boolean that tells legality of action
	 */
	public boolean checkValidAction(CheckersPosition position, CheckersAction action) {
		Iterator<Vertex<CheckersElement>> it;
		it = position.getGraph().getVertices().iterator();
		Vertex<CheckersElement> from, temp;
		from = null;
		while (it.hasNext()) {
			temp = it.next();
			if (temp.getNumber() == action.getFrom()) {
				from = temp;
			}
		}
        Iterator<CheckersAction> it2 = getValidActions(position, from.getElement().whoOwns()).iterator();
        CheckersAction temp2;
        Debug.println("Valid actions are as follows:");
        while (it2.hasNext()) {
            temp2 = it2.next();
            Debug.println(temp2.getFrom() + " " + temp2.getTo());
        }
		if (from.getElement().getType() == 0) {
			return false;
		} else {
			if (getValidActions(position, from.getElement().whoOwns()).contains(action)) {
				Debug.println("returned true to list contains action");
				return true;
			} else {
                Debug.println("Returned false to list contains action");
				return false;
			}
		}
	}
	/**
	 * Creates a list of legal moves.
	 *
	 *
	 * @param position	the position used to check the action's validity
	 * @param action	the action in question
	 * @return			boolean that tells legality of action
	 */
	public List<CheckersAction> getValidActions(CheckersPosition position, int playerNumber) {
		Debug.println("List of valid actions requested");
		List<CheckersAction> nonJumpList;
		List<CheckersAction> jumpList;
		List<Vertex<CheckersElement>> vertexList;
		try {
			nonJumpList = Utility.getList();
			jumpList = Utility.getList();
			vertexList = Utility.getList();
		} catch (Exception ex) {
            Debug.println("List didn't initialize");
			return null;
		}
		Iterator<Vertex<CheckersElement>> it = position.getGraph().getVertices().iterator();
		Vertex<CheckersElement> temp;
		while (it.hasNext()) {
			temp = it.next();
			if (temp.getElement().whoOwns() == playerNumber) {
				vertexList.add(temp);
			}
		}
		it = vertexList.iterator();
		Iterator<Edge<Vertex<CheckersElement>>> it2;
		Iterator<Vertex<CheckersElement>> it3;
		Edge<Vertex<CheckersElement>> tempEdge;
		Vertex<CheckersElement> temp2;
		while (it.hasNext()) {
			temp = it.next();
			it2 = temp.getEdgesOut().iterator();
			while (it2.hasNext()) {
				tempEdge = it2.next();
				if (((tempEdge.getWeight() == (temp.getElement().getType() % 2)) || temp.getElement().isKing()) && (tempEdge.getTo().getElement().getType() == 0)) {
					nonJumpList.add(new CheckersAction(temp.getNumber(), tempEdge.getTo().getNumber()));
				}
			}
			it3 = getJumps(temp).iterator();
			while(it3.hasNext()) {
				jumpList.add(new CheckersAction(temp.getNumber(), it3.next().getNumber()));
			}
		}
		if (jumpList.isEmpty()) {
            Debug.println("List with no jumps returned.");
			return nonJumpList;
		} else {
            Debug.println("List with jumps returned");
			return jumpList;
		}
	}
	/**
	 * Checks to see if pieces in the current position need to be turned into kings
	 *
	 * @param position	The position to be checked to see if pieces should be turned into kings.
	 */
	public void checkForKing(CheckersPosition position) {
		Iterator<Vertex<CheckersElement>> it = position.getGraph().getVertices().iterator();
		Vertex<CheckersElement> temp;
		while (it.hasNext()) {
			temp = it.next();
			if (temp.getNumber() < 8 && temp.getElement().getType() == 2) {
				temp.getElement().makeKing();
			} else {
				if (temp.getNumber() > 55 && temp.getElement().getType() == 1) {
					temp.getElement().makeKing();
				}
			}
		}
	}
	public boolean isAJump(CheckersAction action) {
		if (action.getTo() - action.getFrom() == 14 || action.getTo() - action.getFrom() == -14 || action.getTo() - action.getFrom() == 18 || action.getTo() - action.getFrom() == -18) {
			return true;
		}
		return false;
	}
	public List<Vertex<CheckersElement>> getJumps(Vertex<CheckersElement> vertex) {
		Debug.println("List of jumps from a vertex being generated");
		List<Vertex<CheckersElement>> list;
		try {
			list = Utility.getList();
		} catch (Exception ex) {
			Debug.println("List failed to initialize in getJumps in CheckersRuleSet");
			return null;
		}
		if (vertex.getElement().getType() == 0) {
			return list;
		}
		Iterator<Edge<Vertex<CheckersElement>>> it1;
		Edge<Vertex<CheckersElement>> edge1;
		Vertex<CheckersElement> vertex1;
		Iterator<Edge<Vertex<CheckersElement>>> it2;
		Edge<Vertex<CheckersElement>> edge2;
		Vertex<CheckersElement> vertex2;
		it1 = vertex.getEdgesOut().iterator();
		int distance;
		while (it1.hasNext()) {
			edge1 = it1.next();
			if (vertex.getElement().isKing() || edge1.getWeight() == vertex.getElement().getType() % 2) {
				vertex1 = edge1.getTo();
				if ((vertex.getElement().getType() % 2 != vertex1.getElement().getType() % 2) && (vertex1.getElement().getType() != 0))
				{
					it2 = vertex1.getEdgesOut().iterator();
					while (it2.hasNext()) {
						edge2 = it2.next();
						vertex2 = edge2.getTo();
						distance = vertex2.getNumber() - vertex.getNumber();
						if (vertex2.getElement().getType() == 0 && (distance == 14 || distance == 18 || distance == -14 || distance == -18)) {
							list.add(vertex2);
						}
					}
				}
			}
		}
		return list;
	}
}
