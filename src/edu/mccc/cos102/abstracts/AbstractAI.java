package edu.mccc.cos102.abstracts;
import java.util.Observer;
import java.util.Observable;
import edu.mccc.cos102.interfaces.Player;
import edu.mccc.cos102.interfaces.Action;
import edu.mccc.cos102.interfaces.Position;
import edu.mccc.cos102.interfaces.Strategy;
public abstract class AbstractAI extends Observable implements Player, Observer {
	protected Strategy strat;
	public Action chooseAction(Position position) {
		return strat.generateAction(position);
	}
	public Action chooseAction() {
		return null;
	}
	public void surrender() {
	}
	public void initAI(int difficulty) {
	}
}