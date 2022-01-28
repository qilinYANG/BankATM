package view.Manager;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.Stock;
import service.ServiceManager;
import service.account.IAccountService;
import service.manager.IManagerReviewService;
import service.transaction.ITransactionService;
import view.util.AddListener;
import view.util.Invalid;
import view.util.View;

public class SetStock extends JDialog implements AddListener{
    private JLabel message1;
    private JLabel message2;

    private JComboBox<Stock> stocks;
    private JTextField amount;
    private JButton confirm;
    private JButton cancel;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;

    private Window owner;


    private IManagerReviewService service = ServiceManager.getManagerReviewService();
    private IAccountService service1 = ServiceManager.getAccountService();
    private ITransactionService service2 = ServiceManager.getTransactionService();

    public SetStock(Window owner) {

        super(owner);

        this.owner = owner;
        setLayout(new GridLayout(3,1));
        setSize(800, 800);
        setVisible(true);
        setModal(true);
        setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );

        p1 = new JPanel(new FlowLayout());
        p2 = new JPanel(new FlowLayout());
        p3 = new JPanel(new FlowLayout());

        message1 = new JLabel("Select Stock ");
        message2 = new JLabel("New price ($): ");
        stocks = new JComboBox<>(new Vector<Stock>(service2.getMarketStocks()));
        amount = new JTextField(8);
        confirm = new JButton("Confirm");
        cancel = new JButton("Cancel");

        p1.add(message1);
        p1.add(stocks);
        p2.add(message2);
        p2.add(amount);
        p3.add(confirm);
        p3.add(cancel);
        View.changeFont(p1);
        View.changeFont(p2);
        View.changeFont(p3);
     
        add(p1);
        add(p2);
        add(p3);

        addlisteners();
        
    }

    @Override
    public void addlisteners() {
        confirm.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                
            }
            
        });
        
        cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                close();
                
            }

        });
    }

    private void update(){
        Stock pick = (Stock) stocks.getSelectedItem();
        int price = Integer.valueOf(amount.getText());
        if(service.changeStockPrice(pick, price)){
            this.dispose();
            
        }else{
            Invalid next = new Invalid(owner, "Operate failed");
            this.dispose();
        }
    }

    private void close(){
        this.dispose();
    }
}
