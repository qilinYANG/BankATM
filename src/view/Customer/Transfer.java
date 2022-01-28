package view.Customer;

import java.util.Currency;
import java.util.Vector;
import java.util.List;

import entity.Balance;
import entity.account.IAccount;
import entity.user.ATMAdmin;
import service.ServiceManager;
import service.account.IAccountService;
import service.transaction.ITransactionService;
import service.transaction.TransactionService;
import view.ATMWindow;
import view.util.Invalid;
import view.util.View;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Transfer extends ATMWindow{

    private String username;
    private JLabel lfrom;
    private JComboBox<IAccount> from;
    private JLabel lto;
    private JComboBox<IAccount> to;
    private JLabel lamount;
    private JTextField amount;
    private JLabel lcurrency;
    private JComboBox<Currency> currency;
    private JButton confirm;
    private JButton cancel;
    private Font font =  new Font("Calibri", 3, 33);
    private ITransactionService service1 = ServiceManager.getTransactionService();
    private IAccountService service2 = ServiceManager.getAccountService();

    public Transfer(String user) {

        username = user;
        lfrom = new JLabel("From account: ");
        from = new JComboBox<IAccount>(new Vector<IAccount>(service2.getAllAccounts(username)));
        from.setSelectedItem(null);
        lto = new JLabel("To account: ");
        to = new JComboBox<IAccount>(new Vector<IAccount>(service2.getAllAccounts(username)));
        to.setSelectedItem(null);
        lamount = new JLabel("Amount: ");
        amount = new JTextField(10);
        lcurrency = new JLabel("Currency: ");
        
        Vector<Currency> list = new Vector<>(service1.getSupportedCurrencyTypes());
        currency = new JComboBox<>(list);
        confirm = new JButton("Confirm");
        cancel = new JButton("cancel");
        JPanel p5 = new JPanel(new FlowLayout());
        JPanel p6 = new JPanel(new FlowLayout());
        JPanel p7 = new JPanel(new FlowLayout());

        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        p1.add(lfrom);
        p1.add(from);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        p2.add(lto);
        p2.add(to);
        p5.add(p1);
        p5.add(p2);

        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        p3.add(lcurrency);
        p3.add(currency);
        p4.add(lamount);
        p4.add(amount);
        p6.add(p3);
        p6.add(p4);

        p7.add(confirm);
        p7.add(cancel);
        add(p5);
        add(p6);
        add(p7);
        View.changeFont(p5, font);
        View.changeFont(p6, font);
        View.changeFont(p7, font);
        addlisteners();
        super.init();
    }

    @Override
    public void addlisteners() {
        confirm.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                transfer();
                
            }
            
        });
        cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                back();
                
            }

        });
    }

    private void transfer(){
        String f = ((IAccount) from.getSelectedItem()).getAccountNumber();
        String t = ((IAccount) to.getSelectedItem()).getAccountNumber();
        Currency c = (Currency) currency.getSelectedItem();
        double a = Integer.valueOf(amount.getText());
        Balance b = new Balance(c, a);
        if(service1.transfer(f, t, b)){
            this.dispose();
            ViewAccount next = new ViewAccount(username);
        }else{
            Invalid next = new Invalid(this, "Transaction failed");
        }
    }

    private void back(){
        ViewAccount next = new ViewAccount(username);
        this.dispose();
    }


    public static void main(String[] args) {
        Currency a = Currency.getInstance("USD");
        Currency b = Currency.getInstance("EUR");
        Currency c = Currency.getInstance("AUD");
        Vector<Currency> d = new Vector<>();
        d.add(a);d.add(b);d.add(c);
        JComboBox<Currency> box = new JComboBox<>(d);
        JFrame frame = new JFrame();
        JPanel pane = new JPanel(new FlowLayout());
        JLabel lcurrency = new JLabel("Currency: ");
        pane.add(lcurrency);
        pane.add(box);

        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(pane);

    }
}
