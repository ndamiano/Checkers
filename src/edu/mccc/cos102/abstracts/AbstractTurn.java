package edu.mccc.cos102.abstracts;
import java.util.Observable;
import edu.mccc.cos102.interfaces.Turn;
import edu.mccc.cos102.interfaces.Board;
import edu.mccc.cos102.interfaces.Player;
import edu.mccc.cos102.interfaces.Position;
public abstract class AbstractTurn extends Observable implements Turn {
	public void takeTurn(Player player, Board board) {
		Position position;
		try {
			position = (Position) board.getPosition().clone();
		} catch (CloneNotSupportedException ex) {
			return;
		}
		board.getPosition().update(player.chooseAction(position));
	}
}