package Main;

import Model.GameModel;
import View.GameView;
import Controler.GameCtrl;

/**
 * Main class of the project, containing the 'main' method
 * @author abel
 *
 */
public class Main {
	
	/**
	 * As we are using a MVC architecture, we want the main to only instantiate each one of the game's
	 * class (model -> view -> controler) and then the controler will handle everything from then.
	 * @param args
	 */
	public static void main(String[] args) {
		GameModel game_model = new GameModel();
		GameView game_view = new GameView(game_model);
		new GameCtrl(game_view);
	}

}
