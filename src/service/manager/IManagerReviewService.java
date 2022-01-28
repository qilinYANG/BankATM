package service.manager;


import java.util.Collection;
import entity.Stock;
import entity.Fee;
import entity.Interest;
import entity.transaction.ITransaction;
import entity.user.Customer;
import service.IService;
public interface IManagerReviewService extends IService 
{
    public boolean                  changeStockPrice(Stock stock, double price);
    public Interest                 getRate();
    public boolean                  changeRate(double rate);
    public Fee                      getFee();
    public boolean                  changeFee(double value);
    
    public String                   getManagerAccount();
    public Collection<ITransaction> checkTransaction(int cnt);

    public Collection<Customer>     checkDebtor();
    public boolean                  chargeFee(String accountNumber);

}
