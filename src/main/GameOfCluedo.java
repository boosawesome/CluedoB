package main;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import game.*;
import items.Card;
import items.Character.CharacterToken;
import items.Character;
import items.Envelope;
import items.Location;
import items.Room;
import items.Room.RoomToken;
import items.Weapon;
import items.Weapon.WeaponToken;
import items.WeaponObject;
import moves.Accusation;
import moves.Suggestion;
import ui.BoardFrame;

/**
 * Represents a game of cluedo. This contains an internal representation of
 * the game board, and provides a number of methods for moving players around
 * the board, making suggestions, making accusations, etc.
 * 
 * Inspiration from Monopoly SWEN221 Assignment 6 - Dave JP 
 *
 * @author Andre L Westerlund
 *
 */
public class GameOfCluedo {
	
	/**
	 * Directions a Player can take/face
	 * @author Andre L Westerlund
	 *
	 */
	public enum Direction{
		NORTH,SOUTH,EAST,WEST
	}
	
	public boolean state = false;
	private Board board;
	private Envelope solution; 
	public boolean valid = false; //checking if a move performed by a player is valid

	public List<Card> cards;// deck of all the Cards

	List<Character> characters;
	List<Weapon> weapons;
	List<Room> rooms;

	List<Location> locations;
	List<WeaponObject> objects;

	public Player refuter = null; //current Player who refuted a Suggestion
	public Player currentPlayer = null;
	public ArrayList<Player> players;
	
	/**
	 * Constructs a GameOfCluedo Object
	 */
	public GameOfCluedo(){
		board = new Board();
		locations = new ArrayList<Location>();
		objects = new ArrayList<WeaponObject>();

		cards = new ArrayList<Card>();
		characters = new ArrayList<Character>();
		weapons = new ArrayList<Weapon>();
		rooms = new ArrayList<Room>();

		/*
		 * Initialize all the Cards of the Game in their Categories 
		 */
		
		characters.add(new Character(CharacterToken.MISS_SCARLETT));
		characters.add(new Character(CharacterToken.COLONEL_MUSTARD));
		characters.add(new Character(CharacterToken.MRS_WHITE));
		characters.add(new Character(CharacterToken.THE_REVEREND_GREEN));
		characters.add(new Character(CharacterToken.MRS_PEACOCK));
		characters.add(new Character(CharacterToken.PROFESSOR_PLUM));


		weapons.add(new Weapon(WeaponToken.CANDLESTICK));
		weapons.add(new Weapon(WeaponToken.DAGGER));
		weapons.add(new Weapon(WeaponToken.LEAD_PIPE));
		weapons.add(new Weapon(WeaponToken.REVOLVER));
		weapons.add(new Weapon(WeaponToken.ROPE));
		weapons.add(new Weapon(WeaponToken.SPANNER));


		rooms.add(new Room(RoomToken.BALLROOM));
		rooms.add(new Room(RoomToken.BILLIARD_ROOM));
		rooms.add(new Room(RoomToken.CONSERVATORY, RoomToken.LOUNGE));
		rooms.add(new Room(RoomToken.DINING_ROOM));
		rooms.add(new Room(RoomToken.HALL));
		rooms.add(new Room(RoomToken.KITCHEN, RoomToken.STUDY));
		rooms.add(new Room(RoomToken.LIBRARY));
		rooms.add(new Room(RoomToken.LOUNGE, RoomToken.CONSERVATORY));
		rooms.add(new Room(RoomToken.STUDY, RoomToken.KITCHEN));
		
		
		 //Fill and Set Locations and Weapon Objects
		 
		for(Room rm : rooms){
			locations.add(new Location(rm));
		}

		for(Weapon wp : weapons){
			objects.add(new WeaponObject(wp));
		}
		
		//randomize Locations and Objects

		Collections.shuffle(locations);
		Collections.shuffle(objects);
		
		//place WeaponObjects randomly in a Location (Only one weapon per Location)
		for(int i = 0; i < objects.size(); i++){
			locations.get(i).setWeaponObject(objects.get(i));
			objects.get(i).setRoom(locations.get(i).room);
		}
		
		//shuffle categories of Cards
		Collections.shuffle(characters);
		Collections.shuffle(weapons);
		Collections.shuffle(rooms);

		//Take a random card from each Category and create Solution/Envelope
		Character c = (Character)getRandomCard(characters);
		Weapon w = (Weapon) getRandomCard(weapons);
		Room r = (Room) getRandomCard(rooms);

		characters.remove(c);
		weapons.remove(w);
		rooms.remove(r);

		solution = new Envelope(c,w,r);

		//set deck all shuffled

		cards.addAll(rooms);
		cards.addAll(weapons);
		cards.addAll(characters);

		Collections.shuffle(cards);

	}

	/**
	 * Gets the Board
	 * @return
	 */
	public Board getBoard(){
		return board;
	}

	/**
	 * Gets the Envelope/Solution
	 * @return
	 */
	public Envelope getSolution(){
		return this.solution;
	}
	
	/**
	 * Moves the Player to a Point on the Board based on DiceRoll and Direction
	 * 
	 * @param diceRoll
	 * @param d
	 * @param p
	 * @param players
	 */
	public void movePlayer(int diceRoll, Direction d, Player p, ArrayList<Player> players){

		valid = false;
		int x = (int) p.getLocation().getX();
		int y = (int) p.getLocation().getY();

		int prevX = 0 + x;
		int prevY = 0 + y;


		while(diceRoll != 0){
			if(d.equals(Direction.NORTH)){
				y --;
			}else if(d.equals(Direction.SOUTH)){
				y ++;
			}else if(d.equals(Direction.WEST)){
				x --;
			}else{
				x ++;
			}
			diceRoll--;
			if(!checkValidMove(x,y, p, players)){ 
				return; // check if valid movement each time
			}
		}
		
		//set new Location/Point of Player if valid Movement
		if(checkValidMove(x,y,p, players)){
			p.setLocation(new Point(x,y));
			//updates Board
			board.getMap()[x][y] = "s"; 	
			board.getMap()[prevX][prevY] = "x";
			System.out.println(board.toString());
			valid = true;
		}
	}
	
	/**
	 * Determines if a Player is at the entrance of a Room
	 * @param p
	 * @return
	 */
	public boolean isAtDoor(Player p){
		for(Map.Entry<Point, String> entry : board.entrances.entrySet()){
			if(entry.getKey().getX() == p.getLocation().getX() && entry.getKey().getY() == p.getLocation().getY()){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the Point (Entrance to the Room) where the Player is on 
	 * @param p
	 * @return
	 */
	public Point getDoor(Player p){
		for(Map.Entry<Point, String> entry : board.entrances.entrySet()){
			if(entry.getKey().getX() == p.getLocation().getX() && entry.getKey().getY() == p.getLocation().getY()){
				return entry.getKey();
			}
		}

		return null;
	}

	/**
	 * Checks if Player is on Entrance/Door Point and moves Player within the Room, makes Point null
	 * @param p
	 */
	public void enterRoom(Player p){
		for(Map.Entry<Point, String> entry : board.entrances.entrySet()){
			if(entry.getKey().getX() == p.getLocation().getX() && entry.getKey().getY() == p.getLocation().getY()){
				Room r = board.getRoom(entry.getValue());
				p.setRoom(r);

				for(Location l : locations){ //adds Player to Location
					if(l.room.equals(p.getRoom())){
						board.getMap()[(int) entry.getKey().getX()][(int)entry.getKey().getY()] = "x"; //updates Board
						l.addPlayer(p);
						return;
					}
				}
			}
		}
		System.out.println("Cannot enter a room, Player is not at the door\n");
		System.out.println("*************************************************");
	}

	/**
	 * Moves Player out of a Room and onto the nearest Entrance/Door Point
	 * @param r
	 * @param p
	 */
	public void exitRoom(Room r, Player p){
		if(p.getRoom() == null ){
			System.out.println("Cannot exit room if Player is not in room");
			return;
		}
		else if(r == null){
			System.out.println("Invalid Room!!");
		}

		if(p.getRoom().equals(r)){

			for(Map.Entry<Point, String> entry : board.entrances.entrySet()){

				if(r.getName().equals(entry.getValue())){
					for(Location l : locations){ 
						if(l.room.equals(r)){
							l.removePlayer(p); //removes Player from Location
							board.getMap()[entry.getKey().x][entry.getKey().y] = "s"; //update Board
							p.setLocation(entry.getKey());
							return;
						}
					}
				}
			}
		}

	}

	/**
	 * Checks if a Player's move on the Board and in the Game is valid 
	 * @param x
	 * @param y
	 * @param player
	 * @param players
	 * @return
	 */
	public boolean checkValidMove(int x, int y, Player player, ArrayList<Player> players) {
		if(x < 0 || x > 24 || y < 0 || y > 24){
			System.out.println("Out of Bounds!");
			return false;
		}

		if(player.getLocation() == null)return false; 

		for(Player p : players){
			if(p == player) continue; //making sure that it does not check itself
			if(p.getLocation() == null) continue; //if another player is in a room, continue
			if((board.getMap()[x][y].equals("x")||board.getMap()[x][y].equals("s"))&& p.getLocation().getX() == x && p.getLocation().getY() == y){
				System.out.println("Error! Cannot move into that space, Occupied by another Player");
				System.out.println("*************************************************\n\n");
				return false;
			}
		}
		if(board.getMap()[x][y].equals("x")) return true;
		System.out.println("Cannot move into a wall\n\n");
		System.out.println("*************************************************\n\n");

		return false;

	}

	/**
	 * Moves a Player from one Room to another Room via Stairwell and if and only if the Rooms are connected
	 * @param room
	 * @param p
	 */
	public void useStairWell(Room room, Player p, BoardFrame frame){
		if(p.getRoom() == null){
			JOptionPane.showMessageDialog(frame, "Cannot Use StairWell when Player "+p.num+" is not in a Room!");
			return;
		}else if(!p.getRoom().hasStairWell()){
			JOptionPane.showMessageDialog(frame, room.picture +" does not have StairWell!");
			return;
		}
		
		if(p.getRoom().equals(room) && room.hasStairWell()){
			p.setRoom(null);
			Room opposite = board.getRoom(room.getOpposite());
			getLocation(room).removePlayer(p);
			getLocation(opposite).addPlayer(p);
			p.setRoom(opposite);
			JOptionPane.showMessageDialog(frame, p.getName()+ " used the Stairwell to get from "+ room.getName() +" to "+ opposite.getName());
		}
	}
	
	/**
	 * Gets a WeaponObject from a given Weapon
	 * @param weapon
	 * @return
	 */
	public WeaponObject getObject(Weapon weapon){
		for(WeaponObject ob : objects){
			if(ob.getWeapon().equals(weapon)){
				return ob;
			}
		}
		return null;
	}
	
	/**
	 * Gets a Location from a given Room
	 * @param r
	 * @return
	 */
	public Location getLocation(Room r){
		for(Location l : locations){
			if(l.room.equals(r) || l.room == r){
				return l;
			}
		}
		return null;
	}
	
	/**
	 * Runs through Players excluding Player, and checks if a Suggestion can be refuted if not return null  
	 * @param c
	 * @param w
	 * @param r
	 * @param player
	 * @param players
	 * @return
	 */
	public Card refute(Character c, Weapon w, Room r, Player player, ArrayList<Player> players){

		for(Player p : players){
			if(p == player)continue;
			if(p.hasToken(c.getName())){ 
				p.setRoom(r);
				getLocation(r).addPlayer(p);
			} 

			Collections.shuffle(p.getHand());
			
			//run through each player's hands
			for(Card card : p.getHand()){
				
				//if there is a match return that Card and assign that player to refuter for recording
				
				if(card instanceof Weapon){
					Weapon weapon = (Weapon) card;

					if(weapon.equals(w) && weapon.getName().equals(w.getName())){
						refuter = p;
						return weapon;
					}


				}else if(card instanceof Room){
					Room room = (Room) card;
					if(r.equals(room) && r.getName().equals(room.getName())){
						refuter = p;
						return room;
					}


				}else if(card instanceof Character){
					Character character = (Character) card;
					if(character.equals(c) && character.getName().equals(c.getName())){
						refuter = p;
						return character;
					}

				}

			}

		}

		return null;
	}
	
	/**
	 * Takes a Suggestion Object and checks among the other players if the Suggestion can be refuted.
	 * If refuted, the Player is shown the Card and if not will return null and this will mean it is a
	 * Clue of the Solution.
	 * @param suggestion
	 * @param player
	 * @param players
	 */
	public void suggest(Suggestion suggestion, Player player, ArrayList<Player> players){
		refuter = null;
		Character c = suggestion.getCharacter();
		Weapon w = suggestion.getWeapon();
		Room r = suggestion.getRoom();

		if(player.getRoom() == null && player.getLocation() != null){
			System.out.println("ERROR! Cannot make a suggestion if player is not in a Room!");
			return;
		}
		if(!player.getRoom().equals(r) && !getLocation(r).hasPlayer(player)){
			System.out.println("ERROR! Cannot make a suggestion of "+r.getName() + " When "+player.getName()+" is not in "+r.getName());
			return;
		}

		//placing the WeaponObject of the Suggestion Object's Weapon in the current Location/Room
		Location loc = getLocation(r);
		WeaponObject ob = getObject(w);
		loc.setWeaponObject(ob);
		ob.setRoom(loc.room);

		Card result = refute(c,w,r, player, players);

		if(refuter != null && result != null){ //Suggestion refuted
			System.out.println(refuter.getName()+" has refuted your Suggestion");
			System.out.println("************************************\n\n");
			System.out.println(refuter.getName()+" has the "+result.getName()+" card");
			System.out.println("************************************\n\n");
		}else if(result == null){//Suggestion not refuted
			System.out.println("************************************");
			for(Player play : players){
				System.out.println(play.getName()+" has not been able to refute your suggestion");
			}
			System.out.println("************************************\n\n");
		}
	}
	
	/**
	 * Determines the result of the Player's Accusation 
	 * @param accusation
	 * @param player
	 * @return
	 */
	public boolean accuse(Accusation accusation, Player player){
		
		Character c = accusation.getCharacter();
		Weapon w = accusation.getWeapon();
		Room r = accusation.getRoom();

		Character cSolution = this.solution.getCharacter();
		Weapon wSolution = this.solution.getWeapon();
		Room rSolution = this.solution.getRoom();

		if(c.equals(cSolution) && w.equals(wSolution)&&r.equals(rSolution)){
			state = true;
			return true;
		}
		return false;

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
	 * Gets a Random Card from a Category that extends Card
	 * @param list
	 * @return
	 */
	public Card getRandomCard(List<? extends Card> list){
		int size = list.size();
		int item = new Random().nextInt(size); 
		int i = 0;
		for(Card c : list)
		{
			if (i == item){
				list.remove(c);
				return c;
			}
			i = i + 1;
		}
		return null;
	}
	


	/**
	 * Checks that when a Game starts, WeaponObjects are randomly distributed amongst the Rooms.
	 * @param args
	 */
	public static void main(String[] args){
		GameOfCluedo game = new GameOfCluedo();



		for(Location loc : game.locations){

			WeaponObject wp = loc.getWeaponObject();
			String s;
			if(wp == null) s = "";
			else s = loc.getWeaponObject().getName();
			System.out.println("Room: "+ loc.room.getName() +", Weapon: "+ s);
		}

	}


}
