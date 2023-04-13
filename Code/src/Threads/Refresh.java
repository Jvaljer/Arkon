package Threads;

import View.BoardView;
import View.GameView;

public class Refresh extends Thread {
	private GameView game_view;
	private BoardView board_view;
	
	public Refresh(GameView GV) {
		game_view = GV;
		board_view = game_view.GetBoard();
	}
	
	@Override
	public void run() {
		while(true) {
			board_view.revalidate();
			board_view.repaint();
			
			try {
				Thread.sleep(60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
