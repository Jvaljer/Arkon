package Types;

import java.awt.Point;

public enum Diagonal {
	UpLeft(-1,-1),
	DownLeft(-1,1),
	UpRight(1,-1),
	DownRight(1,1);
	
	private int X_dir;
	private int Y_dir;
	
	private Diagonal(int x, int y) {
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
		return (new Point(X_dir,Y_dir));
	}
}
