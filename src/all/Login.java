/*
 *
 * Author : - Ashfaq Sherwa
 *
*/

package all;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
    private final JLabel askUsername;
    private final JLabel askPassword;
    private final JLabel blank;
    private final JTextField userName;
    private final JPasswordField password;
    private final JButton login;
    private Dashboard dashboard;
    private final JButton button;
    static String currL,currGA,currG;
    static int userID,currT,currD,prevT,currC;
    static boolean firstL;
    Connection connection = null;
    private ArrayList<ResultSet> res;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement2 = null;
    PreparedStatement preparedStatement3 = null;
    public Login() {
        super("Login");
        connection = DatabaseConnection.ConnectDB();
        blank = new JLabel("");
        askUsername = new JLabel("Username: ");
        userName = new JTextField(15);
        askPassword = new JLabel("Password: ");
        password = new JPasswordField(15);
        button = new JButton("Skip");
        login = new JButton("Login");
        res = new ArrayList<>();
        login.setEnabled(true);
        askUsername.setForeground(Color.WHITE);
        askPassword.setForeground(Color.WHITE);
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String sql = "select * from loginCredentials where userName = ? and userPassword = ?";
                String sql2 = "select * from userInfo where referId = ?";
                try {
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement2 = connection.prepareStatement(sql2);                    
                    preparedStatement.setString(1 , userName.getText());
                    preparedStatement.setString(2 , password.getText());                                        
                    res.add(preparedStatement.executeQuery());
                    userID = res.get(0).getInt("userID");
                    preparedStatement2.setString(1, String.valueOf(userID));
                    res.add(preparedStatement2.executeQuery());
                    if(res.get(0).next() && res.get(1).next()) {
                        JOptionPane.showMessageDialog(null , "Login successful.");
                        currG = res.get(1).getString("goal");
                        currL = res.get(1).getString("level");
                        currGA = res.get(1).getString("goalAchieved");
                        currC = res.get(1).getInt("confidence");
                        currT = res.get(1).getInt("currTop");
                        currD = res.get(1).getInt("currDiff");
                        prevT = res.get(1).getInt("prevTest");
                        if(res.get(1).getBoolean("firstLogin")) {
                            firstL = false;
                            preparedStatement3 = connection.prepareStatement("UPDATE userInfo SET firstLogin = '" + firstL + "' where referId ='" + userID + "'");        
                            int checkUpdate = preparedStatement3.executeUpdate();
                        }
                        dashboard = new Dashboard();
                        close();
                        dashboard.setVisible(true);
                    }    
                    else
                        JOptionPane.showMessageDialog(null , "Username and password do not match. Please try again.");
                    
                    res.get(0).close();
                    res.get(1).close();
                }    
                catch(Exception e) {
                    JOptionPane.showMessageDialog(null , e);
                }
            }
        });
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dashboard = new Dashboard();
                dashboard.setVisible(true);
                close();
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
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
	GridBagConstraints gbc = new GridBagConstraints();
		
	gbc.gridx = 1;
	gbc.gridy = 1;
	gbc.weightx = 1;
	gbc.weighty = 0.1;
	gbc.anchor = GridBagConstraints.NORTH;
	add(blank , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 3;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(askUsername , gbc);
		
	gbc.gridx = 1;
	gbc.gridy = 4;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(userName , gbc);
		
	gbc.gridx = 1;
	gbc.gridy = 5;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(askPassword , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 6;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(password , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 7;
	gbc.weightx = 1;
	gbc.weighty = 0.1;		 
	gbc.anchor = GridBagConstraints.NORTH;
	add(login , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 8;
	gbc.weightx = 1;
	gbc.weighty = 0.1;		 
	gbc.anchor = GridBagConstraints.NORTH;
	add(button , gbc); 
    } 
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
}
