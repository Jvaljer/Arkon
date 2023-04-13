package View;

import javax.swing.JPanel;
import Model.GameModel;
import Model.InfobarModel;
import java.awt.*;

/**
 * Infobar View Class, refers to an infobar model to setup & display correclty the infobar of the game
 * @author abel
 *
 */
public class InfobarView extends JPanel {
	//game's view it belongs to
	private GameView game;
	//info bar model it refers to
	private InfobarModel model;
	
	public InfobarView(GameView GV, InfobarModel IbM) {
		game = GV;
		model = IbM;
		
		//panel's initialization
		setPreferredSize(new Dimension(game.getSize().width,model.GetPanelHeight()));
	}
}
