package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import main.GameOfCluedo;
import moves.Accusation;
import moves.Suggestion;
import game.Board;
import game.Player;
import items.Card;
import items.Dice;
import items.Piece;

public class Main {

	public static int turn;
	public static Dice dice;
	public static boolean start = true;
	public static boolean endTurn = false;
	public static int playerNum = 0;

	public static int inputNumber(BoardFrame frame, String message){

		while(1 == 1){
			try{
				String players = JOptionPane.showInputDialog(frame, message);
				if(players == null) return -1;
				return Integer.parseInt(players);
			}catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(frame, "Please enter a Number!!!", "", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	/**
	 * Get string from JDialog
	 */
	private static String inputString(BoardFrame frame, String msg) {
		while (1 == 1) {
			Object[] option = new Object[]{"OK"};
			String s = JOptionPane.showInputDialog(frame,msg, "",JOptionPane.INFORMATION_MESSAGE);
			if(s == null){
				int op = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Exit?");
				if(op == JOptionPane.YES_OPTION){
					System.exit(-1);
				}
				else{
					continue;
				}
			}
			if(s.equals("")){
				JOptionPane.showMessageDialog(frame, "Please try again", "", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			else return s; 

		}
	}


	public static ArrayList<Player> inputPlayers(BoardFrame frame, GameOfCluedo game, int num){

		ArrayList<Player> players = new ArrayList<Player>();
		RadioButton selection = new RadioButton();
		JOptionPane pane = new JOptionPane();
		pane.add(selection);

		Object[] options = {"OK"};

		for(int i = 1; i <= num; i++){

			String name = inputString(frame, "Enter the name of Player "+i+":");			

			//select character token
			JOptionPane.showOptionDialog(frame, selection,"Select your Token", JOptionPane.ERROR_MESSAGE, 
					JOptionPane.OK_OPTION, null, options, options[0] );
			String s = selection.getSelectedButton();

			//when token is selected make sure it is unavailable, (update the dialog)
			JRadioButton button = selection.getRadioButton(s);
			button.setEnabled(false);
			int index = selection.buttons.indexOf(button);
			selection.buttons.get(index).setEnabled(false);
			selection.updatePicture();


			//initialize player 
			Point point = game.getBoard().tokenToPos.get(s);
			Piece piece = game.getBoard().getPiece(s);
			Player p = new Player(name, piece, point);
			p.num = i;
			players.add(p);
		}


		return players;

	}

	public static void movePlayer(BoardFrame frame, Player player, int roll){
		//game.movePlayer(roll, d, p, players);
		//user selects a square
		//check if square is within roll range
		//if it is, checks if x or y is greater, if x is greater move x spaces first then y else y spaces first
		
		
		while(roll > 0){
			double px = player.getLocation().getX();
			double py = player.getLocation().getY();
			
			
			
			
			roll--;
			frame.repaint();
		}
		

	}

	public static void setupButtons(BoardFrame frame){

		ActionListener action = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e){
				playerOptions(e.getActionCommand(),frame);
				return;
			}


		};

		for(JButton button : frame.buttons){
			button.addActionListener(action);
		}

	}
	
	

	private static void playerOptions(String action,BoardFrame frame) {

		if(start && !action.equals("rollDice")){
			JOptionPane.showMessageDialog(frame, "Player "+ frame.getGame().currentPlayer.num+" must roll the Dice" ,"",JOptionPane.WARNING_MESSAGE);
			return;
		}

		if(action.equals("accuse")){
			makeAccusation(frame);
		}else if(action.equals("suggest")){
			makeSuggestion(frame);
		}else if(action.equals("rollDice")){
			if(!start){
				JOptionPane.showMessageDialog(frame, "Cannot roll the Dice Again!!!");
				return;
			}
			start = false;
			int roll = dice.roll();
			JOptionPane.showMessageDialog(frame, frame.getGame().currentPlayer.getName()+" rolls a "+roll);
			movePlayer(frame, frame.getGame().currentPlayer, roll);

		}else if(action.equals("endTurn")){
			endTurn = true;
		}




	}


	private static void makeSuggestion(BoardFrame frame) {
		String player, weapon, room;
		Board board = frame.getBoard();
		GameOfCluedo game = frame.getGame();

		Object[] ppossibilities = { "MISS_SCARLETT", "PROFESSOR_PLUM", "MRS_PEACOCK", "THE_REVEREND_GREEN",
				"COLONEL_MUSTARD", "MRS_WHITE" };

		player = (String) JOptionPane.showInputDialog(frame, "Select a Character:", "Suggestion",
				JOptionPane.PLAIN_MESSAGE, null, ppossibilities, "");
		if(player.equals(null)){return;}

		Object[] wpossibilities = { "CANDLESTICK", "DAGGER", "LEAD_PIPE", "REVOLVER", "ROPE", "SPANNER" };

		weapon = (String) JOptionPane.showInputDialog(frame, "Select a murder weapon:", "Suggestion",
				JOptionPane.PLAIN_MESSAGE, null, wpossibilities, "");
		if(weapon.equals(null)){return;}

		Object[] rpossibilities = { "BALLROOM", "BILLIARD_ROOM", "CONSERVATORY", "DINING_ROOM", "HALL", "KITCHEN",
				"LIBRARY", "LOUNGE", "STUDY" };

		room = (String) JOptionPane.showInputDialog(frame, "Select a murder room:", "Suggestion",
				JOptionPane.PLAIN_MESSAGE, null, rpossibilities, "");
		if(room.equals(null)){return;}

		Suggestion suggest = new Suggestion(board.getCharacter(player), board.getWeapon(weapon), board.getRoom(room));//cause I'm not 100% up to speed on how they're constructed

		game.suggest(suggest, game.currentPlayer, game.players);

	}

	private static void makeAccusation(BoardFrame frame) {
		String player, weapon, room;
		Board board = frame.getBoard();
		GameOfCluedo game = frame.getGame();

		Object[] ppossibilities = { "MISS_SCARLETT", "PROFESSOR_PLUM", "MRS_PEACOCK", "THE_REVEREND_GREEN",
				"COLONEL_MUSTARD", "MRS_WHITE" };

		player = (String) JOptionPane.showInputDialog(frame, "Select a Character to Accuse:", "Accusation",
				JOptionPane.PLAIN_MESSAGE, null, ppossibilities, "");
		if(player.equals(null)){return;}

		Object[] wpossibilities = { "CANDLESTICK", "DAGGER", "LEAD_PIPE", "REVOLVER", "ROPE", "SPANNER" };

		weapon = (String) JOptionPane.showInputDialog(frame, "Select a murder weapon:", "Accusation",
				JOptionPane.PLAIN_MESSAGE, null, wpossibilities, "");
		if(weapon.equals(null)){return;}

		Object[] rpossibilities = { "BALLROOM", "BILLIARD_ROOM", "CONSERVATORY", "DINING_ROOM", "HALL", "KITCHEN",
				"LIBRARY", "LOUNGE", "STUDY" };

		room = (String) JOptionPane.showInputDialog(frame, "Select a room:", "Accusation",
				JOptionPane.PLAIN_MESSAGE, null, rpossibilities, "");
		if(room.equals(null)){return;}

		Accusation accuse = new Accusation(board.getCharacter(player), board.getWeapon(weapon), board.getRoom(room));//have a go at fixing these

		game.accuse(accuse, game.currentPlayer);

	}

	public static int getNumOfPlayers(BoardFrame frame){
		while(1==1){
			int num = inputNumber(frame,"Input number of Players: " );
			if(num == -1){
				int op = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Exit?");
				if(op == JOptionPane.YES_OPTION){
					System.exit(-1);
				}else{
					continue;
				}
			}
			while(num < 3 || num > 6 ){
				JOptionPane.showMessageDialog(frame, "Number of Players must be between 3 and 6", "",JOptionPane.WARNING_MESSAGE);
				num = inputNumber(frame,"Input number of Players: " );
			}
			return num;
		}
	}

	public static void initializeTokens(BoardFrame frame, Set<Piece> pieces){
		GameOfCluedo game = frame.getGame();
		BoardCanvas canvas = frame.boardCanvas;

		for(Piece p : pieces){
			Point point = game.getBoard().tokenToPos.get(p.token);
			int x = (int) point.getX();
			int y = (int) point.getY();

			canvas.squares[x][y] = new Square(new Point(x,y));
			canvas.squares[x][y].setPiece(p);
		}


		frame.drawTokens(frame.boardCanvas.getGraphics());
		frame.repaint();
	}


	public static void main(String[] args){

		BoardFrame frame = new BoardFrame();
		GameOfCluedo game = frame.getGame();
		Board board = frame.getBoard();

		JOptionPane.showMessageDialog(frame, "Welcome to the Game of Cluedo");

		int num = getNumOfPlayers(frame);

		ArrayList<Player> players = inputPlayers(frame,game, num);
		game.players = players;

		String initial = "";
		int counter = 1;
		for(Player p: players){
			double x = p.getLocation().getX();
			double y = p.getLocation().getY();
			initial+= "Player "+ counter +": "+p.getName()+" has taken the role of "+p.getToken().token+ 
					"("+p.getToken().colour+") 		@ ("+x+","+y+")\n"; 
			counter++;
		}
		JOptionPane.showMessageDialog(frame, initial, "Players Information", JOptionPane.INFORMATION_MESSAGE);


		//shuffle cards and deal them to players
		Collections.shuffle(game.cards);
		int count = 0;

		ArrayList<Card> revealed = new ArrayList<Card>();

		int i, j = 0,numCardsLeft = game.cards.size() - ((game.cards.size()/num)*num);
		boolean divisible = game.cards.size() % num == 0;

		int size = game.cards.size();
		if(!divisible) size = game.cards.size() - numCardsLeft;

		for(i = 0; i < size; i++){

			Card c = game.cards.get(i);			

			players.get(count).addCard(c);
			count++;		
			if(count == num){
				count = 0;
			}
		}

		if(!divisible){
			j = i;
			while(j < game.cards.size()){
				revealed.add(game.cards.get(j));
				j++;
			}
		}



		turn = 1;
		dice = new Dice();

		setupButtons(frame);
		Set<Piece> pieces = new HashSet<Piece>();

		pieces.addAll(game.getBoard().pieces);

		for(Player play : players){
			pieces.add(play.getToken());
		}

		initializeTokens(frame, pieces);

		while(1 == 1){


			for(int n = 0; n < players.size(); n++){
				start = true;
				endTurn = false;
				while(!endTurn){
					game.currentPlayer = players.get(n);					
					frame.updateCards(game.currentPlayer);
					frame.repaint();



					frame.drawTokens(frame.boardCanvas.getGraphics());

				}
				frame.repaint();
			}
			frame.repaint();
			turn++;
		}

















	}






}
