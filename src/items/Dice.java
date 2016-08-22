package items;

import java.util.Random;

/***
 * Dice class responsible for contributing to Player movement in Cluedo
 * @author Andre L Westerlund
 *
 */
public class Dice {
	
	Random rand = new Random();

	/**
	 * Returns a random integer between 2 and 12.
	 * @return int
	 */
	public int roll() {
		int dice1 = rand.nextInt(6) + 1;
		int dice2 = rand.nextInt(6) + 1;
		return dice1 + dice2;
	}
}
