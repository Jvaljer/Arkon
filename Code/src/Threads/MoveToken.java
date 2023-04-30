package Threads;

import Controler.BoardCtrl;
import Model.TokenModel;
import Types.CustomException;

import java.awt.Point;

public class MoveToken extends Thread {
	private BoardCtrl board;
	private TokenModel token;
	private Point direction;
	private Point destination;
	private Point source;
	
	public MoveToken(BoardCtrl BC, TokenModel TM, Point dir, Point src) {
		board = BC;
		token = TM;
		direction = dir;
		source = src;
		
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
				sleep(500/s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		token.StopsMoving();
		board.occupied = false;
		if(board.GetModel().TokenOnCoord(destination)) {
			try {
				board.can_drop = board.GetModel().GetTokenFromCoord(destination).GetRole().Side()!=token.GetRole().Side();
			} catch (CustomException c_e) {
				c_e.printStackTrace();
			}
		} else {
			board.can_drop = true;
		}
		board.move_cnt=board.GetModel().CountDist(source, destination);
		token.MoveTo(destination);
	}
}
