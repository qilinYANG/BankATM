package view.util;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.Balance;
import service.ServiceManager;
import service.account.IAccountService;
import service.transaction.ITransactionService;
import service.user.IUserService;
import view.Customer.ViewAccount;

public class PayLoan extends JDialog{
    private String accountnumber;
    private String username;

    private ITransactionService service1 =  ServiceManager.getTransactionService();
    private IAccountService service2 =  ServiceManager.getAccountService();
    
    private Window owner;
    private JLabel title;
    private JLabel pay;
    private JTextField amount;
    private JButton contin;
    private JButton cancel;
    private Font font = new Font("Calibri", 1,33);
    public PayLoan(Window own, String user, String account) {
        super(own);
        owner = own;
        accountnumber = account;
        
        username = user;
        setLayout(new GridLayout(3,1));
        setSize(600, 600);
        setVisible(true);
        setModal(true);
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );


        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());

        String t = service2.getAccount(accountnumber).toString();
        title = new JLabel(t);
        p1.add(title);

        pay = new JLabel("Pay amount: ");
        amount = new JTextField(8);
        p2.add(pay);
        p2.add(amount);

        contin = new JButton("Continue");
        cancel = new JButton("Cancel");
        p3.add(contin);
        p3.add(cancel);
        View.changeFont(p1, font);
        View.changeFont(p2, font);
        View.changeFont(p3, font);
        addlisteners();
        
    }


    public void addlisteners() {
        cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               back();
                
            }
            
        });

        contin.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                next();
                
            }

        });

    }

    private void back(){
        this.dispose();
    }
    
    private void next(){
        int payamount = Integer.valueOf(amount.getText());
        if(service1.payLoan(accountnumber, new Balance(payamount)))
        {
            
            ViewAccount next = new ViewAccount(username);
            this.dispose();
            owner.dispose();
    }else{
        this.dispose();
        Invalid next = new Invalid(owner, "Transaction Failed");
    }

    }
}
