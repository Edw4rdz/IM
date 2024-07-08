package prototype;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class HomePage extends JFrame {
    private JPanel panel;
    private JLabel lblHome;
    private JButton btnViewRecords, btnPersonalRecords, btnLogout;
    public HomePage() {
     
        setTitle("Home Page");
        setSize(800, 600);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setBackground(new Color(139, 0, 0));
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 600);
        add(panel);

       
        lblHome = new JLabel("Home Page");
        lblHome.setBounds(300, 100, 300, 50);
        lblHome.setFont(new Font("Arial", Font.BOLD, 40));
        lblHome.setForeground(Color.WHITE);
        panel.add(lblHome);
         
        ImageIcon LogoIcon = new ImageIcon("pup.png");
        Image LogoImage = LogoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT); 
        ImageIcon scaledLogoIcon = new ImageIcon(LogoImage);
        JLabel Logoicon = new JLabel(scaledLogoIcon);
        Logoicon.setBounds(120, 75, 100, 100); 
        panel.add(Logoicon);

        btnViewRecords = new JButton("View Records");
        btnViewRecords.setFont(new Font("Arial", Font.BOLD, 18));
        btnViewRecords.setBounds(300, 200, 200, 50);
        btnViewRecords.setForeground(new Color(128, 0, 32));
        btnViewRecords.setBackground(new Color(255, 215, 0));
        btnViewRecords.setBorder(new LineBorder(new Color(255, 215, 0), 3));
        btnViewRecords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Viewing().setVisible(true);
            }
        });
        panel.add(btnViewRecords);

       
        btnPersonalRecords = new JButton("Search Student");
        btnPersonalRecords.setFont(new Font("Arial", Font.BOLD, 18));
        btnPersonalRecords.setBounds(300, 300, 200, 50);
        btnPersonalRecords.setForeground(new Color(128, 0, 32));
        btnPersonalRecords.setBackground(new Color(255, 215, 0));
        btnPersonalRecords.setBorder(new LineBorder(new Color(255, 215, 0), 3));
        btnPersonalRecords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StudentRecord().setVisible(true);
               
            }
        });
        panel.add(btnPersonalRecords);

                btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogout.setBounds(300, 400, 200, 50);
        btnLogout.setForeground(new Color(128, 0, 32));
        btnLogout.setBackground(new Color(255, 215, 0));
        btnLogout.setBorder(new LineBorder(new Color(255, 215, 0), 3));
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Logout",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                    new Login().setVisible(true);
                }
            }
        });
        panel.add(btnLogout);
    }
    }
