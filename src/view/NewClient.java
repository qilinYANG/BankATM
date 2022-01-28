package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import service.ServiceManager;
import service.user.IUserService;
import service.user.UserService;
import view.util.Congrats;
import view.util.Invalid;
import view.util.View;

public class NewClient extends ATMWindow{
    public static final Font font = new Font("Calibri", 1, 23);
    private JLabel lusername;
    private JTextField username;
    private JLabel lpassword;
    private JPasswordField password;
    private JLabel rpassword;
    private JPasswordField password2;
    private JLabel warning;
    private JLabel lfirst;
    private JTextField first;
    private JLabel llast;
    private JTextField last;
    private JLabel lemail;
    private JTextField email;
    private JLabel ldob;
    private JTextField dob;

    private JButton sign;
    private JButton back;

    private IUserService service = ServiceManager.getUserService();

    public NewClient() {
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER,150,30));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER,150,30));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER,150,30));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER,150,30));
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER,150,30));
        JPanel p6 = new JPanel(new GridLayout(2,1));
        JPanel p7 = new JPanel(new GridLayout(2,1));
        JPanel p8 = new JPanel(new FlowLayout(FlowLayout.CENTER,150,30));
        
        lusername = new JLabel("Username: ");
        username = new JTextField(10);
        p1.add(lusername);
        p1.add(username);
        lpassword = new JLabel("Password: ");
        password = new JPasswordField(10);
        p1.add(lpassword);
        p1.add(password);
        rpassword = new JLabel("Re-Password: ");
        password2 = new JPasswordField(10);
        warning = new JLabel("Please limit to 10 digits!");
        p2.add(rpassword);
        p2.add(password2);
        p2.add(warning);
        p6.add(p1);
        p6.add(p2);
      
        lfirst = new JLabel("First Name: ");
        first = new JTextField(10);
        p3.add(lfirst);
        p3.add(first);

        llast = new JLabel("Last Name: ");
        last = new JTextField(10);
        p3.add(llast);
        p3.add(last);

        lemail = new JLabel("Email: ");
        email = new JTextField(8);
        p4.add(lemail);
        p4.add(email);

        ldob = new JLabel("Date of Birth (MM/DD/YY) :");
        dob = new JTextField(8);
        p4.add(ldob);
        p4.add(dob);
        p7.add(p3);
        p7.add(p4);

        sign = new JButton("Sign up");
        back = new JButton("Back");

        p8.add(sign);
        p8.add(back);
        
        add(p6);
        add(p7);
        add(p8);
        View.changeFont(p6,font);
        View.changeFont(p7,font);
        View.changeFont(p8,font);
        addlisteners();
        super.init();
    }



    @Override
    public void addlisteners() {
        sign.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = null;
                if(checkUsername()){
                    if(checkPassword()){
                        if(getDate() != null){
                            userID = createUser();
                            if(userID == null){
                                fail();
                            }else{
                                success();
                                }
                        };
                    };
                };
            }
        });

        back.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeClient next = new WelcomeClient();
                dispose();
                
            }


        });
        
    }

    private void fail(){
        Invalid next = new Invalid(this,"Account is not successfully created");
    }

    private void success(){
        Congrats next = new Congrats(this,"New user",username.getText());

    }

    private String createUser(){

        String username = this.username.getText();
        String password = String.valueOf(this.password.getPassword());
        String firstname = first.getText();
        String lastname = last.getText();
        String email = this.email.getText();
        Date dob = getDate();
        return service.createUser(username,password ,email, dob, firstname, lastname, 850);
        
    }


    private boolean checkUsername(){
        String username = this.username.getText();
        
        
        boolean result = service.checkUserNameAvailable(username);
        
        if(result == false){
            Invalid re = new Invalid(this,"Invalid username");
            return false;
        }
        return true;
    }

    private boolean checkPassword(){
        String pass1 = String.valueOf(password.getPassword());
        String pass2 = String.valueOf(password2.getPassword());

        if(!pass1.equals(pass2)){
            Invalid next = new Invalid(this,"Invalid password");
            return false;
        }else{
            return true;
        }
    

    }

    private Date getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/yy");
        String date = this.dob.getText();
        Date dob;
        try {
            dob = formatter.parse(date);
            return dob;
        } catch (ParseException e) {
            
            Invalid next = new Invalid(this,"Invalid date");
            return null;
        }
        }

public static void main(String[] args) {
    NewClient a = new NewClient();
}
    
}
