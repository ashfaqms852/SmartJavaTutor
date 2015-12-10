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
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StudyMaterial_5 extends JFrame {
    private JButton next;
    private JButton previous;
    private ImageIcon image;
    private JLabel imageLabel;
    private StudyMaterial_4 studyMaterial_4;
    static long time_last;
    private JButton video;
    private Video v;
    public StudyMaterial_5() {
        super("StudyMaterials-Slides");     
        setLayout(new BorderLayout());
        next = new JButton("Next");
        previous = new JButton("Previous");
        video = new JButton("Video");
        switch(Login.currT) {
            case 1 : image = new ImageIcon(getClass().getResource("images//1-5.jpg"));
                    break;
            case 2 : image = new ImageIcon(getClass().getResource("images//2-5.jpg"));
                    break;
            case 3 : image = new ImageIcon(getClass().getResource("images//3-5.jpg"));
                    break;
            default : break;    
        }
        imageLabel = new JLabel(image);
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                studyMaterial_4 = new StudyMaterial_4();
                studyMaterial_4.setVisible(true);
                close();
            }
        });
        
        video.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                time_last = (long)(System.currentTimeMillis()/1000);
                long timeDiff = time_last - StudyMaterial_1.time_first;
                if (timeDiff > 10 && (Login.currD == 2 || Login.currD == 3)) Login.currD = 1;
                else if (timeDiff <=10 && timeDiff > 5 && Login.currD == 3) Login.currD = 2;
                try {
                    v = new Video();
                    v.setVisible(true);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(StudyMaterial_5.class.getName()).log(Level.SEVERE, null, ex);
                }
                close();
            }
        });
        
        getContentPane().setBackground(Color.white);
        next.setEnabled(false);
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
	add(previous , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 3;
	gbc.weightx = 1;
	gbc.weighty = 0.05;
	gbc.anchor = GridBagConstraints.NORTH;
	add(video , gbc);
    }
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }

}
