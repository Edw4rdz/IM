 package prototype;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class Registration extends JFrame implements ActionListener {
    private JTextField txtID, txtln, txtfn, txtmn, txtcse, txtsy, txtadd, txtcn, txtbday, txtpos, txtorg;
    private JButton sub;

    private static final String URL = "jdbc:mysql://localhost:3306/db_cite"; // Corrected database name
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Registration() {
        setTitle("Student Registration Form");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 875, 490);
        panel.setBackground(new Color(139, 0, 0));
        panel.setLayout(null);
        add(panel);

        Color white = new Color(255, 255, 255);
        Color gold = new Color(255, 215, 0);

        JLabel lblID = new JLabel("Student ID: ");
        lblID.setBounds(50, 50, 160, 50);
        lblID.setFont(new Font("Calibri", Font.BOLD, 20));
        lblID.setForeground(gold);
        panel.add(lblID);

        JLabel lblln = new JLabel("Last Name: ");
        lblln.setBounds(50, 100, 160, 50);
        lblln.setFont(new Font("Calibri", Font.BOLD, 20));
        lblln.setForeground(gold);
        panel.add(lblln);

        JLabel lblfn = new JLabel("First Name: ");
        lblfn.setBounds(50, 150, 160, 50);
        lblfn.setFont(new Font("Calibri", Font.BOLD, 20));
        lblfn.setForeground(gold);
        panel.add(lblfn);

        JLabel lblmn = new JLabel("Middle Name: ");
        lblmn.setBounds(50, 200, 160, 50);
        lblmn.setFont(new Font("Calibri", Font.BOLD, 20));
        lblmn.setForeground(gold);
        panel.add(lblmn);

        JLabel lblcse = new JLabel("Course:");
        lblcse.setBounds(50, 250, 160, 50);
        lblcse.setFont(new Font("Calibri", Font.BOLD, 20));
        lblcse.setForeground(gold);
        panel.add(lblcse);

        JLabel lblsy = new JLabel("Student Year: ");
        lblsy.setBounds(50, 300, 160, 50);
        lblsy.setFont(new Font("Calibri", Font.BOLD, 20));
        lblsy.setForeground(gold);
        panel.add(lblsy);

        JLabel lbladd = new JLabel("Address: ");
        lbladd.setBounds(50, 350, 160, 50);
        lbladd.setFont(new Font("Calibri", Font.BOLD, 20));
        lbladd.setForeground(gold);
        panel.add(lbladd);

        JLabel lblcn = new JLabel("Contact Number: ");
        lblcn.setBounds(50, 400, 160, 50);
        lblcn.setFont(new Font("Calibri", Font.BOLD, 20));
        lblcn.setForeground(gold);
        panel.add(lblcn);

        JLabel lblbday = new JLabel("Birthday: ");
        lblbday.setBounds(450, 50, 160, 50);
        lblbday.setFont(new Font("Calibri", Font.BOLD, 20));
        lblbday.setForeground(gold);
        panel.add(lblbday);

        JLabel lblpos = new JLabel("Position: ");
        lblpos.setBounds(450, 100, 160, 50);
        lblpos.setFont(new Font("Calibri", Font.BOLD, 20));
        lblpos.setForeground(gold);
        panel.add(lblpos);

        JLabel lblorg = new JLabel("Organization: ");
        lblorg.setBounds(450, 150, 160, 50);
        lblorg.setFont(new Font("Calibri", Font.BOLD, 20));
        lblorg.setForeground(gold);
        panel.add(lblorg);
        
        
        
        JLabel lblnote = new JLabel("Note: Please follow the yr-mm-dd format for birthday");
        lblnote.setBounds(450, 250, 900, 100);
        lblnote.setFont(new Font("Calibri", Font.BOLD, 18));
        lblnote.setForeground(Color.WHITE);
        panel.add(lblnote);

        txtID = new JTextField();
        txtID.setBounds(220, 60, 183, 30);
        txtID.setFont(new Font("Arial", Font.PLAIN, 18));
        txtID.setBackground(new Color(42, 42, 42));
        txtID.setForeground(white);
        panel.add(txtID);

        txtln = new JTextField();
        txtln.setBounds(220, 110, 183, 30);
        txtln.setFont(new Font("Arial", Font.PLAIN, 18));
        txtln.setBackground(new Color(42, 42, 42));
        txtln.setForeground(white);
        panel.add(txtln);

        txtfn = new JTextField();
        txtfn.setBounds(220, 160, 183, 30);
        txtfn.setFont(new Font("Arial", Font.PLAIN, 18));
        txtfn.setBackground(new Color(42, 42, 42));
        txtfn.setForeground(white);
        panel.add(txtfn);

        txtmn = new JTextField();
        txtmn.setBounds(220, 210, 183, 30);
        txtmn.setFont(new Font("Arial", Font.PLAIN, 18));
        txtmn.setBackground(new Color(42, 42, 42));
        txtmn.setForeground(white);
        panel.add(txtmn);

        txtcse = new JTextField();
        txtcse.setBounds(220, 260, 183, 30);
        txtcse.setFont(new Font("Arial", Font.PLAIN, 18));
        txtcse.setBackground(new Color(42, 42, 42));
        txtcse.setForeground(white);
        panel.add(txtcse);

        txtsy = new JTextField();
        txtsy.setBounds(220, 310, 183, 30);
        txtsy.setFont(new Font("Arial", Font.PLAIN, 18));
        txtsy.setBackground(new Color(42, 42, 42));
        txtsy.setForeground(white);
        panel.add(txtsy);

        txtadd = new JTextField();
        txtadd.setBounds(220, 360, 183, 30);
        txtadd.setFont(new Font("Arial", Font.PLAIN, 18));
        txtadd.setBackground(new Color(42, 42, 42));
        txtadd.setForeground(white);
        panel.add(txtadd);

        txtcn = new JTextField();
        txtcn.setBounds(220, 410, 183, 30);
        txtcn.setFont(new Font("Arial", Font.PLAIN, 18));
        txtcn.setBackground(new Color(42, 42, 42));
        txtcn.setForeground(white);
        panel.add(txtcn);

        txtbday = new JTextField();
        txtbday.setBounds(620, 60, 183, 30);
        txtbday.setFont(new Font("Arial", Font.PLAIN, 18));
        txtbday.setBackground(new Color(42, 42, 42));
        txtbday.setForeground(white);
        panel.add(txtbday);

        txtpos = new JTextField();
        txtpos.setBounds(620, 110, 183, 30);
        txtpos.setFont(new Font("Arial", Font.PLAIN, 18));
        txtpos.setBackground(new Color(42, 42, 42));
        txtpos.setForeground(white);
        panel.add(txtpos);

        txtorg = new JTextField();
        txtorg.setBounds(620, 160, 183, 30);
        txtorg.setFont(new Font("Arial", Font.PLAIN, 18));
        txtorg.setBackground(new Color(42, 42, 42));
        txtorg.setForeground(white);
        panel.add(txtorg);

        sub = new JButton("Submit");
        sub.setBounds(620, 210, 183, 30);
        sub.setForeground(gold);
        sub.setBackground(new Color(128, 0, 0));
        sub.addActionListener(this);
        panel.add(sub);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        registerStudent();
    }

    private void registerStudent() {
        String id = txtID.getText();
        String lastName = txtln.getText();
        String firstName = txtfn.getText();
        String middleName = txtmn.getText();
        String course = txtcse.getText();
        String year = txtsy.getText();
        String address = txtadd.getText();
        String contactNum = txtcn.getText();
        String birthday = txtbday.getText();
        String position = txtpos.getText();
        String organization = txtorg.getText();

        String query = "INSERT INTO tbl_studinfo (Stud_ID, Stud_LastName, Stud_FirstName, Stud_MiddleName, Course, Stud_Year, Address, ContactNum, Birthday, Position, Organization) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, id);
            pstmt.setString(2, lastName);
            pstmt.setString(3, firstName);
            pstmt.setString(4, middleName);
            pstmt.setString(5, course);
            pstmt.setString(6, year);
            pstmt.setString(7, address);
            pstmt.setString(8, contactNum);
            pstmt.setString(9, birthday);
            pstmt.setString(10, position);
            pstmt.setString(11, organization);

        pstmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Student registered successfully!");
        
        dispose();
        Viewing v = new Viewing();
        v.setVisible(true);
        
        

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Registration().setVisible(true);
        });
    }
}
