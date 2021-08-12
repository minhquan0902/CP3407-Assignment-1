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
import java.sql.PreparedStatement;
import java.sql.Statement;
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
        // Info Panel
        JLabel info = new JLabel("Taking in your credentials help us save your data for future tracing");
        info.setBounds(10,50, 50, 180);
        add(info);
        // Login panel
        JLabel userLabel = new JLabel("Your full name: ");
        userLabel.setBounds(10,20,80,25);
        add(userLabel);
        JTextField usernameText = new JTextField(20);
        usernameText.setBounds(100,20,165,25);
        add(usernameText);
        setVisible(true);

        // Login panel
        JLabel emailLable = new JLabel("Your Email add: ");
        emailLable.setBounds(10,20,80,25);
        add(emailLable);
        JTextField emailText = new JTextField(20);
        emailText.setBounds(100,20,165,25);
        add(emailText);
        setVisible(true);

        // Weight panel
        JLabel weightLabel = new JLabel("Please Input your Weight (in kg): ");
        weightLabel.setBounds(10,20,80,25);
        add(weightLabel);
        JTextField weightText = new JTextField(20);
        weightText.setBounds(100,20,165,25);
        add(weightText);
        setVisible(true);

        // Height panel
        JLabel heightLabel = new JLabel("Please Input your Height (in m): ");
        heightLabel.setBounds(10,20,80,25);
        add(heightLabel);
        JTextField heightText = new JTextField(20);
        heightText.setBounds(100,20,165,25);
        add(heightText);
        setVisible(true);

        // Age input
        JLabel Age = new JLabel("Please Input your Age (in number): ");
        Age.setBounds(20,20,100,25);
        add(Age);
        JTextField AgeNumber = new JTextField(20);
        AgeNumber.setBounds(120,20,200,25);
        add(AgeNumber);
        setVisible(true);

        // Thanks Panel
        JLabel thankYou = new JLabel("Thank you very much for your corporation :)");
        info.setBounds(10,50, 50, 180);
        add(thankYou);






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
                String age = AgeNumber.getText();
                String weight = weightText.getText();
                String height = heightText.getText();

                // Connect and store user data to MYSQL Database
                getConnection(username, email, age, weight, height);

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

    public static Connection getConnection(String username, String email, String age, String weight, String height )  {

        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/InsulinPump"; // Provide different db name if u want to call in a different db
            String dbUsername = "mysql"; //if using localhost, provide your mysql username here
            String dbPassword = "mysql"; // if running localhost, provide your mysql password here
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            System.out.println("Connected to the database successfully");
            String sql = "INSERT INTO persons (username,email,age,weight,height) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3,age);
            statement.setString(4, weight);
            statement.setString(5, height);

            int rows = statement.executeUpdate();
            if (rows >0) {
                System.out.println("Successfully Insert Data to database");
            }
            statement.close();



        }catch(Exception e){
            System.out.println("Oops, error!");
            e.printStackTrace();
        }

        return null;
    }

}