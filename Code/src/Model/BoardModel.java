package Model;

import java.util.*;

import Types.SlotId;

import java.awt.*;

/**
 * Board Model class, containing all the informations related to the game's board
 * includings its size and all of its parameters such as lumina slots etc.
 * @author abel
 *
 */
public class BoardModel {
	//game to which belongs this board
	private GameModel game;
	//board's grid
	private ArrayList<ArrayList<SlotModel>> slot_grid;
	//board's slots parameters
	private ArrayList<Point> luminas_coords;
	private ArrayList<Point> power_points;
	//board's size parameters
	private final static int lines = 9;
	private final static int columns = 9;
	
	public BoardModel(GameModel GM) {
		game = GM;
		
		slot_grid = new ArrayList<ArrayList<SlotModel>>();
		luminas_coords = new ArrayList<Point>();
		
		for(int i=0; i<lines; i++) {
			slot_grid.add(new ArrayList<SlotModel>());
			for(int j=0; j<columns; j++) {
				slot_grid.get(i).add(new SlotModel(this, SetSlotIdFromCoord(i,j), new Point(i,j)));
			}
		}
		
		power_points = new ArrayList<Point>();
	}
	
	public SlotId SetSlotIdFromCoord(int x, int y) {
		//must implement
		return null;
	}
}
