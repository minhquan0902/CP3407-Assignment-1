package bin;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;


public class InsulinGlucagonPump extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final Component PanelManual = null;

    public InsulinGlucagonPump() {

        JButton Automatic;
       
        
        setTitle("Insulin/Glucagon Pump");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());

        setLayout(new FlowLayout());



        Automatic = new JButton("Start the Simulator");

        JLabel info = new JLabel("Taking in your credentials help us save your data for future tracing");
        info.setBounds(10,50, 100, 180);
        add(info);
        // Login panel
        JLabel userLabel = new JLabel("Please Input your name: ");
        userLabel.setBounds(10,20,80,25);
        add(userLabel);
        JTextField usernameText = new JTextField(20);
        usernameText.setBounds(100,20,165,25);
        add(usernameText);
        setVisible(true);

        // Login panel
        JLabel emailLable = new JLabel("Please Input your email: ");
        emailLable.setBounds(10,20,80,25);
        add(emailLable);
        JTextField emailText = new JTextField(20);
        emailText.setBounds(100,20,165,25);
        add(emailText);
        setVisible(true);




        

        add(Automatic);
      
      

        setSize(399, 399);
        setSize(400, 400);
       

        Automatic.addActionListener(new ActionListener() {
            
                @Override
            public void actionPerformed(ActionEvent e) {
               
                new BloodSugar();
                new Monitor();
                new GUI("Auto");
                String username = usernameText.getText();
                String email = emailText.getText();

                // Connect and store user data to MYSQL Database
                getConnection(username, email);

                }     
        });


        
      
        
   }
    public void close(){
        WindowEvent windClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windClosingEvent);
        
    }
 
    public static void main(String args[]) {

        new InsulinGlucagonPump();

    }

    public static Connection getConnection(String username, String email )  {

        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/InsulinPump"; // Provide different db name if u want to call in a different db
            String dbUsername = "mysql"; //if using localhost, provide your mysql username here
            String dbPassword = "mysql"; // if running localhost, provide your mysql password here
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            System.out.println("Connected");


            return conn;

        }catch(Exception e){
            System.out.println("Oops, error!");
            e.printStackTrace();
        }

        return null;
    }

}