package Model;

import Types.SlotId;
import java.awt.*;

/**
 * Slot Model class, containing all the needed informations to define and use a slot 
 * inside this game.
 * @author abel
 *
 */
public class SlotModel {
	//board to which this slot belongs
	private BoardModel board;
	//slot's id
	private SlotId id;
	//slot's coordinates inside the board
	private Point coord;
	
	public SlotModel(BoardModel BM, SlotId SI, Point P) {
		board = BM;
		id = SI;
		coord = P;
	}
}
