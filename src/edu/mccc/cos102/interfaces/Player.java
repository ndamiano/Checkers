package edu.mccc.cos102.interfaces;
public interface Player {
	Action chooseAction();
	Action chooseAction(Position position);
	void surrender();
	void setPlayerNumber(int number);
	int getPlayerNumber();
	boolean isTurn();
	void toggleIsTurn();
}