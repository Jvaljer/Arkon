package Model;

/**
 * Infobar Model Class, that contains all the needed informations about the display of game's information
 * more precisely, this class only contains the string values of the label we're gonna display with the infobar
 * @author abel
 *
 */
public class InfobarModel {
	//game's model it belongs to
	private GameModel game;
	//title's text 
	private String title;
	//explanation's text
	private String detail;
	//geometrical values & attributes
	private final static int panel_height = 250;
	
	public InfobarModel(GameModel GM) {
		game = GM;
		title = "title";
		detail = "detail";
	}
	
	//getters 
	public String GetTitle() {
		return title;
	}
	public String GetDetail() {
		return detail;
	}
	public int GetPanelHeight() {
		return panel_height;
	}
	
	//setters
	public void SetTitle(String str) {
		title = str;
	}
	public void SetDetail(String str) {
		detail = str;
	}
}
