package view;
import javax.swing.*;

import entity.account.IAccount;
import service.ServiceManager;
import service.account.IAccountService;
import service.user.IUserService;
import view.Customer.NewAccount;
import view.Customer.ViewAccount;
import view.util.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class Option extends ATMWindow{

    private String username;
    private JButton view;
    private JButton create;
    private JButton logout;
    private IAccountService service = ServiceManager.getAccountService();

    public Option(String username) {
        this.username = username;
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());
        view = new JButton("View my account");
        create = new JButton("Create new account");
        logout = new JButton("Log out");
        View.changeFont(view, new Font("Calibri", 3, 33));
        View.changeFont(create, new Font("Calibri", 3, 33));
        View.changeFont(logout, new Font("Calibri", 3, 33));
        p1.add(view);
        p2.add(create);
        p3.add(logout);
        add(p1);
        add(p2);
        add(p3);
        addlisteners();
        super.init();
    }

    @Override
    public void addlisteners() {

        view.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                display();
                
            }

        });

        create.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                create();
                
            }


        });
        logout.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                
            }

        });
    }

    private void display(){
        
        ViewAccount next = new ViewAccount(username);
        dispose();

    }

    private void create(){
        NewAccount next = new NewAccount(username);
        dispose();
        
    }

    private void close(){
        ExistClient next = new ExistClient();
        dispose();
    }
    
    public static void main(String[] args) {
        Option next = new Option("abc");
    }
}
