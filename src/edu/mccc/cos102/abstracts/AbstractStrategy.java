package edu.mccc.cos102.abstracts;
import java.util.Observable;
import edu.mccc.cos102.interfaces.Position;
import edu.mccc.cos102.interfaces.Strategy;
public abstract class AbstractStrategy extends Observable implements Strategy {
	public AbstractAction generateAction(Position position) {
		return null;
	}
}