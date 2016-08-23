package items;

import java.awt.Image;

/***
 * Represents the Character Card in the Cluedo Game. This class implements Card Interface.
 * 
 * @author Andre L Westerlund
 *
 */
public class Character implements Card{
	
	/**
	 * Characters of Cluedo  
	 * 
	 * @author Andre L Westerlund
	 *
	 */ 
	public enum CharacterToken{ 
		MISS_SCARLETT,
		COLONEL_MUSTARD,
		MRS_WHITE,
		THE_REVEREND_GREEN,
		MRS_PEACOCK,
		PROFESSOR_PLUM
	}
	
	Room room;
	private CharacterToken token;
	String picture;

	/**
	 * Constructs a Character object from a given Character Token
	 * 
	 * @param token
	 */
	public Character(CharacterToken token){
		this.token = token;
		setupChar(token);
	}
	
	public void setupChar(CharacterToken token){
		switch(token){
		case MISS_SCARLETT:
			picture = "Miss Scarlett";
			break;
		case COLONEL_MUSTARD:
			picture = "Colonel Mustard";
			break;
		case MRS_WHITE:
			picture = "Mrs White";
			break; 
		case THE_REVEREND_GREEN:
			picture = "The Reverend Green";
			break;
		case MRS_PEACOCK:
			picture = "Mrs Peacock";
			break;
		case PROFESSOR_PLUM:
			picture = "Professor Plum";
			break;
		}
		
		
	}
	
	/**
	 * Gets the Name of the Character Token
	 */
	public String getName(){
		return this.token.name();
	}
	
	/**
	 * Sets the room of this Character Card
	 * 
	 * @param room
	 */
	public void setRoom(Room room){
		this.room = room;
	}
	
	/**
	 * Gets the Room from which this Character object is within
	 * 
	 * @return
	 */
	public Room getRoom(){
		return this.room;
	}
	
	/**
	 * Generated HashCode method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}
	
	/**
	 * Generated Equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		if (token != other.token)
			return false;
		return true;
	}

	@Override
	public String getPicture() {
		return picture;
	}
	
	
	
}
