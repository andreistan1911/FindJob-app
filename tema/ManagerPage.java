package tema;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ManagerPage extends Page {
	//ManagerPage din enunt: are o lista cu cererile curente
	//	si poate sa aleaga daca respinge sau accepta o cerere
	static String companyName = null;
	static Manager manager = null;
	static int selectedIndex = -1;
	static MyPane[] panels;
	
	static class MyPane {
		JPanel pane = new JPanel();
		JLabel reqLabel = new JLabel();
		JButton button = new JButton();
		
		MyPane() {
			//oop
		}
		
		MyPane(JPanel pane, JLabel reqLabel, JButton button) {
			this.pane = pane;
			this.reqLabel = reqLabel;
			this.button = button;
		}
	}
	
	private static void hireAction() {
		//Ce se intampla atunci cand utilizator este angajat
		if(selectedIndex == -1)
			return;
		String[] split = panels[selectedIndex].reqLabel.getText().split(" |\\:");
		Manager oldMan = manager;
		
		String jobName = split[7];
		int i = 8;
		while(!split[i].equals("|"))
			jobName += " " + split[i++];
		
		//Daca job-ul unde a fost angajat nu mai are locuri, dispar toate cererile
		//	pentru acel loc de munca.
		for(i = 0; i < manager.requests.size(); i++)
			if(jobName.equals(manager.requests.get(i).getKey().name))
				if(manager.requests.get(i).getKey().noPositions == 0){
					app.getCompany(companyName).notifyAllObservers();
					manager.requests.remove(manager.requests.get(i));
					i--;
				}
				else
					break;
		
		//Atunci cand un utilizator este angajat la o firma, dispar toate cererile lui
		//	de pretutindeni.
		for(i = 0; i < app.getCompanies().size(); i++) {
			manager = app.getCompanies().get(i).manager;
			for(int j = 0; j < manager.requests.size(); j++) {
    			if(((User)manager.requests.get(j).getValue1()).toString().equals(split[2] + " " + split[3])) {
    				manager.requests.remove(manager.requests.get(j));
    				j--;
    			}
			}
		}
		app.getUser(split[2] + " " + split[3]).update(new Notification("hired", jobName, companyName));
		app.getUser(split[2] + " " + split[3]).convert();
		app.removeUser(split[2] + " " + split[3]);
		manager = oldMan;
	}
	
	private static void rejectAction() {
		//Ce se intampla atunci cand o cerere este respinsa.
		if(selectedIndex == -1)
			return;
		
		String[] split = panels[selectedIndex].reqLabel.getText().split(" |\\:");
		String jobName = split[7];
		int i = 8;
		while(!split[i].equals("|"))
			jobName += " " + split[i++];
		
		for(Request<Job, Consumer> req : manager.requests)
			if(req.getKey().name.equals(jobName) && ((User)req.getValue1()).toString().equals(split[2] + " " + split[3])) {
				manager.requests.remove(req);
				break;
			}
		app.getUser(split[2] + " " + split[3]).update(new Notification("rejected", jobName, companyName));
	}
	
	public static void display() {
		resetFrame();
		frame.setTitle(manager.toString() + " Page");
		
		JScrollPane sPane = new JScrollPane();
		panels = new MyPane[manager.requests.size()];
		
		sPane.setBounds(0, 0, WIDTH, 400);
		
		sPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		sPane.setLayout(null);
		sPane.setBackground(Color.DARK_GRAY);
		
		for(int i = 0; i < manager.requests.size(); i++) {
			int crrIndex = i;
			Request<Job, Consumer> crrReq = manager.requests.get(i);
			MyPane crrPane = new MyPane();
			
			crrPane.reqLabel = new JLabel(crrReq.myToString());
			crrPane.reqLabel.setForeground(Color.ORANGE);
			crrPane.reqLabel.setFont(new Font("Arial", Font.PLAIN, 20));
			crrPane.reqLabel.setBounds(0, 0, WIDTH, PANE_SPACING);
			
			crrPane.button.setBounds(0, 0, WIDTH, PANE_SPACING);
			crrPane.button.setOpaque(false);
			crrPane.button.setContentAreaFilled(false);
			crrPane.button.setBorderPainted(false);
			crrPane.button.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		crrPane.pane.setBackground(Color.GRAY);
	        		if(selectedIndex != -1)
	        			panels[selectedIndex].pane.setBackground(Color.DARK_GRAY);
	        		selectedIndex = crrIndex;
	        	}
	        });
			
			crrPane.pane.setLayout(null);
			crrPane.pane.setBackground(Color.DARK_GRAY);
			crrPane.pane.add(crrPane.reqLabel);
			crrPane.pane.add(crrPane.button);
			panels[i] = crrPane;
			sPane.add(panels[i].pane);
		}
		
		for(int i = 0; i < manager.requests.size(); i++) {
			panels[i].pane.setBounds(MARGIN, 25 * i + MARGIN, WIDTH, PANE_SPACING);
		}
		
		JPanel pane = new JPanel();
		JButton hire = new JButton("Angajeaza");
		JButton reject = new JButton("Respinge");
		JButton backToMenu = new JButton("Back");
		hire.setBounds(WIDTH/2 - 230, 0, 200, BUTTONS_SPACING);
		hire.setForeground(Color.ORANGE);
		hire.setBackground(Color.GRAY);
		hire.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		hire.setFont(new Font("Arial", Font.BOLD, 34));
		reject.setBounds(WIDTH/2 - 30, 0, 200, BUTTONS_SPACING);
		reject.setForeground(Color.ORANGE);
		reject.setBackground(Color.GRAY);
		reject.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		reject.setFont(new Font("Arial", Font.BOLD, 34));
		backToMenu.setBounds(WIDTH/2 - 130, 120, 200, BUTTONS_SPACING);
		backToMenu.setForeground(Color.ORANGE);
		backToMenu.setBackground(Color.GRAY);
		backToMenu.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		backToMenu.setFont(new Font("Arial", Font.BOLD, 34));
		
		hire.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		hireAction();
        		ManagerPage.display();
        		selectedIndex = -1;
        	}
        });
		
		reject.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rejectAction();
        		ManagerPage.display();
        		selectedIndex = -1;
        	}
        });
		
		backToMenu.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AllManagersPage.display();
        	}
        });
		
		pane.setBackground(Color.DARK_GRAY);
		pane.setLayout(null);
		pane.setBounds(0, 401, 800, 200);
		pane.add(hire);
		pane.add(reject);
		pane.add(backToMenu);
		
		frame.add(sPane);
		frame.add(pane);
	}
}
