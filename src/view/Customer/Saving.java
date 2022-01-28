package view.Customer;
import javax.swing.*;

import entity.Balance;
import entity.user.Customer;
import service.ServiceManager;
import service.account.IAccountService;
import service.user.IUserService;
import service.user.UserService;
import view.ATMWindow;
import view.util.CloseA;
import view.util.Deposit;
import view.util.History;
import view.util.View;
import view.util.Withdraw;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class Saving extends ATMWindow{
    private JLabel title;
    private JList<String> details = new JList<>();
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JScrollPane pane;
    private JButton deposit;
    private JButton withdraw;
    private JButton history;
    private JButton back;
    private JButton close;
    private String username;
    private String accountnum;
    private IUserService service1 =  ServiceManager.getUserService();
    private IAccountService service2 = ServiceManager.getAccountService();

    public Saving(String user, String account) {
        accountnum = account;
        username = user;
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());

        String t = service2.getAccount(accountnum).toString();
        title = new JLabel(t);
        p1.add(title);

        addDetails();
        pane = new JScrollPane(details);
        p2.add(pane);

        deposit = new JButton("Deposit");
        withdraw = new JButton("Withdraw");
        history = new JButton("Transaction History");
        back = new JButton("Back");
        close = new JButton("Close Account");
        p3.add(deposit);
        p3.add(withdraw);
        p3.add(back);
        add(p1);
        add(p2);
        add(p3);
        View.changeFont(p1);
        View.changeFont(p2);
        View.changeFont(p3);
        super.init();
        addlisteners();
    }

    private void addDetails(){
        Collection<Balance> list = service2.getDepositBalances(accountnum);
        for(Balance a : list){
            model.addElement(a.toString());
        }
        
        details.setModel(model);
        
    }

    @Override
    public void addlisteners() {
        deposit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
            
        });

        withdraw.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
            
        });
        back.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                back();
                
            }
            
        });

        close.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }

        });

        history.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                history();
            }

        });
        
    }

    private void history(){
        History next = new History(this, username, accountnum);
    }

    private void deposit(){
        Deposit next = new Deposit(this,username, accountnum);

    }

    private void withdraw(){
        Withdraw next = new Withdraw(this,username, accountnum);
    }

    private void back(){
        ViewAccount next = new ViewAccount(username);
        dispose();
    }

    private void close(){
        CloseA next = new CloseA(this,username, accountnum);
        
    }
    
}
