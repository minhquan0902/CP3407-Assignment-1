package bin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class InsulinGlucagonPump extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final Component PanelManual = null;

    public InsulinGlucagonPump() {

        JButton Manually;
        JButton Automatic;
       
        
        setTitle("Insulin/Glucagon Pump");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("E:/programm/InsulinGlucagon/image/insulin.jpg")));
        setLayout(new FlowLayout());
        
        
        Automatic = new JButton("Start the Simulator");

        

     
        add(Automatic);
      
      
        // Just for refresh :) Not optional!
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

}