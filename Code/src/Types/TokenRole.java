package Types;

public enum TokenRole {
	//Dark roles
	Goblin("Goblin",0, MoveMode.Ground, 3),
	Manticore("Manticore",0, MoveMode.Ground, 3),
	Banshee("Banshee",0, MoveMode.Fly, 3),
	ShapeShifter("ShapeShifter",0, MoveMode.Fly, 4),
	Basilisk("Basilisk",0, MoveMode.Ground, 3),
	Troll("Troll",0, MoveMode.Ground, 4),
	Dragon("Dragon",0, MoveMode.Fly, 5),
	Sorceress("Sorceress",0, MoveMode.None, 0),
	//Light roles
	Knight("Knight",1, MoveMode.Ground, 3),
	Archer("Archer",1, MoveMode.Ground, 3),
	Valkyrie("Valkyrie",1, MoveMode.Fly, 3),
	Djinn("Djinn",1, MoveMode.Fly, 4),
	Golem("Golem",1, MoveMode.Ground, 3),
	Unicorn("Unicorn",1, MoveMode.Ground, 4),
	Phoenix("Phoenix",1, MoveMode.Fly, 5),
	Wizard("Wizard",1, MoveMode.None, 0),
	//exception
	None("None",2, MoveMode.None, -1);
	
	private final String name;
	private final int side;
	private final MoveMode move_mode;
	private final int move_limit;
	
	private TokenRole(String str, int bin, MoveMode mm, int ml) {
		name = str;
		side = bin;
		move_mode = mm;
		move_limit = ml;
	}
	
	//getters
	public String Name() {
		return name;
	}
	public int Side() {
		return side;
	}
	public MoveMode MoveMode() {
		return move_mode;
	}
	public int MoveLimit() {
		return move_limit;
	}
}
