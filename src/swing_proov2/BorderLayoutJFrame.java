package swing_proov2;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class BorderLayoutJFrame extends JFrame
{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;

    public BorderLayoutJFrame( )
    {
        super("BorderLayout Demonstration" );
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel label1 = new JLabel("NORTH label");
        add(label1, BorderLayout.NORTH); 

        JLabel label2 = new JLabel("SOUTH label");
        add(label2, BorderLayout.SOUTH); 

        JLabel label3 = new JLabel("CENTER label");
        add(label3, BorderLayout.CENTER); 
        
        JLabel label4 = new JLabel("EAST label");
        add(label4, BorderLayout.EAST); 
        
        JLabel label5 = new JLabel("WEST label");
        add(label5, BorderLayout.WEST);
    }
}

