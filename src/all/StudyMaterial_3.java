/*
 *
 * Author : - Ashfaq Sherwa
 *
*/

package all;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StudyMaterial_3 extends JFrame {
    private JButton next;
    private JButton previous;
    private ImageIcon image;
    private JLabel imageLabel;
    private Test test;
    private StudyMaterial_2 studyMaterial_2;
    private StudyMaterial_4 studyMaterial_4;
    public StudyMaterial_3() {
        super("StudyMaterials-Slides");     
        setLayout(new BorderLayout());
        next = new JButton("Next");
        previous = new JButton("Previous");
        switch(Login.currT) {
            case 1 : image = new ImageIcon(getClass().getResource("images//1-3.jpg"));
                    break;
            case 2 : image = new ImageIcon(getClass().getResource("images//2-3.jpg"));
                    break;
            case 3 : image = new ImageIcon(getClass().getResource("images//3-3.jpg"));
                    break;
            default : break;    
        }
        imageLabel = new JLabel(image);
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                studyMaterial_2 = new StudyMaterial_2();
                studyMaterial_2.setVisible(true);
                close();
            }
        });
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                studyMaterial_4 = new StudyMaterial_4();
                studyMaterial_4.setVisible(true);
                close();
            }
        });
        getContentPane().setBackground(Color.white);
        setSize(750, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setVisible(true);
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
		
	gbc.gridx = 1;
	gbc.gridy = 0;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(imageLabel , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 2;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(next , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 3;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(previous , gbc);
    }
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

}
