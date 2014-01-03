package edu.mccc.cos102.checkers;
import java.util.Observable;
import edu.mccc.cos102.interfaces.Position;
import edu.mccc.cos102.abstracts.AbstractPlayer;
public class CheckersAI extends AbstractPlayer {
	private int playerNumber;
	private CheckersStrategy strat;
	//Used for AI to create actions
	public CheckersAction chooseAction(CheckersPosition position) {
		return strat.generateAction(position);
	}
	//Turns on a surrender flag
	public void surrender() {
	}
	//Sets player's turn number in the game
	public void setPlayerNumber(int number) {
		playerNumber = number;
	}
	public void update(Observable o, Object arg) {
		System.out.println(o);
	}
}
