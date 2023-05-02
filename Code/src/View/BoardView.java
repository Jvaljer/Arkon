package View;

import javax.swing.JPanel;
import Model.BoardModel;
import Types.SlotId;

import java.util.*;
import java.awt.*;
import Types.CustomException;

/**
 * Board View Class, handling all the necessary stuff to properly display the game's board,
 * referring a board model, and having a grid of slot's views as attribute.
 * @author abel
 *
 */
public class BoardView extends JPanel {
	//game's view this panel belongs to
	private GameView game;
	//Selector view 
	private SelectorView selector;
	//board model on which is based this view
	private BoardModel model;
	//grid of the slot's views
	private ArrayList<ArrayList<SlotView>> slots_grid;
	//list of all the light & dark tokens' views
	private ArrayList<TokenView> light_tokens;
	private ArrayList<TokenView> dark_tokens;
	
	public BoardView(GameView GV, BoardModel BM) {
		game = GV;
		model = BM;
		
		slots_grid = new ArrayList<ArrayList<SlotView>>();
		for(int j=0; j<model.GetLines(); j++) {
			ArrayList<SlotView> view_line = new ArrayList<SlotView>();
			for(int i=0; i<model.GetColumns(); i++) {
				view_line.add(new SlotView(this, model.GetSlotFromIndex(i,j)));
			}
			slots_grid.add(view_line);
		}
		
		light_tokens = new ArrayList<TokenView>();
		dark_tokens = new ArrayList<TokenView>();
		
		for(int i=0; i<model.GetTokensAmount(); i++) {
			light_tokens.add(new TokenView(this,model.GetLightTokens().get(i)));
			dark_tokens.add(new TokenView(this,model.GetDarkTokens().get(i)));
		}
		
		selector = new SelectorView(this, model.GetSelector());
		
		//now setting up the JPanel
		setPreferredSize(new Dimension(550,550));
	}
	//getters
	public BoardModel GetModel() {
		return model;
	}
	public SelectorView GetSelector() {
		return selector;
	}
	public Color GetTurnColor() throws CustomException {
		Color color;
		if(model.PlayingSide()==1) {
			//light is playing
			color = new Color(255, 255, 204);
		} else if(model.PlayingSide()==0) {
			//dark is playing
			color = new Color(0, 153, 0);
		} else {
			throw new CustomException("ERROR-> the playing_side isn't a known value");
		}
		return color;
	}
	
	//Draw method for the game's board
	public void DrawBoard(Graphics G) {
		for(ArrayList<SlotView> line : slots_grid) {
			for(SlotView slot : line) {
				slot.Draw(G);
			}
		}
	}
	//Draw method for the board's tokens
	public void DrawTokens(Graphics G) {
		for(int i=0; i<model.GetTokensAmount(); i++) {
			if(!light_tokens.get(i).GetModel().IsMoving()) {
				light_tokens.get(i).Draw(G);
			} else {
				light_tokens.get(i).DrawMoving(G);
			}
			
			if(!dark_tokens.get(i).GetModel().IsMoving()) {
				dark_tokens.get(i).Draw(G);
			} else {
				dark_tokens.get(i).DrawMoving(G);
			}
		}
	}
	//Draw method for the power slots
	public void DrawPowerPoints(Graphics G) {
		ArrayList<Point> power_pts = model.GetPowerPoints();
		for(Point pts : power_pts) {
			G.setColor(new Color(255,255,102));
			G.drawRoundRect(model.GetGap()+(pts.x*model.GetSlotSize())+5, model.GetGap()+(pts.y*model.GetSlotSize())+5, 40, 40, 5, 5);
			G.drawRoundRect(model.GetGap()+(pts.x*model.GetSlotSize())+7, model.GetGap()+(pts.y*model.GetSlotSize())+7, 36, 36, 5, 5);
			G.setColor(new Color(255,255,204));
			G.drawRoundRect(model.GetGap()+(pts.x*model.GetSlotSize())+6, model.GetGap()+(pts.y*model.GetSlotSize())+6, 38, 38, 5, 5);
		}
	}

	//DrawMethod for the whole board
	public void Draw(Graphics G) {
		DrawBoard(G);
		DrawTokens(G);
		DrawPowerPoints(G);
		selector.Draw(G);
	}
	
	@Override
	public void paint(Graphics G) {
		super.paint(G);
		DrawBoard(G);
		DrawPowerPoints(G);
		DrawTokens(G);
		selector.Draw(G);
	}
}
