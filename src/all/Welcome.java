/*
 *
 * Author : - Ashfaq Sherwa
 *
*/
package all;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Welcome extends JPanel {
    private ImageIcon icon;
    private JLabel iconLabel;
    private JButton signupButton;
    private JButton loginButton;
    private Login login;
    private Signup signup;
    private Dashboard dashboard;
    private JLabel alreadyUser;
    private JLabel firstTime;
    public Welcome() {
        icon = new ImageIcon(getClass().getResource("images//tutor.jpg"));
        iconLabel = new JLabel(icon);
        alreadyUser = new JLabel("Already a registered user?");
        firstTime = new JLabel("Haven't registered yet?");
        signupButton = new JButton("Signup");
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                login = new Login();
                login.setVisible(true);
            }
        });
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                signup = new Signup();
            }
        });
        setBackground(Color.white);
        setLayout(new GridBagLayout());
        setSize(600 , 400);
        setVisible(false);
	GridBagConstraints gbc = new GridBagConstraints();
		
	gbc.gridx = 1;
	gbc.gridy = 1;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(iconLabel , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 4;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(firstTime , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 5;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(signupButton , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 6;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(alreadyUser , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 7;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(loginButton , gbc); 
        
    }
}
