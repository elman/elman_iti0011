package swing_proov;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;


public class MultipleListeners extends JFrame {

    private JLabel statusbar;
    private JSpinner spinner;
    private static int count = 0;
    private JLabel Label1;
    public MultipleListeners() {

        initUI();
    }

    
    // 
    public final void initUI() {

        JPanel panel = new JPanel();
        statusbar = new JLabel("0");

        statusbar.setBorder(BorderFactory.createEtchedBorder(
                EtchedBorder.RAISED));

        panel.setLayout(null);

        Label1 = new JLabel("Image and Text");
        
        
        JButton add = new JButton("+");
        add.setBounds(40, 30, 80, 25);
        add.addActionListener(new ButtonListener1());
        add.addActionListener(new ButtonListener2());

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        SpinnerModel yearModel = new SpinnerNumberModel(currentYear,
                currentYear - 100,
                currentYear + 100,
                1);

        spinner = new JSpinner(yearModel);
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "#"));

        spinner.setBounds(190, 30, 80, 25);
        
        //       x pos, y pos, pikkus, kõrgus
        Label1.setBounds(290, 30, 180, 25);
        panel.add(add);
        panel.add(spinner);
        panel.add(Label1);
        
        add(panel);
        add(statusbar, BorderLayout.SOUTH);

        
        // creates a window
        setTitle("Multiple Listeners");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    } // 

    
    public void paint(Graphics g) {
        // Dynamically calculate size information
        Dimension size = getSize();
        // diameter
        int d = Math.min(size.width, size.height); 
        int x = (size.width - d)/2;
        int y = (size.height - d)/2;

        // draw circle (color already set to foreground)
        g.fillOval(x, y, d, d);
        g.setColor(Color.black);
        g.drawOval(x, y, d, d);
    }
    
    
    
    
    
    
    class ButtonListener1 implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int val =  (Integer) spinner.getValue();
            System.out.println(e);
            spinner.setValue(++val);
        }
    }

    class ButtonListener2 implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            statusbar.setText(Integer.toString(++count));
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                MultipleListeners ml = new MultipleListeners();
                ml.setVisible(true);
            }
        });
    }
}