package edu.mccc.cos102.checkers;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;
import com.cbthinkx.util.Debug;
public class CheckersView extends JFrame implements Observer {
	static final long serialVersionUID = 1L;
	protected CheckersGame game;
	protected int[] currentData;
	protected DrawingPanel gamePanel;
	protected InfoPanel infoPanel;
	public CheckersView(CheckersGame game) {
		super("Checkers");
		this.game = game;
		game.addObserver(this);
		currentData = game.getGameData();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		gamePanel = new DrawingPanel();
		gamePanel.addMouseListener(new MouseThing());
		infoPanel = new InfoPanel();
		add(gamePanel, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.LINE_END);
		setSize(1015,835);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	public void update(Observable o, Object arg) {
		currentData = game.getGameData();
		gamePanel.repaint();
		infoPanel.repaint();
	}
	private class InfoPanel extends JPanel {
		static final long serialVersionUID = 1L;
		public JLabel label1, label2, label3, label4;
		public JButton restartButton;
		public InfoPanel() {
			setPreferredSize(new Dimension(200,800));
			label1 = new JLabel("It is");
			label2 = new JLabel("Player 1's");
			label3 = new JLabel("turn");
			label4 = new JLabel("");
			restartButton = new JButton("Restart");
			restartButton.setVisible(false);
			restartButton.addActionListener(new ButtonListener());
			add(label1);
			add(label2);
			add(label3);
			add(label4);
			add(restartButton);
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			label2.setText("Player " + currentData[64] + "'s");
			if (currentData[68] != -1) {
				label4.setText("player " + currentData[68] + " is the winner!");
				restartButton.setVisible(true);
			}
		}
		private class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				label4.setText("");
				restartButton.setVisible(false);
				game.restart();
			}
		}
	}
	private class DrawingPanel extends JPanel {
		static final long serialVersionUID = 1L;
		public DrawingPanel() {
			super();
			setPreferredSize(new Dimension(800,800));
			setLayout(null);
			setBackground(Color.RED);
		}
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			Shape s = new Rectangle2D.Double(0, 0, this.getWidth()/8, this.getHeight()/8);
			int offsetX, offsetY;
			AffineTransform at;
			Shape disc = new Ellipse2D.Double(this.getWidth()/80, this.getHeight()/80, this.getWidth()/80 * 8, this.getHeight()/80 * 8);
			g2d.setColor(Color.BLACK);
			for (int i = 0; i < 64; i++) {
				at = new AffineTransform();
				offsetX = i % 8;
				offsetY = i / 8;
				at.translate(this.getWidth()/8 * offsetX, this.getHeight()/8 * offsetY);
				if ( i % 2 == 0 && ((i / 8) % 2) == 0) {
					g2d.fill(at.createTransformedShape(s));
				} else {
					if (i % 2 == 1 && ((i / 8) % 2) == 1) {
						g2d.fill(at.createTransformedShape(s));
					}
				}
				if (currentData[67] == i) {
					g2d.setColor(Color.GREEN);
					g2d.fill(at.createTransformedShape(s));
					g2d.setColor(Color.BLACK);
				}
				if (currentData[i] == 1) {
					g2d.fill(at.createTransformedShape(disc));
				} else {
					if (currentData[i] == 2) {
						g2d.setStroke(new BasicStroke(3f));
						g2d.draw(at.createTransformedShape(disc));
						g2d.setColor(Color.RED);
						g2d.fill(at.createTransformedShape(disc));
						g2d.setColor(Color.BLACK);
					} else {
						if (currentData[i] == 3) {
							g2d.fill(at.createTransformedShape(disc));
							g2d.setPaint(Color.WHITE);
							g2d.drawString("K", 10, 10);
							g2d.setPaint(Color.BLACK);
						} else {
							if (currentData[i] == 4) {
								g2d.setStroke(new BasicStroke(3f));
								g2d.draw(at.createTransformedShape(disc));
								g2d.setColor(Color.RED);
								g2d.fill(at.createTransformedShape(disc));
								g2d.setPaint(Color.WHITE);
								g2d.drawString("K", 10, 10);
								g2d.setPaint(Color.BLACK);
							}
						}
					}
				}
			}
		}
	}
	private class MouseThing implements MouseListener {
		public void mouseClicked(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
			int x, y, number;
			x = e.getX();
			y = e.getY();
			number = (x / (gamePanel.getWidth() / 8)) + 8 * (y / (gamePanel.getHeight() / 8));
			game.selectSpot(number, game.getCurrentPlayer());
		}
		public void mouseReleased(MouseEvent e) {
		}
	}
}
