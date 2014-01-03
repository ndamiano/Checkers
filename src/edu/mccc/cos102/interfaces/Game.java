package edu.mccc.cos102.interfaces;
import edu.mccc.cos102.ds.DoublyLinkedList;
public interface Game {
	void initGame(DoublyLinkedList<Player> players);
	RuleSet getRules();
	void declareWinner(Player player);
	void playGame();
}