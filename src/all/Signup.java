/*
 *
 * Author : - Ashfaq Sherwa
 *
*/
package all;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Signup extends JFrame {
    private JLabel askUsername;
    private JLabel askPassword;
    private JLabel blank1;
    private JLabel blank2;
    private JLabel askConfirmPassword;
    private JTextField username;
    private JPasswordField password;
    private JPasswordField confirmPassword;
    private JButton register;
    private NewTopic newTopic;
    Connection connection = null;
    ResultSet resultSet;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement2 = null;
    PreparedStatement preparedStatement3 = null;
    public Signup() {
        super("Signup");
        blank1 = new JLabel("");
        blank2 = new JLabel("");
        askUsername = new JLabel("Username: ");
        askPassword = new JLabel("Password: ");
        askUsername.setForeground(Color.WHITE);
        askPassword.setForeground(Color.WHITE);
        askConfirmPassword = new JLabel("Confirm Password: ");
        askConfirmPassword.setForeground(Color.WHITE);
        username = new JTextField(20);
        password = new JPasswordField(20);
        confirmPassword = new JPasswordField(20);
        connection = DatabaseConnection.ConnectDB();
        register = new JButton("Register");
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                char[] p1 = password.getPassword();
                char[] p2 = confirmPassword.getPassword();
                String p3 = new String(p1);
                String p4 = new String(p2);
                if (username.getText().equalsIgnoreCase("") || p3.equalsIgnoreCase("") || p4.equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null , "Please fill up all the fields.");
                }
                else if(p3.equals(p4)) {
                    try {
                        preparedStatement = connection.prepareStatement("insert into loginCredentials values (?,?,?)");
                        preparedStatement.setString(2 , username.getText());
                        preparedStatement.setString(3 , p3);
                        int checkInsert =0;
                        checkInsert = preparedStatement.executeUpdate();
                        if(checkInsert!=0) {                            
                            preparedStatement2 = connection.prepareStatement("select * from loginCredentials where userName =?");
                            preparedStatement2.setString(1, username.getText());
                            resultSet = preparedStatement2.executeQuery();
                            preparedStatement3 = connection.prepareStatement("insert into userInfo values (?,?,?,?,?,?,?,?,?,?)");
                            preparedStatement3.setInt(2 , resultSet.getInt("userID"));
                            preparedStatement3.setString(3 , "N/A");
                            preparedStatement3.setString(4 , "N/A");
                            preparedStatement3.setInt(5 , 50);
                            preparedStatement3.setString(6 , "Not set yet.");
                            preparedStatement3.setInt(7 , -1);
                            preparedStatement3.setInt(8 , 0);
                            preparedStatement3.setInt(9 , 0);
                            preparedStatement3.setBoolean(10 , true);
                            int checkInsert2 = 0;
                            checkInsert2 = preparedStatement3.executeUpdate();
                            if(checkInsert2 !=2) {
                                resultSet.close();
                                JOptionPane.showMessageDialog(null , "Registration successful.");
                                newTopic = new NewTopic();
                                newTopic.setVisible(true);
                                close();
                            }    
                            
                        }    
                        else
                            JOptionPane.showMessageDialog(null , "Something is wrong. Please try again.");
                        resultSet.close();
                    }
                    catch(Exception e) {
                        JOptionPane.showMessageDialog(null , e);
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null , "Two passwords do not match. Please try again.");
                }
            }
        });
        
        try {
            final Image bgImage = javax.imageio.ImageIO.read(getClass().getResource("images//final3.png"));
            setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(bgImage, 0, 0, null);
                }
            });
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        setSize(600 , 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
		
	gbc.gridx = 1;
	gbc.gridy = 1;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(blank1 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 2;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(blank2 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 3;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(askUsername , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 4;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(username , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 5;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(askPassword , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 6;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(password , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 7;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(askConfirmPassword , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 8;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(confirmPassword , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 9;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(register , gbc);
        
    }
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

    
    
}
