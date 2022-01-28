package view.Customer;

import java.util.ArrayList;
import java.util.Collection;

import entity.account.IAccount;
import entity.user.Customer;
import serverUtil.AccountType;
import service.ServiceManager;
import service.account.IAccountService;
import service.user.IUserService;
import service.user.UserService;
import view.ATMWindow;
import view.Option;
import view.util.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAccount extends ATMWindow{

    private String username;
    private Customer customer;
    private int numAccount;
    private Collection<IAccount> accounts;
   
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JPanel p6;
    private JPanel p7;

    private JButton saving;
    private JButton checking;
    private JButton loan;
    private JButton security;

    private JButton transfer;
    private JButton back;
    private Font font =  new Font("Calibri", 3, 33);
    private IAccountService service1 = ServiceManager.getAccountService();
    private IUserService service2 =  ServiceManager.getUserService();

  
    public ViewAccount(String user) {
        username = user;
        accounts = service1.getAllAccounts(username);
        numAccount = accounts.size();

        addButton();
        p3 = new JPanel(new FlowLayout());
        transfer = new JButton("Transfer");
        back = new JButton("Back");
        p3.add(transfer);
        p3.add(back);
        View.changeFont(p1, font);
        View.changeFont(p2, font);
        View.changeFont(p3, font);
        add(p1);
        add(p2);
        add(p3);
        super.init();
        addlisteners();
        
    }

    private void addButton(){
        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        
        p4 = new JPanel(new FlowLayout());
        p5 = new JPanel(new FlowLayout());
        p6 = new JPanel(new FlowLayout());
        p7 = new JPanel(new FlowLayout());

        ArrayList<IAccount> list = new ArrayList<>(accounts);
        for(IAccount a : list){
            switch (a.getAccountType()) {
                case CHECKING:
                
                    checking = new JButton(a.toString());
                    p4.add(checking);
                    checking.addActionListener(new ActionListener(){
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            viewChecking(username,a.getAccountNumber());
                        }

                    });
                    break;
                case SAVING:
               
                    saving = new JButton(a.toString());
                    p5.add(saving);
                    saving.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            viewSaving(username,a.getAccountNumber());
                    }

                    });
                    break;
                case LOAN:
                
                    loan = new JButton(a.toString());
                    p6.add(loan);
                    loan.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                           viewLoan(username,a.getAccountNumber());
                            
                        }

                    });
                    break;
                case SECURITY:
                    security = new JButton(a.toString());
                    
                    p7.add(security);
                    security.addActionListener(new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e) {
                           viewSecurity(username,a.getAccountNumber());
                            
                        }

                    });
                    break;

                default:
                    break;
            }
        }
        p1.add(p4);
        p1.add(p5);
        p2.add(p6);
        p2.add(p7);

    }

    @Override
    public void addlisteners() {
        
        transfer.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                transfer();
                
            }
            
        });

        back.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
            
        });


    }

    private void transfer(){
        Transfer next = new Transfer(username);
        this.dispose();
    }

    private void back(){
        Option next = new Option(username);
        this.dispose();
    }

    private void viewChecking(String username, String accountnum){
        Checking next = new Checking(username, accountnum);
        this.dispose();
    }

    private void viewLoan(String username,String accountnum){
        Loan next = new Loan(username,accountnum);
        this.dispose();
    }
    private void viewSecurity(String username,String accountnum){
        Security next = new Security(username,accountnum);
        this.dispose();
    }

    private void viewSaving(String username,String accountnum){
        Saving next = new Saving(username,accountnum);
        this.dispose();
    }
    public static void main(String[] args) {
        ViewAccount next = new ViewAccount("mababa");
    }
}
