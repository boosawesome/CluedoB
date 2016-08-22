package items;

/***
 * Represents the physcial Weapon objects of the Cluedo Game. These can be set in Locations/Rooms and removed.
 * 
 * @author Andre L Westerlund
 *
 */
public class WeaponObject{
	
	private Weapon weapon;
	Room room;
	
	/**
	 * Constructs a WeaponObject from a given Weapon
	 * 
	 * @param weapon
	 */
	public WeaponObject(Weapon weapon){
		this.weapon = weapon;
	}
	
	/**
	 * Gets the name of the Weapon
	 * 
	 * @return
	 */
	public String getName(){
		return this.weapon.getName();
	}
	
	/**
	 * Sets the room of the WeaponObject
	 * 
	 * @param room
	 */
	public void setRoom(Room room){
		this.room = room;
	}
	
	/**
	 * Gets the room the WeaponObject is contained in
	 * 
	 * @return
	 */
	public Room getRoom(){
		return this.room;
	}
	
	/**
	 * Gets the Weapon 
	 * 
	 * @return
	 */
	public Weapon getWeapon(){
		return weapon;
	}
}