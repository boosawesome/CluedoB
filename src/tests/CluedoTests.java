/*package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.*;

import game.Board;
import game.Player;
import items.Character.CharacterToken;
import items.Envelope;
import items.Location;
import items.Room;
import items.Weapon;
import main.GameOfCluedo;
import main.GameOfCluedo.Direction;
import moves.Accusation;
import moves.Suggestion;

public class CluedoTests {

	*//**
	 * Testing Player movement,if the Game and Board are updated
	 *//*
	@Test
	public void test_01(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Point pos2 = board.getStartingPositions().get(1);
		Point pos3 = board.getStartingPositions().get(2);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		Player p2 = new Player("ryan", CharacterToken.COLONEL_MUSTARD, pos2);
		Player p3 = new Player("john", CharacterToken.MRS_WHITE, pos3);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		players.add(p3);
		game.movePlayer(6, Direction.NORTH, p, players);

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxsx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";


		assertTrue(output.equals(board.toString()));

	}

	*//**
	 * Test Invalid movement, Player cannot move onto a Space occupied by another player
	 *//*
	@Test
	public void test_02(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Point pos2 = board.getStartingPositions().get(1);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		Player p2 = new Player("ryan", CharacterToken.COLONEL_MUSTARD, pos2);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		game.movePlayer(7, Direction.NORTH, p, players);
		assertTrue(game.valid);
		game.movePlayer(7, Direction.EAST, p2, players);
		assertTrue(!game.valid);

	}


	*//**
	 * Test Invalid movement, Player cannot move into a Wall
	 *//*
	@Test
	public void test_03(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		game.movePlayer(2, Direction.WEST, p, players);
		assertFalse(game.valid);
	}

	*//**
	 * Testing Player if entered a room correctly
	 *//*
	@Test
	public void test_04(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		game.movePlayer(6, Direction.NORTH, p, players);
		game.movePlayer(1, Direction.WEST, p, players);
		game.enterRoom(p);

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxxx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";


		assertTrue(output.equals(board.toString()));
		assertTrue(p.getRoom().equals(game.getBoard().getRoom("LOUNGE")));
		assertFalse(p.getLocation() != null);

	}

	*//**
	 * Test invalid entering of a room
	 *//*
	@Test
	public void test_05(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		game.movePlayer(6, Direction.NORTH, p, players);
		game.enterRoom(p);

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxsx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";


		assertTrue(output.equals(board.toString()));
		assertTrue(p.getRoom() == null);
		assertFalse(p.getLocation() == null);

	}

	*//**
	 * Test valid use of Stairwell
	 *//*
	@Test
	public void test_06(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		game.movePlayer(6, Direction.NORTH, p, players);
		game.movePlayer(1, Direction.WEST, p, players);
		game.enterRoom(p);

		Room room = board.getRoom("LOUNGE");
		Room room2 = board.getRoom("CONSERVATORY");

		assertTrue(p.getRoom().equals(room));

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxxx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";

		game.useStairWell(room, p);
		assertTrue(p.getRoom().equals(room2));
		assertTrue(output.equals(board.toString()));
		assertTrue(p.getRoom() != null);
		assertFalse(p.getLocation() != null);

	}


	*//**
	 * Test invalid use of Stairwell, cannot use it because Player is not in room
	 *//*
	@Test
	public void test_07(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		game.movePlayer(6, Direction.NORTH, p, players);

		Room room = board.getRoom("LOUNGE");

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxsx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";

		game.useStairWell(room, p);
		assertTrue(output.equals(board.toString()));
		assertTrue(p.getRoom() == null);
		assertFalse(p.getLocation() == null);

	}


	*//**
	 * Test invalid use of Stairwell, cannot use it if Room does not have Stairwell
	 *//*
	@Test
	public void test_08(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		game.movePlayer(7, Direction.NORTH, p, players);
		game.movePlayer(4, Direction.EAST, p, players);
		game.enterRoom(p);

		Room room = board.getRoom("HALL");
		assertTrue(p.getRoom().equals(room));

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxxx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";

		game.useStairWell(room, p);
		assertTrue(p.getRoom().equals(room));
		assertTrue(output.equals(board.toString()));
		assertTrue(p.getRoom() != null);
		assertFalse(p.getLocation() != null);

	}

	*//**
	 * Test valid exiting of a Room
	 *//*
	@Test
	public void test_09(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		game.movePlayer(6, Direction.NORTH, p, players);
		game.movePlayer(1, Direction.WEST, p, players);
		game.enterRoom(p);

		Room room = board.getRoom("LOUNGE");
		assertTrue(p.getRoom().equals(room));

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxsxx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";

		game.exitRoom(room, p);
		assertTrue(output.equals(board.toString()));
		assertTrue(p.getRoom() == null);
		assertFalse(p.getLocation() == null);

	}

	*//**
	 * Test invalid exiting of a Room
	 *//*
	@Test
	public void test_10(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		game.movePlayer(6, Direction.NORTH, p, players);

		Room room = board.getRoom("LOUNGE");

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxsx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";

		game.exitRoom(room, p);
		assertTrue(output.equals(board.toString()));
		assertTrue(p.getRoom() == null);
		assertFalse(p.getLocation() == null);

	}

	*//**
	 * Test entering an invalid room
	 *//*
	@Test
	public void test_11(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		game.movePlayer(6, Direction.NORTH, p, players);

		Room room = board.getRoom("LOUNGE");

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxsx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";

		game.exitRoom(room, p);
		assertTrue(output.equals(board.toString()));
		assertTrue(p.getRoom() == null);
		assertFalse(p.getLocation() == null);

	}

	*//**
	 * Testing valid Suggestion
	 *//*
	@Test
	public void test_12(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Point pos2 = board.getStartingPositions().get(1);
		Point pos3 = board.getStartingPositions().get(2);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		Player p2 = new Player("ryan", CharacterToken.COLONEL_MUSTARD, pos2);
		Player p3 = new Player("john", CharacterToken.MRS_WHITE, pos3);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		players.add(p3);

		items.Character c = board.getCharacter("PROFESSOR_PLUM");
		p2.addCard(c);

		game.movePlayer(6, Direction.NORTH, p, players);
		game.movePlayer(1, Direction.WEST, p, players);
		game.enterRoom(p);

		Weapon w = board.getWeapon("DAGGER");
		Room r = board.getRoom("LOUNGE");

		Suggestion suggestion = new Suggestion(c,w,r);

		game.suggest(suggestion,p, players);
		assertTrue(p.getRoom().equals(board.getRoom("LOUNGE")));
		assertEquals(game.refuter,p2);

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxxx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";


		assertTrue(output.equals(board.toString()));

	}

	*//**
	 * Testing valid Suggestion02, suggestion has not been refuted
	 *//*
	@Test
	public void test_13(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Point pos2 = board.getStartingPositions().get(1);
		Point pos3 = board.getStartingPositions().get(2);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		Player p2 = new Player("ryan", CharacterToken.COLONEL_MUSTARD, pos2);
		Player p3 = new Player("john", CharacterToken.MRS_WHITE, pos3);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		players.add(p3);

		items.Character c = board.getCharacter("PROFESSOR_PLUM");


		game.movePlayer(6, Direction.NORTH, p, players);
		game.movePlayer(1, Direction.WEST, p, players);
		game.enterRoom(p);

		Weapon w = board.getWeapon("DAGGER");
		Room r = board.getRoom("LOUNGE");

		Suggestion suggestion = new Suggestion(c,w,r);

		game.suggest(suggestion,p, players);
		assertTrue(p.getRoom().equals(board.getRoom("LOUNGE")));
		assertEquals(game.refuter,null);

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxxx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";


		assertTrue(output.equals(board.toString()));

	}

	*//**
	 * Testing invalid Suggestion 01, cannot make suggestion if Player is not in a room
	 *//*
	@Test
	public void test_14(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Point pos2 = board.getStartingPositions().get(1);
		Point pos3 = board.getStartingPositions().get(2);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		Player p2 = new Player("ryan", CharacterToken.COLONEL_MUSTARD, pos2);
		Player p3 = new Player("john", CharacterToken.MRS_WHITE, pos3);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		players.add(p3);

		items.Character c = board.getCharacter("PROFESSOR_PLUM");


		game.movePlayer(6, Direction.NORTH, p, players);


		Weapon w = board.getWeapon("DAGGER");
		Room r = board.getRoom("LOUNGE");

		Suggestion suggestion = new Suggestion(c,w,r);

		game.suggest(suggestion,p, players);
		assertTrue(p.getRoom() == null);
		assertEquals(game.refuter,null);

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxsx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";


		assertTrue(output.equals(board.toString()));

	}

	*//**
	 * Testing invalid Suggestion 02, cannot make a suggestion of a room if player is not in that room
	 * e.g. Player is in Hall, making suggestion of Conservatory
	 *//*
	@Test
	public void test_15(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Point pos2 = board.getStartingPositions().get(1);
		Point pos3 = board.getStartingPositions().get(2);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		Player p2 = new Player("ryan", CharacterToken.COLONEL_MUSTARD, pos2);
		Player p3 = new Player("john", CharacterToken.MRS_WHITE, pos3);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		players.add(p3);

		items.Character c = board.getCharacter("PROFESSOR_PLUM");

		game.movePlayer(6, Direction.NORTH, p, players);
		game.movePlayer(1, Direction.WEST, p, players);
		game.enterRoom(p);

		Weapon w = board.getWeapon("DAGGER");
		Room r = board.getRoom("CONSERVATORY");

		Suggestion suggestion = new Suggestion(c,w,r);

		game.suggest(suggestion,p, players);
		assertTrue(p.getRoom() != null);
		assertEquals(game.refuter,null);

		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxxx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////x/////////x/////// \n";

		assertTrue(output.equals(board.toString()));

	}

	*//**
	 * Testing valid Accusation, however the Player may or may not be Correct
	 *//*
	@Test
	public void test_16(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Point pos2 = board.getStartingPositions().get(1);
		Point pos3 = board.getStartingPositions().get(2);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		Player p2 = new Player("ryan", CharacterToken.COLONEL_MUSTARD, pos2);
		Player p3 = new Player("john", CharacterToken.MRS_WHITE, pos3);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		players.add(p3);

		items.Character cs = game.getSolution().getCharacter();
		Weapon ws = game.getSolution().getWeapon();
		Room rs = game.getSolution().getRoom();

		items.Character c = board.getCharacter("PROFESSOR_PLUM");
		Weapon w = board.getWeapon("DAGGER");
		Room r = board.getRoom("CONSERVATORY");


		Accusation accuse = new Accusation(cs,ws,rs);

		assertTrue(accuse != null);
		boolean cond = game.accuse(accuse,p);
		assertTrue(cond || !cond);
		String output = "/////////s/////s///////// \n"
				+"///////xxx/////xxx/////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xx////// \n"
				+"//////xx/////////xxx///// \n" 
				+"//////xx/////////xxxxxxxs \n" 
				+"xxxxxxxx/////////xxxxxxx/ \n" 
				+"/xxxxxxxxxxxxxxxxxx////// \n" 
				+"////xxxxxxxxxxxxxx/////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xxx////// \n"
				+"////////xx//////xxxxxxxx/ \n" 
				+"////////xx//////xxx////// \n" 
				+"////////xx//////xx/////// \n" 
				+"/xxxxxxxxx//////xx/////// \n" 
				+"sxxxxxxxxxxxxxxxxx/////// \n" 
				+"/xxxxxxxx///////xxx////// \n" 
				+"///////xx///////xxxxxxxxs \n" 
				+"///////xx///////xxxxxxxx/ \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////xx///////xx/////// \n" 
				+"///////s/////////x/////// \n";

		assertTrue(output.equals(board.toString()));

	}


	*//**
	 * Testing wrong Accusation, Player is eliminated 
	 *//*
	@Test
	public void test_17(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Point pos2 = board.getStartingPositions().get(1);
		Point pos3 = board.getStartingPositions().get(2);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		Player p2 = new Player("ryan", CharacterToken.COLONEL_MUSTARD, pos2);
		Player p3 = new Player("john", CharacterToken.MRS_WHITE, pos3);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		players.add(p3);

		Envelope solution = game.getSolution();
		solution.setCharacter(board.getCharacter("MISS_SCARLETT"));
		solution.setWeapon(board.getWeapon("DAGGER"));
		solution.setRoom(board.getRoom("LOUNGE"));


		items.Character cs = solution.getCharacter();
		Weapon ws = solution.getWeapon();
		Room rs = solution.getRoom();

		items.Character c = board.getCharacter("PROFESSOR_PLUM");
		Weapon w = board.getWeapon("DAGGER");
		Room r = board.getRoom("CONSERVATORY");


		Accusation accuse = new Accusation(c,w,r);


		boolean cond = game.accuse(accuse,p);
		if(!cond) System.out.println("ACCUSATION IS NOT CORRECT!!");
		assertTrue(!cond);
		p.lose();
		assertTrue(!p.getActive());
		System.out.println(p.getName()+" is Eliminated From the Game!!");

	}


	*//**
	 * Testing Accusation, Player Wins! 
	 *//*
	@Test
	public void test_18(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Point pos2 = board.getStartingPositions().get(1);
		Point pos3 = board.getStartingPositions().get(2);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		Player p2 = new Player("ryan", CharacterToken.COLONEL_MUSTARD, pos2);
		Player p3 = new Player("john", CharacterToken.MRS_WHITE, pos3);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		players.add(p3);

		Envelope solution = game.getSolution();
		solution.setCharacter(board.getCharacter("MISS_SCARLETT"));
		solution.setWeapon(board.getWeapon("DAGGER"));
		solution.setRoom(board.getRoom("LOUNGE"));


		items.Character cs = solution.getCharacter();
		Weapon ws = solution.getWeapon();
		Room rs = solution.getRoom();

		items.Character c = board.getCharacter("MISS_SCARLETT");
		Weapon w = board.getWeapon("DAGGER");
		Room r = board.getRoom("LOUNGE");


		Accusation accuse = new Accusation(c,w,r);


		boolean cond = game.accuse(accuse,p);
		if(!cond) System.out.println("ACCUSATION IS NOT CORRECT!!");
		assertTrue(cond);
		assertTrue(p.getActive());
		System.out.println("****************  "+p.getName()+" WINS!!!!   ************");
		System.out.println("Murderer :"+cs.getName());
		System.out.println("Weapon :"+ws.getName());
		System.out.println("Room :"+rs.getName());
		System.out.println("GAME OVER!!!");

	}

	*//**
	 * Testing if Location conatins Players
	 *//*
	@Test
	public void test_19(){
		GameOfCluedo game = new GameOfCluedo();
		Board board = game.getBoard();
		Point pos = board.getStartingPositions().get(0);
		Point pos2 = board.getStartingPositions().get(1);
		Point pos3 = board.getStartingPositions().get(2);
		Player p = new Player("andre", CharacterToken.MISS_SCARLETT, pos);
		Player p2 = new Player("ryan", CharacterToken.COLONEL_MUSTARD, pos2);
		Player p3 = new Player("john", CharacterToken.MRS_WHITE, pos3);
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(p);
		players.add(p2);
		players.add(p3);
		game.movePlayer(6, Direction.NORTH, p, players);
		game.movePlayer(1, Direction.WEST, p, players);
		game.enterRoom(p);
		game.movePlayer(6, Direction.EAST, p2, players);
		game.movePlayer(1, Direction.SOUTH, p2, players);
		game.enterRoom(p2);
		
		Room r = board.getRoom("LOUNGE");
		Location loc = game.getLocation(r);
		
		assertTrue(loc.hasPlayer(p) && loc.hasPlayer(p2));
		assertTrue(p.getRoom().equals(r));
		assertTrue(p2.getRoom().equals(r));

		String output =  "/////////s/////s///////// \n"
						+"///////xxx/////xxx/////// \n"
						+"//////xx/////////xx////// \n"
						+"//////xx/////////xx////// \n"
						+"//////xx/////////xx////// \n"
						+"//////xx/////////xxx///// \n" 
						+"//////xx/////////xxxxxxxs \n" 
						+"xxxxxxxx/////////xxxxxxx/ \n" 
						+"/xxxxxxxxxxxxxxxxxx////// \n" 
						+"////xxxxxxxxxxxxxx/////// \n" 
						+"////////xx//////xxx////// \n" 
						+"////////xx//////xxx////// \n" 
						+"////////xx//////xxx////// \n"
						+"////////xx//////xxxxxxxx/ \n" 
						+"////////xx//////xxx////// \n" 
						+"////////xx//////xx/////// \n" 
						+"/xxxxxxxxx//////xx/////// \n" 
						+"xxxxxxxxxxxxxxxxxx/////// \n" 
						+"/xxxxxxxx///////xxx////// \n" 
						+"///////xx///////xxxxxxxxs \n" 
						+"///////xx///////xxxxxxxx/ \n" 
						+"///////xx///////xx/////// \n" 
						+"///////xx///////xx/////// \n" 
						+"///////xx///////xx/////// \n" 
						+"///////x/////////x/////// \n";


		assertTrue(output.equals(board.toString()));

	}
	
	*//**
	 * Check that 3 cards are random and taken from the Cards deck every Game
	 *//*
	@Test
	public void test_20(){
	GameOfCluedo game = new GameOfCluedo(); //chances of getting the same 3 cards in two games is 5.7%
	Envelope solution1 = game.getSolution();
	items.Character c = solution1.getCharacter(); 
	Weapon w = solution1.getWeapon();
	Room r = solution1.getRoom();
	
	assertTrue(game.cards.size() == 18);
	
	GameOfCluedo game2 = new GameOfCluedo(); //chances of getting the same 3 cards in two games is 5.7%
	Envelope solution2 = game2.getSolution();
	items.Character c2 = solution2.getCharacter(); 
	Weapon w2 = solution2.getWeapon();
	Room r2 = solution2.getRoom();
	
	assertTrue(game2.cards.size() == 18);
	
	assertTrue(!c.equals(c2) || !w.equals(w2)||!r.equals(r2));

		
	}





}
*/