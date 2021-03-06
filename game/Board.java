package game;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import items.Character;
import items.Room;
import items.Weapon;
import items.Character.CharacterToken;
import items.Piece;
import items.Room.RoomToken;
import items.Weapon.WeaponToken;

/**
 * Represents the Cluedo Board, which is made up of 9 Rooms/Locations.
 * The board simply provides access methods to determine the location 
 * at a given Point on the board. Positions are taken in a Clock wise fashion
 * 
 * @author Andre L Westerlund
 *
 */
public class Board {
	
	public static String[][] map; //Points in the Board 24x25
	public Map<String, Point> tokenToPos;
	List<Point> startingPositions; 
	private List<Room> rooms; 
	public Map<Point, String> entrances; //contains the Points that allow access into Rooms 
	
	public ArrayList<Piece> pieces;
	
	//although already in GameOfCluedo, used here due to size of cards in Game reduced by 3 because of the Envelope Class
	List<Character> characters; 
	List<Weapon> weapons;
	
	public Map<String, ArrayList<Point>> roomSpaces;	
	
	/**
	 * Constructs a Board Object
	 */
	public Board() {
		rooms = new ArrayList<Room>();
		map = new String[24][25];
		entrances = new HashMap<Point, String>();
		
		//redundant (see GameOfCluedo) but needed because of Structuring of classes and data architectures 
		weapons = new ArrayList<Weapon>();
		weapons.add(new Weapon(WeaponToken.CANDLESTICK));
		weapons.add(new Weapon(WeaponToken.DAGGER));
		weapons.add(new Weapon(WeaponToken.LEAD_PIPE));
		weapons.add(new Weapon(WeaponToken.REVOLVER));
		weapons.add(new Weapon(WeaponToken.ROPE));
		weapons.add(new Weapon(WeaponToken.SPANNER));
		characters = new ArrayList<Character>();
		characters.add(new Character(CharacterToken.MISS_SCARLETT));
		characters.add(new Character(CharacterToken.COLONEL_MUSTARD));
		characters.add(new Character(CharacterToken.MRS_WHITE));
		characters.add(new Character(CharacterToken.THE_REVEREND_GREEN));
		characters.add(new Character(CharacterToken.MRS_PEACOCK));
		characters.add(new Character(CharacterToken.PROFESSOR_PLUM));
		
		// s meaning a Player, x meaning a Point a Player can move to, / meaning cannot move to
		
		String input = 
							  "/ / / / / / / / / s / /  / / s / / / / / / / / / \n"
							+ "/ / / / / / / x x x / /  / / x x x / / / / / / / \n"
							+ "/ / / / / / x x / / / /  / / / / x x / / / / / / \n"
							+ "/ / / / / / x x / / / /  / / / / x x / / / / / / \n"
							+ "/ / / / / / x x / / / /  / / / / x x / / / / / / \n"
							+ "/ / / / / / x x / / / /  / / / / x x x / / / / / \n"
							+ "/ / / / / / x x / / / /  / / / / x x x x x x x s \n"
							+ "/ x x x x x x x / / / /  / / / / x x x x x x x / \n"
							+ "x x x x x x x x x x x x  x x x x x x / / / / / / \n"
							+ "/ x x x x x x x x x x x  x x x x x x / / / / / / \n"
							+ "/ / / / / / x x x x / /  / / / x x x / / / / / / \n"
							+ "/ / / / / / / / x x / /  / / / x x x / / / / / / \n"
							+ "/ / / / / / / / x x / /  / / / x x x / / / / / / \n"
							+ "/ / / / / / / / x x / /  / / / x x x x x x x x / \n"
							+ "/ / / / / / / / x x / /  / / / x x x / / / / / / \n"
							+ "/ / / / / / / / x x / /  / / / x x / / / / / / / \n"
							+ "/ x x x x x x x x x / /  / / / x x / / / / / / / \n"
							+ "s x x x x x x x x x x x  x x x x x / / / / / / / \n"
							+ "/ x x x x x x x x / / /  / / / x x x / / / / / / \n"
							+ "/ / / / / / / x x / / /  / / / x x x x x x x x s \n"
							+ "/ / / / / / / x x / / /  / / / x x x x x x x x / \n"
							+ "/ / / / / / / x x / / /  / / / x x / / / / / / / \n"
							+ "/ / / / / / / x x / / /  / / / x x / / / / / / / \n"
							+ "/ / / / / / / x x / / /  / / / x x / / / / / / / \n"
							+ "/ / / / / / / s / / / /  / / / / x / / / / / / / \n";


		
		/*
		 * Reads in Input via scanner into the Map, this gives the structure of the Board
		 */
		Scanner scan = new Scanner(input);

		int x = 0;
		int y = 0;

		while(scan.hasNext()){

			if(x == 24){
				x = 0;
				y++;
				if(y == 25) break;
			}
			if(scan.hasNext("\n")){
				scan.nextLine();
			}

			map[x][y] = scan.next();
			x++;



		}

		startingPositions = new ArrayList<Point>();

		startingPositions.add(new Point(7, 24));
		startingPositions.add(new Point(0, 17));
		startingPositions.add(new Point(9,0));
		startingPositions.add(new Point(14,0));
		startingPositions.add(new Point(23,6));
		startingPositions.add(new Point(23,19));

		pieces = new ArrayList<Piece>();
		pieces.add(new Piece("Miss Scarlett"));
		pieces.add(new Piece("Colonel Mustard"));
		pieces.add(new Piece("Mrs White"));
		pieces.add(new Piece("The Reverend Green"));
		pieces.add(new Piece("Mrs Peacock"));
		pieces.add(new Piece("Professor Plum"));
		

		rooms.add(new Room(RoomToken.DINING_ROOM));
		rooms.add(new Room(RoomToken.HALL));
		rooms.add(new Room(RoomToken.BALLROOM));
		rooms.add(new Room(RoomToken.BILLIARD_ROOM));
		rooms.add(new Room(RoomToken.LIBRARY));
		rooms.add(new Room(RoomToken.KITCHEN, RoomToken.STUDY));
		rooms.add(new Room(RoomToken.STUDY, RoomToken.KITCHEN));
		rooms.add(new Room(RoomToken.CONSERVATORY, RoomToken.LOUNGE));
		rooms.add(new Room(RoomToken.LOUNGE, RoomToken.CONSERVATORY));
		


		tokenToPos = new HashMap<String, Point>();
		tokenToPos.put("Miss Scarlett", new Point(7,24));
		tokenToPos.put("Colonel Mustard", new Point(0,17));
		tokenToPos.put("Mrs White", new Point(9,0));
		tokenToPos.put("The Reverend Green", new Point(14,0));
		tokenToPos.put("Mrs Peacock", new Point(23,6));
		tokenToPos.put("Professor Plum", new Point(23,19));


		entrances.put(new Point(4, 7), "KITCHEN");
		entrances.put(new Point(6, 18), "LOUNGE");
		entrances.put(new Point(18, 5), "CONSERVATORY");
		entrances.put(new Point(17, 20), "STUDY");
		entrances.put(new Point(8, 12), "DINING_ROOM");
		entrances.put(new Point(6, 17), "DINING_ROOM");
		entrances.put(new Point(17,8), "BILLIARD_ROOM");
		entrances.put(new Point(22, 12), "BILLIARD_ROOM");
		entrances.put(new Point(20, 12), "LIBRARY");
		entrances.put(new Point(16, 15), "LIBRARY");
		entrances.put(new Point(7, 5), "BALLROOM");
		entrances.put(new Point(9, 8), "BALLROOM");
		entrances.put(new Point(15, 8), "BALLROOM");
		entrances.put(new Point(16, 5), "BALLROOM");
		entrances.put(new Point(11, 17), "HALL");
		entrances.put(new Point(12, 17), "HALL");
		entrances.put(new Point(15, 20), "HALL");
		
		roomSpaces = new HashMap<String, ArrayList<Point>>();
		
		//slots in rooms that a token or weapon object can occupy
		roomSpaces.put("LOUNGE", new ArrayList<Point>(Arrays.asList(new Point(0,23), new Point(1,23), new Point(2,23), new Point(3,23), new Point(4,23), new Point(5,23))));
		roomSpaces.put("KITCHEN", new ArrayList<Point>(Arrays.asList(new Point(0,1), new Point(1,1), new Point(2,1), new Point(3,1), new Point(4,1), new Point(0,2))));
		roomSpaces.put("CONSERVATORY", new ArrayList<Point>(Arrays.asList(new Point(19,1), new Point(20,1), new Point(21,1), new Point(22,1), new Point(23,1), new Point(19,2))));
		roomSpaces.put("STUDY", new ArrayList<Point>(Arrays.asList(new Point(19,23), new Point(20,23), new Point(21,24), new Point(22,23), new Point(23,23))));
		roomSpaces.put("DINING_ROOM", new ArrayList<Point>(Arrays.asList(new Point(0,10), new Point(0,11), new Point(0,12), new Point(0,13), new Point(0,14), new Point(1,10))));
		roomSpaces.put("HALL", new ArrayList<Point>(Arrays.asList(new Point(9,23),  new Point(10,23), new Point(11,23), new Point(12,23), new Point(13,23), new Point(14,23))));
		roomSpaces.put("BILLIARD_ROOM", new ArrayList<Point>(Arrays.asList(new Point(23,8), new Point(23,9), new Point(23,10), new Point(23,11))));
		roomSpaces.put("BALLROOM", new ArrayList<Point>(Arrays.asList(new Point(8,2), new Point(9,2), new Point(10,2), new Point(11,2), new Point(12,2), new Point(13,2))));
		roomSpaces.put("LIBRARY", new ArrayList<Point>(Arrays.asList(new Point(14,23), new Point(15,23), new Point(16,23), new Point(17,23), new Point(18,23))));
	
	}
	
	public Character getCharacterToken(String token){
		switch(token){
		case "Miss Scarlett":
			return getCharacter("MISS_SCARLETT");
		case "Colonel Mustard":
			return getCharacter("COLONEL_MUSTARD");
		case "Mrs White":
			return getCharacter("MRS_WHITE");
		case "The Reverend Green":
			return getCharacter("THE_REVEREND_GREEN");
		case "Mrs Peacock":
			return getCharacter("MRS_PEACOCK");
		case "Professor Plum":
			return getCharacter("PROFESSOR_PLUM");
		}
		return null;
		
	}
	
	
	/**
	 * Gets a Room from a given String, null if not found
	 * @param s
	 * @return
	 */
	public Room getRoom(String s){
		for(int i = 0; i < rooms.size(); i++){
			if(rooms.get(i).getName().equals(s)){
				return rooms.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Gets a Room via Point/Location, null if not found
	 * @param c
	 * @return
	 */
	public String getRoom(Point c) {
		if (entrances.containsKey(c)) {
			return entrances.get(c);
		} else
			return null;
	}
	
	/**
	 * Gets the map Outlying the structure of the Board
	 * @return
	 */
	public String[][] getMap(){
		return map;
	}
	
	/**
	 * Gets Starting Points of Players
	 * @return
	 */
	public List<Point> getStartingPositions(){
		return startingPositions;
	}
	
	/**
	 * Gets a Character from a given String
	 * @param s
	 * @return
	 */
	public Character getCharacter(String s){
		for(Character c : characters){
			if(c.getName().equals(s)){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Gets a Weapon from a given String
	 * @param s
	 * @return
	 */
	public Weapon getWeapon(String s){
		for(Weapon w : weapons){
			if(w.getName().equals(s)){
				return w;
			}
		}
		return null;
	}
	
	/**
	 * Prints out the Board (25x25)
	 */
	public String toString(){

		int x = 0;
		int y = 0;

		String board = "";

		while(y < 25){
			while(x < 25){
				board+= map[x][y]; 

				x++;
			}
			board+=" \n";
			x = 0;
			y++;
		}

		return board;

	}
	
	public Piece getPiece(String p){
		for(Piece piece : pieces){
			if(piece.token.equals(p)){
				return piece;
			}
			
		}
		return null;
	}
	
	
	
	/**
	 * Print out Positions of the Players
	 * @param args
	 */
	public static void main(String[] args){
		Board b = new Board();

		for(Point p : b.startingPositions){

			System.out.println(b.map[(int)p.getX()][(int)p.getY()]); //print out the positions of players and positions on board to see match

		}

	}


}