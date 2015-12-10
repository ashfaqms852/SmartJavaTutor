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
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Summary extends JFrame {
    private JLabel goal;
    private JLabel confidenceMeter;
    private JLabel proficiencyLevel;
    private JLabel goalAchieved;
    private JLabel currentTopic;
    private JLabel message;
    private JLabel message1;
    private JLabel message2;
    private JLabel message3;
    private JLabel blank1;
    private JLabel blank2;
    private JLabel blank3;
    private JButton dashboardButton;
    private JButton yield;
    private Dashboard dashboard;
    private NewTopic newTopic;
    private String topic;
    public Summary() throws SQLException {
        super("Summary");
        if (Login.currG != null) goal = new JLabel("Your current goal is: " + Login.currG);
        else goal = new JLabel("Your current goal is: You haven't set your goal yet.");
        confidenceMeter = new JLabel("Confidence meter: " + Login.currC);
        proficiencyLevel = new JLabel("Proficiency Level: " + Login.currL);
        goalAchieved = new JLabel("Goal Acheived: " + Login.currGA);
        switch(Login.currT) {
            case 1 : topic = "Arrays and String";
                     break;
            case 2 : topic = "Classes and Objects";
                     break;
            case 3 : topic = "Operators";
                     break;
            default : break;    
        }
        blank1 = new JLabel("");
        blank2 = new JLabel("");
        blank3 = new JLabel("");
        currentTopic = new JLabel("Current Topic: " + topic);
        dashboardButton = new JButton("Dashboard");
        yield = new JButton("Yield");
        if(Login.currGA.equalsIgnoreCase("Yes")) {
            yield.setEnabled(false);
            message2 = new JLabel("Congrats! You have achieved your goal."); 
            message3 = new JLabel("Go ahead and challenge yourself with a new topic.");
        }
        else {
            message2 = new JLabel("Give it a one more try.");
            message3 = new JLabel("Failure shouldn't halt you.");
        }
        message = new JLabel("Do not want to continue with this topic?");
        message1 = new JLabel("You can yield and select a new topic.");
        
        goal.setForeground(Color.WHITE);
        confidenceMeter.setForeground(Color.WHITE);
        proficiencyLevel.setForeground(Color.WHITE);
        goalAchieved.setForeground(Color.WHITE);
        currentTopic.setForeground(Color.WHITE);
        message.setForeground(Color.WHITE);
        message1.setForeground(Color.WHITE);
        message2.setForeground(Color.WHITE);
        message3.setForeground(Color.WHITE);
        
        dashboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                dashboard = new Dashboard();
                dashboard.setVisible(true);
                close();
            }
        });
        yield.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                newTopic = new NewTopic();
                newTopic.setVisible(true);
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
        setLayout(new GridBagLayout());
        setSize(600 , 400);
        setVisible(false);
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
	add(blank3 , gbc);

        gbc.gridx = 1;
	gbc.gridy = 4;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(goal , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 5;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(confidenceMeter , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 6;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(proficiencyLevel , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 7;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(goalAchieved , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 8;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(message2 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 9;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(message3 , gbc);

        
        gbc.gridx = 1;
	gbc.gridy = 10;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(currentTopic , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 11;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(dashboardButton , gbc);
                
        gbc.gridx = 1;
	gbc.gridy = 12;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(message , gbc);

        gbc.gridx = 1;
	gbc.gridy = 13;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(message1 , gbc);

        
        gbc.gridx = 1;
	gbc.gridy = 14;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(yield , gbc);        
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
}
