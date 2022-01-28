package view;

import javax.swing.*;

import service.ServiceManager;
import service.user.IUserService;
import view.Manager.Manager;
import view.util.Invalid;
import view.util.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExistClient extends ATMWindow {

    private JLabel lusername;
    private JTextField username;
    private JLabel lpassword;
    private JPasswordField password;
    private JButton ok;
    private JButton back;
    private IUserService service = ServiceManager.getUserService();
    public static final Font font = new Font("Calibri", 1, 33);
    
    public ExistClient() {
        lusername = new JLabel("Username: ");
        username = new JTextField(10);
        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(lusername);
        p1.add(username);
        lpassword = new JLabel("Password: ");
        password = new JPasswordField(10);
        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(lpassword);
        p2.add(password);

        ok = new JButton("OK");
        back = new JButton("Back");
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 200, 30));
        p3.add(ok);
        p3.add(back);

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
        ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(service.checkIsAdmin(username.getText())){
                    if(service.logIn(username.getText(), String.valueOf(password.getPassword()))){
                        managerlogin();
                    }else{
                        fail();
                    }
                }else{
                    if(service.logIn(username.getText(), String.valueOf(password.getPassword()))){
                        success();
                    }else{
                        fail();
                    }
                }

                
                
            }

            
        });

        back.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
            }

        });
        
    }

    private void close(){
        WelcomeClient next = new WelcomeClient();
        dispose();

    }

    private void success(){
        Option next = new Option(username.getText());
        dispose();

    }

    private void managerlogin(){
        Manager next = new Manager();
        this.dispose();
    }

    private void fail(){
        Invalid next = new Invalid(this, "Invalid password and/or username");
    }
    
    public static void main(String[] args) {
        ExistClient Client = new ExistClient();

    }
    
}
