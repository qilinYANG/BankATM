package view.util;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit extends JDialog implements AddListener{

    private JButton confirm;
    private JButton cancle;
    private JLabel message;
    private Window owner;

    public Exit(Window owner) {
        this.owner = owner;
        setLayout(new GridLayout(2,1));
        //setUndecorated(true);
        //getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setSize(600, 600);
        setVisible(true);
        setModal(true);
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
        
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());

        message = new JLabel("Are you sure to exit?");
        message.setFont(new Font("Calibri", 2, 33));
        p1.add(message);
        confirm = new JButton("Confirm");
        confirm.setFont(new Font("Calibri", 2, 23));
        cancle = new JButton("Back");
        cancle.setFont(new Font("Calibri", 2, 23));
        p2.add(confirm);
        p2.add(cancle);

        add(p1);
        add(p2);
        addlisteners();
    }

    @Override
    public void addlisteners() {
        confirm.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }
        });

        cancle.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
    }

    private void close(){
        this.dispose();
        owner.dispose();
    }

    private void back(){
        this.dispose();
    }

}
