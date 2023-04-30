package Types;

public enum TokenRole {
	//Dark roles
	Goblin("Goblin",0, MoveMode.Ground),
	Manticore("Manticore",0, MoveMode.Ground),
	Banshee("Banshee",0, MoveMode.Fly),
	ShapeShifter("ShapeShifter",0, MoveMode.Fly),
	Basilisk("Basilisk",0, MoveMode.Ground),
	Troll("Troll",0, MoveMode.Ground),
	Dragon("Dragon",0, MoveMode.Fly),
	Sorceress("Sorceress",0, MoveMode.None),
	//Light roles
	Knight("Knight",1, MoveMode.Ground),
	Archer("Archer",1, MoveMode.Ground),
	Valkyrie("Valkyrie",1, MoveMode.Fly),
	Djinn("Djinn",1, MoveMode.Fly),
	Golem("Golem",1, MoveMode.Ground),
	Unicorn("Unicorn",1, MoveMode.Ground),
	Phoenix("Phoenix",1, MoveMode.Fly),
	Sorcerer("Sorcerer",1, MoveMode.None),
	//exception
	None("None",2, MoveMode.None);
	
	private final String name;
	private final int side;
	private final MoveMode move_mode;
	
	private TokenRole(String str, int bin, MoveMode mm) {
		name = str;
		side = bin;
		move_mode = mm;
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
}
