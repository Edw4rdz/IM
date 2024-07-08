package prototype;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Login extends JFrame {

    JTextField txtuser;
    JPasswordField txtpass;
    Connection conn;
    int logattempt = 0;
    long cdend = 0;
    final long cd_duration = 7000; // Cooldown duration in milliseconds
    Timer cooldownTimer;
    JLabel lblCooldown, lblReg, lblnote;
    JButton LoginButton;
    JCheckBox showPassword;

    public Login() {
        setTitle("Login");
        setSize(885, 500);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        
        getContentPane().setBackground(new Color(139, 0, 0));

        JLabel lbltitle = new JLabel("LOGIN");
        lbltitle.setForeground(new Color(255, 255, 255));
        lbltitle.setBounds(410, 170, 220, 50);
        lbltitle.setFont(new Font("Arial", Font.BOLD, 23));
        add(lbltitle);

        JLabel lblusername = new JLabel("Username: ");
        lblusername.setBounds(235, 225, 155, 22);
        lblusername.setFont(new Font("Arial", Font.BOLD, 16));
        lblusername.setForeground(Color.WHITE);
        add(lblusername);

        JLabel lblpass = new JLabel("Password: ");
        lblpass.setBounds(235, 260, 200, 30);
        lblpass.setFont(new Font("Arial", Font.BOLD, 16));
        lblpass.setForeground(Color.WHITE);
        add(lblpass);

        txtuser = new JTextField();
        txtuser.setBounds(350, 220, 200, 30);
        txtuser.setFont(new Font("Arial", Font.PLAIN, 18));
        txtuser.setBackground(new Color(128, 0, 0));
        txtuser.setForeground(new Color(255, 215, 0));
        add(txtuser);

        txtpass = new JPasswordField();
        txtpass.setBounds(350, 260, 200, 30);
        txtpass.setFont(new Font("Arial", Font.PLAIN, 18));
        txtpass.setForeground(new Color(255, 215, 0));
        txtpass.setBackground(new Color(128, 0, 0));
        add(txtpass);

        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(560, 260, 150, 30);
        showPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        showPassword.setForeground(Color.WHITE);
        showPassword.setBackground(new Color(139, 0, 0));
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    txtpass.setEchoChar((char) 0);
                } else {
                    txtpass.setEchoChar('•');
                }
            }
        });
        add(showPassword);

        LoginButton = new JButton("CONFIRM");
        LoginButton.setBounds(350, 300, 200, 30);
        LoginButton.setForeground(Color.BLACK);
        LoginButton.setBackground(new Color(255, 204, 0));
        LoginButton.setBorder(new LineBorder(new Color(255, 255, 255), 1));
        add(LoginButton);

        lblCooldown = new JLabel();
        lblCooldown.setBounds(330, 330, 300, 30);
        lblCooldown.setFont(new Font("Arial", Font.BOLD, 18));
        lblCooldown.setForeground(Color.WHITE);
        lblCooldown.setVisible(false);
        add(lblCooldown);

        lblnote = new JLabel("Don't have a record yet?");
        lblnote.setFont(new Font("Times New Roman", Font.BOLD, 10));
        lblnote.setBounds(370, 330, 200, 30);
        lblnote.setForeground(Color.WHITE);
        add(lblnote);

        lblReg = new JLabel("Register");
        lblReg.setFont(new Font("Times New Roman", Font.BOLD, 10));
        lblReg.setBounds(480, 330, 200, 30);
        lblReg.setForeground(Color.BLUE);
        add(lblReg);
        lblReg.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose();
                Registration r = new Registration();
                r.setVisible(true);
            }
        });

        ImageIcon userIcon = new ImageIcon("icon.png");
        Image userImage = userIcon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon scaledUserIcon = new ImageIcon(userImage);
        JLabel userLabel = new JLabel(scaledUserIcon);
        userLabel.setBounds(200, 221, 30, 30); // Beside the username label
        add(userLabel);

        ImageIcon lockIcon = new ImageIcon("lock.png");
        Image lockImage = lockIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon scaledLockIcon = new ImageIcon(lockImage);
        JLabel lockLabel = new JLabel(scaledLockIcon);
        lockLabel.setBounds(205, 263, 20, 20); // Beside the password label
        add(lockLabel);

        ImageIcon logoIcon = new ImageIcon("pup.png");
        Image logoImage = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT); 
        ImageIcon scaledLogoIcon = new ImageIcon(logoImage);
        JLabel logoLabel = new JLabel(scaledLogoIcon);
        logoLabel.setBounds(370, 10, 150, 150); 
        add(logoLabel);

        setVisible(true);

        LoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_cite", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLogin() {
        String Username = txtuser.getText().trim();
        String Password = new String(txtpass.getPassword()).trim();

        String query = "SELECT * FROM tbl_user WHERE username = ? AND password = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, Username);
            pst.setString(2, Password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                logattempt = 0;
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                HomePage homePage = new HomePage(); 
            } else {
                logattempt++;
                if (logattempt >= 3) {
                    cdend = System.currentTimeMillis() + cd_duration;
                    JOptionPane.showMessageDialog(this, "Too many failed attempts. Please wait for the cooldown period.", "Cooldown", JOptionPane.WARNING_MESSAGE);
                    lblCooldown.setVisible(true);
                    startCooldownTimer();
                    LoginButton.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database query error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startCooldownTimer() {
        cooldownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                long currentTime = System.currentTimeMillis();
                if (currentTime < cdend) {
                    long remcd = (cdend - currentTime) / 1000;
                    lblCooldown.setText("Cooldown: " + remcd + " seconds remaining");
                } else {
                    cooldownTimer.stop();
                    lblCooldown.setVisible(false);
                    LoginButton.setEnabled(true);
                    logattempt = 0;
                    txtuser.setEnabled(true);
                    txtpass.setEnabled(true);
                }
            }
        });
        cooldownTimer.start();
    }

    public static void main(String[] args) {
        new Login();
    }
}
