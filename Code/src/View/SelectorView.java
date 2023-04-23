package View;

import Model.SelectorModel;
import java.awt.*;

public class SelectorView {
	private BoardView board;
	private SelectorModel model;
	
	public SelectorView(BoardView BV, SelectorModel SM) {
		board = BV;
		model = SM;
	}
	
	public SelectorModel GetModel() {
		return model;
	}
	
	public void Draw(Graphics G) {
		int slot_size = board.GetModel().GetSlotSize();
		int x_coord = model.GetSelected().GetCoord().x;
		int y_coord = model.GetSelected().GetCoord().y;
		//we wanna draw a rectangle line around the (x_coord,y_coord) slot
		int x_pos = x_coord*slot_size + board.GetModel().GetGap();
		int y_pos = y_coord*slot_size + board.GetModel().GetGap();

		G.setColor(Color.RED);
		G.drawRect(x_pos, y_pos, slot_size, slot_size);
	}
}
