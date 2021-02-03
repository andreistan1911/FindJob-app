package tema;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CompanyPage extends Page {
	//Pagina unei companii din care poti selecta unul dintre departamente
	//	pentru detalii despre acesta
	static final int NO_DEPARTMENTS = 4;
	
	static Company company = null;
	static JLabel departmentsLabel = new JLabel("Departments:");
    static JButton backToAdmin = new JButton("Back");
    static JButton[] departmentsButtons = new JButton[NO_DEPARTMENTS];

	static String[] departmentsNames = {"IT", "Management", "Marketing", "Finance"};
	
	public static void display() {
		resetFrame();
		frame.setTitle(company.name + " Page");
		
		backToAdmin.setBounds(WIDTH/2 - 130, HEIGHT - BOTTOM_MARGIN, 200, BUTTONS_SPACING);
		departmentsLabel.setBounds(MARGIN, MARGIN, 300, SPACING);

		departmentsLabel.setForeground(Color.ORANGE);
		departmentsLabel.setFont(new Font("Arial", Font.BOLD, 34));
		backToAdmin.setFont(new Font("Arial", Font.BOLD, 34));
		backToAdmin.setForeground(Color.ORANGE);
		backToAdmin.setBackground(Color.GRAY);
		backToAdmin.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		
		backToAdmin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AdminPage.display();
        	}
        });
		
		for(int i = 0; i < NO_DEPARTMENTS; i++) {
			String departmentName = departmentsNames[i];
			departmentsButtons[i] = new JButton(departmentName);
			departmentsButtons[i].setBounds(MARGIN, BUTTONS_SPACING * i + BUTTONS_SPACING + MARGIN, 300, BUTTONS_SPACING);
			departmentsButtons[i].setFont(new Font("Arial", Font.PLAIN, SPACING));
			departmentsButtons[i].setForeground(Color.ORANGE);
			departmentsButtons[i].setBackground(Color.GRAY);
			departmentsButtons[i].setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
			frame.add(departmentsButtons[i]);
			departmentsButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
	        		DepartmentPage.department = company.getDepartment(departmentName);
	        		DepartmentPage.display();

	        	}
			});
		}
		
		frame.add(departmentsLabel);
		frame.add(backToAdmin);
	}
}
