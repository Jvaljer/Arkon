package Threads;

import View.BoardView;
import View.GameView;

public class Refresh extends Thread {
	private BoardView board_view;
	
	public Refresh(GameView GV) {
		board_view = GV.GetBoard();
	}
	
	@Override
	public void run() {
		board_view.revalidate();
		board_view.repaint();
		
		try {
			Thread.sleep(60);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
