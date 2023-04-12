package View;

import javax.swing.JFrame;
import Model.GameModel;

/**
 * View class of the game, which contains all we'll need to display the game correctly, and also allow 
 * the players to interact with it.
 * @author abel
 *
 */
public class GameView extends JFrame {
	//model on which we'll construct our view
	private GameModel model;
	//view of the game's board
	private BoardView board;
	//predicate to know which view we're in 
	private boolean fighting;
	
	public GameView(GameModel GM) {
		//to switch from the board view to a fight scene we might want to just implement a 
		//new fight scene each time we wanna fight -> easier to create different scene
		//+ less complicated to switch panels and informations
		model = GM;
		fighting = false;
		
		board = new BoardView(this,model.GetBoard());
		//must implement
	}
	
	//getters
	public GameModel GetModel() {
		return model;
	}
}
