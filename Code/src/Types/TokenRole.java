package Types;

public enum TokenRole {
	//Dark roles
	Goblin("Goblin",0),
	Manticore("Manticore",0),
	Basilisk("Basilisk",0),
	Banshee("Banshee",0),
	ShapeShifter("ShapeShifter",0),
	Dragon("Dragon",0),
	Troll("Troll",0),
	Sorceress("Sorceress",0),
	//Light roles
	Knight("Knight",1),
	Valkyrie("Valkyrie",1),
	Archer("Archer",1),
	Djinn("Djinn",1),
	Golem("Golem",1),
	Unicorn("Unicorn",1),
	Phoenix("Phoenix",1),
	Sorcerer("Sorcerer",1),
	//exception
	None("None",2);
	
	private final String name;
	private final int side;
	
	private TokenRole(String str, int bin) {
		name = str;
		side = bin;
	}
	
	//getters
	public String Name() {
		return name;
	}
	public int Side() {
		return side;
	}
}
