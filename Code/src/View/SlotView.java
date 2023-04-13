package View;

import Model.SlotModel;
import java.awt.*;
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
		center_pos = new Point(model.GetCoord().x*side_len - side_len/2, model.GetCoord().y*side_len - side_len/2);
		color = SetColorFromId(model.GetId());
	}
	
	//setters
	public Color SetColorFromId(SlotId sid) {
		switch (sid) {
			case Dark:
				return (new Color(50,100,150));
			case Light:
				return (new Color(150,200,250));
			case Lumina:
				return (new Color(0,0,255));
			default:
				return (new Color(255,255,255));
		}
	}
	
	//Draw method for this slot
	public void Draw(Graphics G) {
		System.out.println("drawing the slot ("+model.GetCoord().x+","+model.GetCoord().y+")");
		System.out.println("     its center point is : ("+center_pos.x+","+center_pos.y+")");
		G.setColor(color);
		G.fillRect(center_pos.x, center_pos.y, side_len, side_len);
	}
}
