package tema;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class AdminPage extends Page{
	//AdminPage descris in enunt
	static JLabel usersLabel = new JLabel("Users:");
	static JLabel companiesLabel = new JLabel("Companies:");
	static JLabel[] usersLabelList = new JLabel[app.getUsers().size()];
	static JButton backToMenu = new JButton("Back");
	static JButton[] companiesButtons = new JButton[app.getCompanies().size()];
	
    public static void display() {
		resetFrame();
		frame.setTitle("Admin Page");
		
		usersLabel.setBounds(MARGIN, MARGIN, 200, SPACING);
		usersLabel.setForeground(Color.ORANGE);
		usersLabel.setFont(new Font("Arial", Font.BOLD, 34));
		companiesLabel.setBounds(WIDTH - RIGHT_MARGIN, MARGIN, 200, SPACING);
		companiesLabel.setForeground(Color.ORANGE);
		companiesLabel.setFont(new Font("Arial", Font.BOLD, 34));
		backToMenu.setBounds(WIDTH/2 - 130, HEIGHT - BOTTOM_MARGIN, 200, BUTTONS_SPACING);
		backToMenu.setForeground(Color.ORANGE);
		backToMenu.setBackground(Color.GRAY);
		backToMenu.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		backToMenu.setFont(new Font("Arial", Font.BOLD, 34));
		
		frame.add(usersLabel);
		frame.add(companiesLabel);
		frame.add(backToMenu);
		
		backToMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GraphicInterface.display();
        	}
        });
		
		for(int i = 0; i < app.getCompanies().size(); i++) {
			String companyName = app.getCompanies().get(i).name;
			companiesButtons[i] = new JButton(companyName);
			companiesButtons[i].setBounds(WIDTH - RIGHT_MARGIN - 30, BUTTONS_SPACING * (i + 1) + MARGIN, 275, BUTTONS_SPACING);
			companiesButtons[i].setFont(new Font("Arial", Font.PLAIN, SPACING));
			companiesButtons[i].setBackground(Color.GRAY);
			companiesButtons[i].setForeground(Color.ORANGE);
			companiesButtons[i].setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
			
			companiesButtons[i].addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        CompanyPage.company = app.getCompany(companyName);
			        CompanyPage.display();
			    }
			});
			frame.add(companiesButtons[i]);
		}
		
		for(int i = 0; i < app.getUsers().size(); i++) {
			usersLabelList[i] = new JLabel(app.getUsers().get(i).toString());
			usersLabelList[i].setFont(new Font("Arial", Font.PLAIN, SPACING));
			usersLabelList[i].setForeground(Color.ORANGE);
			usersLabelList[i].setBounds(SPACING, SPACING * (i + 1) + 10, 500, SPACING);
			frame.add(usersLabelList[i]);
		}
	}
}
