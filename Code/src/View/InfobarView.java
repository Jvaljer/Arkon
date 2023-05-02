package View;

import javax.swing.JPanel;
import Model.GameModel;
import Model.InfobarModel;
import Model.TokenModel;
import Types.TokenRole;

import java.awt.*;
import javax.swing.JLabel;

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
	//token's name label
	private JLabel token_name;
	//token's specification label
	private JLabel token_detail;
	
	public InfobarView(GameView GV, InfobarModel IbM) {
		game = GV;
		model = IbM;
		
		//here we wanna set the title & specific infos
		token_name = new JLabel("Info-Bar Title");
		token_name.setFont(new Font("Serif", Font.BOLD, 20));
		token_detail = new JLabel("this text is meant to be replaced with some details for sure");
		token_detail.setFont(new Font("Serif", Font.ITALIC, 18));
		//panel's initialization
		setPreferredSize(new Dimension(game.getSize().width,model.GetPanelHeight()));
		setLayout(new GridBagLayout());
		setBackground(new Color(100,150,150));
		
		GridBagConstraints cstr = new GridBagConstraints();
		
		cstr.gridx = 0;
		cstr.gridy = 0;
		add(token_name, cstr);
		
		cstr.gridx = 0;
		cstr.gridy = 1;
		add(token_detail, cstr);
	}
	
	public void Update(TokenModel token) {
		token_name.setText(token.GetRole().Name());
		String move_mode;
		if(token.Fly()) {
			move_mode = "Fly";
		} else if(token.GetRole()!=TokenRole.Sorceress && token.GetRole()!=TokenRole.Wizard){
			move_mode = "Ground";
		} else {
			move_mode = "Doesn't Move";
		}
		token_detail.setText("("+move_mode+"  "+token.GetRole().MoveLimit()+" )");
	}
	public void Clear() {
		token_name.setText(" ");
		token_detail.setText(" ");
	}
	
	public void Draw(Graphics G) {
		//must implement
		return;
	}
	
	@Override
	public void paint(Graphics G) {
		super.paint(G);
		Draw(G);
	}
}
