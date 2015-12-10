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
import javax.swing.JPanel;

public class Questionnaire extends JFrame {
    private JLabel confidence;
    private JLabel understanding;
    private JLabel likeness;
    private JLabel willingness;
    private JLabel blank1;
    private JLabel blank2;
    private JComboBox ratings_1;
    private JComboBox ratings_2;
    private JComboBox ratings_3;
    private JComboBox ratings_4;
    private String[] listOfRatings = {"1" , "2" , "3" , "4" , "5"};
    private JButton submit;
    private StudyMaterial_1 studyMaterial_1;
    public Questionnaire(final int t) {
        super("Questionnaire");
        confidence = new JLabel("How confident are you for this topic?");
        understanding = new JLabel("How is your understanding about this topic?");
        likeness = new JLabel("How much do you like this topic?");
        willingness = new JLabel("How much is your willingness to acheive this goal?");
        confidence.setForeground(Color.WHITE);
        understanding.setForeground(Color.WHITE);
        willingness.setForeground(Color.WHITE);
        likeness.setForeground(Color.WHITE);
        blank1 = new JLabel("");
        blank2 = new JLabel("");
        ratings_1 = new JComboBox();
        ratings_2 = new JComboBox();
        ratings_3 = new JComboBox();
        ratings_4 = new JComboBox();
        for(int i = 0 ; i<listOfRatings.length ; ++i) {
            ratings_1.addItem(listOfRatings[i]);
            ratings_2.addItem(listOfRatings[i]);
            ratings_3.addItem(listOfRatings[i]);
            ratings_4.addItem(listOfRatings[i]);
        }
        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                int w = (int)(ratings_1.getSelectedIndex() + 1);
                int x = (int)(ratings_2.getSelectedIndex() + 1);
                int y = (int)(ratings_3.getSelectedIndex() + 1);
                int z = (int)(ratings_4.getSelectedIndex() + 1);
                int avgRating = (int)((double)(w+x+y+z)/(double)4);
                if (avgRating<2) {
                    Login.currD = 1;
                }
                else if (avgRating <=2 && avgRating<3) {
                    Login.currD = 2;
                }
                else if (avgRating <=3) {
                    Login.currD = 3;
                }
                Login.currT = t;
                studyMaterial_1 = new StudyMaterial_1(); 
                studyMaterial_1.setVisible(true);
                close();
            }
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	gbc.gridy = 4;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(confidence , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 5;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(ratings_1 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 6;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(understanding , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 7;
        gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(ratings_2 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 8;
        gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(likeness , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 9;
        gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(ratings_3 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 10;
        gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(willingness , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 11;
        gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(ratings_4 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 12;
        gbc.weightx = 1;
	gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.NORTH;
	add(submit , gbc); 
        
    }   
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
}
