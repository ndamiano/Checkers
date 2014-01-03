package edu.mccc.cos102.checkers;
import java.util.Observer;
import java.util.Observable;
import java.util.Scanner;
import com.cbthinkx.util.Debug;
import edu.mccc.cos102.ds.*;
import edu.mccc.cos102.abstracts.AbstractPlayer;
import edu.mccc.cos102.interfaces.Player;
public class ViewTest implements Observer {
	CheckersGame game;
	public static void main(String[] sa) {
		new ViewTest().doIt();
	}
	private void doIt() {
		DoublyLinkedList<Player> players;
		try {
			players = Utility.getDoublyLinkedList();
		} catch (Exception ex) {
			Debug.println("Failed Doubly linked list View Test");
			return;
		}
		CheckersPlayer player1;
		CheckersAI player2;
		player1 = new CheckersPlayer();
		player2 = new CheckersAI();
		players.addFirst(player1);
		players.addNext(player2);
		game = new CheckersGame();
		game.initGame(players);
		game.addObserver(this);
		game.addObserver(player2);
		Scanner sc = new Scanner(System.in);
		int[] data = game.getGameData();
		for (int i = 0; i < 67; i++) {
			System.out.print(data[i] + " ");
			if (i % 8 == 7) {
				System.out.println();
			}
		}
		System.out.println();
		while (true) {
			int i = sc.nextInt();
			game.selectSpot(i, game.getCurrentPlayer());
		}
	}
	public void update(Observable o, Object arg) {
		int[] data = game.getGameData();
		for (int i = 0; i < 67; i++) {
			System.out.print(data[i] + " ");
			if (i % 8 == 7) {
				System.out.println();
			}
		}
		System.out.println();
	}
}
