/**
 *
 * @author - Ashfaq Sherwa
 *
 **/

package all;

import java.awt.BorderLayout;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard extends JFrame {
    private JLabel message1;
    private JLabel message2;
    private JLabel blank1;
    private JLabel blank2;
    private JButton studyMaterialButton;
    private JButton testButton;
    private JButton summaryButton;
    private JButton newTopicButton;
    private JButton logout;
    private StudyMaterial_1 studyMaterial_1;
    private Test test;
    private Summary summary;
    private NewTopic newTopic;
    private Welcome welcome;
    public Dashboard() {
        super("Dashboard");
        studyMaterialButton = new JButton("Study Material");
        summaryButton = new JButton("Summary");
        newTopicButton = new JButton("New Topic");
        testButton = new JButton("Test");
        logout = new JButton("Logout");
        blank1 = new JLabel("");
        blank2 = new JLabel("");
        message1 = new JLabel("Not able to select new topic? You must achieve your goal in order to select a new topic.");
        message2 = new JLabel("You mush have high proficiency level to directly give tests.");
        if(Login.prevT == -1){
            studyMaterialButton.setEnabled(false);
            testButton.setEnabled(false);
        }
        if(Login.currL.equalsIgnoreCase("Low") || Login.currL.equalsIgnoreCase("Medium")) testButton.setEnabled(false);
        if(Login.currGA.equalsIgnoreCase("No")) newTopicButton.setEnabled(false);
        studyMaterialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                studyMaterial_1 = new StudyMaterial_1();
                studyMaterial_1.setVisible(true);
                close();
            }
        });
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                welcome = new Welcome();
                welcome.setVisible(true);
                close();
            }
        });
        testButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                try {
                    test = new Test();
                    test.setVisible(true);
                    close();
                } catch (SQLException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        summaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                try {
                    summary = new Summary();
                } catch (SQLException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
                summary.setVisible(true);
                close();
            }
        });
        newTopicButton.addActionListener(new ActionListener() {
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
        setSize(600, 500);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setVisible(true);
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 1;
	gbc.gridy = 2;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(blank1 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 3;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(blank2 , gbc);

        
        gbc.gridx = 1;
	gbc.gridy = 4;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(studyMaterialButton , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 5;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(testButton , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 6;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(summaryButton , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 7;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(newTopicButton , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 8;
	gbc.weightx = 1;
	gbc.weighty = 0.1;
	gbc.anchor = GridBagConstraints.NORTH;
	add(logout , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 14;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.SOUTH;
	add(message1 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 15;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.SOUTH;
	add(message2 , gbc);
	
    }
    
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
}