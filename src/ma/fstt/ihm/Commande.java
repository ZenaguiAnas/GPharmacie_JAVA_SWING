package ma.fstt.ihm;

import java.awt.*;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;
import ma.fstt.model.Client;
import ma.fstt.model.Product;

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

public class Commande extends JFrame implements DBInfo, ActionListener  {
    JPanel panel;
    JLabel image;
    JLabel idLabel;
    JLabel dateLabel;
    JLabel qtePrdLabel;
    JLabel clientLabel;
    JLabel productLabel;
    JLabel searchLabel;
    JTextField idField;
    JTextField dateField;
    JTextField qtePrdField;
    JTextField clientField;
    JTextField productField;
    JTextField searchField;
    JDateChooser date_Field;
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

    public Commande() {
        this.createAndShowGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Commande();
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
        this.image = new JLabel();
        this.idLabel = new JLabel("ID");
        this.productLabel = new JLabel("Product Name");
        this.clientLabel = new JLabel("Client Name");
        this.qtePrdLabel = new JLabel("QTE");
        this.dateLabel = new JLabel("Date");
        this.searchLabel = new JLabel();
        this.idField = new JTextField();
        this.productField = new JTextField();
        this.clientField = new JTextField();
        this.qtePrdField = new JTextField();
        this.date_Field = new JDateChooser();
        this.searchField = new JTextField();
        this.updateImageButton = new JButton("Update Image");
        this.insertButton = new JButton("Add New", new ImageIcon(this.getClass().getResource("/images/insert.png")));
        this.updateButton = new JButton("Update", new ImageIcon(this.getClass().getResource("/images/update.png")));
        this.deleteButton = new JButton("Delete", new ImageIcon(this.getClass().getResource("/images/delete.png")));
        this.searchLabel = new JLabel("Search");
        this.backButton = new JButton("Back", new ImageIcon(this.getClass().getResource("/images/previous.png")));
        this.exitButton = new JButton("Exit", new ImageIcon(this.getClass().getResource("/images/exit.png")));
//        this.addProductDialog = new AddNewProductDialog(this, this.model);
        this.image.setBounds(80, 41, 270, 250);
        this.updateImageButton.setBounds(150, 300, 130, 34);
        this.idLabel.setBounds(20, 355, 50, 40);
        this.idField.setBounds(80, 355, 270, 40);
        this.productLabel.setBounds(20, 405, 50, 40);
        this.productField.setBounds(80, 405, 270, 40);
        this.clientLabel.setBounds(20, 455, 50, 40);
        this.clientField.setBounds(80, 455, 270, 40);
        this.qtePrdLabel.setBounds(20, 455, 50, 40);
        this.qtePrdField.setBounds(80, 455, 270, 40);
        this.dateLabel.setBounds(20, 505, 50, 40);
        this.deleteButton.setBounds(80, 575, 130, 40);
        this.updateButton.setBounds(220, 575, 130, 40);
        this.tableScroller.setBounds(377, 40, 520, 505);
        this.searchField.setBounds(530, 577, 255, 36);
        this.searchLabel.setBounds(460, 575, 115, 40);
        this.insertButton.setBounds(920, 40, 130, 60);
        this.backButton.setBounds(920, 350, 130, 40);
        this.exitButton.setBounds(920, 575, 130, 40);
        this.updateImageButton.setFont(new Font("Arial", 1, 14));
        this.idLabel.setFont(new Font("Arial", 1, 16));
        this.idField.setFont(new Font("Arial", 1, 15));
        this.productLabel.setFont(new Font("Arial", 1, 16));
        this.productField.setFont(new Font("Arial", 1, 15));
        this.clientLabel.setFont(new Font("Arial", 1, 16));
        this.clientField.setFont(new Font("Arial", 1, 15));
        this.qtePrdLabel.setFont(new Font("Arial", 1, 16));
        this.qtePrdField.setFont(new Font("Arial", 1, 15));
        this.dateLabel.setFont(new Font("Arial", 1, 16));
        this.deleteButton.setFont(new Font("Arial", 1, 16));
        this.updateButton.setFont(new Font("Arial", 1, 16));
        this.insertButton.setFont(new Font("Arial", 1, 16));
        this.searchField.setFont(new Font("Arial", 1, 15));
        this.searchLabel.setFont(new Font("Arial", 1, 17));
        this.backButton.setFont(new Font("Arial", 1, 16));
        this.exitButton.setFont(new Font("Arial", 1, 16));
        this.image.setBorder(BorderFactory.createLineBorder(Color.gray, 1, true));
        this.idField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        this.productField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        this.clientField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        this.qtePrdField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        this.searchField.setBorder(BorderFactory.createLineBorder(Color.gray, 2, true));
        this.idField.setEditable(false);
        this.idField.setBackground(new Color(240, 240, 240));
        this.table.setColumnSelectionAllowed(false);
        this.table.getParent().setBackground(Color.white);
        this.tableScroller.setViewportView(this.table);
        this.model.addColumn("ID");
        this.model.addColumn("Name");
        this.model.addColumn("Price ($)");
        this.model.addColumn("Date Of Add");

        try {
            this.viewCommandesInTheTable();
        } catch (Exception var5) {
            ;
        }

        this.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                int index = Commande.this.table.getSelectedRow();
                Commande.this.currentImagePath = null;
            }
        });
        this.table.addKeyListener(new KeyListener() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 38 || e.getKeyCode() == 40) {
                }

            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }
        });
        this.searchField.addKeyListener(new KeyListener() {
            public void keyReleased(KeyEvent e) {
                Commande.this.search();
            }

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
            }
        });
        this.updateImageButton.addActionListener(this);
        this.insertButton.addActionListener(this);
        this.updateButton.addActionListener(this);
        this.deleteButton.addActionListener(this);
        this.backButton.addActionListener(this);
        this.exitButton.addActionListener(this);
        this.panel.add(this.image);
        this.panel.add(this.updateImageButton);
        this.panel.add(this.idLabel);
        this.panel.add(this.idField);
        this.panel.add(this.productLabel);
        this.panel.add(this.productField);
        this.panel.add(this.clientLabel);
        this.panel.add(this.clientField);
        this.panel.add(this.qtePrdLabel);
        this.panel.add(this.qtePrdField);
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
        this.setTitle("Pharmacy Orders");
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

    private void viewCommandesInTheTable() {
        ArrayList<ma.fstt.model.Commande> cmdList = new ArrayList<>();
        Connection con = this.getConnection();
        String query = "SELECT * FROM commande";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                ma.fstt.model.Commande cmd = new ma.fstt.model.Commande(rs.getInt("id_commande"), rs.getString("date"), rs.getInt("qte"), rs.getInt("id_client"), rs.getInt("id_prod"));
                cmdList.add(cmd);
            }

            con.close();
        } catch (SQLException var8) {
            JOptionPane.showMessageDialog(this, var8.getMessage(), "Error", 0);
        }

        this.model.setRowCount(0);
        Object[] row = new Object[5];

        for(int i = 0; i < cmdList.size(); ++i) {
            row[0] = ((ma.fstt.model.Commande)cmdList.get(i)).getId_commande();
            row[1] = ((ma.fstt.model.Commande)cmdList.get(i)).getDate();
            row[2] = ((ma.fstt.model.Commande)cmdList.get(i)).getQtePrd();
            row[3] = ((ma.fstt.model.Commande)cmdList.get(i)).getProduct();
            row[4] = ((ma.fstt.model.Commande)cmdList.get(i)).getClient();
            this.model.addRow(row);
        }

    }

    private boolean checkInputs() {
        if (!this.clientField.getText().equals("") && !this.productField.getText().equals("") && this.dateField.getText() != null) {
            try {
                Integer.parseInt(this.qtePrdField.getText());
                return true;
            } catch (NumberFormatException var2) {
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Commande information are not updated because one or more fields are empty", "Error", 0);
            return false;
        }
    }

    private ImageIcon resizeImage(byte[] pic) {
        ImageIcon myImage;
        if (pic == null) {
            myImage = new ImageIcon(this.getClass().getResource("/images/no-image.jpg"));
        } else {
            myImage = new ImageIcon(pic);
        }

        Image tempImage = myImage.getImage().getScaledInstance(this.image.getWidth(), this.image.getHeight(), 4);
        return new ImageIcon(tempImage);
    }


    private void addNewProduct() {
        this.addProductDialog.show();
    }

    private void updateProduct() {
        if (this.checkInputs() && this.idField.getText() != null) {
            if (this.currentImagePath != null) {
                try {
                    InputStream img = new FileInputStream(new File(this.currentImagePath));
                    String query = "UPDATE commande SET qte = ?, date = ?, id_client = ?, id_prod = ? WHERE id = ?";
                    Connection con = this.getConnection();
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, this.qtePrdField.getText());
//                    ps.setString(2, this.dateField.getText());
                    ps.setString(3, this.clientField.getText());
                    ps.setString(4, this.productField.getText());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(this.dateField.getText());
                    ps.setString(2, addDate);
                    ps.executeUpdate();
                    con.close();
                    this.viewCommandesInTheTable();
                    JOptionPane.showMessageDialog(this, "Commande information has been successfully updated");
                } catch (FileNotFoundException | NumberFormatException | SQLException | HeadlessException var8) {
                    JOptionPane.showMessageDialog(this, var8.getMessage(), "Error", 0);
                }
            } else {
                try {
                    String query = "UPDATE commande SET qte = ?, date = ?, id_client = ?, id_prod = ? WHERE id = ?";
                    Connection con = this.getConnection();
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, this.qtePrdField.getText());
//                    ps.setString(2, this.dateField.getText());
                    ps.setString(3, this.clientField.getText());
                    ps.setString(4, this.productField.getText());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String addDate = dateFormat.format(this.dateField.getText());
                    ps.setString(2, addDate);
                    ps.executeUpdate();
                    con.close();
                    this.viewCommandesInTheTable();
                    JOptionPane.showMessageDialog(this, "Commande information are successfuly updated");
                } catch (NumberFormatException | SQLException | HeadlessException var7) {
                    JOptionPane.showMessageDialog(this, var7.getMessage(), "Error", 0);
                }
            }
        }

    }

    private ArrayList<ma.fstt.model.Commande> getCommandeList() {
        ArrayList<ma.fstt.model.Commande> cmdList = new ArrayList();
        Connection con = this.getConnection();
        String query = "SELECT * FROM commande";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()) {
                ma.fstt.model.Commande cmd = new ma.fstt.model.Commande(rs.getInt("id_commande"), rs.getString("date"), rs.getInt("qte"), rs.getInt("id_client"), rs.getInt("id_prod"));
                cmdList.add(cmd);
            }

            con.close();
        } catch (SQLException var7) {
            JOptionPane.showMessageDialog(this, var7.getMessage(), "Error", 0);
        }

        return cmdList;
    }


    private void deleteProduct() {
        if (this.table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please select the Commande that you want to delete from the table and try again");
        } else {
            try {
                Connection con = this.getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM commande WHERE id = ?");
                int id = Integer.parseInt(this.idField.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                con.close();
                int nextSelectedRowIndex = this.table.getSelectedRow();
                this.viewCommandesInTheTable();
                if (this.table.getRowCount() == 1) {
                    this.table.setRowSelectionInterval(0, 0);
                } else if (this.table.getRowCount() > 1 && nextSelectedRowIndex < this.table.getRowCount()) {
                    this.table.setRowSelectionInterval(nextSelectedRowIndex, nextSelectedRowIndex);
                } else if (this.table.getRowCount() > 1 && nextSelectedRowIndex == this.table.getRowCount()) {
                    --nextSelectedRowIndex;
                    this.table.setRowSelectionInterval(nextSelectedRowIndex, nextSelectedRowIndex);
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
        if (e.getSource() == this.insertButton) {
            this.addNewProduct();
        } else if (e.getSource() == this.updateButton) {
            this.updateProduct();
        } else if (e.getSource() == this.deleteButton) {
            this.deleteProduct();
        } else if (e.getSource() == this.backButton) {
            this.showPreviousPage();
        } else if (e.getSource() == this.exitButton) {
            System.exit(0);
        }

    }
}
