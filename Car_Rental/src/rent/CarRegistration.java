package rent;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class CarRegistration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRegNo;
	private JTextField txtMake;
	private JTextField txtType;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table_2;
	private JTextField textField;
	

	
	Connection con; //se foloseste pentru baza de date sql
	PreparedStatement pst;
	String url = "jdbc:mysql://127.0.0.1:3306/nikrent_schema" ;
	String user = "root";
	String password = "nikolaos411405518";
	private JTextField txtReg;
	/**
	 * Launch the application.
	 */
	
	public void autoID() {
	    String sql = "SELECT MAX(Car_no) AS maxCar FROM carregistration"; // numele tabelului și coloanei să fie exact ca în MySQL

	    try (Connection con = DriverManager.getConnection(url, user, password);
	         PreparedStatement pst = con.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {

	        if (rs.next()) {
	            String maxCar = rs.getString("maxCar"); // will return null if it is empty

	            if (maxCar == null) {
	                
	                txtReg.setText("C001");
	            } else {
	                // Extract numeric part of C
	                long id = Long.parseLong(maxCar.substring(1));
	                id++; 
	                txtReg.setText("C" + String.format("%03d", id)); // 3 digit format
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarRegistration frame = new CarRegistration();
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
	public CarRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1279, 707);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Car Registration", TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, SystemColor.desktop));
		panel.setBounds(10, 10, 1245, 623);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblMake = new JLabel("Make");
		lblMake.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMake.setBounds(88, 183, 96, 35);
		panel.add(lblMake);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblModel.setBounds(88, 228, 96, 35);
		panel.add(lblModel);
		
		JLabel lblColour = new JLabel("Colour");
		lblColour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblColour.setBounds(88, 273, 96, 35);
		panel.add(lblColour);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblType.setBounds(88, 318, 96, 35);
		panel.add(lblType);
		
		JLabel lblPrice = new JLabel("Price / day");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrice.setBounds(88, 363, 96, 35);
		panel.add(lblPrice);
		
		txtRegNo = new JTextField();
		txtRegNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRegNo.setBounds(236, 191, 96, 19);
		panel.add(txtRegNo);
		txtRegNo.setColumns(10);
		
		txtMake = new JTextField();
		txtMake.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMake.setBounds(236, 236, 96, 19);
		panel.add(txtMake);
		txtMake.setColumns(10);
		
		txtType = new JTextField();
		txtType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtType.setBounds(236, 281, 96, 19);
		panel.add(txtType);
		txtType.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setBounds(236, 326, 96, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setBounds(236, 371, 96, 19);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setBounds(88, 478, 104, 35);
		panel.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEdit.setBounds(236, 478, 96, 35);
		panel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(88, 536, 104, 35);
		panel.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.setBounds(236, 539, 96, 32);
		panel.add(btnCancel);
		
		table_2 = new JTable();
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_2.setRowSelectionAllowed(false);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"Photo", "Car Registration No", "Make", "Model", "Colour", "Type of Car", "Price per day", "Available"},
			},
			new String[] {
				"Title", "Type", "Resizable", "Editable", "New column", "New column", "New column", "New column"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(0).setMinWidth(75);
		table_2.setColumnSelectionAllowed(true);
		table_2.setCellSelectionEnabled(true);
		table_2.setBounds(395, 99, 742, 472);
		panel.add(table_2);
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setBounds(236, 407, 96, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAvailable.setBounds(88, 408, 96, 16);
		panel.add(lblAvailable);
		
		JLabel lblRegNo = new JLabel("Registration No");
		lblRegNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRegNo.setBounds(88, 154, 104, 19);
		panel.add(lblRegNo);
		
		txtReg = new JTextField();
		txtReg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtReg.setBounds(236, 155, 96, 19);
		panel.add(txtReg);
		txtReg.setColumns(10);
		
		autoID();
		
	}
}
