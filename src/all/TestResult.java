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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TestResult extends JFrame {
    private JLabel score;
    private JLabel comment;
    private JLabel comment2;
    private JLabel blank1;
    private JLabel blank2;
    private Dashboard dashboard;
    private Summary summary;
    private JButton toDashboard;
    private JButton toSummary;
    static int s;
    Connection connection = null;
    PreparedStatement ps = null;
    public TestResult(int scoreCount) throws SQLException {
        super("TestResult");     
        connection = DatabaseConnection.ConnectDB();
        toDashboard = new JButton("Dashboard");
        toSummary = new JButton("Summary");
        blank1 = new JLabel("");
        blank2 = new JLabel("");
        s = (int)(((double)scoreCount/(double)5) * 100);
        if(s<=40 && s>=0) {
            comment = new JLabel("This might be a bad day for you.");
            comment2 = new JLabel("Never mind, you can get only better from here onwards.");
        }
        else if(s>40 && s<=60) {
            comment = new JLabel("You can do much better.");
            comment2 = new JLabel("Keep calm and you would do well.");
        }
        else if(s>60 && s<=80) {
            comment = new JLabel("Great going. You are almost there.");
            comment2 = new JLabel("");
        }
        else if(s>80) {
            comment = new JLabel("Perfect. Now, this topic should have nightmares about you.");
            comment2 = new JLabel("Instead of the other way around.");
        } 
        if(NewTopic.targetScore <= TestResult.s && TestResult.s>0) {
            Login.currGA = "Yes";
            Login.currL = "High";
            Login.currC = Login.currC + 20;
            if(Login.currC < 0) Login.currC = 0;
            else if(Login.currC > 100) Login.currC = 100;
        }   
        else if (TestResult.s <= (0.5*NewTopic.targetScore)){
            Login.currGA = "No";
            Login.currL = "Low";
            Login.currC = Login.currC -20;
            if(Login.currC < 0) Login.currC = 0;
            else if(Login.currC > 100) Login.currC = 100;
        }
        else if (TestResult.s > (0.5*NewTopic.targetScore) && TestResult.s < NewTopic.targetScore){
            Login.currGA = "No";
            Login.currL = "Medium";
            Login.currC = Login.currC - 10;
            if(Login.currC < 0) Login.currC = 0;
            else if(Login.currC > 100) Login.currC = 100;
        }
        score = new JLabel("Your test score is " + String.valueOf(s) + "%");
        Login.prevT = s;
        String sql = "UPDATE userInfo SET level='" + Login.currL + "' , goalAchieved ='" + Login.currGA +"' , confidence ='" + Login.currC + "' , goal = '" + Login.currG + "' , prevTest = '" + Login.prevT + "' , currTop = '" + Login.currT + "' , currDiff = '" + Login.currD + "' where referId ='" + Login.userID + "'";
        ps = connection.prepareStatement(sql);
        int checkUpdate = 0;
        checkUpdate = ps.executeUpdate();
        score.setForeground(Color.WHITE);
        comment.setForeground(Color.WHITE);
        comment2.setForeground(Color.WHITE);
        toDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                dashboard = new Dashboard();
                dashboard.setVisible(true);
                close();
            }
        });
        toSummary.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                try {
                    summary = new Summary();
                } catch (SQLException ex) {
                    Logger.getLogger(TestResult.class.getName()).log(Level.SEVERE, null, ex);
                }
                summary.setVisible(true);
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
	gbc.gridy = 2;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(blank1 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 3;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(blank2 , gbc);
        
	gbc.gridx = 1;
	gbc.gridy = 4;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(score , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 5;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(comment , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 6;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(comment2 , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 7;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(toSummary , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 8;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(toDashboard , gbc);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
}
