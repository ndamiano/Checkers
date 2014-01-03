package edu.mccc.cos102.checkers;
import com.cbthinkx.util.Debug;
import java.util.Observable;
import java.util.Iterator;
import edu.mccc.cos102.interfaces.Player;
import edu.mccc.cos102.ds.List;
import edu.mccc.cos102.ds.Utility;
import edu.mccc.cos102.ds.graph.Vertex;
import edu.mccc.cos102.ds.DoublyLinkedList;
import edu.mccc.cos102.abstracts.AbstractGame;
import edu.mccc.cos102.abstracts.AbstractPlayer;
public class CheckersGame extends AbstractGame {
	private CheckersRuleSet rules;
	private CheckersBoard board;
	private CheckersTurn turn;
	private DoublyLinkedList<Player> players;
	private int whoseTurn;
	private int firstSpot;
	private int[] gameData;
	//Initializes the game, creating a ruleset, board, and turn
	//Tells the players what order/color they are
	//Doesn't currently handle if you give it an improperly lengthed DLL
	@Override
	public void initGame(DoublyLinkedList<Player> players) {
		super.initGame(players);
		players.getFirst().setPlayerNumber(CheckersPlayer.BLACK_PLAYER);
		players.getNext().setPlayerNumber(CheckersPlayer.RED_PLAYER);
		players.getFirst().toggleIsTurn();
		rules = new CheckersRuleSet();
		board = new CheckersBoard();
		board.initBoard(players);
		turn = new CheckersTurn();
		board.getPosition().addObserver(this);
		firstSpot = -1;
		whoseTurn = 1;
		gameData = new int[69];
		setGameData();
		this.players = players;
	}
	//Needs to be removed
	public CheckersPlayer getWinner() {
		return null;
	}
	//Gets the current ruleset in case something needs to check it.
	public CheckersRuleSet getRules() {
		return rules;
	}
	//Sets the current ruleset
	public void setRules(CheckersRuleSet rules) {
		this.rules = rules;
	}
	//Likely going to be depricated out
	public void playGame() {
	}
	public CheckersBoard getBoard() {
		return this.board;
	}
	/**
	 * Returns an array of length 67 filled with information about the game.
	 *
	 * @return Array with spots 0-63 are the piece spots, 64 is whose turn it is, 65 and 66 are the amount of pieces captured for each player.
	 */
	public int[] getGameData() {
		return gameData;
	}
	public void update(Observable o, Object arg) {
		setGameData();
		setChanged();
		notifyObservers();
	}
	/* This will actually be what runs the game, seeing as although it is turn based, it is event driven.
	 * Still needs to check if the player passed in is actually playing the game.
	 */
	public void selectSpot(int spot, Player player) {
		if (player.isTurn()) {
			if (board.getPosition().findVertex(spot).getElement().whoOwns() == whoseTurn || board.getPosition().findVertex(spot).getElement().getType() == 0) {
				if (firstSpot == -1) {
					firstSpot = spot;
					setGameData();
					setChanged();
					notifyObservers();
				} else {
					if (board.getPosition().update(new CheckersAction(firstSpot, spot))) {
						rules.checkForKing(board.getPosition());
						if (rules.isAJump(new CheckersAction(firstSpot, spot))) {
							if (rules.getJumps(board.getPosition().findVertex(spot)).isEmpty()) {
								firstSpot = -1;
								endTurn();
								setGameData();
								setChanged();
								notifyObservers();
							} else {
								firstSpot = spot;
								setGameData();
								setChanged();
								notifyObservers();
							}
						} else {
							firstSpot = -1;
							endTurn();
							setGameData();
							setChanged();
							notifyObservers();
						}
					} else {
						firstSpot = -1;
						setGameData();
						setChanged();
						notifyObservers();
					}
				}
			}
		}
		rules.checkForKing(board.getPosition());
		if (rules.checkForWinner(board.getPosition(), whoseTurn)) {
			gameData[68] = rules.getWinner(board.getPosition(), whoseTurn);
			setChanged();
			notifyObservers();
		}
	}
	public void cancelLastSpot() {
		firstSpot = -1;
	}
	public Player getCurrentPlayer() {
		Player temp;
		if (players.getFirst().isTurn()) {
			temp = players.getFirst();
		} else {
			temp = players.getLast();
		}
		return temp;
	}
	public void restart() {
		if (players.getFirst().isTurn()) {
			players.getFirst().toggleIsTurn();
		} else {
			players.getNext().toggleIsTurn();
		}
		initGame(players);
		setGameData();
		setChanged();
		notifyObservers();
	}
	private void endTurn() {
		players.getFirst().toggleIsTurn();
		players.getNext().toggleIsTurn();
		if (players.getFirst().isTurn()) {
			whoseTurn = 1;
		} else {
			whoseTurn = 2;
		}
	}
	private void setGameData() {
		Iterator<Vertex<CheckersElement>> it = getBoard().getPosition().getGraph().getVertices().iterator();
		Vertex<CheckersElement> temp;
		int redPieces = 0, blackPieces = 0;
		while (it.hasNext()) {
			temp = it.next();
			gameData[temp.getNumber()] = temp.getElement().getType();
			if (gameData[temp.getNumber()] == 1 || gameData[temp.getNumber()] == 3) {
				blackPieces++;
			} else {
				if (gameData[temp.getNumber()] == 2 || gameData[temp.getNumber()] == 4) {
					redPieces++;
				}
			}
		}
		gameData[64] = whoseTurn;
		gameData[65] = 12 - blackPieces;
		gameData[66] = 12 - redPieces;
		gameData[67] = firstSpot;
		gameData[68] = -1;
	}
}
