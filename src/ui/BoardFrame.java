package ui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import main.GameOfCluedo;
import moves.Accusation;
import moves.Suggestion;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;

import game.Board;
import game.Player;
import items.Card;

import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;

public class BoardFrame extends JFrame implements ActionListener, MouseListener {

	private JPanel bottomPanel;
	private JPanel rightPanel;
	public BoardCanvas boardCanvas;
	private GameOfCluedo game;
	private Square selected;
	
	public ArrayList<JButton> buttons;
	public JLabel profile;

	private Board board;
	
	public BoardFrame() {
		super("Cluedo Board Game");

		game = new GameOfCluedo();
		board = game.getBoard();
		buttons  = new ArrayList<JButton>();
		this.setSize(1008, 778);
		this.setMinimumSize(new Dimension(600, 500)); //855 without rightPanel
		setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu game = new JMenu("Game");
		menuBar.add(file);

		JMenuItem mntmSave = new JMenuItem("Save");
		file.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("Load");
		file.add(mntmLoad);
		menuBar.add(game);

		JMenuItem mntmStartNewGame = new JMenuItem("Start New Game");
		game.add(mntmStartNewGame);

		getContentPane().add(menuBar, BorderLayout.PAGE_START);

		bottomPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setHgap(150);
		bottomPanel.setLayout(flow);
		bottomPanel.setPreferredSize(new Dimension(855, 100));
		bottomPanel.setMinimumSize(new Dimension(855, 100));
		
		profile = new JLabel("<html>Player Info<br> <br>text to edit</html>");
		
		JButton suggest = new JButton("Suggest");
		suggest.setActionCommand("suggest");
		suggest.addActionListener(this);
		suggest.setPreferredSize(new Dimension(50,50));
		buttons.add(suggest);

		JButton accuse = new JButton("Accuse");
		accuse.setActionCommand("accuse");
		accuse.addActionListener(this);
		accuse.setPreferredSize(new Dimension(50,50));
		buttons.add(accuse);
		
		JButton rollDice = new JButton(new ImageIcon("src/images/dice.png"));
		rollDice.setBackground(Color.LIGHT_GRAY);
		ImageIcon dice = new ImageIcon("src/images/dice.png");
		Image d = dice.getImage();
		Image newD = d.getScaledInstance(45, 45, 20);
		ImageIcon d2 = new ImageIcon(newD);
		rollDice.setMargin(new Insets(0, 0, 0, 0));
		rollDice.setIcon(d2);
		buttons.add(rollDice);
		
		JButton endTurn = new JButton("End Turn");
		endTurn.setActionCommand("endTurn");
		endTurn.addActionListener(this);
		endTurn.setPreferredSize(new Dimension(100,30));
		buttons.add(endTurn);

		rollDice.setActionCommand("rollDice");
		rollDice.addActionListener(this);

		suggest.setPreferredSize(new Dimension(100,30));
		accuse.setPreferredSize(new Dimension(100,30));

		bottomPanel.add(suggest);
		bottomPanel.add(accuse);
		bottomPanel.add(rollDice);
		bottomPanel.add(endTurn);
		bottomPanel.add(profile);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(3,2));
		rightPanel.add(new JLabel(new ImageIcon("src/images/clue.jpg")));
		rightPanel.add(new JLabel(new ImageIcon("src/images/clue.jpg")));
		rightPanel.add(new JLabel(new ImageIcon("src/images/clue.jpg")));
		rightPanel.add(new JLabel(new ImageIcon("src/images/clue.jpg")));
		rightPanel.add(new JLabel(new ImageIcon("src/images/clue.jpg")));
		rightPanel.add(new JLabel(new ImageIcon("src/images/clue.jpg")));
		
		getContentPane().add(rightPanel, BorderLayout.EAST);
		
		boardCanvas = new BoardCanvas();
		add(boardCanvas, BorderLayout.CENTER);
		this.setVisible(true);
	}

public void updateCards(Player player){
		
		ArrayList<Card> cards = player.getHand();
		
		Component[] comp = rightPanel.getComponents();
		int count = 0;
		for(int i = 0; i < comp.length; i++ ){
			Card c = cards.get(count%cards.size());
			if(count >= cards.size()){
				((JLabel) comp[i]).setIcon((resize("src/images/clue.jpg", (JLabel) comp[i])));
			}
			else if(c == null){
			((JLabel) comp[i]).setIcon((resize("src/images/clue.jpg", (JLabel) comp[i])));
			}else{
			((JLabel) comp[i]).setIcon((resize("src/images/"+cards.get(i).getPicture()+".jpg", (JLabel) comp[i])));
			}
			count++;
			bottomPanel.repaint();
			
		}
		

			String info = "<html>Player"
			+player.num+ " Info<br>"
					+ "Name: " + player.getName()
					+ "<br>Token: " + player.getToken().token+" ("+ player.getToken().colour+")<br>"
					+ "Location: " + player.getLocation().getX() +"," +player.getLocation().getY()+"</html>";
		
		
		
		profile.setText(info);
		
		int x = (int)player.getLocation().getX();
		int y = (int) player.getLocation().getY();
		
		
	}
	
	
	
	public ImageIcon resize(String path, JLabel label){
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(),
		        Image.SCALE_SMOOTH);
		
		return new ImageIcon(dimg);
		
		
	}
	
	public void drawTokens(Graphics g){
		for(int x = 0; x < 24; x++){
			for(int y = 0; y < 25; y++){
				if(boardCanvas.squares[x][y].hasPiece()){
					String colour = boardCanvas.squares[x][y].getPiece().colour;
					Image img = null;
					try {
						img = ImageIO.read(new File("src/images/"+colour+".png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g.drawImage(img, x * 25, y*25, 23, 23, null);
				}
				}
		}
		}

	
	
	public static void main(String[] args) {
		BoardFrame frame = new BoardFrame();
		Player p = new Player("andre", null, null );
		p.addCard(frame.getBoard().getCharacter("MISS_SCARLETT"));
		p.addCard(frame.getBoard().getWeapon("DAGGER"));
		p.addCard(frame.getBoard().getRoom("LOUNGE"));
		frame.updateCards(p);
	}
	
	public GameOfCluedo getGame() {
		return game;
	}

	public void setGame(GameOfCluedo game) {
		this.game = game;
	}
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}


	public void actionPerformed(ActionEvent e) {
		String player;
		String weapon;
		String room;
		String cmd = e.getActionCommand();
		if (cmd.equals("accuse")) {

		}
		if (cmd.equals("suggest")){
		
		}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		x = x/35;
		y = y/35;
		
		if (boardCanvas.squares[x][y] != null){
			selected = boardCanvas.squares[x][y];
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}