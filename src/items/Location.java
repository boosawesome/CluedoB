package items;

import java.util.ArrayList;

import game.Player;

/***
 * Represents the physcial (relative) location of the Rooms in Cluedo. These can contain the Weapons Objects 
 * and Character Piece Tokens. 
 * 
 * @author Andre L Westerlund
 *
 */
public class Location {
	
		public Room room;
		WeaponObject w = null; //start off with no weapons
		ArrayList<Player> players;
		
		/**
		 * Constructs a Location Object from a Given Room object
		 * 
		 * @param room
		 */
		public Location(Room room){
			this.room = room;
			players = new ArrayList<Player>();
		}
		
		/**
		 * Gets the number of Players inside the Location
		 * 
		 * @return
		 */
		public int size(){
			return players.size();
		}
		
		/**
		 * Determines if the Location contains a certain Person Object
		 * 
		 * @param p
		 * @return
		 */
		public boolean hasPlayer(Player p){
			return players.contains(p);
		}
		
		/**
		 * Determines if the Location contains a certain Weapon object
		 * 
		 * @param w
		 * @return
		 */
		public boolean hasWeaponObject(WeaponObject w){
			return this.w.equals(w);
		}
		
		/**
		 * Sets the WeaponObject
		 * 
		 * @param w
		 */
		public void setWeaponObject(WeaponObject w){
			this.w = w;
		}
		
		/**
		 * Gets the WeaponObject if not null
		 * 
		 * @return
		 */
		public WeaponObject getWeaponObject(){
			if(w != null)
			return w;
			else return null;
		}
		
		/**
		 * Adds a Player to the Location
		 * 
		 * @param p
		 */
		public void addPlayer(Player p){
			this.players.add(p);
			
		}
		
		/**
		 * Removes a Player from the Location
		 * 
		 * @param p
		 */
		public void removePlayer(Player p){
			if(players.contains(p))players.remove(p);
			p.setRoom(null);
		}
		
		/**
		 * Gets the Players in the Location
		 * 
		 * @return
		 */
		public ArrayList<Player> getPlayers(){
			return this.players;
		}
		
		
		
	
}
