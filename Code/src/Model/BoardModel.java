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
	private ArrayList<ArrayList<SlotModel>> slots_grid;
	//board's power slots parameters
	private ArrayList<Point> power_points;
	//board's size parameters
	private final static int lines = 9;
	private final static int columns = 9;
	
	public BoardModel(GameModel GM, ArrayList<ArrayList<String>> map) {
		game = GM;
		
		slots_grid = new ArrayList<ArrayList<SlotModel>>();
		
		for(int j=0; j<9; j++) {
			ArrayList<SlotModel> model_line = new ArrayList<SlotModel>();
			ArrayList<String> str_line = map.get(j);
			for(int i=0; i<9; i++) {
				Point coord = new Point(j,i);
				switch (str_line.get(i)) {
					case "D":
						model_line.add(new SlotModel(this, SlotId.Dark, coord));
						break;
						
					case "L":
						model_line.add(new SlotModel(this, SlotId.Light, coord));
						break;
						
					case "x":
						model_line.add(new SlotModel(this, SlotId.Lumina, coord));
						break;
						
					default:
						break;
				}
			}
			slots_grid.add(model_line);		
		}

		power_points = new ArrayList<Point>();
		power_points.add(new Point(0,4));
		power_points.add(new Point(4,4));
		power_points.add(new Point(8,4));
		power_points.add(new Point(4,0));
		power_points.add(new Point(4,8));
		
		System.out.println("done with board initialization");
	}
	
	//getters
	public int GetLines() {
		return lines;
	}
	public int GetColumns() {
		return columns;
	}
	public SlotModel GetSlotFromIndex(int i, int j) {
		return slots_grid.get(i).get(j);
	}
}
