package View;

import Model.SlotModel;
import java.awt.*;

import Types.CustomException;
import Types.SlotId;

/**
 * Slot View class, containing everything we need to display a slot (color & position) correclty 
 * from a precise model and belonging to the game's board.
 * @author abel
 *
 */
public class SlotView {
	//belonging game's board view
	private BoardView board;
	//referred slot model
	private SlotModel model;
	//all geometrical attributes
	private Point center_pos;
	private int side_len;
	private Color color;
	
	public SlotView(BoardView BV, SlotModel SM) {
		board = BV;
		model = SM;
		
		side_len = model.GetSideLength();
		center_pos = new Point(model.GetCoord().x*side_len + board.GetModel().GetGap(), model.GetCoord().y*side_len + board.GetModel().GetGap());
		try {
			color = SetColorFromId(model.GetId());
		} catch (CustomException c_e) {
			c_e.printStackTrace();
		}
	}
	
	//setters
	public Color SetColorFromId(SlotId sid) throws CustomException {
		switch (sid) {
			case Dark:
				return (new Color(50,100,150));
			case Light:
				return (new Color(150,200,250));
			case Lumina:
				return (new Color(0,0,255));
			default:
				throw new CustomException("ERROR-> the id of the slot isn't a known value");
		}
	}
	
	//Draw method for this slot
	public void Draw(Graphics G) {
		G.setColor(color);
		G.fillRect(center_pos.x, center_pos.y, side_len, side_len);
	}
}
