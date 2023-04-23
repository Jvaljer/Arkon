package Controler;

import View.GameView;
import Model.GameModel;
import Threads.Refresh;

/**
 * Controler class of our game, which will define all the behaviors we want our game to have, taking
 * informations on both the corresponding view & model of the game, then modifying these to make it 
 * evolve following the defined rules.
 * @author abel
 *
 */
public class GameCtrl extends Thread {
	//game's model & view which we'll take our informations and modify throughout this class
	private GameModel model;
	private GameView view;
	
	public GameCtrl(GameView GV) {
		view = GV;
		model = view.GetModel();
		view.GetBoard().addKeyListener(new BoardCtrl(this, view.GetBoard()));
		view.GetBoard().setFocusable(true);
		(new Refresh(view)).start();
	}
	
	@Override
	public void run() {
		//must implement
		return;
	}
}
