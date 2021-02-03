package tema;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class UserPage extends Page {
	//UserPage din enunt
	
	static JFrame allUsersPage = new JFrame("Users' Pages");
	static JScrollPane east;
	static JTextArea textArea = new JTextArea();
	static JTextField textField = new JTextField();
	static JLabel label = new JLabel("<html>Scrieti numele<br>utilizatorului cautat:</html>");
	static JButton search = new JButton("Cauta");
	static JButton backToMenu = new JButton("Back");
	
	public static void display() {
		resetFrame();
		
		label.setBounds(MARGIN, MARGIN, 400, 2 * SPACING);
		label.setForeground(Color.ORANGE);
		label.setFont(new Font("Arial", Font.PLAIN, 30));
		
		textArea.setBounds(0, 0, 300, 700);
		textArea.setForeground(Color.ORANGE);
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		
		east = new JScrollPane(textArea);
		east.setBackground(Color.DARK_GRAY);
		east.setBounds(400, 0, 400, 700);
		
		textField.setBounds(MARGIN, 80, 300, 40);
		textField.setForeground(Color.ORANGE);
		textField.setBackground(Color.GRAY);
		textField.setFont(new Font("Arial", Font.PLAIN, 30));
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		search.setBounds(MARGIN, 130, 200, 50);
		search.setForeground(Color.ORANGE);
		search.setBackground(Color.GRAY);
		search.setFont(new Font("Arial", Font.PLAIN, 30));
		search.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		search.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		User searched = app.getUser(textField.getText());
        		if(searched != null)
        			textArea.setText(searched.getUserAllInfo());
        		else 
        			textArea.setText("\n    Utilizatorul nu a fost gasit");
        		UserPage.display();
        	}
        });

		backToMenu.setBounds(90, HEIGHT - BOTTOM_MARGIN, 200, BUTTONS_SPACING);
		backToMenu.setForeground(Color.ORANGE);
		backToMenu.setBackground(Color.GRAY);
		backToMenu.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		backToMenu.setFont(new Font("Arial", Font.BOLD, 34));
		backToMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		GraphicInterface.display();
        	}
        });
		
		frame.add(backToMenu);
		frame.add(label);
		frame.add(east);
		frame.add(textField);
		frame.add(search);
	}	
	
}
