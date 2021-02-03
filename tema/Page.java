package tema;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public abstract class Page {
	//Clasa abstracta cu un JFrame static si ccateva constante.
	//Stiu ca Application este singleton, dar "app" este mai scurt decat Application.getInstance() 
	static final int WIDTH = 800;
	static final int HEIGHT = 600;
	static final int RIGHT_MARGIN = 270;
	static final int BOTTOM_MARGIN = 85;
	static final int BUTTONS_SPACING = 40;
	static final int PANE_SPACING = 25;
	static final int MARGIN = 10;
	static final int SPACING = 30;
	static Application app = null;
	static JFrame frame = new JFrame();
	
	static void resetFrame() {
		frame.getContentPane().removeAll();
		frame.setLayout(null);
		frame.repaint();
	}
	
	static void setFrame() {
		frame.setSize(WIDTH, HEIGHT);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
	}
}
