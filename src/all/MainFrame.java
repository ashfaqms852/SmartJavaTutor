/*
 *
 * Author : - Ashfaq Sherwa
 *
*/

package all;

import java.awt.BorderLayout;
import javax.swing.JFrame;
public class MainFrame extends JFrame {
    private Welcome welcome;
    public MainFrame() {
        super("Java Smart Tutor");
        setLayout(new BorderLayout());
        welcome = new Welcome();
        welcome.setVisible(true);
        add(welcome , BorderLayout.CENTER);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    
}
