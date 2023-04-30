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
import Threads.MoveToken;
import Types.CustomException;
import Types.Direction;

public class BoardCtrl implements KeyListener {
	private GameCtrl game;
	private BoardView view;
	private BoardModel model;
	
	private boolean fst_select;
	private Point source_coord;
	private TokenModel selected_tok;
	
	public boolean occupied;
	public boolean can_drop;
	public int move_cnt;
	
	public BoardCtrl(GameCtrl GC, BoardView BV) {
		game = GC;
		view = BV;
		model = view.GetModel();
		fst_select = true;
		occupied = false;
		can_drop = true;
		move_cnt = 0;
	}
	
	//getters
	public BoardModel GetModel() {
		return model;
	}
	public BoardView GetView() {
		return view;
	}
	
	//keystroke methods
	public void UpPressed() {
		if(model.select_state==SelectState.None) {
			System.out.println("select_state is on Spell");
			SelectorModel selector = model.GetSelector();
			Point old_select = selector.GetSelected().GetCoord();
			Point new_select = new Point(old_select.x, old_select.y -1);
			if(model.CoordInBound(new_select)) {
				selector.SetSelected(new_select);
			}
		} else if(model.select_state==SelectState.Move) {
			try {
	    		if(model.TokenCanMove(selected_tok, Direction.Up, move_cnt)) {
					(new MoveToken(this, selected_tok, Direction.Up.Point(), source_coord)).start();
				} else {
					//here we wanna tell on the InfoBar that moving in that direction isn't possible
				}
	    	} catch (CustomException c_e) {
	    		c_e.printStackTrace();
	    	}
		}
	}
	public void DownPressed() {
		if(model.select_state==SelectState.None) {
			SelectorModel selector = model.GetSelector();
			Point old_select = selector.GetSelected().GetCoord();
			Point new_select = new Point(old_select.x, old_select.y +1);
			if(model.CoordInBound(new_select)) {
				selector.SetSelected(new_select);
			}
		} else if(model.select_state==SelectState.Move) {
			try {
	    		if(model.TokenCanMove(selected_tok, Direction.Down, move_cnt)) {
					(new MoveToken(this, selected_tok, Direction.Down.Point(), source_coord)).start();
				} else {
					//here we wanna tell on the InfoBar that moving in that direction isn't possible
				}
	    	} catch (CustomException c_e) {
	    		c_e.printStackTrace();
	    	}
		}
	}
	public void LeftPressed() {
		if(model.select_state==SelectState.None) {
			SelectorModel selector = model.GetSelector();
			Point old_select = selector.GetSelected().GetCoord();
			Point new_select = new Point(old_select.x -1, old_select.y);
			if(model.CoordInBound(new_select)) {
				selector.SetSelected(new_select);
			}
		} else if(model.select_state==SelectState.Move) {
			try {
	    		if(model.TokenCanMove(selected_tok, Direction.Left, move_cnt)) {
					(new MoveToken(this, selected_tok, Direction.Left.Point(), source_coord)).start();
				} else {
					//here we wanna tell on the InfoBar that moving in that direction isn't possible
				}
	    	} catch (CustomException c_e) {
	    		c_e.printStackTrace();
	    	}
		}
	}
	public void RightPressed() {
		if(model.select_state==SelectState.None) {
			SelectorModel selector = model.GetSelector();
			Point old_select = selector.GetSelected().GetCoord();
			Point new_select = new Point(old_select.x +1, old_select.y);
			if(model.CoordInBound(new_select)) {
				selector.SetSelected(new_select);
			}
	    }else if(model.select_state==SelectState.Move) {
	    	try {
	    		if(model.TokenCanMove(selected_tok, Direction.Right, move_cnt)) {
					(new MoveToken(this, selected_tok, Direction.Right.Point(), source_coord)).start();
				} else {
					//here we wanna tell on the InfoBar that moving in that direction isn't possible
				}
	    	} catch (CustomException c_e) {
	    		c_e.printStackTrace();
	    	}
		}
	}
	
	public void SelectPressed() throws CustomException {
		SelectorModel selector = model.GetSelector();
		//if X is pressed, then we wanna know if it's the first time or not
		if(!fst_select) {
			System.out.println("Not the first select");
			//if this keystroke is to validate something then match on the Select state 
			switch (model.select_state) {
				case Move:
					//first we wanna check if the current slot is possible for the selected token
					if(can_drop) {
						selected_tok = null;
						fst_select = true;
						model.select_state = SelectState.None;
						move_cnt=0;
					}
					break;
				case Spell:
					break;
					
				default:
					throw new CustomException("ERROR-> the select stae wasn't one of the knew ones");
			}
		} else {
			System.out.println("First select");
			//then we wanna set a state for the board 
			//possible states are : 
				//Token Selected -> we are simply gonna move it 
				//MainTok Selected -> we offer a choice between the different powers
			SlotModel slot = selector.GetSelected();
			TokenModel tok = model.GetTokenFromSlot(slot);
			if(tok!=null) {
				fst_select = false;
				selected_tok = tok;
				if(selected_tok.GetRole()==TokenRole.Sorcerer || selected_tok.GetRole()==TokenRole.Sorceress) {
					model.SetSelectState(SelectState.Spell);
					//we also wanna update the InfoBar
				} else {
					model.SetSelectState(SelectState.Move);
					source_coord = selected_tok.GetPos();
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
		if(e.getKeyCode()==KeyEvent.VK_UP && !occupied) {
			UpPressed();
		} else if(e.getKeyCode()==KeyEvent.VK_DOWN && !occupied) {
			DownPressed();
		} else if(e.getKeyCode()==KeyEvent.VK_LEFT && !occupied) {
			LeftPressed();
		} else if(e.getKeyCode()==KeyEvent.VK_RIGHT && !occupied) {
			RightPressed();
		} else if(e.getKeyCode()==KeyEvent.VK_X && !occupied) {
			try {
				SelectPressed();
			} catch (CustomException c_e) {
				c_e.printStackTrace();
			}
		} else if(e.getKeyCode()==KeyEvent.VK_A && !occupied) {
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
