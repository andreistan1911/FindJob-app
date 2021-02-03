package tema;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.text.DecimalFormat;

public class DepartmentPage extends Page{
	//Pagina unui departament in care afisez detalii despre salariu total si job-urile
	//	in cadrul departamentului.
	static final int MAX_JOBS_DEPARTMENT = 10;
	
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	static Department department = null;
	static JLabel salaryLabel = new JLabel();
	static JLabel jobsLabel = new JLabel("Jobs:");
	static JLabel[] jobLabel = new JLabel[MAX_JOBS_DEPARTMENT];
    static JButton backToCompany = new JButton("Back");
	
	public static void display() {
		resetFrame();
		frame.setTitle(CompanyPage.company.name + " " + department.getClass().toString().split(" |\\.")[2] + " Page");

		salaryLabel.setBounds(MARGIN, MARGIN, 500, 34);
		jobsLabel.setBounds(MARGIN, SPACING + MARGIN , 100, 34);
		backToCompany.setBounds(WIDTH/2 - 130, HEIGHT - BOTTOM_MARGIN, 200, BUTTONS_SPACING);
		
		salaryLabel.setFont(new Font("Arial", Font.BOLD, 34));
		jobsLabel.setFont(new Font("Arial", Font.BOLD, 34));
		backToCompany.setFont(new Font("Arial", Font.BOLD, 34));


		salaryLabel.setForeground(Color.ORANGE);
		jobsLabel.setForeground(Color.ORANGE);
		backToCompany.setForeground(Color.ORANGE);
		backToCompany.setBackground(Color.GRAY);
		backToCompany.setBorder(BorderFactory.createBevelBorder(1, Color.BLACK, Color.GRAY));
		
		backToCompany.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CompanyPage.display();
        	}
        });
		
		int i;
		
		for(i = 0; i < MAX_JOBS_DEPARTMENT; i++) {
			jobLabel[i] = new JLabel();
			jobLabel[i].setBounds(SPACING + MARGIN, SPACING * i + 80, 300, 34);
			jobLabel[i].setFont(new Font("Arial", Font.PLAIN, SPACING));
			frame.add(jobLabel[i]); 
		}
		
		salaryLabel.setText("Total salary: " + df2.format(department.getTotalSalaryBudget()) + " RON");
		ArrayList<Job> jobs = department.getAllJobs();
		for(int j = 0; j < MAX_JOBS_DEPARTMENT; j++) {
			jobLabel[j].setText("");
			jobLabel[j].setForeground(Color.ORANGE);
			if(j < jobs.size())
				jobLabel[j].setText(jobs.get(j).name);
		}
		
		frame.add(salaryLabel);
		frame.add(jobsLabel);
		frame.add(backToCompany);
	}

}
