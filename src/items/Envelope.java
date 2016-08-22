package items;
/***
 * Represents the Solution of the GameOfCluedo Class, this is randomized and created in an instance of GameOfCluedo
 * The class contains the murder cards of the Game
 * @author Andre L Westerlund
 *
 */
public class Envelope {

Character character; //made accessible, so less methods to use
Weapon weapon;
Room room;

/**
 * Construct a given Envelope with a given Character, Weapon, and Room
 * 
 * 
 * @param character
 * @param weapon
 * @param room
 */
public Envelope(Character character, Weapon weapon, Room room){
	this.character = character;
	this.weapon = weapon;
	this.room = room;
}
	
/**
 * Gets Character Solution from Envelope 
 * @return
 */
public Character getCharacter() {
	return character;
}
/**
 * Sets the Character of the Envelope
 * @param c
 */
public void setCharacter(Character c){
	character = c;
}

/**
 * Gets Weapon Solution from Envelope 
 * @return
 */
public Weapon getWeapon() {
	return weapon;
}
/**
 * Sets the Weapon of the Envelope
 */
public void setWeapon(Weapon w){
	this.weapon = w;
}

/**
 * Gets Room Solution from Envelope 
 * @return
 */
public Room getRoom() {
	return room;
}

/**
 * Sets the Room of the Envelope
 * @param r
 */
public void setRoom(Room r){
	this.room = r;
}

/**
 * Access Field Names
 */
public String toString(){
	return "Murderer: " + character.getName() + " 	Weapon: "+weapon.getName()+"	 Room: "+room.getName();
}



	
	
	
	
	
}
