package Model;

import java.util.*;

import Types.SelectState;
import Types.SlotId;
import Types.TokenRole;

import java.awt.*;

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
	
	public BoardModel(GameModel GM, ArrayList<ArrayList<String>> map) {
		game = GM;
		
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
						break;
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
		return slots_grid.get(i).get(j);
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
	public int PlayingSide() {
		return playing_side;
	}
	public SelectorModel GetSelector() {
		return selector;
	}
	
	public TokenModel GetTokenFromSlot(SlotModel slot) {
		if(TokenOnSlot(slot)) {
			for(TokenModel tok : dark_tokens) {
				if(tok.GetPos().x==slot.GetCoord().x && tok.GetPos().y==slot.GetCoord().y) {
					return tok;
				}
			}
			for(TokenModel tok : dark_tokens) {
				if(tok.GetPos().x==slot.GetCoord().x && tok.GetPos().y==slot.GetCoord().y) {
					return tok;
				}
			}
			
			return null;
			
		}
		
		return null;
	}
	
	//setters
	private void CreateTokens() {
		//creating all basic tokens
		for(int k=1; k<lines-1; k++) {
			light_tokens.add(new TokenModel(this, new Point(1,k), TokenRole.Knight));
			dark_tokens.add(new TokenModel(this, new Point(7,k), TokenRole.Goblin));
		}
		//creating all couples of tokens
		for(int k=0; k<2; k++) {
			light_tokens.add(new TokenModel(this, new Point(1,k*8), TokenRole.Archer));
			dark_tokens.add(new TokenModel(this, new Point(7,k*8), TokenRole.Manticore));
			
			light_tokens.add(new TokenModel(this, new Point(0,k*8), TokenRole.Valkyrie));
			dark_tokens.add(new TokenModel(this, new Point(8,k*8), TokenRole.Banshee));
			
			light_tokens.add(new TokenModel(this, new Point(0,1+k*6), TokenRole.Golem));
			dark_tokens.add(new TokenModel(this, new Point(8,1+k*6), TokenRole.Troll));
			
			light_tokens.add(new TokenModel(this, new Point(0,2+k*4), TokenRole.Unicorn));
			dark_tokens.add(new TokenModel(this, new Point(8,2+k*4), TokenRole.Basilisk));
		}
		//creating 3 last tokens
		light_tokens.add(new TokenModel(this, new Point(0,3), TokenRole.Djinn));
		light_tokens.add(new TokenModel(this, new Point(0,4), TokenRole.Sorcerer));
		light_tokens.add(new TokenModel(this, new Point(0,5), TokenRole.Phoenix));
		
		dark_tokens.add(new TokenModel(this, new Point(8,3), TokenRole.ShapeShifter));
		dark_tokens.add(new TokenModel(this, new Point(8,4), TokenRole.Sorceress));
		dark_tokens.add(new TokenModel(this, new Point(8,5), TokenRole.Dragon));
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
}
