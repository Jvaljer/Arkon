package Model;

import java.awt.*;

/**
 * Selector Model Class, containing all the needed informations to define and interact with the slot's selector
 * that the player will move using the directionnal arrows
 * @author abel
 *
 */
public class SelectorModel {
	//board this selector belongs to
	private BoardModel board;
	//Selected Slot
	private SlotModel selected;
	//geometrical informations
	private static int selector_size;
	//side color of the selector
	private static Color playing_col;
	
	//Constructor
	public SelectorModel(BoardModel BM) {
		board = BM;
		selected = new SlotModel(board,null, new Point(0,0));
		selector_size = board.GetSlotSize();
		switch (board.PlayingSide()) {
			case 0:
				playing_col = Color.LIGHT_GRAY;
				break;
			case 1:
				playing_col = Color.GREEN;
				break;
			default:
				break;
		}
	}
	
	//getters
	public SlotModel GetSelected() {
		return selected;
	}
	public Color GetColor() {
		return playing_col;
	}
	
	//setters
	public void SetSelected(Point coord) {
		selected = board.GetSlotFromIndex(coord.y, coord.x);
	}
}

