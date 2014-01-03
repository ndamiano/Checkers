package edu.mccc.cos102.checkers;
import edu.mccc.cos102.abstracts.AbstractTurn;
public class CheckersTurn extends AbstractTurn {
	//Takes a turn. The board's position is updated with the players chosen Action
	//Might be depricated out, and the class changed to simply hold actions that happened on this turn
	//This would allow us to keep a log, or display it later, etc.
	public void takeTurn(CheckersPlayer player, CheckersBoard board) {
		super.takeTurn(player, board);
	}
}
