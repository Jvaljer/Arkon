package Controler;

import View.BoardView;
import Model.SelectorModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BoardCtrl implements KeyListener {
	private GameCtrl game;
	private BoardView board;
	
	public BoardCtrl(GameCtrl GC, BoardView BV) {
		game = GC;
		board = BV;
	}
	
	//keystroke methods
	public void UpPressed() {
		System.out.println("up pressed");
		SelectorModel selector = board.GetSelector().GetModel();
		Point old_select = selector.GetSelected().GetCoord();
		Point new_select = new Point(old_select.x, old_select.y - 1);
		selector.SetSelected(new_select);
	}
	public void DownPressed() {
		System.out.println("down pressed");
		SelectorModel selector = board.GetSelector().GetModel();
		Point old_select = selector.GetSelected().GetCoord();
		Point new_select = new Point(old_select.x, old_select.y + 1);
		selector.SetSelected(new_select);
	}
	public void LeftPressed() {
		System.out.println("left pressed");
		SelectorModel selector = board.GetSelector().GetModel();
		Point old_select = selector.GetSelected().GetCoord();
		Point new_select = new Point(old_select.x -1 , old_select.y);
		selector.SetSelected(new_select);
	}
	public void RightPressed() {
		System.out.println("right pressed");
		SelectorModel selector = board.GetSelector().GetModel();
		Point old_select = selector.GetSelected().GetCoord();
		Point new_select = new Point(old_select.x + 1, old_select.y);
		selector.SetSelected(new_select);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			UpPressed();
		} else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			DownPressed();
		} else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			LeftPressed();
		} else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			RightPressed();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//must implement
		return;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//must implement
		return;
	}
}
