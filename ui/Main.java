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
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
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
import items.Room;
import items.Weapon;

public class Main {

	public static int currentRoll;
	public static int turn;
	public static Dice dice;
	public static boolean start = true;
	public static boolean endTurn = false;
	public static int playerNum = 0;
	public static boolean enterPrompt = false;
	public static boolean exitPrompt = false;
	public static boolean atDoor = false;
	public static boolean game = false;

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
			piece.setPoint(point);
			Player p = new Player(name, piece, point);
			p.num = i;
			players.add(p);
		}


		return players;

	}

	public static void movePlayer(BoardFrame frame, Player player){
		//game.movePlayer(roll, d, p, players);
		//user selects a square
		//check if square is within roll range
		if(currentRoll == 0 || start) return;

		if(frame.selected != null){
			while(currentRoll > 0){
				double px = player.getToken().getPoint().getX();
				double py = player.getToken().getPoint().getY();

				double dx = frame.selected.point.x; 
				double dy = frame.selected.point.y;

				int diffX = (int) Math.abs(dx - px);
				int diffY = (int) Math.abs(dy - py);

				if(frame.boardCanvas.squares[(int)dx][(int)dy].hasPiece()){
					return;
				}

				if(diffX + diffY > currentRoll && !start){
					JOptionPane.showMessageDialog(frame, "Your roll does not take you that far!");
					frame.boardCanvas.deselect = true;
					frame.selected = null;
					frame.drawTokens(frame.boardCanvas.getGraphics());
					return;
				}else if(diffX + diffY == 0){
					frame.drawTokens(frame.boardCanvas.getGraphics());
					return;
				}else if(diffX + diffY <= currentRoll) {

					player.getToken().setPoint(new Point((int)dx, (int)dy));
					frame.boardCanvas.squares[(int)dx][(int)dy].setPiece(player.getToken());
					frame.boardCanvas.squares[(int)px][(int)py].setPiece(null);
					frame.repaint();
					frame.drawTokens(frame.boardCanvas.getGraphics());
					frame.updateCards(player);
					currentRoll -= (diffX + diffY);
					if(frame.getGame().isAtDoor(player)) atDoor = true;
				}

				break;
			}
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
			
			int pane = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Make an Accusation?"); 
			
			if(pane == JOptionPane.YES_OPTION){
			makeAccusation(frame);
			}
			else return;
		
		
		}else if(action.equals("suggest")){
			makeSuggestion(frame);
		}else if(action.equals("rollDice")){
			if(!start && exitPrompt){
				JOptionPane.showMessageDialog(frame, "Player "+ frame.getGame().currentPlayer.num+" cannot roll the Dice \n"+"When Player "+frame.getGame().currentPlayer.num+ " chose to Stay in Room!" ,"",JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(!start){
				JOptionPane.showMessageDialog(frame, "Cannot roll the Dice Again!!!");
				return;
			}

			frame.boardCanvas.deselect = false;
			currentRoll = dice.roll();
			JOptionPane.showMessageDialog(frame, "		"+frame.getGame().currentPlayer.getName()+" rolls a "+currentRoll+"\n Please Select a Space to Move");

			movePlayer(frame, frame.getGame().currentPlayer);
			start = false;

		}else if(action.equals("endTurn")){
			if(currentRoll > 0){
				JOptionPane.showMessageDialog(frame, "You have "+ currentRoll+ " Moves Left!!!");
				return;
			}
			endTurn = true;
			frame.boardCanvas.deselect = true;
			frame.boardCanvas.setSelected(frame.selected);
			frame.boardCanvas.repaint();

		}else if(action.equals("stairwell")){
		
				if(frame.getGame().currentPlayer.getRoom() == null){
					JOptionPane.showMessageDialog(frame, "Cannot Use StairWell when Player "+frame.getGame().currentPlayer.num+" is not in a Room!");
					return;
				}else if(!frame.getGame().currentPlayer.getRoom().hasStairWell()){
					JOptionPane.showMessageDialog(frame, frame.getGame().currentPlayer.getRoom().picture +" does not have StairWell!");
					return;
				}

				frame.getGame().useStairWell(frame.getGame().currentPlayer.getRoom(), frame.getGame().currentPlayer, frame);
				frame.drawTokens(frame.boardCanvas.getGraphics());
		}




	}


	private static void makeSuggestion(BoardFrame frame) {
		String player, weapon, room;
		Board board = frame.getBoard();
		GameOfCluedo game = frame.getGame();
		
		if(game.currentPlayer.getRoom() == null){
			JOptionPane.showMessageDialog(frame,"ERROR! Cannot make a suggestion if Player "+game.currentPlayer.num+" is not in a Room!");
			return;
		}
		frame.drawTokens(frame.boardCanvas.getGraphics());

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

		game.suggest(frame, suggest, game.currentPlayer, game.players);
		frame.drawTokens(frame.boardCanvas.getGraphics());

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

		Accusation accuse = new Accusation(board.getCharacter(player), board.getWeapon(weapon), board.getRoom(room));
		items.Character cs = game.getSolution().getCharacter();
		Weapon ws = game.getSolution().getWeapon();
		Room rs = game.getSolution().getRoom();
		
		String csName = cs.getName();
		String wsName = ws.getName();
		String rsName = rs.getName();
		
		if(!game.accuse(accuse, game.currentPlayer)){
			game.currentPlayer.lose();
			int num = game.currentPlayer.num;
			String acc = "\tPlayer "+num+" has Made An Accusation!!!!!\n";
			acc+= "Player "+num+" Choices:\nCharacter: "+player+"\nWeapon: "+weapon+"\nRoom: "+room;
			
			String result = "\tCluedo Solution\nCharacter : "+csName+"\nWeapon :"+wsName+"\nRoom: "+rsName+"\n";
			
			JOptionPane.showMessageDialog(frame,acc);
			JOptionPane.showMessageDialog(frame,result);
			JOptionPane.showMessageDialog(frame,"PLAYER "+num+":  "+game.currentPlayer.getName()+" has been ELIMINATED!!!!!");
			endTurn = true;
		}else{
			int num = game.currentPlayer.num;
			String acc = "\tPlayer "+num+" has Made An Accusation!!!!!\n";
			acc+= "Player "+num+" Choices:\nCharacter: "+player+"\nWeapon: "+weapon+"\nRoom: "+room;
			
			String result = "\tCluedo Solution\nCharacter : "+csName+"\nWeapon :"+wsName+"\nRoom: "+rsName+"\n";
			
			JOptionPane.showMessageDialog(frame,acc);
			JOptionPane.showMessageDialog(frame,result);
			
			JDialog dialog = new JDialog();
			JLabel label = new JLabel("<html>GAME OVER!!!!!!<br>Player "+num+" has Won the GAME!!!!</html>");
			dialog.add( label );
			dialog.pack();
			dialog.setLocationRelativeTo(frame);
			dialog.setVisible(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			Main.game = true;
			
			
		}

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
			
			JOptionPane.showMessageDialog(frame, 
					"*********************************\n\t\t\t\t           TURN   "+turn+" \n*********************************\n",
					"", JOptionPane.INFORMATION_MESSAGE);

			for(int n = 0; n < players.size(); n++){
				if(gameOver(frame)){
					game.state = true;
					JDialog dialog = new JDialog();
					JLabel label = new JLabel("<html>ALL PLAYERS HAVE BEEN ELIMINATED!!!</html><br>GAME OVER!!!!!!");
					dialog.add( label );
					dialog.pack();
					dialog.setLocationRelativeTo(frame);
					dialog.setVisible(true);
					System.exit(0);
				}
				if(!players.get(n).getActive()) continue;
				frame.boardCanvas.deselectP = false;
				selectCurrentToken(frame, players.get(n));
				start = true;
				endTurn = false;
				while(!endTurn){
					if(gameOver(frame)){
						game.state = true;
						JDialog dialog = new JDialog();
						JLabel label = new JLabel("<html>ALL PLAYERS HAVE BEEN ELIMINATED!!!</html><br>GAME OVER!!!!!!");
						dialog.add( label );
						dialog.pack();
						dialog.setLocationRelativeTo(frame);
						dialog.setVisible(true);
						
					}
					game.currentPlayer = players.get(n);					
					frame.updateCards(game.currentPlayer);
					frame.repaint();
		
					if(Main.game){ 
						System.exit(0);
					}
					//exit room
					if(game.currentPlayer.getRoom() != null && start && !exitPrompt){
						Room room = game.currentPlayer.getRoom();
						String rmName = room.getName();
						
						int answer = JOptionPane.showConfirmDialog(frame, "Do you want to exit "+rmName+"?");
						if(answer == JOptionPane.YES_OPTION){
						exitRoom(frame, game.currentPlayer);
						exitPrompt = true;
						start = true;
						}else{
							exitPrompt = true;
							currentRoll = 0;
							start = false;
						}
					}
					
					
					
					
					
					
					if(!start ){
					
						
						movePlayer(frame, game.currentPlayer);
						if(atDoor)enterPrompt = false;
						if(game.isAtDoor(game.currentPlayer) && !enterPrompt && atDoor){
							Point point = game.getDoor(game.currentPlayer);
							String rmName = game.getBoard().getRoom(point);
							Room room = game.getBoard().getRoom(rmName);

							int answer = JOptionPane.showConfirmDialog(frame, "Do you want to enter "+rmName+"?");
							
							if(answer == JOptionPane.YES_OPTION){
								enterPrompt = true;
								atDoor = false;
								currentRoll = 0;
								game.currentPlayer.setRoom(room);
								enterRoom(frame, game.currentPlayer);
								frame.drawTokens(frame.boardCanvas.getGraphics());
								frame.repaint();
								frame.boardCanvas.repaint();
								
							}else{
								enterPrompt = true;
								atDoor = false;
							}
							
							
						}
						
					}
					frame.drawTokens(frame.boardCanvas.getGraphics());

				}
				deselect(frame);
				enterPrompt = false;
				exitPrompt = false;
				atDoor = false;
				
			}
			frame.repaint();
			turn++;
		}

















	}
	 
	

	private static void exitRoom(BoardFrame frame, Player currentPlayer) {
		int x = currentPlayer.getToken().getPoint().x;
		int y = currentPlayer.getToken().getPoint().y;
		Square s = frame.boardCanvas.squares[x][y];
		Piece token = currentPlayer.getToken();
		
		if(s.hasPiece()){
			
			
			for(Map.Entry<Point, String> entry : frame.getBoard().entrances.entrySet()){
				
				if(entry.getValue().equals(currentPlayer.getRoom().getName())){
					int px = (int)entry.getKey().getX();
					int py = (int)entry.getKey().getY();
					
					Square square = frame.boardCanvas.squares[px][py];
					
					if(!square.hasPiece()){
						s.setPiece(null);
						token.setPoint(entry.getKey());
						square.setPiece(token);
						frame.repaint();
						frame.drawTokens(frame.boardCanvas.getGraphics());
						currentPlayer.getRoom().capacity-=1;
						currentPlayer.setRoom(null);
						return;
					}else if(!checkAvailableSpaces(frame, currentPlayer)){
						JOptionPane.showMessageDialog(frame, "Cannot Exit "+currentPlayer.getRoom().getName()+", all Exits Blocked");
						return;
					}
				}	
			}
		
		}
		
	}
	
	private static boolean checkAvailableSpaces(BoardFrame frame, Player player){
		Room r = player.getRoom();
		String rmName = r.getName();
		Board b = frame.getBoard();
		
		for(Map.Entry<Point, String> entry : b.entrances.entrySet()){
			if(entry.getValue().equals(rmName)){
				Point p = entry.getKey();
				int x = (int) p.getX();
				int y = (int) p.getY();
			
				if(!frame.boardCanvas.squares[x][y].hasPiece()) return true;
				
				
			}
		}
		
		
		return false;
		
		
		
		
	}

	private static void selectCurrentToken(BoardFrame frame, Player player) {
		
		int x = (int) player.getToken().getPoint().getX();
		int y = (int) player.getToken().getPoint().getY();
		
		if(frame.boardCanvas.squares[x][y] != null){
			frame.selectedPlayer = frame.boardCanvas.squares[x][y];
			frame.boardCanvas.setSelectedPlayer(frame.selectedPlayer);
			frame.boardCanvas.drawSelectedPlayer(frame.boardCanvas.getGraphics(), frame.boardCanvas.selectedPlayer);
			
		}
		
		
		
	}
	
	private static boolean gameOver(BoardFrame frame){
		
		for(Player p : frame.getGame().players){
			if(p.getActive()){
				return false;
			}
		}
		return true;
	}
	
	private static void deselect(BoardFrame frame){
		frame.boardCanvas.deselect = true;
		frame.selected = null;
		frame.drawTokens(frame.boardCanvas.getGraphics());
		frame.boardCanvas.deselectP = true;
		frame.boardCanvas.selectedPlayer = null;
		frame.repaint();
		frame.boardCanvas.repaint();
	}

	private static void enterRoom(BoardFrame frame, Player currentPlayer) {
		int x = currentPlayer.getToken().getPoint().x;
		int y = currentPlayer.getToken().getPoint().y;
		Square s = frame.boardCanvas.squares[x][y];
		Piece token = currentPlayer.getToken();
		
		if(s.hasPiece()){
			s.setPiece(null);
			

		frame.drawTokens(frame.boardCanvas.getGraphics());
		Room r = currentPlayer.getRoom();
		
		ArrayList<Point> spaces = frame.getBoard().roomSpaces.get(r.getName());
		
		for(int i = 0; i < spaces.size(); i++){

			int sx = (int) spaces.get(i).getX();
			int sy = (int) spaces.get(i).getY();
			
			if(r.capacity == i && !frame.boardCanvas.squares[sx][sy].hasPiece()){
			token.setPoint(spaces.get(i));
			frame.boardCanvas.squares[sx][sy].setPiece(token);
			frame.drawTokens(frame.boardCanvas.getGraphics());
			r.capacity++;
			break;
			}
		}
		
		
		
		
		
		}
		
		
		
		
		
		
	}






}
