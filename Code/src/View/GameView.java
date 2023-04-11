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
	
	public GameView(GameModel GM) {
		model = GM;
		//must implement
	}
	
	//getters
	public GameModel GetModel() {
		return model;
	}
}
