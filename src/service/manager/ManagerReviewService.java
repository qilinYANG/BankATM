package service.manager;

import entity.Balance;
import entity.Fee;
import entity.Interest;
import entity.Stock;
import entity.transaction.ITransaction;
import entity.user.Customer;
import service.ServiceManager;
import service.transaction.TransactionService;

import java.util.ArrayList;
import java.util.Collection;

public class ManagerReviewService implements IManagerReviewService{

    private static ManagerReviewService managerReviewService = null;
    
    private ManagerReviewService(){}
    
    public static synchronized ManagerReviewService getInstance()
    {
        if(managerReviewService == null)
        {
            managerReviewService = new ManagerReviewService();
        }
        return managerReviewService;
    }
    
    @Override
    public String getManagerAccount()
    {
        return ServiceManager.getDatabaseService().getManagerAccount();
    }
    
    @Override
    public Interest getRate()
    {
        return ServiceManager.getDatabaseService().getInterestRate();
    }
    
    @Override
    public Fee getFee()
    {
        return ServiceManager.getDatabaseService().getFee("general");
    }
    
    @Override
    public boolean changeRate(double rate) {
        return ServiceManager.getDatabaseService().updateInterestRate(rate);
    }

    @Override
    public Collection<ITransaction> checkTransaction(int cnt) 
    {
        return ServiceManager.getTransactionService().getTransactionHistory(cnt);
    }
    
    @Override
    public boolean changeStockPrice(Stock stock, double price)
    {
        return ServiceManager.getDatabaseService().updateStockPrice(stock, price);
    }

    @Override
    public boolean chargeFee(String accountNumber)
    {
        return TransactionService.getInstance().transfer(accountNumber, this.getManagerAccount(), new Balance(getFee().getValue()));
    }
    
    @Override
    public boolean changeFee(double value)
    {
        return ServiceManager.getDatabaseService().updateFee(value);        
    }

    @Override
    public Collection<Customer> checkDebtor() {
        Collection<Customer> res = new ArrayList<Customer>();
        for(Customer cust : ServiceManager.getUserService().getAllUsers())
        {
           if(cust.getAccountCollection().containsLoanAccount())
           {
               res.add(cust);
           }
        }
        return res;
    }
    


    @Override
    public boolean isServiceAvailable()
    {
        return true;
    }
}
