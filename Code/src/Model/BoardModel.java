package Model;

import java.util.*;

import Types.SelectState;
import Types.SlotId;
import Types.TokenRole;

import java.awt.*;
import Types.CustomException;
import Types.Diagonal;
import Types.Direction;

/**
 * Board Model class, containing all the informations related to the game's board
 * includings its size and all of its parameters such as lumina slots etc.
 * @author abel
 *
 */
public class BoardModel {
	//game to which belongs this board
	private GameModel game;
	//selector model
	private SelectorModel selector;
	//board's grid
	private ArrayList<ArrayList<SlotModel>> slots_grid;
	//board's power slots parameters
	private ArrayList<Point> power_points;
	//board's size parameters
	private final static int lines = 9;
	private final static int columns = 9;
	private final static int slot_size = 50;
	//gap value to the sides of the frame
	private final static int edge_gap = slot_size;
	//list of all the light & dark tokens' model
	private ArrayList<TokenModel> light_tokens;
	private ArrayList<TokenModel> dark_tokens;
	//playing side -> 0 for light, 1 for dark
	private int playing_side;
	//board's selection state 
	public SelectState select_state;
	
	public BoardModel(GameModel GM, ArrayList<ArrayList<String>> map) throws CustomException {
		game = GM;
		playing_side = 1;
		slots_grid = new ArrayList<ArrayList<SlotModel>>();
		
		for(int j=0; j<9; j++) {
			ArrayList<SlotModel> model_line = new ArrayList<SlotModel>();
			ArrayList<String> str_line = map.get(j);
			for(int i=0; i<9; i++) {
				Point coord = new Point(i,j);
				switch (str_line.get(i)) {
					case "D":
						model_line.add(new SlotModel(this, SlotId.Dark, coord));
						break;
						
					case "L":
						model_line.add(new SlotModel(this, SlotId.Light, coord));
						break;
						
					case "x":
						model_line.add(new SlotModel(this, SlotId.Lumina, coord));
						break;
						
					default:
						throw new CustomException("ERROR-> character from the map file wasn't recognized : "+str_line.get(i));
				}
			}
			slots_grid.add(model_line);		
		}

		power_points = new ArrayList<Point>();
		power_points.add(new Point(0,4));
		power_points.add(new Point(4,4));
		power_points.add(new Point(8,4));
		power_points.add(new Point(4,0));
		power_points.add(new Point(4,8));
		
		light_tokens = new ArrayList<TokenModel>();
		dark_tokens = new ArrayList<TokenModel>();
		
		selector = new SelectorModel(this);
		
		select_state = SelectState.None;
		
		CreateTokens();
	}
	
	//getters
	public int GetLines() {
		return lines;
	}
	public int GetColumns() {
		return columns;
	}
	public SlotModel GetSlotFromIndex(int i, int j) {
		//indexs are switched up with coords
		return slots_grid.get(i).get(j);
	}
	public SlotModel GetSlotFromCoord(Point coord) {
		SlotModel slot = slots_grid.get(coord.y).get(coord.x);
		return slot;
	}
	public int GetGap() {
		return edge_gap;
	}
	public int GetSlotSize() {
		return slot_size;
	}
	public int GetTokensAmount() {
		return 18;
	}
	public ArrayList<TokenModel> GetLightTokens(){
		return light_tokens;
	}
	public ArrayList<TokenModel> GetDarkTokens(){
		return dark_tokens;
	}
	public ArrayList<Point> GetPowerPoints(){
		return power_points;
	}
	public int PlayingSide() {
		return playing_side;
	}
	public SelectorModel GetSelector() {
		return selector;
	}
	
	public TokenModel GetTokenFromSlot(SlotModel slot) throws CustomException {
		if(TokenOnSlot(slot)) {
			for(TokenModel tok : dark_tokens) {
				if(tok.GetPos().x==slot.GetCoord().x && tok.GetPos().y==slot.GetCoord().y) {
					return tok;
				}
			}
			for(TokenModel tok : light_tokens) {
				if(tok.GetPos().x==slot.GetCoord().x && tok.GetPos().y==slot.GetCoord().y) {
					return tok;
				}
			}
			
			throw new CustomException("ERROR-> there's a token on the slot that hasn't been recognized");
		}
		throw new CustomException("ERROR-> there is not any token on this slot");
	}
	
	public TokenModel GetTokenFromCoord(Point coord) throws CustomException {
		if(TokenOnCoord(coord)) {
			for(TokenModel tok : dark_tokens) {
				if(tok.GetPos().x==coord.x && tok.GetPos().y==coord.y) {
					return tok;
				}
			}
			for(TokenModel tok : light_tokens) {
				if(tok.GetPos().x==coord.x && tok.GetPos().y==coord.y) {
					return tok;
				}
			}
			
			throw new CustomException("ERROR-> there's a token at the coord that hasn't been recognized");
		}
		throw new CustomException("ERROR-> there is not any token at this coord");
	}
	
	public ArrayList<Point> GetGroundNeighbors(Point pts){
		ArrayList<Point> ret = new ArrayList<Point>();
		for(Direction dir : Direction.values()) {
			Point neigh = new Point(pts.x+dir.GetX(), pts.y+dir.GetY());
			if(CoordInBound(neigh)) {
				ret.add(neigh);
			}
		}
		return ret;
	}
	
	public ArrayList<Point> GetFlyNeighbors(Point pts){
		ArrayList<Point> ret = new ArrayList<Point>();
		for(Direction dir : Direction.values()) {
			Point neigh = new Point(pts.x+dir.GetX(), pts.y+dir.GetY());
			if(CoordInBound(neigh)) {
				ret.add(neigh);
			}
		}
		for(Diagonal diag : Diagonal.values()) {
			Point neigh = new Point(pts.x+diag.GetX(), pts.y+diag.GetY());
			if(CoordInBound(neigh)) {
				ret.add(neigh);
			}
		}
		return ret;
	}
	
	//setters
	private void CreateTokens() {
		//creating all basic tokens
		for(int k=1; k<lines-1; k++) {
			try {
				light_tokens.add(new TokenModel(this, new Point(1,k), TokenRole.Knight));
				dark_tokens.add(new TokenModel(this, new Point(7,k), TokenRole.Goblin));
			} catch (CustomException c_e) {
				c_e.printStackTrace();
			}
		}
		//creating all couples of tokens
		for(int k=0; k<2; k++) {
			try {
				light_tokens.add(new TokenModel(this, new Point(1,k*8), TokenRole.Archer));
				dark_tokens.add(new TokenModel(this, new Point(7,k*8), TokenRole.Manticore));
				
				light_tokens.add(new TokenModel(this, new Point(0,k*8), TokenRole.Valkyrie));
				dark_tokens.add(new TokenModel(this, new Point(8,k*8), TokenRole.Banshee));
				
				light_tokens.add(new TokenModel(this, new Point(0,1+k*6), TokenRole.Golem));
				dark_tokens.add(new TokenModel(this, new Point(8,1+k*6), TokenRole.Troll));
				
				light_tokens.add(new TokenModel(this, new Point(0,2+k*4), TokenRole.Unicorn));
				dark_tokens.add(new TokenModel(this, new Point(8,2+k*4), TokenRole.Basilisk));
			} catch(CustomException c_e) {
				c_e.printStackTrace();
			}
		}
		//creating 3 last tokens
		try {
			light_tokens.add(new TokenModel(this, new Point(0,3), TokenRole.Djinn));
			light_tokens.add(new TokenModel(this, new Point(0,4), TokenRole.Wizard));
			light_tokens.add(new TokenModel(this, new Point(0,5), TokenRole.Phoenix));
			
			dark_tokens.add(new TokenModel(this, new Point(8,3), TokenRole.ShapeShifter));
			dark_tokens.add(new TokenModel(this, new Point(8,4), TokenRole.Sorceress));
			dark_tokens.add(new TokenModel(this, new Point(8,5), TokenRole.Dragon));
		} catch (CustomException c_e) {
			c_e.printStackTrace();
		}
	}
	public void SetSelectState(SelectState state) {
		select_state = state;
	}
	
	//predicates and methods
	public boolean TokenOnSlot(SlotModel slot) {
		for(TokenModel tok : light_tokens) {
			if(tok.GetPos().x==slot.GetCoord().x && tok.GetPos().y==slot.GetCoord().y) {
				return true;
			}
		}
		for(TokenModel tok : dark_tokens) {
			if(tok.GetPos().x==slot.GetCoord().x && tok.GetPos().y==slot.GetCoord().y) {
				return true;
			}
		}
		return false;
	}
	
	public boolean TokenOnCoord(Point coord) {
		for(TokenModel tok : light_tokens) {
			if(tok.GetPos().x==coord.x && tok.GetPos().y==coord.y) {
				return true;
			}
		}
		for(TokenModel tok : dark_tokens) {
			if(tok.GetPos().x==coord.x && tok.GetPos().y==coord.y) {
				return true;
			}
		}
		return false;
	}
	
	public boolean CoordInBound(Point coord) {
		return (coord.x>=0) && (coord.x<lines) && (coord.y>=0) && (coord.y<columns);
	}
	
	public boolean TokenCanMove(TokenModel token, Direction direction, Point src) throws CustomException {
		Point nxt_coord = new Point(token.GetPos().x + direction.GetX(), token.GetPos().y + direction.GetY());
		SlotModel nxt_slot = GetSlotFromCoord(nxt_coord);
		if(!token.MovingRules(CountDist(src,nxt_coord,token.Fly()))) {
			return false;
		}
		boolean tok_on_slot = TokenOnSlot(nxt_slot);
		if(tok_on_slot) {
			TokenModel tok = GetTokenFromSlot(nxt_slot);
			boolean cond = (tok.GetRole().Side()!=token.GetRole().Side()) || (tok.GetRole().Side()==token.GetRole().Side() && token.Fly());
			return cond;
		} else {
			return true;
		}
	}
	
	public int CountDist(Point src, Point dst, boolean fly_tok) throws CustomException {
		//first we wanna get the shortest path between src and dst inside the grid 
		Point[][] graph = new Point[lines][columns];
		
		HashMap<Point, Integer> dists = new HashMap<Point, Integer>();
		HashMap<Point, Point> previous = new HashMap<Point, Point>();
		
		for(ArrayList<SlotModel> line : slots_grid) {
			for(SlotModel slot : line) {
				int x = slot.GetCoord().x;
				int y = slot.GetCoord().y;
				graph[x][y] = new Point(x, y);
				dists.put(graph[x][y], Integer.MAX_VALUE);
				previous.put(graph[x][y], null);
			}
		}
		
		dists.put(src, 0);
		PriorityQueue<Point> unvisited = new PriorityQueue<Point>(Comparator.comparingInt(dists::get));
		unvisited.add(src);
		while(!unvisited.isEmpty()) {
			Point cur = unvisited.poll();
			if(cur==dst) {
				break;
			}
			
			ArrayList<Point> neighs;
			if(fly_tok) {
				neighs = GetFlyNeighbors(cur);
			} else {
				neighs = GetGroundNeighbors(cur);
			}
			for(Point neigh : neighs) {
				int d = dists.get(cur)+1;
				if(d<dists.get(neigh)) {
					dists.put(neigh, d);
					previous.put(neigh,  cur);
					unvisited.add(neigh);
				}
			}
		}
		if(dists.get(dst)==Integer.MAX_VALUE) {
			throw new CustomException("ERROR-> there's no such path between the indicated coordinates");
		}
		ArrayList<Point> path = new ArrayList<Point>();
		Point cp = dst;
		while(cp!=null) {
			path.add(cp);
			cp = previous.get(cp);
		}
		return path.size()-1;
	}
	
	public void SwitchTurn() throws CustomException {
		if(playing_side==0) {
			playing_side = 1;
			selector.SetSelected(new Point(0,4));
		} else if(playing_side==1) {
			playing_side = 0;
			selector.SetSelected(new Point(8,4));
		} else {
			throw new CustomException("ERROR-> the playing side has an unknown value");
		}
	}
}
