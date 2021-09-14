package bin;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;

public class RegisterForm extends JFrame {

    public RegisterForm() {
        JButton SubmitButton;
        JButton BackButton;
        setTitle("Register/Insulin Glucagon Pump");
        setSize(800, 2000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());

        setLayout(new FlowLayout());

        SubmitButton = new JButton("Submit and Register!");
        // Info Panel
        JLabel info = new JLabel("Register your account for future health tracing progress :)");
        info.setBounds(10,50, 50, 180);
        add(info);

        BackButton = new JButton("Cancel");


        // Login panel
        JLabel userLabel = new JLabel("Please input your username: ");
        userLabel.setBounds(10,20,80,25);
        add(userLabel);
        JTextField usernameText = new JTextField(20);
        usernameText.setBounds(100,20,165,25);
        add(usernameText);
        setVisible(true);

        JLabel passwordLabel = new JLabel("Please input your password: ");
        passwordLabel.setBounds(10,20,80,25);
        add(passwordLabel);
        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100,20,165,25);
        add(passwordText);
        setVisible(true);



        JLabel confirmPassword = new JLabel("Please input confirm password: ");
        confirmPassword.setBounds(10,20,80,25);
        add(confirmPassword);
        JPasswordField confirmPasswordText = new JPasswordField(20);
        confirmPasswordText.setBounds(100,20,165,25);
        add(confirmPasswordText);
        setVisible(true);

        // Email pannel
        JLabel emailLable = new JLabel("Please Input email address: ");
        emailLable.setBounds(10,20,100,25);
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


        add(SubmitButton);
        setSize(399, 399);
        setSize(400, 400);


        add(BackButton);
        setSize(399, 399);
        setSize(400, 400);

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        SubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = passwordText.getText();
                String confirmpassword = confirmPasswordText.getText();
                String email = emailText.getText();
                String age = AgeNumber.getText();
                String weight = weightText.getText();
                String height = heightText.getText();
                JFrame f;

                if (!Objects.equals(password, confirmpassword)){
                    f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Password does not match","Alert",JOptionPane.WARNING_MESSAGE);

                }else if (!email.contains("@")){
                    f=new JFrame();
                    JOptionPane.showMessageDialog(f,"Invalid Email Address","Alert",JOptionPane.WARNING_MESSAGE);

                } else{
                    getConnection(username, password, email, age, weight, height);
                }




            }
        });
    }

    public static Connection getConnection(String username, String password, String email, String age, String weight, String height){


        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/InsulinPump"; // Provide different db name if u want to call in a different db
            String dbUsername = "mysql"; //if using localhost, provide your mysql username here
            String dbPassword = "mysql"; // if running localhost, provide your mysql password here
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            System.out.println("Connected to the database successfully");

            // Hash the Password before storing it to db for security purposes
            String hashPassword = MD5Hash.HashPassword(password);
            System.out.println("Hash Password: "+ hashPassword);



            // Execute SQL Queries to store data to db
            String sql_users = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement_users = conn.prepareStatement(sql_users);
            statement_users.setString(1, username);
            statement_users.setString(2, hashPassword);
            statement_users.setString(3, email);

            int rows_1 = statement_users.executeUpdate();
            if (rows_1 >0) {
                System.out.println("Successfully Insert Data to users");
            }
            statement_users.close();

            String sql_user_details = "INSERT INTO users_detail (username,email,age,weight,height) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement_users_detail = conn.prepareStatement(sql_user_details);
            statement_users_detail.setString(1, username);
            statement_users_detail.setString(2, email);
            statement_users_detail.setString(3,age);
            statement_users_detail.setString(4, weight);
            statement_users_detail.setString(5, height);

            int rows = statement_users_detail.executeUpdate();
            if (rows >0) {
                System.out.println("Successfully Insert Data to users_detail");
            }
            statement_users_detail.close();


            JFrame g = new JFrame();
            JOptionPane.showMessageDialog(g,"User created successfully :) ");









        }catch(Exception e){
            JFrame f = new JFrame();
            System.out.println("Oops, error!");

            if (e.toString().contains("users.username")){
                JOptionPane.showMessageDialog(f,"Username is already taken!","Alert",JOptionPane.WARNING_MESSAGE);
            }
            if (e.toString().contains("users_detail.email")){
                JOptionPane.showMessageDialog(f,"email is already taken!","Alert",JOptionPane.WARNING_MESSAGE);
            }


            e.printStackTrace();

        }

        return null;
    }
}
