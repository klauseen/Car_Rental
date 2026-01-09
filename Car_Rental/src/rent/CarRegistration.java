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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class CarRegistration extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMake;
	private JTextField txtModel;
	private JTextField txtColour;
	private JTextField txtType;
	private JTextField txtPricePerDay;
	private JTable table_2;
	String imagePath = null;
	

	
	Connection con; //se foloseste pentru baza de date sql
	PreparedStatement pst;
	String url = "jdbc:mysql://127.0.0.1:3306/nikrent_schema" ;
	String user = "root";
	String password = "nikolaos411405518";
	String sql = "SELECT MAX(Car_no) AS maxCar FROM carregistration";// numele tabelului și coloanei să fie exact ca în MySQL
	private JTextField txtReg;
	/**
	 * Launch the application.
	 */
	
	private JComboBox<String> cBAvailable;
	
	private void loadCars() {

	    DefaultTableModel model = (DefaultTableModel) table_2.getModel();
	    model.setRowCount(0); // curăță tabelul

	    String sql = "SELECT * FROM carregistration";

	    try (Connection con = DriverManager.getConnection(url, user, password);
	         PreparedStatement pst = con.prepareStatement(sql);
	         ResultSet rs = pst.executeQuery()) {

	        while (rs.next()) {

	            String reg = rs.getString("Car_no");
	            String make = rs.getString("Make");
	            String modelCar = rs.getString("Model");
	            String colour = rs.getString("Colour");
	            String type = rs.getString("Type");
	            String price = rs.getString("PricePerDay");
	            String available = rs.getString("Available");
	            String imgPath = rs.getString("Image");

	            ImageIcon icon = null;
	            if (imgPath != null && !imgPath.isEmpty()) {
	                icon = new ImageIcon(imgPath);
	            }

	            model.addRow(new Object[]{
	                icon, reg, make, modelCar, colour, type, price, available
	            });
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void autoID() {
	    

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
		
		
		
		
		JLabel lblRegNo = new JLabel("Registration No");
		lblRegNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRegNo.setBounds(88, 154, 104, 19);
		panel.add(lblRegNo);
		
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
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAvailable.setBounds(88, 408, 96, 16);
		panel.add(lblAvailable);
		
		txtReg = new JTextField();
		txtReg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtReg.setBounds(236, 155, 96, 19);
		panel.add(txtReg);
		txtReg.setColumns(10);
		
		txtMake = new JTextField();
		txtMake.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMake.setBounds(236, 191, 96, 19);
		panel.add(txtMake);
		txtMake.setColumns(10);
		
		txtModel = new JTextField();
		txtModel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtModel.setBounds(236, 236, 96, 19);
		panel.add(txtModel);
		txtModel.setColumns(10);
		
		txtColour = new JTextField();
		txtColour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtColour.setBounds(236, 281, 96, 19);
		panel.add(txtColour);
		txtColour.setColumns(10);
		
		txtType = new JTextField();
		txtType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtType.setBounds(236, 326, 96, 19);
		panel.add(txtType);
		txtType.setColumns(10);
		
		txtPricePerDay = new JTextField();
		txtPricePerDay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtPricePerDay.setBounds(236, 371, 96, 19);
		panel.add(txtPricePerDay);
		txtPricePerDay.setColumns(10);
		

		JButton btnUpload = new JButton("Upload Image");
		btnUpload.setBounds(88, 440, 150, 30);
		panel.add(btnUpload);

		btnUpload.addActionListener(e -> {
		    JFileChooser chooser = new JFileChooser();
		    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		    int result = chooser.showOpenDialog(null);

		    if (result == JFileChooser.APPROVE_OPTION) {
		        File selectedFile = chooser.getSelectedFile();

		        // Creează folderul images dacă nu există
		        File imagesDir = new File("images");
		        if (!imagesDir.exists()) {
		            imagesDir.mkdirs();
		        }

		        // Salvează calea imaginii în proiect
		        imagePath = "images/" + selectedFile.getName();

		        try {
		            Files.copy(
		                selectedFile.toPath(),
		                Paths.get(imagePath),
		                StandardCopyOption.REPLACE_EXISTING
		            );
		            System.out.println("Imaginea a fost încărcată: " + imagePath);
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		 table_2 = new JTable();
		    DefaultTableModel model = new DefaultTableModel(
		        new Object[]{"Photo", "Reg No", "Make", "Model", "Colour", "Type", "Price / day", "Available"}, 0
		    ) {
		        @Override
		        public Class<?> getColumnClass(int column) {
		            if (column == 0) return ImageIcon.class;
		            return String.class;
		        }

		        @Override
		        public boolean isCellEditable(int row, int column) {
		            return false;
		        }
		    };
		    table_2.setModel(model);
		    table_2.setRowHeight(80);
		    table_2.getColumnModel().getColumn(0).setPreferredWidth(80);
		    table_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    
		    
		    table_2.addMouseListener(new java.awt.event.MouseAdapter() {
		        public void mouseClicked(java.awt.event.MouseEvent evt) {
		            int row = table_2.rowAtPoint(evt.getPoint());
		            int col = table_2.columnAtPoint(evt.getPoint());
		            if (col == 0) { // click pe poza
		                ImageIcon icon = (ImageIcon) table_2.getValueAt(row, col);
		                JFrame f = new JFrame();
		                f.setSize(400, 400);
		                JLabel lbl = new JLabel(icon);
		                f.add(lbl);
		                f.setVisible(true);
		            }
		        }
		    });

		    JScrollPane scrollPane = new JScrollPane(table_2);
		    scrollPane.setBounds(395, 99, 742, 472);
		    panel.add(scrollPane);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String regno = txtReg.getText();
				String make = txtMake.getText();
				String carModel = txtModel.getText();
				String colour = txtColour.getText();
				String type = txtType.getText();
				String pricePerDay = txtPricePerDay.getText();
				String available = cBAvailable.getSelectedItem().toString();
				
				
				String insertSQL = "INSERT INTO carregistration(Car_no, Make, Model, Colour, Type, PricePerDay, Available, Image) "
		                 + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				
				

				
				Connection con;
				try {
					con = DriverManager.getConnection(url, user, password);
					pst = con.prepareStatement(insertSQL);
					
					pst.setString(1, regno);
				    pst.setString(2, make);
				    pst.setString(3, carModel);
				    pst.setString(4, colour);
				    pst.setString(5, type);
				    pst.setString(6, pricePerDay);
				    pst.setString(7, available);
				    pst.setString(8, imagePath);
				    
				    pst.executeUpdate();
				    
				    ImageIcon icon = new ImageIcon(imagePath);
				    model.addRow(new Object[]{icon, regno, make, carModel, colour, type, pricePerDay, available});
				    
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	         
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
		
		cBAvailable = new JComboBox<>();
		cBAvailable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cBAvailable.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
		cBAvailable.setBounds(236, 407, 96, 21);
		panel.add(cBAvailable);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblMake, lblModel, lblColour, lblType, lblPrice, txtMake, txtModel, txtColour, txtType, txtPricePerDay, btnAdd, btnEdit, btnDelete, btnCancel, table_2, lblAvailable, lblRegNo, txtReg, cBAvailable}));
		
		
		
		autoID();
		loadCars();
		
	}
}
