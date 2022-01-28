package view.util;

import javax.swing.*;

import view.Option;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Congrats extends JDialog implements AddListener{
    private JLabel message1;
    private JLabel message2;
    private JButton next;
    private JPanel p1;
    private JPanel p2;
    private Window owner;
    private String id;

    public Congrats(Window owner, String type, String id) {

        super(owner);
        this.id = id;
        this.owner = owner;
        setLayout(new GridLayout(2,1));
        setSize(600, 600);
        setVisible(true);
        setModal(true);
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

        p1 = new JPanel(new FlowLayout(FlowLayout.CENTER,150,30));
        p2 = new JPanel(new FlowLayout());
        message1 = new JLabel("Congratulations!");
        message2 = new JLabel( type + " has been created!");
        
        View.changeFont(message1, new Font("Calibri", 2, 32));
        View.changeFont(message2, new Font("Calibri", 2, 32));
        p1.add(message1);
        p1.add(message2);
        next = new JButton("Next");
        View.changeFont(next, new Font("Calibri", 1, 32));
        p2.add(next);
        add(p1);
        add(p2);
        addlisteners();
        
    }

    @Override
    public void addlisteners() {
        next.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Option next = new Option(id);
                close();
            }
            
        });
        
    }

    private void close(){
        this.dispose();
        owner.dispose();
    }
}
