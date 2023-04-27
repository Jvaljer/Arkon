package Model;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

/**
 * Model class of the game, which contains all the primary variables and attributes for this game
 * to behave as we want. 
 * @author abel
 *
 */
public class GameModel {
	//board model of the game
	private BoardModel board;
	//infobar model of the game
	private InfobarModel infobar;
	//string value to represent map's slots
	private ArrayList<ArrayList<String>> map;
	
	public GameModel() {
		map = ConvertMapFile("maps/board_1.txt");
		board = new BoardModel(this, map);
		infobar = new InfobarModel(this);
	}
	
	//getters
	public ArrayList<ArrayList<String>> GetStrMap(){
		return map;
	}
	public BoardModel GetBoard() {
		return board;
	}
	public InfobarModel GetInfobar() {
		return infobar;
	}
	public TokenModel GetTokenOnSlot(SlotModel slot){
		for(TokenModel tok : board.GetDarkTokens()) {
			if(tok.GetPos().x==slot.GetCoord().x && tok.GetPos().y==slot.GetCoord().y) {
				return tok;
			}
		}
		//must replace this with a thrown error
		return null;
	}
	
	//other useful functions
	public ArrayList<ArrayList<String>> ConvertMapFile(String path){
		ArrayList<ArrayList<String>> str_map = new ArrayList<ArrayList<String>>();
		
		//starting to read the file
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] strings = line.split(" ");
                ArrayList<String> split_line = new ArrayList<String>();
                for (String s : strings) {
                	//here I wanna add the character
                	split_line.add(s);
                }
                str_map.add(split_line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		return str_map;
	}
	
	
}
