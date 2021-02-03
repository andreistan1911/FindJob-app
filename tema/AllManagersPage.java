package tema;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class AllManagersPage extends Page {
	//Un meniu pre-ManagerPage, descris in enunt, prin care selectezi
	//	managerul a carui lista de cereri vrei sa o vezi 
	static JLabel managersLabel = new JLabel("Managers:");
	static JButton backToMenu = new JButton("Back");
	static JButton[] managersButtons = new JButton[app.getCompanies().size()];
	
	public static void display() {
		resetFrame();
		frame.setTitle("Managers' Page");

		managersLabel.setBounds(MARGIN, 0, 200, 40);
		managersLabel.setForeground(Color.ORANGE);
		managersLabel.setFont(new Font("Arial", Font.BOLD, 34));
		backToMenu.setBounds(WIDTH/2 - 130, HEIGHT - BOTTOM_MARGIN, 200, BUTTONS_SPACING);
		backToMenu.setForeground(Color.ORANGE);
		backToMenu.setBackground(Color.GRAY);
		backToMenu.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		backToMenu.setFont(new Font("Arial", Font.BOLD, 34));
		
		frame.add(managersLabel);
		frame.add(backToMenu);
		
		backToMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GraphicInterface.display();
        	}
        });
		
		for(int i = 0; i < app.getCompanies().size(); i++) {
			String companyName = app.getCompanies().get(i).name;
			managersButtons[i] = new JButton("<html>" + app.getCompanies().get(i).manager.toString() + " " + "<i><b>" + 
											companyName + "</b></i></html>");
			managersButtons[i].setBounds(MARGIN, BUTTONS_SPACING * i + BUTTONS_SPACING + MARGIN, 500, BUTTONS_SPACING);
			managersButtons[i].setFont(new Font("Arial", Font.PLAIN, SPACING));
			managersButtons[i].setForeground(Color.ORANGE);
			managersButtons[i].setBackground(Color.GRAY);
			managersButtons[i].setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
			frame.add(managersButtons[i]);
			managersButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ManagerPage.companyName = companyName;
	        		ManagerPage.manager = app.getCompany(companyName).manager;
	        		ManagerPage.display();
	        	}
			});
		}
	}
}
