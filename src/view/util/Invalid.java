package view.util;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Invalid extends JDialog implements AddListener{

    private JLabel message1;
    private JLabel message2;
    private JButton ok;
    private JPanel p1;
    private JPanel p2;

    public Invalid(Window owner, String a) {

        super(owner);
        setLayout(new GridLayout(2,1));
        setSize(600, 600);
        setVisible(true);
        setModal(true);
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER,150,30));
        p2 = new JPanel(new FlowLayout());
        message1 = new JLabel( a + "!");
        message2 = new JLabel ("Please try again!");
        View.changeFont(message1, new Font("Calibri", 1, 32));
        View.changeFont(message2, new Font("Calibri", 1, 32));
        p1.add(message1);
        p1.add(message2);
        ok = new JButton("OK");
        View.changeFont(ok, new Font("Calibri", 1, 32));
        p2.add(ok);
        add(p1);
        add(p2);
        addlisteners();
        
    }

    @Override
    public void addlisteners() {
        ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                
            }
            
        });
        
    }

    private void close(){
        this.dispose();
    }
    
    
}
