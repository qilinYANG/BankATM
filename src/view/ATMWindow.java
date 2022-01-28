package view;

import javax.swing.*;

import view.util.AddListener;
import view.util.Exit;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public abstract class ATMWindow extends JFrame implements AddListener{

    private JLabel title;
    private JLabel time;
    private JButton exit;
    private JPanel p;

    public ATMWindow() {

        setLayout(new GridLayout(4,1));
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setVisible(true);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel p = new JPanel(new GridLayout(1,3));
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());

        title = new JLabel("Bank of Scar");
        title.setFont(new Font("Calibri",1,30));

        //p2.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        p1.add(title);
        
        SimpleDateFormat sdf = new SimpleDateFormat();
        String t = sdf.format(new Date());
        t = "Current time: " + t;
        time = new JLabel(t);
        time.setFont(new Font("Calibri",1,20));
        p2.add(time);

        exit = new JButton("Exit");
        exit.setFont(new Font("Calibri",1,30));
        p3.add(exit);
        
        p.add(p2);
        p.add(p1);
        p.add(p3);
        add(p);

        addlistener();
        
    }

    public void init(){
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

  
    public void addlistener() {
        exit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                confirm();
            }
            
        });
        
    }

    private void confirm(){
        Exit exit = new Exit(this);
    }

    @Override
    public void addlisteners() {
        // TODO Auto-generated method stub
        
    }
    
}
