package view.util;

import javax.swing.*;

import entity.Balance;
import service.ServiceManager;
import service.account.IAccountService;
import service.transaction.ITransactionService;
import service.user.IUserService;
import view.Customer.ViewAccount;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Currency;
import java.util.Vector;

public class Deposit extends JDialog implements AddListener{
    private JLabel message1;
    private JLabel message2;
    private JLabel message3;
    private JComboBox<Currency> currency;
    private JTextField amount;
    private JButton confirm;
    private JButton cancel;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private Window owner;
    private String accountnum;
    private String username;
    private IAccountService service = ServiceManager.getAccountService();
    private ITransactionService service1 = ServiceManager.getTransactionService();

    public Deposit(Window owner, String user, String account) {

        super(owner);
        this.accountnum = account;
        username = user;
        this.owner = owner;
        setLayout(new GridLayout(4,1));
        setSize(800, 800);
        setVisible(true);
        setModal(true);
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());
        p4 = new JPanel(new FlowLayout());
        message1 = new JLabel(service.getAccount(accountnum).toString());
        message2 = new JLabel("Deposit currency: ");
        message3 = new JLabel("Deposit amount: ");
        currency = new JComboBox<>(new Vector<Currency>(service1.getSupportedCurrencyTypes()));
        amount = new JTextField(8);
        confirm = new JButton("Confirm");
        cancel = new JButton("Cancel");

        p1.add(message1);
        p2.add(message2);
        p2.add(currency);
        p3.add(message3);
        p3.add(amount);
        p4.add(confirm);
        p4.add(cancel);
     
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        View.changeFont(p1);
        View.changeFont(p2);
        View.changeFont(p3);
        View.changeFont(p4);
        addlisteners();
        
    }

    @Override
    public void addlisteners() {
        confirm.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
                
            }
            
        });
        
        cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                
            }

        });
    }

    private void deposit(){
        Currency c = (Currency) currency.getSelectedItem();
        int a = Integer.valueOf(amount.getText());

        if(service1.deposit(accountnum, new Balance(c, a))){
            this.dispose();
            owner.dispose();
            ViewAccount next = new ViewAccount(username);
        }else{
            Invalid next = new Invalid(owner, "Operation Failed");
            this.dispose();
        }
    }

    private void close(){
        this.dispose();
    }
}
