package Threads;

import View.BoardView;
import View.GameView;
import View.InfobarView;

public class Refresh extends Thread {
	private GameView game_view;
	private BoardView board_view;
	private InfobarView infobar_view;
	
	public Refresh(GameView GV) {
		game_view = GV;
		board_view = game_view.GetBoard();
		infobar_view = game_view.GetInfobar();
	}
	
	@Override
	public void run() {
		while(true) {
			board_view.revalidate();
			board_view.repaint();
			
			infobar_view.revalidate();
			infobar_view.repaint();
			
			try {
				Thread.sleep(60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
