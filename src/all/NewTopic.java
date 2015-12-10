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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class NewTopic extends JFrame {
    private JLabel selectTopic;
    private JLabel selectLevel;
    private JLabel selectGoal;
    private JLabel goal;
    private JLabel blank1;
    private JLabel blank2;
    private JButton submit;
    private JComboBox topics;
    private JComboBox levels;
    private JComboBox goals;
    private Questionnaire questionnaire;
    static int targetScore , targetCM;
    static String targetProfLevel;
    private String g1 , g2;
    private String[] listOfTopics = { "Select Topic" , "Arrays and String" , "Object and Classes" , "Operators" };
    private String[] listOfLevels = {"Select Level","Low" , "Medium" , "High"};
    private String[] listOfGoals;
    public NewTopic() {
        super("New Topic");
        selectTopic = new JLabel("Select the topic on which you want tutoring");
        selectLevel = new JLabel("Select the proficiency level you want to reach");
        selectGoal = new JLabel("Your goal is to ");
        selectTopic.setForeground(Color.WHITE);
        selectLevel.setForeground(Color.WHITE);
        selectGoal.setForeground(Color.WHITE);
        submit = new JButton("Submit");
        topics = new JComboBox();
        levels = new JComboBox();
        goals = new JComboBox();
        blank1 = new JLabel("");
        blank2 = new JLabel("");
        for(int i = 0 ; i<listOfTopics.length ; ++i) {
            topics.addItem(listOfTopics[i]);
        }
        for(int i = 0 ; i<listOfLevels.length ; ++i) {
            levels.addItem(listOfLevels[i]);
        }
        
        levels.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                if ((int)(levels.getSelectedIndex()) == 1) {
                    targetScore = 60;
                    targetCM = 60;
                }
                if ((int)(levels.getSelectedIndex()) == 2) {
                    targetScore = 80;
                    targetCM = 80;
                } 
                if ((int)(levels.getSelectedIndex()) == 3) {
                    targetScore = 100;
                    targetCM = 100;
                }
                g1 = "Score " + targetScore + " % in one of the tests.";
                g2 = "Get " + targetCM + " as your confidence meter score.";
                listOfGoals = new String[] { g1 , g2 };
                goals.removeAllItems();
                for(int i = 0 ; i<listOfGoals.length ; ++i) {
                    goals.addItem(listOfGoals[i]);
                }
            }
        });
        goals.setEditable(false);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                if(topics.getSelectedIndex() == -1 || goals.getSelectedIndex() == -1 || levels.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(null , "Please fill in all values.");
                }
                else {
                    Login.currG = String.valueOf(goals.getSelectedItem());              
                    targetProfLevel = String.valueOf(levels.getSelectedItem());
                    Login.currT = topics.getSelectedIndex();
                    questionnaire = new Questionnaire(Login.currT);
                    questionnaire.setVisible(true);
                    close();
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        setSize(600 , 420);
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
	add(selectTopic , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 4;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(topics , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 5;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(selectLevel , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 6;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(levels , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 7;
        gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(selectGoal , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 8;
        gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(goals , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 9;
        gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(submit , gbc); 
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
}
