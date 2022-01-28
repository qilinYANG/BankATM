package view.Customer;

import javax.swing.*;

import entity.Balance;
import entity.PurchasedStock;
import entity.Stock;
import entity.user.Customer;
import service.ServiceManager;
import service.account.IAccountService;
import service.user.IUserService;
import service.user.UserService;
import view.ATMWindow;
import view.util.CloseA;
import view.util.PurchaseStock;
import view.util.SellStock;
import view.util.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;

public class Security extends ATMWindow {
    private JLabel title1;
    private JLabel title2;
    private JList<String> details = new JList<>();
    private DefaultListModel<String> model = new DefaultListModel<>();

    private JScrollPane pane;
    private JButton purchase;
    private JButton sell;
    private JButton back;
    private JButton close;
    private String username;
    private String accountnum;
    private IUserService service1 = ServiceManager.getUserService();
    private IAccountService service2 = ServiceManager.getAccountService();

    public Security(String user, String account) {
        accountnum = account;
        username = user;
        JPanel p1 = new JPanel(new FlowLayout());
        JPanel p2 = new JPanel(new FlowLayout());
        JPanel p3 = new JPanel(new FlowLayout());

        String t1 = service2.getAccount(accountnum).toString();
        String t2 = " Profolio " + service2.getProtfolio(accountnum).toString();
        title1 = new JLabel(t1);
        title2 = new JLabel(t2);
        p1.add(title1);
        p1.add(title2);

        addDetails();
        pane = new JScrollPane(details);
        p2.add(pane);

        purchase = new JButton("Purchase");
        sell = new JButton("Sell");
        back = new JButton("Back");
        close = new JButton("Close Account");
        p3.add(purchase);
        p3.add(sell);
        p3.add(close);
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

    private void addDetails() {
        Collection<PriorityQueue<PurchasedStock>> stocks = service2.getPurchasedSotcks(accountnum);
        for (PriorityQueue<PurchasedStock> pq : stocks) {
            int num = 0;
            String name = "";
            ArrayList<PurchasedStock> timeBaseSortedStocks = new ArrayList<PurchasedStock>();
            int pqsize = pq.size();

            for (int i = 0; i < pqsize; i++) {
                PurchasedStock s = pq.poll();
                name = s.getStock().getName();
                num += s.getPurchaseCnt();
                timeBaseSortedStocks.add(s);
            }
            
            name += " Holding: " + num;   
            model.addElement(name);
        }

        details.setModel(model);
    }

    @Override
    public void addlisteners() {
        purchase.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                purchase();
            }

        });

        sell.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                sell();
                
            }

        });

        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }

        });
        back.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                back();

            }

        });

    }

    private void purchase() {  
        PurchaseStock next = new PurchaseStock(this, username, accountnum);

    }

    private void sell(){
        SellStock next = new SellStock(this, username, accountnum);
    }


    private void back() {
        ViewAccount next = new ViewAccount(username);
        dispose();
    }

    private void close() {
        CloseA next = new CloseA(this, username, accountnum);

    }

}
