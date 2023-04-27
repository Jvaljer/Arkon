package Controler;

import View.BoardView;
import Model.SlotModel;
import Model.SelectorModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Model.BoardModel;
import Model.TokenModel;
import Types.TokenRole;
import Types.SelectState;

public class BoardCtrl implements KeyListener {
	private GameCtrl game;
	private BoardView view;
	private BoardModel model;
	
	private boolean fst_select;
	private TokenModel selected_tok;
	
	public BoardCtrl(GameCtrl GC, BoardView BV) {
		game = GC;
		view = BV;
		model = view.GetModel();
		fst_select = false;
	}
	
	//keystroke methods
	public void UpPressed() {
		if(model.select_state==SelectState.None) {
			SelectorModel selector = model.GetSelector();
			Point old_select = selector.GetSelected().GetCoord();
			Point new_select = new Point(old_select.x, old_select.y -1);
			selector.SetSelected(new_select);
		} else if(model.select_state==SelectState.Move) {
			(new MoveToken(this, selected_tok)).start();
		}
		//then if there's a token to move then move it (without canceling any state)
	}
	public void DownPressed() {
		SelectorModel selector = model.GetSelector();
		Point old_select = selector.GetSelected().GetCoord();
		Point new_select = new Point(old_select.x, old_select.y +1);
		selector.SetSelected(new_select);
	}
	public void LeftPressed() {
		SelectorModel selector = model.GetSelector();
		Point old_select = selector.GetSelected().GetCoord();
		Point new_select = new Point(old_select.x -1, old_select.y);
		selector.SetSelected(new_select);
	}
	public void RightPressed() {
		SelectorModel selector = model.GetSelector();
		Point old_select = selector.GetSelected().GetCoord();
		Point new_select = new Point(old_select.x +1, old_select.y);
		selector.SetSelected(new_select);
	}
	
	public void SelectPressed() {
		SelectorModel selector = model.GetSelector();
		//if X is pressed, then we wanna know if it's the first time or not
		if(!fst_select) {
			//if this keystroke is to validate something then match on the Select state 
			switch (model.select_state) {
				case Move:
					break;
				case Spell:
					break;
				default:
					break;
			}
		} else {
			//if this keystroke is to make the first select then just initiate the select procedure
			fst_select = true;
			//then we wanna set a state for the board 
			//possible states are : 
				//Token Selected -> we are simply gonna move it 
				//MainTok Selected -> we offer a choice between the different powers
			SlotModel slot = selector.GetSelected();
			TokenModel tok = model.GetTokenFromSlot(slot);
			if(tok!=null) {
				selected_tok = tok;
				if(selected_tok.GetRole()==TokenRole.Sorcerer || selected_tok.GetRole()==TokenRole.Sorceress) {
					model.SetSelectState(SelectState.Spell);
					//we also wanna update the InfoBar
				} else {
					model.SetSelectState(SelectState.Move);
					//we also wanna update the InfoBar
				}
			}
		}
	}
	
	public void CancelPressed() {
		//must implement
		return;
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
		} else if(e.getKeyCode()==KeyEvent.VK_X) {
			SelectPressed();
		} else if(e.getKeyCode()==KeyEvent.VK_A) {
			CancelPressed();
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
