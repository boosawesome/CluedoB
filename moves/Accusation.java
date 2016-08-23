package moves;

import items.*;
import items.Character;

/**
 * Represents the Accusation Move in the Cluedo Game. Takes into a Character, Weapon, and Room, and checks
 * it against the Solutions in the Envelope. If Accusation is right, the Player wins the game,
 * else the Player gets Eliminated from the Game.
 * 
 * @author Andre L Westerlund
 *
 */
public class Accusation {

	Character character;
	Weapon weapon;
	Room room;
	
	/**
	 * Constructs an Accusation Object from a given character, weapon, and room
	 * 
	 * @param character
	 * @param weapon
	 * @param room
	 */
	public Accusation(Character character, Weapon weapon, Room room){
		this.character = character;
		this.weapon = weapon;
		this.room = room;
	}
	
	/**
	 * Gets the Character
	 * @return
	 */
	public Character getCharacter() {
		return character;
	}
	
	/**
	 * Gets the Weapon
	 * @return
	 */
	public Weapon getWeapon() {
		return weapon;
	}
	/**
	 * Gets the Room
	 * @return
	 */
	public Room getRoom() {
		return room;
	}





}
