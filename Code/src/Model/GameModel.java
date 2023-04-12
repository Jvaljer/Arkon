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
	//string value to represent map's slots
	private ArrayList<ArrayList<String>> map;
	
	public GameModel() {
		map = ConvertMapFile("maps/initial_map.txt");
		board = new BoardModel(this, map);
	}
	
	//getters (yet for testing)
	public ArrayList<ArrayList<String>> GetStrMap(){
		return map;
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
