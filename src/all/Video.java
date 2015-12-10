/*
 *
 * Author : - Ashfaq Sherwa
 *
*/
package all;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Video extends JFrame {
    private JFrame frame;
    private Container container;
    private JButton button;
    private JButton testButton;
    private JLabel blank1;
    private JLabel blank2;
    private Test test;
    private String str;
    private final URI uri;
    Video() throws URISyntaxException {
        super("Video Tutorial");
        if(Login.currT == 1) {
            str = "https://www.youtube.com/watch?v=L06uGnF4IpY";
        }
        else if (Login.currT == 2) { 
            str = "https://www.youtube.com/watch?v=OHw2t8BaIUg";
        }
        else if (Login.currT == 3) {
            str = "https://www.youtube.com/watch?v=PAaqgTr7Cx4";
        }
        uri = new URI(str);
        class OpenUrlAction implements ActionListener 
        {
            @Override public void actionPerformed(ActionEvent e) {
            open(uri);
            }
        }
        button = new JButton();
        blank1 = new JLabel("");
        blank2 = new JLabel("");
        testButton = new JButton("Test");
        button.setText("<HTML>Click here for the <FONT color=\"#FFFFFF\"><U>video</U></FONT>");
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setBorderPainted(false);
        button.setOpaque(false);
        button.setBackground(Color.WHITE);
        button.setToolTipText(uri.toString());
        button.addActionListener(new OpenUrlAction());
        testButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                try {
                    test = new Test();
                } catch (SQLException ex) {
                    Logger.getLogger(Video.class.getName()).log(Level.SEVERE, null, ex);
                }
                test.setVisible(true);
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	add(button , gbc);
        
        gbc.gridx = 1;
	gbc.gridy = 4;
	gbc.weightx = 1;
	gbc.weighty = 0.01;
	gbc.anchor = GridBagConstraints.NORTH;
	add(testButton , gbc);
    }
    private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            }
            catch (IOException e) 
            { /* TODO: error handling */ }
        }
        else
        { /* TODO: error handling */ }
    }
    public void close() {
        WindowEvent winClosingEvent = new WindowEvent(this , WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
           
}
