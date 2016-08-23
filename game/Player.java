package game;

import java.awt.Point;
import java.util.ArrayList;

import items.Card;
import items.Character.CharacterToken;
import items.Piece;
import items.Room;

/***
 * Represents a player in the Cluedo Game, particularly record what Cards are in the Player's hand, Player token,
 * location of Player, and Room that the Player is in.
 * 
 * @author Andre L Westerlund
 *
 */
public class Player {

	private boolean active; //determines if a player is eliminated or not
	private String name;
	private Point location;
	private Piece character; // piece token of Player
	public int num;
	private ArrayList<Card> hand; 
	private Room room = null; 
	
	/**
	 * Constructs a Player object with a given name, character token and location
	 * 
	 * @param name
	 * @param character
	 * @param location
	 */
	public Player(String name, Piece character, Point location) {
		this.name = name;
		this.active = true;
		this.character = character;
		this.location = location;
		this.hand = new ArrayList<Card>(); 
	}
	
	/**
	 * Eliminates the Player from the Game of Cluedo
	 */
	public void lose() {
		this.active = false;
	}

	
	/**
	 * Sets the Player in a Room, and removes the Player from a location/Point on the Board
	 * 
	 * @param newRoom
	 */
	public void setRoom(Room newRoom) {
		this.room = newRoom;
		this.location = null;
	}
	
	/**
	 * Sets the Player at a Point/Location on the Board, and Removes the Player from within a Room
	 * 
	 * @param coord
	 */
	public void setLocation(Point coord) {
		this.location = coord;
		this.room = null;

	}
	
	/**
	 * Determines whether a Player is still active in the Game
	 * 
	 * @return
	 */
	public boolean getActive() {
		return active;
	}
	
	/**
	 * Get Player name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get Player's Piece Token
	 * 
	 * @return
	 */
	public Piece getToken(){
		return character;
	}
	
	/**
	 * Adds a Card Object into the Player's Hand of Cards
	 * 
	 * @param c
	 */
	public void addCard(Card c){
		hand.add(c);
	}
	
	/**
	 * Gets the Player's Hand
	 * 
	 * @return
	 */
	public ArrayList<Card> getHand(){
		return this.hand;
	}
	
	/**
	 * Determines if a Character is the Player's Piece Token Character
	 * 
	 * @param c
	 * @return
	 */
	public boolean hasToken(String c){
		return c.equals(this.character.token);
	}
	
	/**
	 * Gets the room the Player is in, null if Player is not in a room
	 * 
	 * @return
	 */
	public Room getRoom(){
		return this.room;
	}
	
	/**
	 * Gets the Player's current Point/location
	 * 
	 * @return
	 */
	public Point getLocation() {
		return this.location;
	}
	
	/**
	 * Returns Player information
	 * 
	 */
	public String toString(){
		return "Player: "+name+"	Token: "+this.character.token +"	Location On Board: ["+(int)this.location.getX()+"]["+(int)this.location.getY()+"] "; 
	}


}
