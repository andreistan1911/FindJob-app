package tema;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class GraphicInterface extends Page {
	//Meniul principal
	
	static BufferedImage image;
	static JPanel photoPane = new JPanel();
	static JPanel buttonsPane = new JPanel();
	static JButton adminPageButton = new JButton("<html>" + new String("Admin\nPage").replaceAll("\\n", "<br>") + "</html>");
	static JButton managersPagesButton = new JButton("<html>" + new String("Managers\nPages").replaceAll("\\n", "<br>") + "</html>");
	static JButton usersPagesButton = new JButton("<html>" + new String("User\nPage").replaceAll("\\n", "<br>") + "</html>"); 
	
	public static void display() {
		resetFrame();
		frame.setTitle("Menu");
		frame.setLayout(new GridLayout(2, 1));
		
		adminPageButton.setFont(new Font("Arial", Font.PLAIN, 40));
		managersPagesButton.setFont(new Font("Arial", Font.PLAIN, 40));
		usersPagesButton.setFont(new Font("Arial", Font.PLAIN, 40));
		
		adminPageButton.setForeground(Color.ORANGE);
		managersPagesButton.setForeground(Color.ORANGE);
		usersPagesButton.setForeground(Color.ORANGE);
		
		adminPageButton.setBackground(Color.DARK_GRAY);
		managersPagesButton.setBackground(Color.DARK_GRAY);
		usersPagesButton.setBackground(Color.DARK_GRAY);
		
		adminPageButton.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		managersPagesButton.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		usersPagesButton.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
			
		try {                
			image = ImageIO.read(new File("findJob.png"));
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
		
		JLabel picLabel = new JLabel(new ImageIcon(image));
		photoPane.add(picLabel);
		
		buttonsPane.setLayout(new GridLayout(1, 3));
		buttonsPane.add(adminPageButton);
		buttonsPane.add(managersPagesButton);
		buttonsPane.add(usersPagesButton);
		
		frame.add(photoPane);
		frame.add(buttonsPane);
		
		
		adminPageButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AdminPage.display();
        	}
        });
		
		managersPagesButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AllManagersPage.display();
        	}
        });
		
		usersPagesButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UserPage.display();
        	}
        });
	}
}
