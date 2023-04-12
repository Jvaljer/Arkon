package Model;

/**
 * Model class of the game, which contains all the primary variables and attributes for this game
 * to behave as we want. 
 * @author abel
 *
 */
public class GameModel {
	//board model of the game
	private BoardModel board;
	
	public GameModel() {
		board = new BoardModel(this);
	}
}
