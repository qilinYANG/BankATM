package view;

import javax.swing.*;


import java.awt.*;
import java.awt.Font;
import java.awt.event.*;


public class Welcome extends ATMWindow{

    private JButton contin;

    private JLabel message;

    public Welcome() {
      
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());
        
        message = new JLabel("Welcome to Bank of Scar!");
        message.setFont(new Font("Calibri",3,50));
        p2.add(message);

        contin = new JButton("Continue");
        contin.setFont(new Font("Calibri", 2,40));
        p3.add(contin);
      

        add(p2);
        add(p3);
      

        addlisteners();
        super.init();
    }

    public void addlisteners(){
        contin.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeClient next = new WelcomeClient();
                dispose();
            }

        });
    }

    public static void main(String[] args) {
    
        Welcome start = new Welcome();
    }
}


    

