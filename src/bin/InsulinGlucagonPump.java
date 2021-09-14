package bin;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Objects;
import javax.swing.*;


public class InsulinGlucagonPump extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final Component PanelManual = null;

    public InsulinGlucagonPump() {

        JButton Automatic;
        JButton RegisterButton;
       
        
        setTitle("Insulin/Glucagon Pump");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());

        setLayout(new FlowLayout());



        Automatic = new JButton("Start the Simulator");
        RegisterButton = new JButton ("Register for an account if you hadn't had one :)");
        // Info Panel
        JLabel info = new JLabel("Taking in your credentials help us save your data for future tracing");
        info.setBounds(10,50, 50, 180);
        add(info);
        // Login panel
        JLabel userLabel = new JLabel("Input Username ");
        userLabel.setBounds(10,20,80,25);
        add(userLabel);
        JTextField usernameText = new JTextField(20);
        usernameText.setBounds(100,20,165,25);
        add(usernameText);
        setVisible(true);

        // Login panel
        JLabel passwordLabel = new JLabel("Input Password ");
        passwordLabel.setBounds(10,20,100,25);
        add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,20,165,25);
        add(passwordText);
        setVisible(true);

        add(Automatic);
        setSize(399, 399);
        setSize(400, 400);

        add(RegisterButton);
        setSize(399, 399);
        setSize(400, 400);


        RegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterForm();
            }
        });

        Automatic.addActionListener(new ActionListener() {
            
                @Override
            public void actionPerformed(ActionEvent e) {
               

                String username = usernameText.getText();
                String password = passwordText.getText();



                // Login
                Boolean loginState = login(username, password, false);

                if (loginState){
                    new BloodSugar();
                    new Monitor();
                    new GUI("Auto");
                    JFrame g = new JFrame();
                    JOptionPane.showMessageDialog(g,"Login successfully :) ");
                }else {
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f,"Error Login in, check your credentials again :(","Alert",JOptionPane.WARNING_MESSAGE);

                }
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

    public static Boolean login(String username, String password, Boolean status )  {

        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/InsulinPump"; // Provide different db name if u want to call in a different db
            String dbUsername = "mysql"; //if using localhost, provide your mysql username here
            String dbPassword = "mysql"; // if running localhost, provide your mysql password here
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            System.out.println("Connected to the database successfully");

            Statement stmt = conn.createStatement();
            String sql = String.format( "SELECT password from users WHERE username = '%s'", username);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                String passwordHash = rs.getString("password");
                System.out.println(passwordHash);

                String hashPassword = MD5Hash.HashPassword(password);

                // compare two hash passwords for login

                // Login confirmed
                status = Objects.equals(passwordHash, hashPassword);


            }





        }catch(Exception e){
            System.out.println("Oops, error!");
            JFrame g = new JFrame();
            JOptionPane.showMessageDialog(g,"Oops, Error, check your connection again :(","Alert",JOptionPane.WARNING_MESSAGE);

            e.printStackTrace();
        }

        return status;
    }

}