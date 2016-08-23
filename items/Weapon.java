package items;

/***
 * Represents the Weapon Card in the Cluedo Game. This class implements Card Interface.
 * 
 * @author Andre L Westerlund
 *
 */
public class Weapon implements Card{


	/**
	 * Weapons of Cluedo
	 * 
	 * @author Andre L Westerlund
	 *
	 */
	public enum WeaponToken{
		CANDLESTICK,
		DAGGER,
		LEAD_PIPE,
		REVOLVER,
		ROPE,
		SPANNER
	}
 
	Room room;
	private WeaponToken token;
	public String picture;
	
	/**
	 * Constructs a Weapon Object from a given Weapon Token
	 * 
	 * @param token
	 */
	public Weapon(WeaponToken token){
		this.token = token;
		setupWeapon(token);
	}
	
	public void setupWeapon(WeaponToken token){
		switch(token){
		case DAGGER:
			picture = "dagger";
			break;
		case ROPE:
			picture = "rope";
			break;
		case SPANNER:
			picture = "spanner";
			break;
		case REVOLVER:
			picture = "revolver";
			break;
		case CANDLESTICK:
			picture = "candlestick";
			break;
		case LEAD_PIPE:
			picture = "leadpipe";
			break;
		
		}
	}
	
	
	/**
	 * Gets Weapon name
	 */
	public String getName(){
		return this.token.name();
	}
	
	/**
	 * Sets the room of the Weapon
	 * 
	 * @param room
	 */
	public void setRoom(Room room){
		this.room = room;
	}
	
	/**
	 * Gets the Room that the Weapon is in
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
		Weapon other = (Weapon) obj;
		if (token != other.token)
			return false;
		return true;
	}

	@Override
	public String getPicture() {
		return picture;
	}







}
