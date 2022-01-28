package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class WelcomeClient extends ATMWindow{
    public static Font font = new Font("Calibri", 2,40);
    private JButton newclient;
    private JButton exclient;

    public WelcomeClient() {

        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());

        newclient = new JButton("New Client");
        newclient.setFont(font);
        p2.add(newclient);
        exclient = new JButton("Existing Client");
        exclient.setFont(font);
        p3.add(exclient);
        add(p2);
        add(p3);
        addlisteners();
        super.init();
    }

    @Override
    public void addlisteners() {
    newclient.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            NewClient next = new NewClient();
            dispose();
        }
        
    });

    exclient.addActionListener(new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            ExistClient next = new ExistClient();
            dispose();
            
        }

    });
    }

    public static void main(String[] args) {
        WelcomeClient a = new WelcomeClient();
    }

    
}
