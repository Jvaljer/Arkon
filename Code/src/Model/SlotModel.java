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
	//geometrical values
	private final static int side_length = 50;
	
	public SlotModel(BoardModel BM, SlotId SI, Point P) {
		board = BM;
		id = SI;
		coord = P;
	}
	
	//getters
	public Point GetCoord() {
		return coord;
	}
	public SlotId GetId() {
		return id;
	}
	public int GetSideLength() {
		return side_length;
	}
}
