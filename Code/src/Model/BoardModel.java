package Model;

import java.util.*;
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
	private ArrayList<Point> init_luminas_coords;
	//board's size parameters
	private final static int lines = 9;
	private final static int columns = 9;
	
	public BoardModel(GameModel GM) {
		game = GM;
		
		slot_grid = new ArrayList<ArrayList<SlotModel>>();
		init_luminas_coords = new ArrayList<Point>();
	}
}
