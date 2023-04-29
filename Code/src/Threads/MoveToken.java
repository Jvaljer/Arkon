package Threads;

import Controler.BoardCtrl;
import Model.TokenModel;
import java.awt.Point;

public class MoveToken extends Thread {
	private BoardCtrl board;
	private TokenModel token;
	private Point direction;
	private Point destination;
	
	public MoveToken(BoardCtrl BC, TokenModel TM, Point dir) {
		board = BC;
		token = TM;
		direction = dir;
		
		Point old_pos = token.GetPos();
		destination = new Point(old_pos.x +direction.x, old_pos.y +direction.y);
	}
	
	@Override
	public void run() {
		int s = board.GetModel().GetSlotSize();
		board.occupied = true;
		token.StartsMoving();
		for(int i=0; i<s; i++) {
			//for each pixel separating two board's slots
			//we wanna increment the token's position
			token.IncrementMovingPos(direction);
			try {
				sleep(1000/s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		token.StopsMoving();
		board.occupied = false;
		token.MoveTo(destination);
	}
}
