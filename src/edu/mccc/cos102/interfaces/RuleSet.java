package edu.mccc.cos102.interfaces;
import edu.mccc.cos102.ds.List;
public interface RuleSet {
	boolean checkForWinner(Position position, int playerNumber);
	boolean checkValidAction(Position position, Action action);
	List<Action> getValidActions(Position position, int playerNumber);
	int getWinner(Position position, int playerNumber);
}