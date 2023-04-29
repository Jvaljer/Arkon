package View;

import Model.TokenModel;
import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;

public class TokenView {
	//board view class the token belongs to
	private BoardView board;
	//token model this view is referring to
	private TokenModel model;
	//image icon got from model's path (size of images is 32x32 px)
	private Image img;
	//geometrical needed informations 
	private Point center_pos;
	
	public TokenView(BoardView BV, TokenModel TM) {
		board = BV;
		model = TM;
		try {
			img = ImageIO.read(new File(model.GetImagePath()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		center_pos = new Point(
			board.GetModel().GetGap() + (model.GetPos().x+1) * board.GetModel().GetSlotSize(),
			board.GetModel().GetGap() + (model.GetPos().y+1) * board.GetModel().GetSlotSize() 
		);
	}
	
	//getter
	public TokenModel GetModel() {
		return model;
	}
	
	//Draw method for the concerned token
	public void Draw(Graphics G) {
		center_pos = new Point(
				board.GetModel().GetGap() + (model.GetPos().x+1) * board.GetModel().GetSlotSize(),
				board.GetModel().GetGap() + (model.GetPos().y+1) * board.GetModel().GetSlotSize() 
		);
		G.drawImage(img, center_pos.x-50, center_pos.y-50, 50, 50, null);
	}
	public void DrawMoving(Graphics G) {
		G.drawImage(img, model.GetMovingPos().x, model.GetMovingPos().y, 50, 50, null);
	}
}
