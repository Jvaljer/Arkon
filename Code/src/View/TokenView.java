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
	private static Image img;
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
				board.GetModel().GetGap() + model.GetPos().x * board.GetModel().GetSlotSize(),
				board.GetModel().GetGap() + model.GetPos().y * board.GetModel().GetSlotSize() );
	}
	
	//Draw method for the concerned token
	public void Draw(Graphics G) {
		G.drawImage(img,center_pos.x-32,center_pos.y-32, 32, 32, null);
	}
}
