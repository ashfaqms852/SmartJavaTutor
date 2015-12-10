/*
 *
 * Author : - Ashfaq Sherwa
 *
*/
package all;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    Connection connection = null;
    public static Connection ConnectDB() {
        try{
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ashfa_000\\Documents\\NetBeansProjects\\SmartJavaTutor\\userCredentials.sqlite");
            return connection;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null , e);
            return null;
        }
    }
    
}
