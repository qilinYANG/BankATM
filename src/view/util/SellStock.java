package view.util;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Vector;

import javax.swing.*;

import entity.Stock;
import service.ServiceManager;
import service.account.AccountService;
import service.account.IAccountService;
import service.transaction.ITransactionService;
import service.user.UserService;

public class SellStock extends JDialog implements AddListener{
    private String accountnumber;
    private IAccountService service1 =  ServiceManager.getAccountService();
    private ITransactionService service2 =  ServiceManager.getTransactionService();;

    private String username;
    private Window owner;
    private JLabel message1;
    private JLabel message2;
    private JLabel message3;

    private JComboBox<Stock> purchase;

    private JTextField amount;

  
    private JButton confirm;
    private JButton back;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;


    public SellStock(Window own, String user, String account) {
        super(own);
        owner = own;
        username = user;
        accountnumber = account;
        setLayout(new GridLayout(3,1));
        setSize(800, 800);
        setVisible(true);
        setModal(true);
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());

        message1 = new JLabel("Sell  ");
        purchase = new JComboBox<>(new Vector<Stock>(service1.getHoldingStocks(accountnumber)));
        purchase.setSelectedItem(null);
        p1.add(message1);
        p1.add(purchase);

        message2 = new JLabel("Shares to sell: ");
        amount = new JTextField(5);
        p2.add(message2);
        p2.add(amount);

        confirm = new JButton("Confirm");
        back = new JButton("Back");

        p3.add(confirm);
        p3.add(back);
        
        View.changeFont(p1);
        View.changeFont(p2);
        View.changeFont(p3);
        addlisteners();

        add(p1);
        add(p2);
        add(p3);
        }





    @Override
    public void addlisteners() {
        confirm.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                sell();
                
            }
            
        });

        back.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                
            }

        });
        
       
    }

    private void sell(){
        int count = Integer.valueOf(amount.getText());
        Stock pick = (Stock) purchase.getSelectedItem();

        if(service2.sellStock(accountnumber, pick, count)){
            this.dispose();
        }else{
            this.dispose();
            Invalid next = new Invalid(owner, "Trade failed");
        }

    }

    private void close(){
        this.dispose();
    }

   public static void main(String[] args) {
     
   }
}
