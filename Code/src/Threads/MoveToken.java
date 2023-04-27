package Threads;

import Controler.BoardCtrl;
import Model.TokenModel;

public class MoveToken extends Thread {
	private BoardCtrl board;
	private TokenModel token;
	
	public MoveToken(BoardCtrl BC, TokenModel TM) {
		board = BC;
		token = TM;
	}
	
	@Override
	public void run() {
		//here we wanna move the selected token
	}
}
