package edu.mccc.cos102.checkers;
import java.util.Observable;
import edu.mccc.cos102.abstracts.AbstractPlayer;
public class CheckersPlayer extends AbstractPlayer {
	public static final int BLACK_PLAYER = 1;
	public static final int RED_PLAYER = 2;
	private int playerNumber;
	private boolean isTurn;
	//Turns on a surrender flag
	public void surrender() {
	}
	//Sets player's turn number in the game
	public void setPlayerNumber(int number) {
		playerNumber = number;
		if (playerNumber == BLACK_PLAYER) {
			isTurn = true;
		} else {
			isTurn = false;
		}
	}
}
