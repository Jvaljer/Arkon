package Types;

import java.awt.Point;

public enum Direction {
	Up(0,-1),
	Down(0,1),
	Left(-1,0),
	Right(1,0);
	
	private int X_dir;
	private int Y_dir;
	
	private Direction(int x, int y) {
		X_dir = x;
		Y_dir = y;
	}
	
	public int GetX() {
		return X_dir;
	}
	public int GetY() {
		return Y_dir;
	}
	public Point Point() {
		return (new Point(X_dir, Y_dir));
	}
}
