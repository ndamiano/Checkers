package edu.mccc.cos102.abstracts;
import java.util.Observable;
import java.util.Observer;
import edu.mccc.cos102.interfaces.Game;
import edu.mccc.cos102.interfaces.Turn;
import edu.mccc.cos102.interfaces.Board;
import edu.mccc.cos102.interfaces.Player;
import edu.mccc.cos102.interfaces.RuleSet;
import edu.mccc.cos102.ds.DoublyLinkedList;
import edu.mccc.cos102.ds.Utility;
public abstract class AbstractGame extends Observable implements Game, Observer {
	protected RuleSet rules;
	protected Board board;
	protected Turn turn;
	protected DoublyLinkedList<Player> players;
	public void initGame(DoublyLinkedList<Player> players) {
		try {
			this.players = players;
		} catch (Exception ex) {
		}
	}
	public void declareWinner(Player player) {
	}
	public RuleSet getRules() {
		return rules;
	}
	public void setRules(RuleSet rules) {
		this.rules = rules;
	}
	public void playGame() {
	}
}