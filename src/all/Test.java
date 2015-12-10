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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Test extends JFrame {
    private JButton submit;
    private TestResult testResult;
    private ArrayList<JLabel> questions;
    private ArrayList<ButtonGroup> answersGroup;
    private ArrayList<JRadioButton> a;
    private ArrayList<JRadioButton> b;
    private ArrayList<JRadioButton> c;
    private ArrayList<ArrayList<String>> optionsOuter;
    private ArrayList<String> optionsInner;
    private ArrayList<ResultSet> res;
    private ArrayList<PreparedStatement> ps;
    private ArrayList<String> sql;
    Connection connection = null;
    public Test() throws SQLException {
        super("Test");
        connection = DatabaseConnection.ConnectDB();
        String diff = String.valueOf(Login.currD);
        String top = String.valueOf(Login.currT);
        questions = new ArrayList<>();
        a = new ArrayList<>();
        b = new ArrayList<>();
        c = new ArrayList<>();
        answersGroup = new ArrayList<>();
        res = new ArrayList<>();
        ps = new ArrayList<>();
        sql = new ArrayList<>();
        optionsOuter = new ArrayList<>();
        optionsInner = new ArrayList<>();
        for (int i = 0 ; i<5 ; ++i) {            
            sql.add("select * from testQuestions where topic = ? and testType = ? and questionNo = ?");
            ps.add(connection.prepareStatement(sql.get(i)));
            ps.get(i).setString(1 , top);
            ps.get(i).setString(2, diff);
            ps.get(i).setString(3, String.valueOf(1+i));
            res.add(ps.get(i).executeQuery());
            optionsInner.add(res.get(i).getString("correctAns"));
            optionsInner.add(res.get(i).getString("optionA"));
            optionsInner.add(res.get(i).getString("optionB"));
            optionsInner.add(res.get(i).getString("optionC"));
            optionsInner.add(res.get(i).getString("question"));
            optionsOuter.add(optionsInner);
            optionsInner = new ArrayList<>();
            res.get(i).close();
        }
        
        for (int i = 0 ; i<5 ; ++i) {
            questions.add(new JLabel(optionsOuter.get(i).get(4)));
            questions.get(i).setForeground(Color.WHITE);
            a.add(new JRadioButton(optionsOuter.get(i).get(1)));
            b.add(new JRadioButton(optionsOuter.get(i).get(2)));
            c.add(new JRadioButton(optionsOuter.get(i).get(3)));
            a.get(i).setActionCommand(optionsOuter.get(i).get(1));
            b.get(i).setActionCommand(optionsOuter.get(i).get(2));
            c.get(i).setActionCommand(optionsOuter.get(i).get(3));
            answersGroup.add(new ButtonGroup());
            answersGroup.get(i).add(a.get(i));
            answersGroup.get(i).add(b.get(i));
            answersGroup.get(i).add(c.get(i));
         
        }
        submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int correctAns =0;
                boolean flag = false;
                for (int i = 0 ; i<5 ; ++i) {
                    if(answersGroup.get(i).getSelection() != null) {
                        if(answersGroup.get(i).getSelection().getActionCommand().equals(optionsOuter.get(i).get(0))) {
                            ++correctAns;                           
                        }
                        flag = true;
                
                    }
                    else {
                        JOptionPane.showMessageDialog(null , "Please fill in all answers.");
                        flag = false;
                        break;
                    }
                    
                }
                if(flag) {
                    try {
                        System.out.println("CA = " + correctAns);
                        testResult = new TestResult(correctAns);
                    } catch (SQLException ex) {
                        Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    testResult.setVisible(true);
                    close();
                }    
            }
	});
        try {
            final Image bgImage = javax.imageio.ImageIO.read(getClass().getResource("images//bb1.jpg"));
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
        setSize(800 , 623);
        setVisible(false);
	GridBagConstraints gbc = new GridBagConstraints();
	int k = 1;
        for (int i=0 ; i<5 ; ++i) {
            for (int j=0 ; j<4 ; ++j) {
                gbc.gridx = 1;
                gbc.gridy = k;
                gbc.weightx = 1;
                gbc.weighty = 0.01;
                gbc.anchor = GridBagConstraints.NORTH;
                if(j==0) add(questions.get(i) , gbc);
                else if(j==1) add(a.get(i) , gbc);
                else if(j==2) add(b.get(i) , gbc);
                else if(j==3) add(c.get(i) , gbc);
                ++k;
            }
        }
	
	gbc.gridx = 1;
	gbc.gridy = k++;
        gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.fill = GridBagConstraints.NONE;
	add(submit , gbc);
	        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
}