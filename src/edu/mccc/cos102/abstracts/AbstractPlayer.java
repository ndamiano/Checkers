package edu.mccc.cos102.abstracts;
import java.util.Observer;
import java.util.Observable;
import edu.mccc.cos102.interfaces.Player;
import edu.mccc.cos102.interfaces.Position;
public abstract class AbstractPlayer extends Observable implements Player, Observer {
	protected int playerNumber;
	protected boolean isTurn = false;
	public AbstractAction chooseAction(Position position) {
		return null;
	}
	public AbstractAction chooseAction() {
		return null;
	}
	public void surrender() {
	}
	public void setPlayerNumber(int number) {
		playerNumber = number;
	}
	public int getPlayerNumber() {
		return playerNumber;
	}
	public boolean isTurn() {
		return isTurn;
	}
	public void toggleIsTurn() {
		if (isTurn) {
			isTurn = false;
		} else {
			isTurn = true;
		}
	}
	public void update(Observable o, Object arg) {
	}
}