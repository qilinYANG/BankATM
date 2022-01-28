package view.util;

import javax.swing.*;

import service.ServiceManager;
import service.account.AccountService;
import service.account.IAccountService;
import service.user.IUserService;
import service.user.UserService;
import view.Customer.ViewAccount;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseA extends JDialog{
    private String accountnumber;
    private IAccountService service1 =  ServiceManager.getAccountService();
    private IUserService service2 =  ServiceManager.getUserService();;
    private String username;
    private Window owner;
    private JLabel message1;
    private JButton yes;
    private JButton no;
    private JPanel p1;
    private JPanel p2;

    public CloseA(Window owner, String user, String num) {

        super(owner);
        this.owner = owner;
        username = user;
        accountnumber = num;
        setLayout(new GridLayout(2,1));
        setSize(600, 600);
        setVisible(true);
        setModal(true);
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        message1 = new JLabel("Are you sure to close this account?");
        View.changeFont(message1, new Font("Calibri", 2, 32));
     
        p1.add(message1);
       
        yes = new JButton("Yes");
        View.changeFont(yes, new Font("Calibri", 1, 32));
        no = new JButton("No");
        View.changeFont(no, new Font("Calibri", 1, 32));
        p2.add(yes);
        p2.add(no);
        add(p1);
        add(p2);
        addlisteners();
        
    }


    public void addlisteners() {
        yes.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                closeaccount();
                
            }
            
        });

        no.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                back();
                
            }

        });
        
    }

    private void closeaccount(){
        if(service1.closeAccount(accountnumber))
        {
            ViewAccount next = new ViewAccount(username);
            this.dispose();
            owner.dispose();
        }else{
        Invalid next = new Invalid(owner,"Invalid operation");
        this.dispose();
        }
    }

    private void back(){
        this.dispose();
    }
    
}
