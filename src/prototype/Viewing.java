package prototype;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.LineBorder;

public class Viewing extends JFrame {

    private JTable tb;
    private JScrollPane scrllTb;
    private JLabel lblView;
    private JButton viewDataButton, btnLogout;
    private JPanel panel;
    private DefaultTableModel tableModel;

    public Viewing() {
        
        setTitle("Viewing Page");
        setSize(1200, 800); // Increased size to accommodate the table and other components
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

       
        panel = new JPanel();
        panel.setBackground(new Color(139, 0, 0));
        panel.setLayout(null);
        panel.setBounds(0, 0, 1500, 1300); // Adjusted size of the panel to match the frame size
        add(panel);

        
        tableModel = new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Student ID", "Last Name", "First Name", "Middle Name", "Course", "Year", "Address", "Contact No", "Birthday", "Position", "Affiliation"
            }
        );
        tb = new JTable(tableModel);
        scrllTb = new JScrollPane(tb);
        scrllTb.setBounds(30, 100, 1140, 500); // Adjusted bounds to ensure the table fits within the frame
        tb.setBackground(new Color(255, 215, 0));
        scrllTb.setBorder(new LineBorder(Color.BLACK, 3));
        tb.setFillsViewportHeight(true);

        
        tb.getColumnModel().getColumn(0).setPreferredWidth(300); // Student ID
        tb.getColumnModel().getColumn(1).setPreferredWidth(200); // Last Name
        tb.getColumnModel().getColumn(2).setPreferredWidth(300); // First Name
        tb.getColumnModel().getColumn(3).setPreferredWidth(250); // Middle Name
        tb.getColumnModel().getColumn(4).setPreferredWidth(150); // Course
        tb.getColumnModel().getColumn(5).setPreferredWidth(200);  // Year
        tb.getColumnModel().getColumn(6).setPreferredWidth(200); // Address
        tb.getColumnModel().getColumn(7).setPreferredWidth(250); // Contact Number
        tb.getColumnModel().getColumn(8).setPreferredWidth(300); // BirthDate
        tb.getColumnModel().getColumn(9).setPreferredWidth(550); // Position
        tb.getColumnModel().getColumn(10).setPreferredWidth(200); // Affiliation

    
        JTableHeader tblHeader = tb.getTableHeader();
        tblHeader.setBackground(new Color(255, 255, 255));
        tblHeader.setFont(new Font("Arial", Font.BOLD, 15));
        tblHeader.setForeground(Color.BLACK);
        tblHeader.setEnabled(false);

        panel.add(scrllTb);

        
        lblView = new JLabel("IBITS Student Database");
        lblView.setBounds(30, 30, 1140, 50); // Adjusted bounds to fit the entire width of the screen
        lblView.setFont(new Font("Arial", Font.BOLD, 40)); // Increased font size
        lblView.setForeground(Color.WHITE);
        panel.add(lblView);

        
        viewDataButton = new JButton("View");
        viewDataButton.setFont(new Font("Arial", Font.BOLD, 18));
        viewDataButton.setBounds(1000, 620, 150, 40); // Positioned at the lower right
        viewDataButton.setForeground(new Color(128,0,32));
        viewDataButton.setBackground(new Color(255, 215, 0));
        viewDataButton.setBorder(new LineBorder(new Color(255,215,0), 3));
        viewDataButton.addActionListener(e -> loadStudentData());
        panel.add(viewDataButton);
        
        
        
        btnLogout = new JButton("Logout");
        btnLogout.setBounds(1000, 670, 150, 40); // Positioned above the "View" button
        btnLogout.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogout.setForeground(new Color(128,0,32));
        btnLogout.setBackground(new Color(255, 215, 0));
        btnLogout.setBorder(new LineBorder(new Color(255,215,0), 3));
        btnLogout.addActionListener(e -> Logout());
        panel.add(btnLogout);
    }

    public void refreshData() {
        loadStudentData();
    }

    private void loadStudentData() {
        String query = "SELECT * FROM tbl_studinfo"; // Fetch all student records

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cite", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            tableModel.setRowCount(0); // Clear existing data in the table

            while (rs.next()) {
                Object[] row = new Object[]{
                    rs.getString("Stud_ID"),
                    rs.getString("Stud_LastName"),
                    rs.getString("Stud_FirstName"),
                    rs.getString("Stud_MiddleName"),
                    rs.getString("Course"),
                    rs.getString("Stud_Year"),
                    rs.getString("Address"),
                    rs.getString("ContactNum"),
                    rs.getString("Birthday"),
                    rs.getString("Position"),
                    rs.getString("Organization")};
     
                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading student data: " + e.getMessage());
        }
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

    public static void main(String[] args) {
        new Viewing();
    }
}
