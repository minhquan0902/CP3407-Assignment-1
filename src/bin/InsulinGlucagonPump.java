package bin;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



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

        

        add(Automatic);
      
      

        setSize(399, 399);
        setSize(400, 400);
       

        Automatic.addActionListener(new ActionListener() {
            
                @Override
            public void actionPerformed(ActionEvent e) {
               
                new BloodSugar();
                new Monitor();
                new GUI("Auto");
                
           
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

    public static Connection getConnection() throws Exception {

        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/InsulinPump";
            String username = "hey";

        }catch(Exception e){
            return null;
        }

        return null;
    }

}