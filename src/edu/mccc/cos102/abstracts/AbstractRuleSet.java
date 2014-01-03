package edu.mccc.cos102.abstracts;
import java.util.Observable;
import edu.mccc.cos102.ds.List;
import edu.mccc.cos102.interfaces.Action;
import edu.mccc.cos102.interfaces.RuleSet;
import edu.mccc.cos102.interfaces.Position;
public abstract class AbstractRuleSet extends Observable implements RuleSet {
	public boolean checkForWinner(Position position, int playerNumber) {
		return false;
	}
	public int getWinner(Position position, int playerNumber) {
		return -1;
	}
	public boolean checkValidAction(Position position, Action action) {
		return true;
	}
	public List<Action> getValidActions(Position position, int playerNumber) {
		return null;
	}
}