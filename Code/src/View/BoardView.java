package View;

import javax.swing.JPanel;
import Model.BoardModel;
import java.util.*;
import java.awt.*;

/**
 * Board View Class, handling all the necessary stuff to properly display the game's board,
 * referring a board model, and having a grid of slot's views as attribute.
 * @author abel
 *
 */
public class BoardView extends JPanel {
	//game's view this panel belongs to
	private GameView game;
	//board model on which is based this view
	private BoardModel model;
	//grid of the slot's views
	private ArrayList<ArrayList<SlotView>> slots_grid;
	
	public BoardView(GameView GV, BoardModel BM) {
		game = GV;
		model = BM;
		
		slots_grid = new ArrayList<ArrayList<SlotView>>();
		for(int j=0; j<model.GetLines(); j++) {
			ArrayList<SlotView> view_line = new ArrayList<SlotView>();
			for(int i=0; i<model.GetColumns(); i++) {
				view_line.add(new SlotView(this, model.GetSlotFromIndex(i,j)));
			}
			slots_grid.add(view_line);
		}
		
		//now setting up the JPanel
		setPreferredSize(new Dimension(500,500));
	}
	//getters
	public BoardModel GetModel() {
		return model;
	}
	
	//Draw method for the game's board
	public void Draw(Graphics G) {
		for(ArrayList<SlotView> line : slots_grid) {
			for(SlotView slot : line) {
				slot.Draw(G);
			}
		}
	}
	
	@Override
	public void paint(Graphics G) {
		super.paint(G);
		Draw(G);
	}
}
