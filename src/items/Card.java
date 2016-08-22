package items;

/***
 * Card Interface that can be contained by GameOfCluedo, Player, etc
 * Character, Weapon, and Room implement this class
 *  
 * @author Andre L Westerlund
 *
 */
public interface Card {

	

	/**
	 * Gets the name of the Card
	 * 
	 * @return
	 */
	public String getName();

	public String getPicture();
}
