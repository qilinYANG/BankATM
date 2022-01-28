package view.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.account.IAccount;
import entity.transaction.ITransaction;
import entity.user.ATMAdmin;
import service.ServiceManager;
import service.account.IAccountService;
import service.manager.IManagerReviewService;
import view.ATMWindow;
import view.util.View;

public class Manager extends ATMWindow{
    private JButton setstock;
    private JButton checkaccount;
    private JButton setrate;
    private JButton transaction;
    private JButton logout;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;

    public Manager() {
        this.setstock = new JButton("Stock settings");
        this.checkaccount = new JButton("Check Account info");
        this.setrate = new JButton("Interst settings");
        this.transaction = new JButton("View Transaction Records");
        this.logout = new JButton("Log out");
        this.p1 = new JPanel(new FlowLayout());
        this.p2 = new JPanel(new FlowLayout());
        this.p3 = new JPanel(new FlowLayout());


        View.changeFont(p1);
        View.changeFont(p2);
        View.changeFont(p3);
        add(p1);
        add(p2);
        add(p3);

        addlisteners();
        super.init();
    }
    @Override
    public void addlisteners() {
        setstock.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                setstock();
                
            }
            
        });

        setrate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                setrate();
                
            }
            
        });

        checkaccount.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                viewaccount();;
                
            }
            
        });
        transaction.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                viewtrans();;
                
            }
            
        });

        logout.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                logout();;
                
            }
            
        });

    }

    private void setstock(){
        SetStock next = new SetStock(this);
    }

    private void setrate(){
        SetRate next = new SetRate(this);

    }
    private void viewaccount(){

    }
    private void viewtrans(){

    }

    private void logout(){

    }


    
}
