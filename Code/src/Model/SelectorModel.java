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
	
	public SelectorModel(BoardModel BM) {
		board = BM;
		selected = new SlotModel(board,null, new Point(-1,-1));
		selector_size = board.GetSlotSize();
	}
}
