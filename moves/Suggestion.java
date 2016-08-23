package moves;


import items.*;
import items.Character;

/**
 * Represents the Suggestion Move in the Cluedo Game. It consists of a Character, Weapon, and Room. A Player 
 * performs this move and hypothesizing to solve the murder. When this happens, each Player responds 
 * in a clock-wise fashion until a suggestion is refuted; or all players have indicated they cannot refute the
 * suggestion.
 * 
 * @author Andre L Westerlund
 *
 */
public class Suggestion {
	
	Character character;
	Weapon weapon;
	Room room;
	
	/**
	 * Constructs a Suggestion Object from a given character, weapon, and room.
	 * @param charc
	 * @param weapon
	 * @param room
	 */
	public Suggestion(Character charc, Weapon weapon, Room room){
		this.character = charc;
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
