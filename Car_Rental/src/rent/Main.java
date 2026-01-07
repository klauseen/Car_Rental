package rent;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import java.awt.Font;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 750);
		
		setLocationRelativeTo(null); // pentru ca sa fie centrat pe centru
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JToggleButton btnCarReg = new JToggleButton("Car Registration");
		btnCarReg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCarReg.setBounds(293, 58, 240, 79);
		contentPane.add(btnCarReg);
		
		JToggleButton btnCustomer = new JToggleButton("Customer");
		btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCustomer.setBounds(293, 178, 240, 67);
		contentPane.add(btnCustomer);
		
		JToggleButton btnReturn = new JToggleButton("Return");
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReturn.setBounds(293, 392, 240, 67);
		contentPane.add(btnReturn);
		
		JToggleButton btnRental = new JToggleButton("Rental");
		btnRental.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRental.setBounds(293, 283, 240, 67);
		contentPane.add(btnRental);
		
		JToggleButton btnLogout = new JToggleButton("Logout");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogout.setBounds(293, 500, 240, 72);
		contentPane.add(btnLogout);
	}
}
