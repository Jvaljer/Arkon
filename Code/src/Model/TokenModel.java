package Model;

import java.awt.*;
import Types.TokenRole;

/**
 * Token Model class, that contains all the necessary informations and values concerning a token on the board
 * @author abel
 *
 */
public class TokenModel {
	//board model's to which this token belongs
	private BoardModel board;
	//token's position on the board
	private Point pos;
	//token's moving pos (visual pos on board in pixel) 
	private Point moving_pos;
	//token's role
	private TokenRole role;
	//alive predicate that tells if this token is alive or not
	private boolean alive;
	//moving predicate that tells if this token is moving or not
	private boolean moving;
	//path to the corresponding image
	private String img_path;
	//fighting attributes of the token
	private int health_value;
	private int atk_value;
	private boolean range_atk;
	private boolean fly;
	
	public TokenModel(BoardModel BM, Point P, TokenRole TR) {
		board = BM;
		pos = P;
		moving_pos = pos;
		role = TR;
		alive = true;
		moving = false;
		
		img_path = SetImagePath();
	}
	
	//getters
	public String GetImagePath() {
		return img_path;
	}
	public Point GetPos() {
		return pos;
	}
	public Point GetMovingPos() {
		return moving_pos;
	}
	public TokenRole GetRole() {
		return role;
	}
	public boolean IsMoving() {
		return moving;
	}
	
	//setter for the image's path to go
	private String SetImagePath() {
		String tok_name = role.Name();
		String path;
		switch (role.Side()) {
			case 0:
				path = "./images/Tokens/DarkSide/";
				break;
			case 1:
				path = "./images/Tokens/LightSide/";
				break;
			default:
				path = "";
				break;
		}
		return (path+tok_name+".png");
	}
	
	public void StartsMoving() {
		moving = true;
	}
	public void StopsMoving() {
		moving = false;
	}
	
	public void IncrementMovingPos(Point direction) {
		Point old = new Point(moving_pos.x,moving_pos.y);
		moving_pos = new Point(old.x+direction.x, old.y+direction.y);
	}
	
	public void MoveTo(Point dst) {
		pos = dst;
	}
}
