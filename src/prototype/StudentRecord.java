package prototype;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class StudentRecord extends JFrame implements ActionListener {
    private JTextField txtID, txtln, txtfn, txtmn, txtcse, txtsy, txtadd, txtcn, txtbday, txtpos, txtorg, txtSearch;
    private JButton btnSearch, btnClear, btnLogout, btnBack;
    private Connection conn;

    public StudentRecord() {
        setTitle("Student Records");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cite", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 875, 490);
        panel.setBackground(new Color(139, 0, 0));
        panel.setLayout(null);
        add(panel);

        Color white = new Color(255, 255, 255);
        Color red = new Color(139, 0, 0);
        Color darkRed = new Color(128, 0, 32);
        Color gold = new Color(255, 215, 0);

          JLabel lblTitle = new JLabel("Student Records");
        lblTitle.setBounds(300, 10, 300, 40);
        lblTitle.setFont(new Font("Calibri", Font.BOLD, 30));
        lblTitle.setForeground(white);  // Change to white for better visibility
        lblTitle.setVisible(true);
        panel.add(lblTitle);
       
        JLabel lblSearch = new JLabel("Student No:");
        lblSearch.setBounds(50, 60, 160, 30);
        lblSearch.setFont(new Font("Calibri", Font.BOLD, 20));
        lblSearch.setForeground(red);
        panel.add(lblSearch);

        txtSearch = new JTextField("input stud id here:");
        txtSearch.setBounds(220, 60, 183, 30);
        txtSearch.setFont(new Font("Arial", Font.PLAIN, 18));
        txtSearch.setBackground(new Color(42, 42, 42));
        txtSearch.setForeground(white);
        panel.add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(420, 60, 100, 30);
        btnSearch.setForeground(darkRed);
        btnSearch.setBackground(gold);
        btnSearch.addActionListener(this);
        panel.add(btnSearch);

        btnClear = new JButton("Clear");
        btnClear.setBounds(530, 60, 100, 30);
        btnClear.setForeground(darkRed);
        btnClear.setBackground(gold);
        btnClear.addActionListener(this);
        panel.add(btnClear);

        createFormFields(panel, white, gold);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(700, 500, 100, 35);
        btnLogout.setBackground(gold);
        btnLogout.setForeground(darkRed);
        btnLogout.setFont(new Font("Arial", Font.BOLD, 15));
        btnLogout.addActionListener(this);
        panel.add(btnLogout);

        btnBack = new JButton("Back");
        btnBack.setBounds(100, 500, 100, 35);
        btnBack.setBackground(gold);
        btnBack.setForeground(darkRed);
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        btnBack.addActionListener(this);
        panel.add(btnBack);
    }

    private void createFormFields(JPanel panel, Color white, Color gold) {
        JLabel lblID = new JLabel("Student ID: ");
        lblID.setBounds(50, 110, 160, 50);
        lblID.setFont(new Font("Calibri", Font.BOLD, 20));
        lblID.setForeground(gold);
        panel.add(lblID);

        txtID = new JTextField("");
        txtID.setBounds(220, 120, 183, 30);
        txtID.setFont(new Font("Arial", Font.PLAIN, 18));
        txtID.setBackground(new Color(42, 42, 42));
        txtID.setForeground(white);
        txtID.setEditable(false);
        panel.add(txtID);

        JLabel lblln = new JLabel("Last Name: ");
        lblln.setBounds(50, 160, 160, 50);
        lblln.setFont(new Font("Calibri", Font.BOLD, 20));
        lblln.setForeground(gold);
        panel.add(lblln);

        txtln = new JTextField();
        txtln.setBounds(220, 170, 183, 30);
        txtln.setFont(new Font("Arial", Font.PLAIN, 18));
        txtln.setBackground(new Color(42, 42, 42));
        txtln.setForeground(white);
        txtln.setEditable(false);
        panel.add(txtln);

        JLabel lblfn = new JLabel("First Name: ");
        lblfn.setBounds(50, 210, 160, 50);
        lblfn.setFont(new Font("Calibri", Font.BOLD, 20));
        lblfn.setForeground(gold);
        panel.add(lblfn);

        txtfn = new JTextField();
        txtfn.setBounds(220, 220, 183, 30);
        txtfn.setFont(new Font("Arial", Font.PLAIN, 18));
        txtfn.setBackground(new Color(42, 42, 42));
        txtfn.setForeground(white);
        txtfn.setEditable(false);
        panel.add(txtfn);

        JLabel lblmn = new JLabel("Middle Name: ");
        lblmn.setBounds(50, 260, 160, 50);
        lblmn.setFont(new Font("Calibri", Font.BOLD, 20));
        lblmn.setForeground(gold);
        panel.add(lblmn);

        txtmn = new JTextField();
        txtmn.setBounds(220, 270, 183, 30);
        txtmn.setFont(new Font("Arial", Font.PLAIN, 18));
        txtmn.setBackground(new Color(42, 42, 42));
        txtmn.setForeground(white);
        txtmn.setEditable(false);
        panel.add(txtmn);

        JLabel lblcse = new JLabel("Course:");
        lblcse.setBounds(50, 310, 160, 50);
        lblcse.setFont(new Font("Calibri", Font.BOLD, 20));
        lblcse.setForeground(gold);
        panel.add(lblcse);

        txtcse = new JTextField();
        txtcse.setBounds(220, 320, 183, 30);
        txtcse.setFont(new Font("Arial", Font.PLAIN, 18));
        txtcse.setBackground(new Color(42, 42, 42));
        txtcse.setForeground(white);
        txtcse.setEditable(false);
        panel.add(txtcse);

        JLabel lblsy = new JLabel("Student Year: ");
        lblsy.setBounds(50, 360, 160, 50);
        lblsy.setFont(new Font("Calibri", Font.BOLD, 20));
        lblsy.setForeground(gold);
        panel.add(lblsy);

        txtsy = new JTextField();
        txtsy.setBounds(220, 370, 183, 30);
        txtsy.setFont(new Font("Arial", Font.PLAIN, 18));
        txtsy.setBackground(new Color(42, 42, 42));
        txtsy.setForeground(white);
        txtsy.setEditable(false);
        panel.add(txtsy);

        JLabel lbladd = new JLabel("Address: ");
        lbladd.setBounds(50, 410, 160, 50);
        lbladd.setFont(new Font("Calibri", Font.BOLD, 20));
        lbladd.setForeground(gold);
        panel.add(lbladd);

        txtadd = new JTextField();
        txtadd.setBounds(220, 420, 183, 30);
        txtadd.setFont(new Font("Arial", Font.PLAIN, 18));
        txtadd.setBackground(new Color(42, 42, 42));
        txtadd.setForeground(white);
        txtadd.setEditable(false);
        panel.add(txtadd);

        JLabel lblcn = new JLabel("Contact Number: ");
        lblcn.setBounds(450, 110, 160, 50);
        lblcn.setFont(new Font("Calibri", Font.BOLD, 20));
        lblcn.setForeground(gold);
        panel.add(lblcn);

        txtcn = new JTextField();
        txtcn.setBounds(620, 120, 183, 30);
        txtcn.setFont(new Font("Arial", Font.PLAIN, 18));
        txtcn.setBackground(new Color(42, 42, 42));
        txtcn.setForeground(white);
        txtcn.setEditable(false);
        panel.add(txtcn);

        JLabel lblbday = new JLabel("Birthday: ");
        lblbday.setBounds(450, 160, 160, 50);
        lblbday.setFont(new Font("Calibri", Font.BOLD, 20));
        lblbday.setForeground(gold);
        panel.add(lblbday);

        txtbday = new JTextField();
        txtbday.setBounds(620, 170, 183, 30);
        txtbday.setFont(new Font("Arial", Font.PLAIN, 18));
        txtbday.setBackground(new Color(42, 42, 42));
        txtbday.setForeground(white);
        txtbday.setEditable(false);
        panel.add(txtbday);

        JLabel lblpos = new JLabel("Position: ");
        lblpos.setBounds(450, 210, 160, 50);
        lblpos.setFont(new Font("Calibri", Font.BOLD, 20));
        lblpos.setForeground(gold);
        panel.add(lblpos);

        txtpos = new JTextField();
        txtpos.setBounds(620, 220, 183, 30);
        txtpos.setFont(new Font("Arial", Font.PLAIN, 18));
        txtpos.setBackground(new Color(42, 42, 42));
        txtpos.setForeground(white);
        txtpos.setEditable(false);
        panel.add(txtpos);

        JLabel lblorg = new JLabel("Organization: ");
        lblorg.setBounds(450, 260, 160, 50);
        lblorg.setFont(new Font("Calibri", Font.BOLD, 20));
        lblorg.setForeground(gold);
        panel.add(lblorg);

        txtorg = new JTextField();
        txtorg.setBounds(620, 270, 183, 30);
        txtorg.setFont(new Font("Arial", Font.PLAIN, 18));
        txtorg.setBackground(new Color(42, 42, 42));
        txtorg.setForeground(white);
        txtorg.setEditable(false);
        panel.add(txtorg);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            searchStudent();
        } else if (e.getSource() == btnClear) {
            clearFields();
        } else if (e.getSource() == btnLogout) {
            Logout();
        } else if (e.getSource() == btnBack) {
            backToHomePage();
        }
    }

    private void searchStudent() {
        String searchQuery = txtSearch.getText().trim();

        if (searchQuery.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Student Number.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM tbl_studinfo WHERE Stud_ID = ?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, searchQuery);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    txtID.setText(rs.getString("Stud_id"));
                    txtln.setText(rs.getString("Stud_LastName"));
                    txtfn.setText(rs.getString("Stud_FirstName"));
                    txtmn.setText(rs.getString("Stud_MiddleName"));
                    txtcse.setText(rs.getString("Course"));
                    txtsy.setText(rs.getString("Stud_Year"));
                    txtadd.setText(rs.getString("Address"));
                    txtcn.setText(rs.getString("ContactNum"));
                    txtbday.setText(rs.getString("Birthday"));
                    txtpos.setText(rs.getString("Position"));
                    txtorg.setText(rs.getString("Organization"));
                } else {
                    JOptionPane.showMessageDialog(this, "No student found with the given Student Number.", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error executing query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        txtID.setText("");
        txtln.setText("");
        txtfn.setText("");
        txtmn.setText("");
        txtcse.setText("");
        txtsy.setText("");
        txtadd.setText("");
        txtcn.setText("");
        txtbday.setText("");
        txtpos.setText("");
        txtorg.setText("");
        txtSearch.setText("");
    }

   private void Logout() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            dispose();
            Login L = new Login();
            L.setVisible(true);
                    
        }
    }

    private void backToHomePage() {
        JOptionPane.showMessageDialog(this, "Redirecting to Home Page.", "Back", JOptionPane.INFORMATION_MESSAGE);
        new HomePage().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new StudentRecord().setVisible(true);
    }
}
