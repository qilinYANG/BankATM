package view.util;

import javax.swing.*;

import entity.Balance;
import entity.transaction.ITransaction;
import service.ServiceManager;
import service.account.IAccountService;
import service.transaction.ITransactionService;
import service.user.IUserService;
import view.Customer.ViewAccount;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Currency;
import java.util.Vector;

public class History extends JDialog implements AddListener{
    private JList<String> details = new JList<>();
    private DefaultListModel<String> model = new DefaultListModel<>();

    private JScrollPane pane;
    private JButton back;
    private JPanel p1;
    private JPanel p2;

    private Window owner;
    private String accountnum;
    private String username;
    private ITransactionService service = ServiceManager.getTransactionService();
    private ITransactionService service1 = ServiceManager.getTransactionService();

    public History(Window owner, String user, String account) {

        super(owner);
        this.accountnum = account;
        username = user;
        this.owner = owner;
        setLayout(new GridLayout(2,1));
        setSize(800, 800);
        setVisible(true);
        setModal(true);
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        addDetails();
        pane = new JScrollPane(details);
        p1.add(pane);

        back = new JButton("Back");
        add(p1);
        add(p2);
      
        View.changeFont(p1);
        View.changeFont(p2);
        addlisteners();
    }

    private void addDetails() {
        Collection<ITransaction> list = service.getTransactionHistory(accountnum);

        for (ITransaction t : list) {
            model.addElement(t.toString());
        }

        details.setModel(model);
    }

    @Override
    public void addlisteners() {
        back.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                back();
                
            }
            
        });
        
    }

    private void back(){
        this.dispose();
    }

}
