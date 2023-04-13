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
	//token's role
	private TokenRole role;
	//alive predicate that tells if this token is alive or not
	private boolean alive;
	
	public TokenModel(BoardModel BM, Point P, TokenRole TR) {
		board = BM;
		pos = P;
		role = TR;
		alive = true;
	}
	
}
