package view.Customer;

import java.util.Collection;
import java.util.Vector;

import javax.swing.*;

import serverUtil.AccountType;

import java.awt.event.*;
import java.awt.*;

import service.ServiceManager;
import service.account.IAccountService;
import service.user.IUserService;
import view.ATMWindow;
import view.Option;
import view.util.View;

public class NewAccount extends ATMWindow{

    private String username;
    private IAccountService service = ServiceManager.getAccountService();

    private JPanel p1;
    private JPanel p2;
    private JPanel p3;

    private JButton confirm;
    private JButton cancel;
    private JLabel laccounts;
    private JLabel starting; 
    private JTextField balance;
    private JComboBox<AccountType> types;
    private Vector<AccountType> accounts;
    private Font font =  new Font("Calibri", 3, 33);

    public NewAccount(String user) {
        username = user;

        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());
        laccounts = new JLabel("Account Type: ");
        accounts = new Vector<AccountType>(service.getCreatableAccountType(username));
        types = new JComboBox<>(accounts);
        types.setSelectedItem(null);
        p1.add(laccounts);
        p1.add(types);

        starting = new JLabel("Starting Balance: ");
        balance = new JTextField(10);
        p2.add(starting);
        p2.add(balance);

        confirm = new JButton("Confirm");
        cancel = new JButton("Cancel");
        p3.add(confirm);
        p3.add(cancel);
        View.changeFont(p1, font);
        View.changeFont(p2, font);
        View.changeFont(p3, font);
        add(p1);
        add(p2);
        add(p3);
        addlisteners();
        super.init();
    }

    @Override
    public void addlisteners() {
        confirm.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                open();
                
            }

        });

        cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                back();
                
            }

        });

        ItemListener i = new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
            AccountType t = (AccountType) ((JComboBox<AccountType>)e.getSource()).getSelectedItem();
            if(t.equals(AccountType.SECURITY)){
                p2.setVisible(false);
            }
            }
    
            };
    
            types.addItemListener(i);
      
    }


    private void open(){
        AccountType a = (AccountType) types.getSelectedItem();
        int b = Integer.valueOf(balance.getText());
        switch (a) {
            case SAVING:
                service.openSavingAccount(username, b);
                break;
            case CHECKING:
                service.openCheckingAccount(username, b);
                break;
            case LOAN:
                service.openLoanAccount(username, b);
                break;
            case SECURITY:
                service.openSecurityAccount(username);
                break;
            default:
                break;
        }

        Option next = new Option(username);
        dispose();
    }

    private void back(){
        Option next = new Option(username);
        dispose();
    }
    
}
