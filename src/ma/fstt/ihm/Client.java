package ma.fstt.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import ma.fstt.model.ClientTransaction;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import java.awt.*;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class Client extends JFrame implements DBInfo, ActionListener {

	JPanel panel;
	JLabel idLabel;
	JLabel nameLabel;
	JLabel emailLabel;
	JLabel genreLabel;
	JLabel dateLabel;
	JLabel searchLabel;
	JTextField idField;
	JTextField nameField;
	JTextField emailField;
	JTextField genreField;
	JTextField searchField;
	JDateChooser dateField;
	JButton updateImageButton;
	JButton insertButton;
	JButton updateButton;
	JButton deleteButton;
	JButton exitButton;
	JButton backButton;
	JTable table;
	JScrollPane tableScroller;
	DefaultTableModel model;
	String currentImagePath = null;
	AddNewProductDialog addProductDialog;

	public Client() {
		this.createAndShowGUI();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Client();
			}
		});
	}

	private void createAndShowGUI() {
		try {
			UIManager.LookAndFeelInfo[] var1 = UIManager.getInstalledLookAndFeels();
			int var2 = var1.length;

			for(int var3 = 0; var3 < var2; ++var3) {
				UIManager.LookAndFeelInfo info = var1[var3];
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException var6) {
			;
		}

		this.panel = new JPanel((LayoutManager)null);
		this.model = new DefaultTableModel();
		this.table = new JTable(this.model);
		this.tableScroller = new JScrollPane(this.table);
		this.idLabel = new JLabel("ID");
		this.nameLabel = new JLabel("Name");
		this.emailLabel = new JLabel("Email");
		this.genreLabel = new JLabel("Genre");
		this.dateLabel = new JLabel("Date");
		this.searchLabel = new JLabel();
		this.idField = new JTextField();
		this.nameField = new JTextField();
		this.emailField = new JTextField();
		this.genreField = new JTextField();
		this.dateField = new JDateChooser();
		this.searchField = new JTextField();
		this.insertButton = new JButton("Add New", new ImageIcon(this.getClass().getResource("/images/insert.png")));
		this.updateButton = new JButton("Update", new ImageIcon(this.getClass().getResource("/images/update.png")));
		this.deleteButton = new JButton("Delete", new ImageIcon(this.getClass().getResource("/images/delete.png")));
		this.searchLabel = new JLabel("Search");
		this.backButton = new JButton("Back", new ImageIcon(this.getClass().getResource("/images/previous.png")));
		this.exitButton = new JButton("Exit", new ImageIcon(this.getClass().getResource("/images/exit.png")));
		this.addProductDialog = new AddNewProductDialog(this, this.model);
		this.idLabel.setBounds(20, 200, 50, 40);
		this.idField.setBounds(80, 200, 270, 40);
		this.nameLabel.setBounds(20, 300, 50, 40);
		this.nameField.setBounds(80, 300, 270, 40);
		this.emailLabel.setBounds(20, 400, 50, 40);
		this.emailField.setBounds(80, 400, 270, 40);
		this.genreLabel.setBounds(20, 500, 50, 40);
		this.genreField.setBounds(80, 500, 270, 40);
		this.deleteButton.setBounds(80, 575, 130, 40);
		this.updateButton.setBounds(220, 575, 130, 40);
		this.tableScroller.setBounds(377, 40, 520, 505);
		this.searchField.setBounds(530, 577, 255, 36);
		this.searchLabel.setBounds(460, 575, 115, 40);
		this.insertButton.setBounds(920, 40, 130, 60);
		this.backButton.setBounds(920, 350, 130, 40);
		this.exitButton.setBounds(920, 575, 130, 40);
		this.idLabel.setFont(new Font("Arial", 1, 16));
		this.idField.setFont(new Font("Arial", 1, 15));
		this.nameLabel.setFont(new Font("Arial", 1, 16));
		this.nameField.setFont(new Font("Arial", 1, 15));
		this.emailLabel.setFont(new Font("Arial", 1, 16));
		this.emailField.setFont(new Font("Arial", 1, 15));
		this.genreLabel.setFont(new Font("Arial", 1, 16));
		this.genreField.setFont(new Font("Arial", 1, 15));
		this.dateLabel.setFont(new Font("Arial", 1, 16));
		this.dateField.setFont(new Font("Arial", 1, 13));
		this.deleteButton.setFont(new Font("Arial", 1, 16));
		this.updateButton.setFont(new Font("Arial", 1, 16));
		this.insertButton.setFont(new Font("Arial", 1, 16));
		this.searchField.setFont(new Font("Arial", 1, 15));
		this.searchLabel.setFont(new Font("Arial", 1, 17));
		this.backButton.setFont(new Font("Arial", 1, 16));
		this.exitButton.setFont(new Font("Arial", 1, 16));
		this.idField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
		this.nameField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
		this.emailField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
		this.genreField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
		this.searchField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
		this.idField.setEditable(false);
		this.idField.setBackground(new Color(240, 240, 240));
		this.table.setColumnSelectionAllowed(false);
		this.table.getParent().setBackground(Color.white);
		this.tableScroller.setViewportView(this.table);
		this.model.addColumn("ID");
		this.model.addColumn("Name");
		this.model.addColumn("Email");
		this.model.addColumn("Genre");

		try {
			this.viewProductsInTheTable();
		} catch (Exception var5) {
			;
		}

		this.table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				int index = Client.this.table.getSelectedRow();
				Client.this.showClient(index);
				Client.this.currentImagePath = null;
			}
		});
		this.table.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 38 || e.getKeyCode() == 40) {
					Client.this.showClient(Client.this.table.getSelectedRow());
				}

			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});
		this.searchField.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				Client.this.search();
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});

		this.insertButton.addActionListener(this);
		this.updateButton.addActionListener(this);
		this.deleteButton.addActionListener(this);
		this.backButton.addActionListener(this);
		this.exitButton.addActionListener(this);
		this.panel.add(this.idLabel);
		this.panel.add(this.idField);
		this.panel.add(this.idField);
		this.panel.add(this.nameLabel);
		this.panel.add(this.nameField);
		this.panel.add(this.emailLabel);
		this.panel.add(this.emailField);
		this.panel.add(this.genreLabel);
		this.panel.add(this.genreField);
		this.panel.add(this.dateLabel);
		this.panel.add(this.dateField);
		this.panel.add(this.insertButton);
		this.panel.add(this.updateButton);
		this.panel.add(this.deleteButton);
		this.panel.add(this.tableScroller);
		this.panel.add(this.searchField);
		this.panel.add(this.searchLabel);
		this.panel.add(this.backButton);
		this.panel.add(this.exitButton);
		this.panel.setPreferredSize(new Dimension(1070, 640));
		this.panel.setMinimumSize(new Dimension(1070, 640));
		this.setContentPane(new JPanel(new GridBagLayout()));
		this.add(this.panel);
		this.setTitle("Pharmacy Products");
		this.setDefaultCloseOperation(3);
		this.pack();
		this.setLocationRelativeTo((Component)null);
		this.setVisible(true);
	}

	private Connection getConnection() {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gpharmacie?useUnicode=yes&characterEncoding=UTF-8", "root", "");
			return con;
		} catch (SQLException var3) {
			JOptionPane.showMessageDialog(this, var3.getMessage(), "Connection Error", 0);
			return null;
		}
	}

	private void viewProductsInTheTable() {
		ArrayList<ma.fstt.model.Client> clients = new ArrayList<>();
		Connection con = this.getConnection();
		String query = "SELECT * FROM client";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				ma.fstt.model.Client client = new ma.fstt.model.Client(rs.getInt("id_client"), rs.getString("nom"), rs.getString("email"), rs.getString("genre"), "");
				clients.add(client);
			}

			con.close();
		} catch (SQLException var8) {
			JOptionPane.showMessageDialog(this, var8.getMessage(), "Error", 0);
		}

		this.model.setRowCount(0);
		Object[] row = new Object[4];

		for(int i = 0; i < clients.size(); ++i) {
			row[0] = (clients.get(i)).getId_client();
			row[1] = (clients.get(i)).getNom();
			row[2] = (clients.get(i)).getEmail();
			row[3] = (clients.get(i)).getGenre();
			this.model.addRow(row);
		}

	}

	private boolean checkInputs() {
		if (!this.nameField.getText().equals("") && !this.emailField.getText().equals("") && !this.genreField.getText().equals("")) {
			try {
				this.emailField.getText();
				return true;
			} catch (NumberFormatException var2) {
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(this, "Product information are not updated because one or more fields are empty", "Error", 0);
			return false;
		}
	}



	private void addNewProduct() {
		this.addProductDialog.show();
	}

	private void updateClient() {
		if (this.checkInputs() && this.idField.getText() != null) {
			if (this.currentImagePath != null) {
				try {
					InputStream img = new FileInputStream(new File(this.currentImagePath));
					String query = "UPDATE client SET nom = ?, email = ?, genre = ? WHERE id_client = ?";
					Connection con = this.getConnection();
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, this.nameField.getText());
					ps.setString(2, this.emailField.getText());
					ps.setString(3, this.genreField.getText());
					ps.setInt(4, Integer.parseInt(this.idField.getText()));
					ps.executeUpdate();
					con.close();
					this.viewProductsInTheTable();
					JOptionPane.showMessageDialog(this, "Product information has been successfully updated");
				} catch (FileNotFoundException | NumberFormatException | SQLException | HeadlessException var8) {
					JOptionPane.showMessageDialog(this, var8.getMessage(), "Error", 0);
				}
			} else {
				try {
					String query = "UPDATE client SET nom = ?, email = ?, genre = ? WHERE id_client = ?";
					Connection con = this.getConnection();
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, this.nameField.getText());
					ps.setString(2, this.emailField.getText());
					ps.setString(3, this.genreField.getText());
					ps.setInt(4, Integer.parseInt(this.idField.getText()));
					ps.executeUpdate();
					con.close();
					this.viewProductsInTheTable();
					JOptionPane.showMessageDialog(this, "Product information are successfuly updated");
				} catch (NumberFormatException | SQLException | HeadlessException var7) {
					JOptionPane.showMessageDialog(this, var7.getMessage(), "Error", 0);
				}
			}
		}

	}

	private ArrayList<ma.fstt.model.Client> getClientList() {
		ArrayList<ma.fstt.model.Client> clients = new ArrayList<>();
		Connection con = this.getConnection();
		String query = "SELECT * FROM client";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while(rs.next()) {
				ma.fstt.model.Client client = new ma.fstt.model.Client(rs.getInt("id_client"), rs.getString("nom"), rs.getString("email"), rs.getString("genre"), "");
				clients.add(client);
			}

			con.close();
		} catch (SQLException var7) {
			JOptionPane.showMessageDialog(this, var7.getMessage(), "Error", 0);
		}

		return clients;
	}

	private void showClient(int index) {
		this.idField.setText(Integer.toString((this.getClientList().get(index)).getId_client()));
		this.nameField.setText((this.getClientList().get(index)).getNom());
		this.emailField.setText((this.getClientList().get(index)).getEmail());
		this.genreField.setText((this.getClientList().get(index)).getGenre());

	}

	private void deleteClient() {
		if (this.table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Please select the product that you want to delete from the table and try again");
		} else {
			try {
				Connection con = this.getConnection();
				PreparedStatement ps = con.prepareStatement("DELETE FROM client WHERE id = ?");
				int id = Integer.parseInt(this.idField.getText());
				ps.setInt(1, id);
				ps.executeUpdate();
				con.close();
				int nextSelectedRowIndex = this.table.getSelectedRow();
				this.viewProductsInTheTable();
				if (this.table.getRowCount() == 1) {
					this.table.setRowSelectionInterval(0, 0);
					this.showClient(0);
				} else if (this.table.getRowCount() > 1 && nextSelectedRowIndex < this.table.getRowCount()) {
					this.table.setRowSelectionInterval(nextSelectedRowIndex, nextSelectedRowIndex);
					this.showClient(nextSelectedRowIndex);
				} else if (this.table.getRowCount() > 1 && nextSelectedRowIndex == this.table.getRowCount()) {
					--nextSelectedRowIndex;
					this.table.setRowSelectionInterval(nextSelectedRowIndex, nextSelectedRowIndex);
					this.showClient(nextSelectedRowIndex);
				}

				if (this.table.getRowCount() == 0) {
					this.idField.setText("");
					this.nameField.setText("");
					this.emailField.setText("");
					this.genreField.setText("");
				}
			} catch (SQLException var5) {
				JOptionPane.showMessageDialog(this, var5.getMessage(), "Error", 0);
			}
		}

	}


	private void showPreviousPage() {
//        if (this.table.getSelectedRow() > 0) {
//            int currentSelectedRow = this.table.getSelectedRow() - 1;
//            this.table.setRowSelectionInterval(currentSelectedRow, currentSelectedRow);
//            this.showProduct(currentSelectedRow);
//        }

	}

	private void search() {
		String keyword = this.searchField.getText();
		TableRowSorter<DefaultTableModel> tr = new TableRowSorter(this.model);
		this.table.setRowSorter(tr);
		tr.setRowFilter(RowFilter.regexFilter(keyword, new int[0]));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.updateImageButton) {
		} if (e.getSource() == this.insertButton) {
			this.addNewProduct();
		} else if (e.getSource() == this.updateButton) {
			this.updateClient();
		} else if (e.getSource() == this.deleteButton) {
			this.deleteClient();
		} else if (e.getSource() == this.backButton) {
			this.showPreviousPage();
		} else if (e.getSource() == this.exitButton) {
			System.exit(0);
		}

	}

}
